package com.company.WebNestApp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.WebNestApp.model.Comment;
import com.company.WebNestApp.model.Task;
import com.company.WebNestApp.model.User;
import com.company.WebNestApp.service.CommentService;
import com.company.WebNestApp.service.TaskService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private CommentService commentService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Task> tasks = taskService.listByUser(user.getId());
            model.addAttribute("tasks", tasks);
            model.addAttribute("user", user);
            model.addAttribute("role", "USER");
            return "userDashboard";
        }
        return "redirect:/login";
    }


    @GetMapping("/tasks/updateStatus")
    public String updateTaskStatus(@RequestParam Long taskId, 
                                  @RequestParam String status,
                                  HttpSession session) {
        User user = (User) session.getAttribute("user");
        Task task = taskService.findById(taskId);
        
        // Check if the task belongs to the current user
        if (task != null && task.getAssignedTo().getId().equals(user.getId())) {
            task.setStatus(status);
            taskService.updateTask(task);
        }
        return "redirect:/user/tasks";
    }

    @GetMapping("/tasks/comments")
    public String viewTaskComments(@RequestParam Long taskId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Task task = taskService.findById(taskId);
        
        // Check if the task belongs to the current user
        if (task != null && task.getAssignedTo().getId().equals(user.getId())) {
            List<Comment> comments = commentService.listByTask(taskId);
            model.addAttribute("task", task);
            model.addAttribute("comments", comments);
            return "taskComments";
        }
        return "redirect:/user/tasks";
    }

    @PostMapping("/tasks/comments/add")
    public String addComment(@RequestParam Integer taskId,
                            @RequestParam String content,
                            HttpSession session) {
        User user = (User) session.getAttribute("user");
        Task task = taskService.get(taskId);
        
        // Check if the task belongs to the current user
        if (task != null && task.getAssignedTo().getId().equals(user.getId())) {
            Comment comment = new Comment(content, user, task);
            commentService.add(comment);
        }
        return "redirect:/user/tasks/comments?taskId=" + taskId;
    }
    
    @GetMapping("/tasks")
    public String viewTasks(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Task> tasks = taskService.listByUser(user.getId());
            model.addAttribute("tasks", tasks);
            return "userTasks";
        }
        return "redirect:/login";
    }
    
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}