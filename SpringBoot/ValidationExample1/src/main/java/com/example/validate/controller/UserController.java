package com.example.validate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.validate.model.User;
import com.example.validate.service.UserService;

@RestController
@RequestMapping("user")
public class UserController 
{
//	Autowire the Service
	@Autowired
	public UserService userService;
	
	@PostMapping("/add")
	public ResponseEntity saveuser(@RequestBody User u)
	{
		ResponseEntity responseEntity = null;
		User uobj = userService.SaveUser(u);
		if(uobj != null)
			responseEntity = new ResponseEntity<>("Data added", HttpStatus.OK);
		else
			responseEntity = new ResponseEntity<>("Data not added", HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity; 
	}
	
//	@PostMapping("/add")
//	public String saveuser(@RequestBody User u)
//	{
//		ResponseEntity responseEntity = null;
//		User uobj = userService.SaveUser(u);
//		if(uobj != null)
//		return "Record not added"; 
////		responseEntity = new ResponseEntity<>("Data added", HttpStatus.OK);
//		else
//		return "Record not added"; 
// //			responseEntity = new ResponseEntity<>("Data not added", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	@GetMapping("/viewall")
	public ResponseEntity<List<User>> getAllUsers()
	{
		List<User> userList = userService.getAllUsers();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	
	
}
