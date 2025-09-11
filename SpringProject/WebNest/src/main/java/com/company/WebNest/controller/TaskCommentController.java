package com.company.WebNest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.WebNest.model.Comment;
import com.company.WebNest.model.Task;
import com.company.WebNest.model.User;
import com.company.WebNest.service.CommentService;
import com.company.WebNest.service.TaskService;

@Controller
@RequestMapping("/user/tasks/comments")
public class TaskCommentController {
	 @Autowired
	    private CommentService commentService;

	    @Autowired
	    private TaskService taskService;

	    // Show all comments for a specific task
	    @GetMapping("/{taskId}")
	    public String viewComments(@PathVariable Long taskId, Model model) {
	        Task task = taskService.findById(taskId);
	        List<Comment> comments = commentService.findByTaskId(taskId);

	        model.addAttribute("task", task);
	        model.addAttribute("comments", comments);

	        return "TaskComments"; // JSP page you created
	    }

	    // Add a new comment
	    @PostMapping("/add")
	    public String addComment(@RequestParam Long taskId,
	                             @RequestParam String content,
	                             HttpSession session) {
	        User user = (User) session.getAttribute("user");
	        if (user != null) {
	            Comment comment = new Comment();
	            comment.setContent(content);
	            comment.setTask(taskService.findById(taskId));
	            comment.setUser(user);
	            commentService.saveComment(comment);
	        }
	        return "redirect:/user/tasks/comments/" + taskId;
	    }

	    // Delete a comment
	    @GetMapping("/delete/{id}")
	    public String deleteComment(@PathVariable Long id) {
	        List<Comment> comment = commentService.findByTaskId(id);
	        if (comment != null) {
	            Long taskId = ((Comment) comment).getTask().getId();
	            commentService.deleteCommentById(id);
	            return "redirect:/user/tasks/comments/" + taskId;
	        }
	        return "redirect:/user/tasks";
	    }
}
