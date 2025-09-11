package com.company.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.entity.NotifiedOwner;
import com.company.entity.User;
import com.company.service.NotifiedOwnerService;
import com.company.service.UserService;

@Controller
public class UserController {

    private final UserService userService;
    private final NotifiedOwnerService notifiedOwnerService;

    public UserController(UserService userService, NotifiedOwnerService notifiedOwnerService) {
        this.userService = userService;	
        this.notifiedOwnerService = notifiedOwnerService;
    }

    // Show registration page
    @GetMapping("/register.html")
    public String showRegisterForm(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "register"; // register.html in templates/static folder
    }

    // Handle registration form submission
    @PostMapping("/users/register")
    public String registerUser(@RequestParam String userName,
                               @RequestParam String email,
                               @RequestParam String phone,
                               @RequestParam String password) {
        try {
            User user = User.builder()
                    .userName(userName)
                    .email(email)
                    .phone(phone)
                    .password(password)
                    .build();

            userService.register(user);

            // Redirect to login page with success message
            return "redirect:/login.html?success=1";

        } catch (RuntimeException e) {
            // Redirect back to register page with error message
            return "redirect:/register.html?error=" + e.getMessage();
        }
    }

    // Show login page
    @GetMapping("/login.html")
    public String showLoginForm(@RequestParam(required = false) String success,
                                @RequestParam(required = false) String error,
                                Model model) {
        if (success != null) {
            model.addAttribute("successMessage", "Registration successful! Please login.");
        }
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "login"; // login.html
    }

    // Handle login form submission
    @PostMapping("/users/login")
    public String loginUser(@RequestParam String identifier,
                            @RequestParam String password,
                            Model model) {
        try {
            User user = userService.login(identifier, password);
            model.addAttribute("user", user);
            return "dashboard"; // show dashboard after login
        } catch (RuntimeException e) {
            model.addAttribute("error", "Invalid credentials");
            return "login"; // stay on login page if login fails
        }
    }

    // Dashboard (direct access with identifier)
    @GetMapping("/dashboard")
    public String dashboard(Model model, @RequestParam String identifier) {
        User user = userService.findByEmailOrPhone(identifier);
        model.addAttribute("user", user);

        // Add notifications
        List<NotifiedOwner> notifications = notifiedOwnerService.findAll();
        model.addAttribute("notifications", notifications);

        return "dashboard"; // dashboard.html
    }
}
