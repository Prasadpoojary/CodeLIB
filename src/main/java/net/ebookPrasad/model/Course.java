package net.ebookPrasad.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Courses")
public class Course 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long uid;
	private String title;
	private String source;
	private String uploader;
	private String url;
	private String date;
	private String category;
	
	public  Course()
	{
		
	}
	
	public Course(Long id, Long uid, String title, String source, String uploader, String url, String date, String category) {
		super();
		this.id=id;
		this.uid = uid;
		this.title = title;
		this.source = source;
		this.uploader = uploader;
		this.url = url;
		this.date = date;
		this.category=category;
	}
	
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}

