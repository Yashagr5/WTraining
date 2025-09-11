package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.entity.Owner;
import com.company.exception.CustomException;
import com.company.service.OwnerService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    // Redirect root (localhost:8082) to login page
    @GetMapping("/")
    public String home() {
        return "redirect:/owners/login-page";
    }

    // Show registration page
    @GetMapping("/owners/register-page")
    public String showRegisterPage(Model model) {
        model.addAttribute("owner", new Owner());
        return "owner-register";
    }

    // Handle registration
    @PostMapping("/owners/register")
    public String registerOwner(@ModelAttribute Owner owner, Model model) {
        try {
            ownerService.registerOwner(owner);
            return "redirect:/owners/login-page";
        } catch (CustomException e) {
            model.addAttribute("error", e.getMessage());
            return "owner-register";
        }
    }

    // Show login page
    @GetMapping("/owners/login-page")
    public String showLoginPage() {
        return "owner-login";
    }

    // Handle login
    @PostMapping("/owners/login")
    public String loginOwner(@RequestParam String identifier,
                             @RequestParam String password,
                             HttpSession session,
                             Model model) {
        try {
            Owner owner = ownerService.login(identifier, password);
            session.setAttribute("loggedInOwner", owner);
            return "redirect:/owners/dashboard";
        } catch (CustomException e) {
            model.addAttribute("error", e.getMessage());
            return "owner-login";
        }
    }

    // Owner dashboard
    @GetMapping("/owners/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Owner owner = (Owner) session.getAttribute("loggedInOwner");
        if (owner == null) {
            return "redirect:/owners/login-page";
        }
        model.addAttribute("ownerName", owner.getOwnerName());
        return "owner-dashboard";
    }

    // Show add-name form
    @GetMapping("/owners/add-name")
    public String showAddNameForm(HttpSession session, Model model) {
        Owner owner = (Owner) session.getAttribute("loggedInOwner");
        if (owner == null) {
            return "redirect:/owners/login-page";
        }
        model.addAttribute("ownerId", owner.getOwnerId());
        model.addAttribute("name", owner.getOwnerName());
        return "add-name";
    }

    // Handle add-name form submission
    @PostMapping("/owners/add-name")
    public String addNameAndNotify(@RequestParam int ownerId,
                                   @RequestParam String name,
                                   Model model) {
        try {
            ownerService.addNameAndNotify(ownerId, name);
            model.addAttribute("msg", "✅ Successfully notified users!");
        } catch (Exception e) {
            model.addAttribute("error", "❌ Failed: " + e.getMessage());
        }
        return "add-name";
    }

    // Logout
    @GetMapping("/owners/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/owners/login-page";
    }
}
