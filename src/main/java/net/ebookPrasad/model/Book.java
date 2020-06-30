package net.ebookPrasad.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;



@Entity
@Table(name = "Books")
public class Book 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long uid;
	private String title;
	private String auther;
	private String uploader;
	private String fileName;
	private String fileType;
	private String date;
	private String category;
	
	@Lob
	private byte[] fileData;
	
	

	public Book()
	{
		
	}
	
	public Book(Long id, Long uid,byte[] fileData, String title,String fileType, String auther, String uploader, String fileName, String date, String category) {
		super();
		this.id =id;
		this.uid = uid;
		this.title = title;
		this.auther = auther;
		this.uploader = uploader;
		this.fileName = fileName;
		this.date = date;
		this.category=category;
		this.fileType=fileType;
		this.fileData=fileData;
	}
	public Long getUid() {
		return uid;
	}
	
	
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public String getFilename() {
		return fileName;
	}
	public void setFilename(String fileName) {
		this.fileName = fileName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date =date;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getFiletype() {
		return fileType;
	}

	public void setFiletype(String fileType) {
		this.fileType = fileType;
	}
	

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] bs) {
		this.fileData = bs;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

}
