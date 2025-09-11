package com.company.WebNest.dao;

import java.util.List;

import com.company.WebNest.model.User;

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
    // to find by ID
    User findById(long id);
    void deleteUserById(Long id);
	}

