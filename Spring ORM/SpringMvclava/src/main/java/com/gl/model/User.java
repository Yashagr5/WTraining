package com.gl.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")

public class User {
	
	@Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Id
    @Column(name = "email", unique = true)
    private String email;

public User() {
	super();
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}


public User(String name, String password, String email) {
	super();
	this.name = name;
	this.password = password;
	this.email = email;
	
}



}
