package com.example.validate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.validate.dao.UserRepository;
import com.example.validate.model.User;

@Service
public class UserService 
{
	
//	Firstly autowired the UserRepository
	
	@Autowired
	public UserRepository userRepo;
	
	public User SaveUser(User u)
	{
		return userRepo.save(u);
	}
	
	public List<User> getAllUsers()
	{
		return (List<User>) userRepo.findAll();
	}

}
