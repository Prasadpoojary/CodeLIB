package net.ebookPrasad.service;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.ebookPrasad.dau.BookRepository;
import net.ebookPrasad.dau.CourseRepository;
import net.ebookPrasad.dau.UserRepository;
import net.ebookPrasad.model.Book;
import net.ebookPrasad.model.Course;
import net.ebookPrasad.model.User;

@Service
public class PrasadService {
	


	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	
	String[] CourseCatList= {"Python","Java","Javascript","Machine Learning","Artificial Intelligency","Ethical Hacking",
			"C","C++","Ruby","Flutter","Kotlin","Android Development","PHP","Django","Spring Boot","Linux",
			"Operating System","Angular","React","Vue","Node","Swift","SQL","Database","MongoDB","Networking","HTML",
			"CSS","Web development","Dart","Sass","ASP.net","AWS","IoT","UI/UX","deno","jQuery","Others"};
	
	public boolean hasString(String element,String[] courseCatList2) 
	{
		for(String str : courseCatList2)
		{
			if(element.equals(str))
			{
				return true;
			}
		}
		return false;	
	}

	public String getDate()
	{
		DateFormat date=DateFormat.getDateInstance();
		Calendar cal=Calendar.getInstance();
		String formatedDate=date.format(cal.getTime());
		return formatedDate;
	}
	
	public String capitalize(String line)
	{
		String result="";
		
		String[] strlist=line.split(" ");
		for(String word:strlist)
		{
			String res="";
			char rech=word.toUpperCase().charAt(0);
			res=word.substring(1,word.length());
			result+=rech+res;
			result+=" ";
		}

		return result;
	}
	
	
	public Iterable<String> getAllCategory()
	{
		ArrayList<String> resultIterable = new ArrayList<String>();
		Iterable<Book> booksIterable=bookRepository.findAll();
		for(Book book : booksIterable)
		{
			resultIterable.add(book.getCategory());
		}
		return resultIterable;
	}
	
