package com.company.WebNestApp.dao;

import java.util.List;

import com.company.WebNestApp.model.User;

public interface UserDao {
	// to save
    void saveUser(User user);
    // to display
    List<User> getUsers();
  //to find name
    User findByName(String name);
    //to find email
    User findByEmail(String email);
    // to check
    User validateUser(String email, String password);
    // to delete
    void deleteUser(String email);
	}
