package com.gl.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl.dao.UserDao;
import com.gl.model.User;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao dao;

    // GET: Show login form
    @RequestMapping(method = RequestMethod.GET)
    public String getUserForm(Model model) {
        model.addAttribute("user", new User()); // needed for form binding
        model.addAttribute("msg", "Enter your details");
        return "Signin";
    }

    // POST: Handle login form submission
    @RequestMapping(method = RequestMethod.POST)
    public String loginUser(Model model, @ModelAttribute("user") User user) {
    	if (user.getEmail() != null && user.getPassword() != null) {
            User validUser = dao.validateUser(user.getEmail(), user.getPassword());

            if (validUser != null) {
                model.addAttribute("msg", "Welcome, " + validUser.getName());
                return "success";
            } else {
                model.addAttribute("error", "Invalid credentials");
                return "Signin";
            }
        } else {
            model.addAttribute("error", "User details can't be empty");
            return "Signin";
        }
    }
    // GET: List all users
    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        List<User> list = dao.getUsers(); // Hibernate-based fetch
        model.addAttribute("userlist", list);
        return "homeuser";
    }
}
