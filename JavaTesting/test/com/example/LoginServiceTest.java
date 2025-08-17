package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoginServiceTest {
	LoginService loginservice = new LoginService();

	 @Test
	    void testValidAdminLogin() {
	        assertTrue(loginservice.login("admin", "Admin@123"));
	    }

	    @Test
	    void testValidGuestLogin() {
	        assertTrue(loginservice.login("guestuser", "Guest@123"));
	    }
	    
	    @Test
	    void testInvalidPassword1() {
	        assertFalse(loginservice.login("guestuser", "wrongpass"));
	    }
	    
	    @Test
	    void testInvalidPassword2() {
	        assertFalse(loginservice.login("admin", "wrongpass"));
	    }

	    @Test
	    void testNullPassword() {
	        assertFalse(loginservice.login("admin", null));
	    }
	    
	}



























//System.out.println("Enter UserName and Pa: ");
//String user=in.nextLine();
//System.out.println("Enter Password: ");
//String password=in.nextLine();
//Scanner in = new Scanner(System.in);

