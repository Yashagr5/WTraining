package com.company.WebNest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.company.WebNest.config.AppConfig;
import com.company.WebNest.model.Comment;
import com.company.WebNest.model.Task;
import com.company.WebNest.model.User;
import com.company.WebNest.service.CommentService;
import com.company.WebNest.service.TaskService;
import com.company.WebNest.service.UserService;

public class App {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = ctx.getBean(UserService.class);
        TaskService taskService = ctx.getBean(TaskService.class);
        CommentService commentService = ctx.getBean(CommentService.class);
        PlatformTransactionManager txManager = ctx.getBean(PlatformTransactionManager.class);

        // start a transaction
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);
        
     // ===Create and save a User ===
        User user = new User(null, "mohit", "mohit@example.com", "12345", "ADMIN");
        userService.saveData(user);
        System.out.println("User saved: " + user);

        // ===Create and save a Task ===
        Task task = new Task();
        task.setTitle("Build WorkNest App");
        task.setStatus("Pending");
        task.setUser(user);
        taskService.saveTask(task);
        System.out.println("Task saved: " + task);

        // ===Create and save a Comment ===
        Comment comment = new Comment();
        comment.setTask(task);
        comment.setContent("This task must be finished by Friday!");
        commentService.saveComment(comment);
        System.out.println("Comment saved: " + comment);

        txManager.commit(status);
        
        // ===Fetch and print all users ===
        System.out.println("All Users:");
        userService.getUsers().forEach(System.out::println);

        // ===Fetch tasks by user ===
        System.out.println("Tasks assigned to User ID: " + user.getId());
        taskService.findByAssignedUserId(user.getId()).forEach(System.out::println);

        // ===Fetch comments by task ===
        System.out.println("Comments for Task ID: " + task.getId());
        commentService.findByTaskId(task.getId()).forEach(System.out::println);

     
        ctx.close(); // close context
	}
}
