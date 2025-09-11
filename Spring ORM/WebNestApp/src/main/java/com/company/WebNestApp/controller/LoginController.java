package com.company.WebNestApp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.WebNestApp.dao.UserDao;
import com.company.WebNestApp.model.User;

@Controller
public class LoginController {

    @Autowired
    private UserDao userDao;

    // Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "Login";  // JSP: Login.jsp
    }

    // Handle login
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = userDao.validateUser(email, password);

        if (user != null) {
            // put logged user in session
            session.setAttribute("loggedUser", user);

            // redirect based on role
            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/user/tasks";
            }
        } else {
            model.addAttribute("error", "Invalid email or password!");
            return "Login"; // back to login page with error
        }
    }

    // Handle logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // clear session
        return "redirect:/login";
    }
}