	public Iterable<String> getAllCourseCategory()
	{
		ArrayList<String> resultIterable = new ArrayList<String>();
		Iterable<Course> coursesIterable=courseRepository.findAll();
		for(Course course : coursesIterable)
		{
			resultIterable.add(course.getCategory());
		}
		return resultIterable;
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	public String register(User user) {
		User fUser=userRepository.findByUsername(user.getUsername());
		if(fUser != null)
		{
			return "redirect:/register?exists";
		}
		else 
		{
			userRepository.save(user);
			return "redirect:/login";
		}
	}
	
	

	public ModelAndView home(Authentication auth) 
	{
		ModelAndView mv = new ModelAndView("home");
		if(auth != null)
		{
			Boolean user=auth.isAuthenticated();
			Iterable<Book> books=bookRepository.findAll();
			Iterable<String> categories=getAllCategory();
			ArrayList<String> checkecategory =new ArrayList<String>();
			for(String category : categories)
			{
				if(!checkecategory.contains(category))
				{
					checkecategory.add(category);
				}
			}
			
			mv.addObject("books", books);
			mv.addObject("categories", checkecategory);
			mv.addObject("isAuthenticated", user);
			return mv;
			
		}
		else 
		{
			Boolean user=false;
			Iterable<Book> books=bookRepository.findAll();
			Iterable<String> categories=getAllCategory();
			ArrayList<String> checkecategory =new ArrayList<String>();
			for(String category : categories)
			{
				if(!checkecategory.contains(category))
				{
					checkecategory.add(category);
				}
			}
		
			mv.addObject("books", books);
			mv.addObject("categories", checkecategory);
			mv.addObject("isAuthenticated", user);
			return mv;
		}
		
	}
	
	

	public ModelAndView courses(Authentication auth) 
	{
		ModelAndView mv = new ModelAndView("course");
		if(auth != null)
		{
			Boolean user=auth.isAuthenticated();
			Iterable<Course> courses=courseRepository.findAll();
			Iterable<String> categories=getAllCourseCategory();
			ArrayList<String> checkecategory =new ArrayList<String>();
			for(String category : categories)
			{
				if(!checkecategory.contains(category))
				{
					checkecategory.add(category);
				}
			}
			mv.addObject("courses", courses);
			mv.addObject("categories", checkecategory);
			mv.addObject("isAuthenticated", user);
			return mv;
		}
		else 
		{
			Boolean user=false;
			Iterable<Course> courses=courseRepository.findAll();
			Iterable<String> categories=getAllCourseCategory();
			ArrayList<String> checkecategory =new ArrayList<String>();
			for(String category : categories)
			{
				if(!checkecategory.contains(category))
				{
					checkecategory.add(category);
				}
			}
			mv.addObject("courses", courses);
			mv.addObject("categories", checkecategory);
			mv.addObject("isAuthenticated", user);
			return mv;
		}
		
	}



	public ModelAndView upload(Authentication auth, String done, String error, String deleted, String cheating) {
		ModelAndView mv=new ModelAndView("upload");
		String username=auth.getName();
		Long uid=userRepository.findByUsername(username).getId();
		Iterable<Book> uploadBooks=bookRepository.findAllByUid(uid);
		Iterable<Course> uploadCourses=courseRepository.findAllByUid(uid);
		mv.addObject("uploadBooks", uploadBooks);
		mv.addObject("category", CourseCatList);
		mv.addObject("uploadCourses", uploadCourses);
		if(done!=null)
		{
			mv.addObject("msg", "Thank You for your Upload.");
		}
		else if(error!=null)
		{
			mv.addObject("msg", "Please Select a Valid Category");
		}
		else if(deleted!=null)
		{
			mv.addObject("msg", "Deleted Succesfully.");
		}
		else if(cheating!=null)
		{
			mv.addObject("msg", "Don't Act Too smart");
		}
		else 
		{
			mv.addObject("msg","Please Upload only Free Resources");
		}
		return mv;
	}

	public String uploadCourse(String title, String source, String url,String category, Authentication auth) {
		
		String username=auth.getName();
		Long uid=userRepository.findByUsername(username).getId();
		String name=userRepository.findByUsername(username).getName();
		Course course=new Course();
		if(title.length()>36)
		{
			title=title.substring(0,35);
			title=title+"...";
		}
		
		if(source.length()>15)
		{
			source=source.substring(0,14);
			source=source+"...";
		}
		if(hasString(category, CourseCatList))
		{
			course.setUid(uid); 
			course.setTitle(capitalize(title));
			course.setSource(capitalize(source));
			course.setUploader(capitalize(name));
			course.setCategory(category);
			course.setUrl(url);
			course.setDate(getDate());
			courseRepository.save(course);
			return "redirect:/upload?done";
		}
		else 
		{
			return "redirect:/upload?error";
		}
		
	}

	public String uploadBook(String title, String auther, String category, Authentication auth, MultipartFile file) throws IOException {
		String username=auth.getName();
		Long uid=userRepository.findByUsername(username).getId();
		String name=userRepository.findByUsername(username).getName();
		Book book=new Book();
		if(title.length()>29)
		{
			title=title.substring(0,28);
			title=title+"...";
		}
		
		if(auther.length()>20)
		{
			auther=auther.substring(0,19);
			auther=auther+"...";
		}
		if(hasString(category, CourseCatList))
		{
			book.setUid(uid); 
			book.setTitle(capitalize(title));
			book.setAuther(capitalize(auther));
			book.setUploader(capitalize(name));
			book.setCategory(category);
			book.setDate(getDate());
			book.setFilename(file.getOriginalFilename());
			book.setFiletype(file.getContentType());
			book.setFileData(file.getBytes());
			bookRepository.save(book);
			return "redirect:/upload?done";
		}
		else 
		{
			return "redirect:/upload?error";
		}
	}
	
	public Optional<Book> getfile(Long id)
	{
		return bookRepository.findById(id);
	}
	
	public ResponseEntity<ByteArrayResource> download(Long id) 
	{
		Book book=getfile(id).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(book.getFiletype()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename-\""+book.getFilename()+"\"")
				.body(new ByteArrayResource(book.getFileData()));
	}

	public String delete(String type, Long id, Authentication auth) {
		if(type.equals("book"))
		{
			String username=auth.getName();
			Long uid=userRepository.findByUsername(username).getId();
			Iterable<Book> books=bookRepository.findAllByUid(uid);
			if(hasBook(id,books))
			{
				// some extra code to delete file from AWS
				bookRepository.deleteById(id);
				return "redirect:/upload?deleted";
			}
			else 
			{
				return "redirect:/upload?cheating";
			}
	    }
		if(type.equals("course"))
		{
			String username=auth.getName();
			Long uid=userRepository.findByUsername(username).getId();
			Iterable<Course> courses=courseRepository.findAllByUid(uid);
			if(hasCourse(id,courses))
			{
				courseRepository.deleteById(id);
				return "redirect:/upload?deleted";
			}
			else 
			{
				return "redirect:/upload?cheating";
			}
	    }
		
			
		return null;
	}

	
	private boolean hasBook(Long id, Iterable<Book> books) 
	{
		for(Book book : books)
		{
			if(book.getId()==id)
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean hasCourse(Long id, Iterable<Course> courses)
	{
		for(Course course : courses)
		{
			if(course.getId()==id)
			{
				return true;
			}
		}
		return false;
	}

	public ModelAndView searchBYCategory(String type, String category,Authentication auth) 
	{
		category=category.replace("%20", " ");
		
		if(type.equals("book"))
		{
					
				Iterable<Book> books=bookRepository.findByCategory(category);
				ModelAndView mv = new ModelAndView("home");
				if(auth != null)
				{
					Boolean user=auth.isAuthenticated();
					Iterable<String> categories=getAllCategory();
					ArrayList<String> checkecategory =new ArrayList<String>();
					for(String cat: categories)
					{
						if(!checkecategory.contains(cat))
						{
							checkecategory.add(cat);
						}
					}
					if(checkecategory.contains(category))
					{
						mv.addObject("books", books);
						mv.addObject("categories", checkecategory);
						mv.addObject("isAuthenticated", user);
						return mv;
					}
					else 
					{
						books=bookRepository.findAll();
						mv.addObject("books", books);
						mv.addObject("categories", checkecategory);
						mv.addObject("isAuthenticated", user);
						mv.addObject("msg","Please select a valid category");
						return mv;
					}
				}
				else 
				{
					Boolean user=false;
					Iterable<String> categories=getAllCategory();
					ArrayList<String> checkecategory =new ArrayList<String>();
					for(String cat: categories)
					{
						if(!checkecategory.contains(cat))
						{
							checkecategory.add(cat);
						}
					}
					if(checkecategory.contains(category))
					{
						mv.addObject("books", books);
						mv.addObject("categories", checkecategory);
						mv.addObject("isAuthenticated", user);
						return mv;
					}
					else 
					{
						books=bookRepository.findAll();
						mv.addObject("books", books);
						mv.addObject("categories", checkecategory);
						mv.addObject("isAuthenticated", user);
						mv.addObject("msg","Please select a valid category");
						return mv;
					}
				}
		}
		if(type.equals("course"))
		{
				Iterable<Course> course=courseRepository.findByCategory(category);
				ModelAndView mv = new ModelAndView("course");
				if(auth != null)
				{
					Boolean user=auth.isAuthenticated();
					Iterable<String> categories=getAllCourseCategory();
					ArrayList<String> checkecategory =new ArrayList<String>();
					for(String cat: categories)
					{
						if(!checkecategory.contains(cat))
						{
							checkecategory.add(cat);
						}
					}
					if(checkecategory.contains(category))
					{
						mv.addObject("courses", course);
						mv.addObject("categories", checkecategory);
						mv.addObject("isAuthenticated", user);
						return mv;
					}
					else 
					{
						course=courseRepository.findAll();
						mv.addObject("courses", course);
						mv.addObject("categories", checkecategory);
						mv.addObject("isAuthenticated", user);
						mv.addObject("msg","Please select a valid category");
						return mv;
					}
				}
				else 
				{
					Boolean user=false;
					Iterable<String> categories= getAllCourseCategory();
					ArrayList<String> checkecategory =new ArrayList<String>();
					for(String cat: categories)
					{
						if(!checkecategory.contains(cat))
						{
							checkecategory.add(cat);
						}
					}
					if(checkecategory.contains(category))
					{
						mv.addObject("courses", course);
						mv.addObject("categories", checkecategory);
						mv.addObject("isAuthenticated", user);
						return mv;
					}
					else 
					{
						course=courseRepository.findAll();
						mv.addObject("courses", course);
						mv.addObject("categories", checkecategory);
						mv.addObject("isAuthenticated", user);
						mv.addObject("msg","Please select a valid category");
						return mv;
					}
						
				}
		}
		
		return null;
		
		
		
	}
	
	

	public ModelAndView searchBYKeyword(String type, String keyword, Authentication auth) 
	{
	String key=keyword.replace("%20", " ");
	String[] keywordList=key.split(" ");
	
		if(type.equals("book"))
		{
				ArrayList<Long> result=new ArrayList<Long>();
				for(String word : keywordList)
				{
					if(word.toLowerCase().contains("for") || word.toLowerCase().equals("the")|| word.toLowerCase().equals("a")|| word.length()<=2)
					{
						continue;
					}
					Iterable<Book> books=bookRepository.findAll();
					for(Book book : books)
					{
						if(book.getCategory().toLowerCase().contains(word.toLowerCase()))
						{
							if(!result.contains(book.getId()))
							{
								result.add(book.getId());
							}
						}
						
						if(book.getTitle().toLowerCase().contains(word.toLowerCase()))
						{
							if(!result.contains(book.getId()))
							{
								result.add(book.getId());
							}
						}
						
						if(book.getAuther().toLowerCase().contains(word.toLowerCase()))
						{
							if(!result.contains(book.getId()))
							{
								result.add(book.getId());
							}
						}
						
						if(book.getUploader().toLowerCase().contains(word.toLowerCase()))
						{
							if(!result.contains(book.getId()))
							{
								result.add(book.getId());
							}
						}
					}
				}
				Iterable<Book> books=bookRepository.findAllById(result);
				ModelAndView mv = new ModelAndView("home");
				if(auth != null)
				{
					Boolean user=auth.isAuthenticated();
					Iterable<String> categories= getAllCategory();
					ArrayList<String> checkecategory =new ArrayList<String>();
					for(String cat: categories)
					{
						if(!checkecategory.contains(cat))
						{
							checkecategory.add(cat);
						}
					}
					if(!result.isEmpty())
					{
						mv.addObject("books", books);
						mv.addObject("categories", checkecategory);
						mv.addObject("isAuthenticated", user);
						return mv;
					}
					else 
					{
						books=bookRepository.findAll();
						mv.addObject("books", books);
						mv.addObject("categories", checkecategory);
						mv.addObject("isAuthenticated", user);
						mv.addObject("msg","No result found");
						return mv;
					}
					
				}
				else 
				{
					Boolean user=false;
					Iterable<String> categories= getAllCategory();
					ArrayList<String> checkecategory =new ArrayList<String>();
					for(String cat: categories)
					{
						if(!checkecategory.contains(cat))
						{
							checkecategory.add(cat);
						}
					}
					if(!result.isEmpty())
					{
						mv.addObject("books", books);
						mv.addObject("categories", checkecategory);
						mv.addObject("isAuthenticated", user);
						return mv;
					}
					else 
					{
						books=bookRepository.findAll();
						mv.addObject("books", books);
						mv.addObject("categories", checkecategory);
						mv.addObject("isAuthenticated", user);
						mv.addObject("msg","No results found");
						return mv;
					}
				}
		}
		
		
		if(type.equals("course"))
		{
				
				ArrayList<Long> result=new ArrayList<Long>();
				for(String word : keywordList)
				{
					if(word.toLowerCase().contains("for") || word.toLowerCase().equals("the")|| word.toLowerCase().equals("a")|| word.length()<=2)
					{
						continue;
					}
					Iterable<Course> courses=courseRepository.findAll();
					for(Course course : courses)
					{
						if(course.getCategory().toLowerCase().contains(word.toLowerCase()))
						{
							if(!result.contains(course.getId()))
							{
								result.add(course.getId());
							}
						}
						
						if(course.getTitle().toLowerCase().contains(word.toLowerCase()))
						{
							if(!result.contains(course.getId()))
							{
								result.add(course.getId());
							}
						}
						
						if(course.getSource().toLowerCase().contains(word.toLowerCase()))
						{
							if(!result.contains(course.getId()))
							{
								result.add(course.getId());
							}
						}
						
						if(course.getUploader().toLowerCase().contains(word.toLowerCase()))
						{
							if(!result.contains(course.getId()))
							{
								result.add(course.getId());
							}
						}
					}
				}
				Iterable<Course> courses=courseRepository.findAllById(result);
				ModelAndView mv = new ModelAndView("course");
				if(auth != null)
				{
					Boolean user=auth.isAuthenticated();
					Iterable<String> categories= getAllCourseCategory();
					ArrayList<String> checkecategory =new ArrayList<String>();
					for(String cat: categories)
					{
						if(!checkecategory.contains(cat))
						{
							checkecategory.add(cat);
						}
					}
					if(!result.isEmpty())
					{
						mv.addObject("courses", courses);
						mv.addObject("categories", checkecategory);
						mv.addObject("isAuthenticated", user);
						return mv;
					}
					else 
					{
						courses=courseRepository.findAll();
						mv.addObject("courses", courses);
						mv.addObject("categories", checkecategory);
						mv.addObject("isAuthenticated", user);
						mv.addObject("msg","No results found");
						return mv;
					}
					
				}
				else 
				{
					Boolean user=false;
					Iterable<String> categories=getAllCourseCategory();
					ArrayList<String> checkecategory =new ArrayList<String>();
					for(String cat: categories)
					{
						if(!checkecategory.contains(cat))
						{
							checkecategory.add(cat);
						}
					}
					if(!result.isEmpty())
					{
						mv.addObject("courses", courses);
						mv.addObject("categories", checkecategory);
						mv.addObject("isAuthenticated", user);
						return mv;
					}
					else 
					{
						courses=courseRepository.findAll();
						mv.addObject("courses", courses);
						mv.addObject("categories", checkecategory);
						mv.addObject("isAuthenticated", user);
						mv.addObject("msg","No results found");
						return mv;
					}
					
				}
		}
			
		return null;
	}

	public ModelAndView getpassword(String email) throws Exception
	{
		User user=userRepository.findByUsername(email);
		if(user != null)
		{
			ModelAndView mv = new ModelAndView("forgot");
			
			sendmail(email,user);			
			 
			mv.addObject("error","Password sent to your E-mail");
			return mv;
		}
		else 
		{
			ModelAndView mv = new ModelAndView("forgot");
			mv.addObject("error", "No such E-mail exists");
			return mv;
		}
	}

	private void sendmail(String email, User user) throws Exception
	{
		
		SendMail(email,user);
		
	}
	
	private void SendMail(String recepient, User user) throws MessagingException 
	{
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		
		String musername="codelibgcs@gmail.com";
		String mpassword="Prasad815@";
		
		Session session = Session.getInstance(properties,new Authenticator()
				{
					@Override 
					protected PasswordAuthentication getPasswordAuthentication() 
					{
						return new PasswordAuthentication(musername, mpassword);
					}
				});
		
		Message message = prepareMessage(session,musername,recepient,user);
		
		Transport.send(message);

	}

	private Message prepareMessage(Session session, String musername, String recepient, User user) 
	{
		try 
		{
			Message message= new MimeMessage(session);
			message.setFrom(new InternetAddress(musername));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Password From CodeLIB");
			String htmlCode="<p>Your Password is <b>"+user.getfPass()+"</b></p><br/><h3>-Thank You</h3>";
			message.setContent(htmlCode, "text/html");
			return message;
		} 
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	
	}

	public ModelAndView viewbook(Long id,Authentication auth) 
	{
		ModelAndView mv = new ModelAndView("home");
		if(auth != null)
		{
			Boolean user=auth.isAuthenticated();
			Iterable<Book> books=bookRepository.findAllById(id);
			mv.addObject("books", books);
			mv.addObject("isAuthenticated", user);
			return mv;
		}
		else 
		{
			Boolean user=false;
			Iterable<Book> books=bookRepository.findAllById(id);
			mv.addObject("books", books);
			mv.addObject("isAuthenticated", user);
			return mv;
		}
		
	}

	public ModelAndView viewCourse(Long id, Authentication auth) 
	{
		ModelAndView mv = new ModelAndView("course");
		if(auth != null)
		{
			Boolean user=auth.isAuthenticated();
			Iterable<Course> courses=courseRepository.findAllById(id);
			mv.addObject("courses", courses);
			mv.addObject("isAuthenticated", user);
			return mv;
		}
		else 
		{
			Boolean user=false;
			Iterable<Course> courses=courseRepository.findAllById(id);
			mv.addObject("courses", courses);
			mv.addObject("isAuthenticated", user);
			return mv;
		}
	}

	
	
	
//											Admin Page
	
	public boolean authen=false;
	
	public ModelAndView admin() {
		authen=false;
		ModelAndView mv=new ModelAndView("adminAuth");
		return mv;
	}

	
	public ModelAndView adminDashboard(String password) 
	{
		authen=false;
		User admin=userRepository.findByUsername("prasadpoojary815@gmail.com");
		if(password.equals(admin.getfPass()))
		{
			ModelAndView mv=new ModelAndView("admin");
			authen=true;
			mv.addObject("isAuthenticated",true);
			return mv;
		}
		else 
		{
			ModelAndView mv=new ModelAndView("adminAuth");
			return mv; 
		}
	}

	public ModelAndView dashboardNextStep(String type)
	{
		if(authen)
		{
			if(type.equals("users"))
			{
				Iterable<User> users=userRepository.findAll();
				ModelAndView mv=new ModelAndView("data");
				mv.addObject("table", "Users");
				mv.addObject("objects", users);
				mv.addObject("isUser", true);
				return mv;
			}
			else if(type.equals("books"))
			{
				Iterable<Book> books=bookRepository.findAll();
				ModelAndView mv=new ModelAndView("data");
				mv.addObject("table", "books");
				mv.addObject("objects", books);
				mv.addObject("isUser", false);
				return mv;
			}
			else if(type.equals("courses"))
			{
				Iterable<Course> courses=courseRepository.findAll();
				ModelAndView mv=new ModelAndView("data");
				mv.addObject("table", "courses");
				mv.addObject("objects", courses);
				mv.addObject("isUser", false);
				return mv;
			}
			else 
			{
				return null;
			}
		}
		else 
		{
			ModelAndView mv=new ModelAndView("adminAuth");
			return mv;
		}

	}

	public ModelAndView dashboardEditData(String type, Long id) 
	{
		if(authen)
		{
			if(type.equals("users"))
			{
				Iterable<User> user=userRepository.findAllById(id);
				ModelAndView mv =new ModelAndView("adminEdit");
				mv.addObject("object", user);
				mv.addObject("isUser",true);
				return mv;
			}
			else if(type.equals("books"))
			{
				Iterable<Book> book=bookRepository.findAllById(id);
				ModelAndView mv =new ModelAndView("adminEdit");
				mv.addObject("object", book);
				mv.addObject("isBook",true);
				return mv;
			}
			else if(type.equals("courses"))
			{
				Iterable<Course> course=courseRepository.findAllById(id);
				ModelAndView mv =new ModelAndView("adminEdit");
				mv.addObject("object", course);
				mv.addObject("isCourse",true);
				return mv;
			}
			else 
			{
				return null;
			}
		}
		else 
		{
			ModelAndView mv=new ModelAndView("adminAuth");
			return mv;
		}

	}

	

	public ModelAndView deleteRecord(String type, Long id)
	{
		if(authen)
		{
			if(type.equals("users"))
			{
				userRepository.deleteById(id);
				Iterable<User> users=userRepository.findAll();
				ModelAndView mv=new ModelAndView("data");
				mv.addObject("table", "Users");
				mv.addObject("objects", users);
				mv.addObject("isUser", true);
				return mv;
			}
			else if(type.equals("books"))
			{
				bookRepository.deleteById(id);
				Iterable<Book> books=bookRepository.findAll();
				ModelAndView mv=new ModelAndView("data");
				mv.addObject("table", "books");
				mv.addObject("objects", books);
				mv.addObject("isUser", false);
				return mv;
			}
			else if(type.equals("courses"))
			{
				courseRepository.deleteById(id);
				Iterable<Course> courses=courseRepository.findAll();
				ModelAndView mv=new ModelAndView("data");
				mv.addObject("table", "courses");
				mv.addObject("objects", courses);
				mv.addObject("isUser", false);
				return mv;
			}
			else 
			{
				return null;
			}
		}
		else 
		{
			ModelAndView mv=new ModelAndView("adminAuth");
			return mv;
		}
		
	}



	public ModelAndView editBook(Long id, String title, String auther, Long uid, String fileName, String cat,
			String date)
	{
		Book book=bookRepository.getOne(id);
		book.setTitle(title);
		book.setAuther(auther);
		book.setUid(uid);
		book.setFilename(fileName);
		book.setCategory(cat);
		book.setDate(date);
		bookRepository.save(book);
		Iterable<Book> books=bookRepository.findAll();
		ModelAndView mv=new ModelAndView("data");
		mv.addObject("table", "books");
		mv.addObject("objects", books);
		mv.addObject("isUser", false);
		return mv;
	}

	public ModelAndView editCourse(Long id, String title, String source, Long uid, String url, String cat,
			String date) 
	{
		Course course=courseRepository.getOne(id);
		course.setTitle(title);
		course.setSource(source);
		course.setUid(uid);
		course.setUrl(url);	
		course.setCategory(cat);
		course.setDate(date);
		courseRepository.save(course);
		Iterable<Course> courses=courseRepository.findAll();
		ModelAndView mv=new ModelAndView("data");
		mv.addObject("table", "courses");
		mv.addObject("objects", courses);
		mv.addObject("isUser", false);
		return mv;
	}


	
	
}
