package com.company.WebNestApp.services;

import java.util.List;

import com.company.WebNestApp.model.User;

public interface UserService {
	Integer register(User user);
    void update(User user);
    void delete(Integer id);
    User get(Integer id);
    User getByEmail(String email);
    User getByName(String name); // Added for username lookup
    List<User> listAll();
}
