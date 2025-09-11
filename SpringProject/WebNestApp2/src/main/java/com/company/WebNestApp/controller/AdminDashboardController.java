package com.company.WebNestApp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.WebNestApp.model.Task;
import com.company.WebNestApp.model.User;
import com.company.WebNestApp.model.Comment;
import com.company.WebNestApp.service.TaskService;
import com.company.WebNestApp.service.CommentService;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentService commentService;

    /** ================= DASHBOARD ================= */
    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        User admin = (User) session.getAttribute("user");

        // load tasks by status
        List<Task> pendingTasks = taskService.findByStatus("PENDING");
        List<Task> inProgressTasks = taskService.findByStatus("IN_PROGRESS");
        List<Task> completedTasks = taskService.findByStatus("COMPLETED");
        List<Task> delayedTasks = taskService.findByStatus("DELAYED");

        // put in model for JSP
        model.addAttribute("pendingTasks", pendingTasks);
        model.addAttribute("inProgressTasks", inProgressTasks);
        model.addAttribute("completedTasks", completedTasks);
        model.addAttribute("delayedTasks", delayedTasks);

        return "adminDashboard";  // maps to your JSP
    }

    /** ================= TASK MANAGEMENT ================= */
    @GetMapping("/tasks")
    public String manageTasks(Model model) {
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "adminTasks";
    }

    /** ================= COMMENT MANAGEMENT ================= */
    @GetMapping("/comments")
    public String manageComments(Model model) {
        List<Comment> comments = commentService.listAll();
        model.addAttribute("comments", comments);
        return "adminComments";
    }

    /** ================= LOGOUT ================= */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
