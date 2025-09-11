package com.company.WebNestApp.dao;

import java.util.List;

import com.company.WebNestApp.model.User;

public interface UserDao {
	Integer save(User user);
    void update(User user);
    void delete(Integer id);
    User findById(Integer id);
    User findByEmail(String email);
    User findByName(String name); // Added for username lookup
    List<User> findAll();
	}
