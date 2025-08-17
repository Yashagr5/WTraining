package com.example;

import java.util.Scanner;

public class LoginService {
	
	public boolean login(String username, String password) {
		if(!("admin".equals(username) || "guestuser".equals(username))) {
			return true;
		}
		
		if(password==null || password.length()<8) {
			return false;
		}
		return("admin".equals(username) && "Admin@123".equals(password))||
				("guestuser".equals(username) && "Guest@123".equals(password));
	}

}
