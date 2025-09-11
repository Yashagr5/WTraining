package com.worknest.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.model.Comment;
import com.company.model.Task;
import com.company.model.User;
import com.company.repository.CommentRepository;
import com.company.repository.TaskRepository;
import com.company.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    // ---------------- DASHBOARD ----------------
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "admin/dashboard"; // assumes you have admin/dashboard.html
    }

    // ---------------- TASKS ----------------
    @GetMapping("/tasks")
    public String tasksPage(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "admin/tasks";
    }

    @PostMapping("/tasks/add")
    public String addTask(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam Task.Status status,
            @RequestParam String startDate,
            @RequestParam String dueDate,
            @RequestParam List<Long> assignedUserIds
    ) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status); // ✅ Enum type instead of String
        task.setStartDate(LocalDate.parse(startDate)); // ✅ LocalDate
        task.setDueDate(LocalDate.parse(dueDate));     // ✅ LocalDate

        // Convert List<User> → Set<User>
        Set<User> users = new HashSet<>(userRepository.findAllById(assignedUserIds));
        task.setAssignedUsers(users);

        taskRepository.save(task);
        return "redirect:/admin/tasks";
    }

    @GetMapping("/tasks/updateStatus")
    public String updateTaskStatus(@RequestParam Long taskId, @RequestParam String status) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(status);
            taskRepository.save(task);
        }
        return "redirect:/admin/tasks";
    }

    // ---------------- COMMENTS ----------------
    @GetMapping("/tasks/{taskId}/comments")
    public String viewComments(@PathVariable Long taskId, Model model) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            List<Comment> comments = commentRepository.findByTaskId(taskId);
            model.addAttribute("task", task);
            model.addAttribute("comments", comments);
            return "admin/comments";
        }
        return "redirect:/admin/tasks";
    }

    @PostMapping("/tasks/comments/add")
    public String addComment(@RequestParam Long taskId,
                             @RequestParam String content) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            Comment comment = new Comment();
            comment.setTask(task);
            comment.setCommentText(content);
            comment.setCreatedAt(LocalDateTime.now());

            // Hardcode admin user for now, or get from logged-in user
            User admin = userRepository.findById(1L).orElse(null); 
            comment.setUser(admin);

            commentRepository.save(comment);
        }
        return "redirect:/admin/tasks/" + taskId + "/comments";
    }

    @GetMapping("/comments")
    public String viewAllComments(Model model) {
        List<Comment> comments = commentRepository.findAll();
        model.addAttribute("comments", comments);
        return "admin/view-all-comments";
    }
}
