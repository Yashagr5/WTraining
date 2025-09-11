package com.company.WebNest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.WebNest.model.Comment;
import com.company.WebNest.model.Task;
import com.company.WebNest.model.User;
import com.company.WebNest.service.CommentService;
import com.company.WebNest.service.TaskService;

/**
 * Servlet implementation class UserDashboardController
 */
@Controller
@RequestMapping("/user")
public class UserDashboardController {

	@Autowired
    private TaskService taskService;

    @Autowired
    private CommentService commentService;

    /** 
     * User Dashboard → shows user tasks and statistics
     */
    @GetMapping("/user/dashboard")
    public String userDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<Task> tasks = taskService.findByAssignedUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("tasks", tasks);

        return "userDashboard";  // JSP page
    }

    /**
     * Update task status from dropdown
     */
    @GetMapping("/user/tasks/updateStatus")
    public String updateTaskStatus(@RequestParam Long taskId, 
                                   @RequestParam String status,
                                   HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Task task = taskService.findById(taskId);
        if (task != null && task.getAssignedTo().getId().equals(user.getId())) {
            task.setStatus(status);
            taskService.updateTask(task);
        }

        return "redirect:/user/dashboard";
    }

    /**
     * Show task comments
     */
    @GetMapping("/user/tasks/comments")
    public String viewComments(@RequestParam Long taskId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Task task = taskService.findById(taskId);
        if (task == null || !task.getAssignedTo().getId().equals(user.getId())) {
            return "redirect:/user/dashboard";
        }

        List<Comment> comments = commentService.findByTaskId(taskId);
        model.addAttribute("task", task);
        model.addAttribute("comments", comments);

        return "taskComments"; // JSP for comments
    }
}