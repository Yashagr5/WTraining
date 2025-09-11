package com.company.WebNestApp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.WebNestApp.dao.CommentDaoImpl;
import com.company.WebNestApp.dao.TaskDaoImpl;
import com.company.WebNestApp.model.Comment;
import com.company.WebNestApp.model.Task;
import com.company.WebNestApp.model.User;

@Controller
public class UserController {
	@Autowired
    private TaskDaoImpl taskDao;

    @Autowired
    private CommentDaoImpl commentDao;

    // View user tasks
    @GetMapping("/user/tasks")
    public String viewTasks(Model model, HttpSession session) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            return "redirect:/login"; // redirect if not logged in
        }
        List<Task> assignedTasks = taskDao.findByAssignedUserId(loggedUser.getId());
        model.addAttribute("assignedTasks", assignedTasks);
        model.addAttribute("user", loggedUser);
        return "UserDashboard"; // JSP name
    }

    // Update task status
    @PostMapping("/user/updateTaskStatus")
    public String updateTaskStatus(@RequestParam Long taskId, @RequestParam String status) {
        taskDao.updateTaskStatus(taskId, status);
        return "redirect:/user/tasks";
    }

    // Add comment
    @PostMapping("/user/addComment")
    public String addComment(@RequestParam Long taskId, @RequestParam String content, HttpSession session) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            return "redirect:/login";
        }
        Comment comment = new Comment();
        comment.setTask(new Task(taskId)); // constructor with only ID
        comment.setUser(loggedUser);
        comment.setContent(content);
        commentDao.saveComment(comment);
        return "redirect:/user/tasks";
    }
}
