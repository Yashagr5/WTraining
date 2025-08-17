package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserServiceTest {
	private final UserService us = new UserService();

//	@Test
//	void testConnection() {
//		actualValue = DBConnUtil.getConn();
//		assertNotNull(actualValue , " DB Connection failure");
//	}
//		
//	}
//	
	//Positive test case
	@Test
	void testValidUser() {
		assertTrue(us.isValidUser("Yash", "1234567")); // assuming that this user name exist 
	}
	
	// Negative test case
	@Test
	void testInValidUser()
	{
		
		assertFalse(us.isValidUser("Hello", "abc123")); // To check invalid user that does not exist in a table
	}
	
	@Test
	void testShortPassword()
	{
		
		assertFalse(us.isValidUser("Yash", "123")); // checking the password length which should be greater than 6
	}
	
	@Test
	void testEmptyInput()
	{
		
		assertFalse(us.isValidUser("Yash", "")); // checking the empty fields 
	}
}
