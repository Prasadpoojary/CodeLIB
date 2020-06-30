package net.ebookPrasad.controller;


import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import net.ebookPrasad.model.User;
import net.ebookPrasad.service.PrasadService;

@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private PrasadService prasadservice;

	@GetMapping("/login")
	public ModelAndView login(String error,String logout) {
		ModelAndView mv = new ModelAndView("login");
		if(error!=null)
		{
			mv.addObject("error", "Invalid E-mail or Password");
		}
		if(logout!=null)
		{
			mv.addObject("error", "Logout Successful");
		}
		return mv;
	}
	
	@GetMapping("/register")
	public ModelAndView register(String exists,String error,String msg) {
		ModelAndView mv = new ModelAndView("register");
		if(exists!=null)
		{
			mv.addObject("msg", "E-mail already Exists, Please Login.");
		}
		if(error!=null)
		{
			mv.addObject("msg", "Password missmatch.");
		}
		if(msg!=null)
		{
			mv.addObject("msg", "Password is too weak.");
		}
		return mv;
	}

	
	@PostMapping("register-user")
	public String registerUser(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email,@RequestParam(name = "password1") String password1,@RequestParam(name = "password2")String password2)
	{
		if(password1.equals(password2))
		{
			if(password1.length()>=5)
			{
				User user=new User();
				user.setUsername(email);
				user.setName(name);
				user.setPassword(bCryptPasswordEncoder.encode(password1));
				user.setfPass(password1);
				return prasadservice.register(user);
			}
			else 
			{
				return "redirect:/register?msg";
			}
			
		}
		else 
		{
			return "redirect:/register?error";
		}
		
	}

	@GetMapping("/")
	public ModelAndView Home(Authentication auth) 
	{
		return prasadservice.home(auth);
	}
	

	@GetMapping("/upload")
	public ModelAndView upload(Authentication auth,String done, String error, String deleted, String cheating) {
		return prasadservice.upload(auth,done,error,deleted,cheating);
	}
	
	@GetMapping("/courses")
	public ModelAndView courses(Authentication auth) {
		return prasadservice.courses(auth);
	}
	
	@PostMapping("/upload-course")
	public String uploadCourse(@RequestParam(name="title")String title, @RequestParam(name="source")String source,@RequestParam(name="url") String url, @RequestParam(name="CourseCategory") String category ,Authentication auth)
	{
		return prasadservice.uploadCourse(title,source,url,category,auth);
	}
	
	@PostMapping("/upload-book")
	public String uploadBook(@RequestParam(name="title")String title, @RequestParam(name="auther")String auther, @RequestParam(name="category") String category ,Authentication auth,@RequestParam(name = "file") MultipartFile file) throws IOException
	{
		return prasadservice.uploadBook(title,auther,category,auth,file);
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<ByteArrayResource> download(@PathVariable("id") Long id)
	{
		return prasadservice.download(id);
	}
	
	@GetMapping("/delete/{type}/{id}")
	public String delete(@PathVariable("type") String type, @PathVariable("id") Long id,Authentication auth) 
	{
		return prasadservice.delete(type,id,auth);
	}
	
	@GetMapping("/category/{type}")
	public ModelAndView searchByCategory(@PathVariable("type") String type,@RequestParam("category") String category,Authentication auth) 
	{
		return prasadservice.searchBYCategory(type,category,auth);
	}
	
	@GetMapping("/search/{type}")
	public ModelAndView searchByKeyword(@PathVariable("type") String type,@RequestParam("keyword") String keyword,Authentication auth) 
	{
		return prasadservice.searchBYKeyword(type,keyword,auth);
	}
	
	@GetMapping("/book/view/{id}")
	public ModelAndView viewBook(@PathVariable("id") Long id,Authentication auth)
	{
		return prasadservice.viewbook(id,auth);
	}
	
	@GetMapping("/course/view/{id}")
	public ModelAndView viewCourse(@PathVariable("id") Long id, Authentication auth)
	{
		return prasadservice.viewCourse(id,auth);
	}
	
	
	
	
//									Admin Page
	
	
	
	@GetMapping("/admin")
	public ModelAndView admin()
	{
		return prasadservice.admin();
	}
	
	@PostMapping("/dashboard")
	public ModelAndView dashboard(@RequestParam("password") String password)
	{
		return prasadservice.adminDashboard(password);
	}
	
	@GetMapping("/dashboard/{type}")
	public ModelAndView dashboardNextStep(@PathVariable("type") String type)
	{
		return prasadservice.dashboardNextStep(type);
	}
	
	@GetMapping("/dashboard/{type}/{id}")
	public ModelAndView dashboardEditData(@PathVariable("type") String type, @PathVariable("id") Long id)
	{
		return prasadservice.dashboardEditData(type,id);
	}
	
	@GetMapping("/dashboard/{type}/delete/{id}")
	public ModelAndView deleteRecord(@PathVariable("type") String type, @PathVariable("id") Long id)
	{
		return prasadservice.deleteRecord(type,id);
	}
	
	@PostMapping("/editbook")
	public ModelAndView editBook(@RequestParam(name = "id") Long id,@RequestParam(name = "title") String title,
			@RequestParam(name = "auther") String auther,@RequestParam(name = "uid") Long uid,
			@RequestParam(name = "awsurl") String awsurl,@RequestParam(name = "cat") String cat,
			@RequestParam(name = "date") String date) 
	{
		return prasadservice.editBook(id,title,auther,uid,awsurl,cat,date);
	}
	
	@PostMapping("/editcourse")
	public ModelAndView editCourse(@RequestParam(name = "id") Long id,@RequestParam(name = "title") String title,
			@RequestParam(name = "source") String source,@RequestParam(name = "uid") Long uid,
			@RequestParam(name = "url") String url,@RequestParam(name = "cat") String cat,
			@RequestParam(name = "date") String date) 
	{
		return prasadservice.editCourse(id,title,source,uid,url,cat,date);
	}
	
	@GetMapping("/forgot")
	public ModelAndView forgot() 
	{
		return new ModelAndView("forgot");
	}
	
	@PostMapping("/getpassword")
	public ModelAndView getpassword(@RequestParam(name = "email") String email) throws Exception 
	{
		return prasadservice.getpassword(email);
	}
	
}
