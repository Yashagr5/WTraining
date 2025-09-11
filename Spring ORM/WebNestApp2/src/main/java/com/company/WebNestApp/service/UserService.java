package com.company.WebNestApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.WebNestApp.dao.UserDao;
import com.company.WebNestApp.model.User;

@Service
public class UserService {
	private final UserDao dao;  // <-- inject interface

    public UserService(UserDao dao) {
        this.dao = dao;
    }
    
    public void saveData(User user) {
        dao.saveUser(user);
    }
   
    public List<User> getUsers() {
        return dao.getUsers();
    }
    
    public User findById(long id) {
        return dao.findById(id);
    }
    
    public User findByName(String name) {
        return dao.findByName(name);
    }

    public User findByEmail(String email) {
        return dao.findByEmail(email);
    }

    public User validateUser(String email, String password) {
        return dao.validateUser(email, password);
    }

    public void deleteUser(String email) {
        dao.deleteUser(email);
    }
    public User findByEmailAndPassword(String email, String password) {
        return dao.findByEmailAndPassword(email, password);
    }
}
