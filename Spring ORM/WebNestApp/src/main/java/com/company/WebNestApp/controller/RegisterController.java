package com.company.WebNestApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.WebNestApp.dao.UserDao;
import com.company.WebNestApp.model.User;

public class RegisterController {
	@Autowired
    private UserDao userDao;

    // Show register page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "Register"; // JSP: Register.jsp
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String role,
                               Model model) {

        // Check if email already exists
        User existingUser = userDao.findByEmail(email);
        if (existingUser != null) {
            model.addAttribute("error", "Email already registered! Try login.");
            return "Register";
        }

        // Create new user
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password); // ⚠️ (later we should hash this)
        newUser.setRole(role);

        // Save to DB
        userDao.saveUser(newUser);

        model.addAttribute("success", "Registration successful! Please login.");
        return "Login"; // after registration go to login page
    }
}
