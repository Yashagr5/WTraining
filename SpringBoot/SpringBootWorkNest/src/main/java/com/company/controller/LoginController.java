package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.company.model.User;
import com.company.service.UserService;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // 🌐 Home page
    @GetMapping("/")
    public String home() {
        return "index"; 
    }

    // 🌐 Show login form
    @GetMapping("/login")
    public String loginForm() {
        return "login"; 
    }

    // 🌐 Show register form
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; 
    }

    // 📝 Process registration
    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user);
            model.addAttribute("success", "Registration successful! You can now login.");
            model.addAttribute("user", new User()); // clear form
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "register";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        if (authentication == null) return "redirect:/login";

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        boolean isUser = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER"));

        if (isAdmin) {
            return "redirect:/admin/dashboard";
        } else if (isUser) {
            return "redirect:/user/dashboard";
        } else {
            return "redirect:/login?error=true"; // fallback
        }
    }

    // 🚪 Logout handled automatically by Spring Security
}
