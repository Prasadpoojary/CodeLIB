package net.ebookPrasad.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
	private String name;
	private String fPass;

    
	public User() {
    	
    }
    
    public User(String username, String password, String name, String fPass) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.fPass = fPass;
	}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getfPass() {
		return fPass;
	}

	public void setfPass(String fPass) {
		this.fPass = fPass;
	}


}
