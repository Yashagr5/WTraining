package com.company.WebNest.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TaskService taskService;
    private UserService userService;
    private CommentService commentService;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
        	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
            UserService userService = ctx.getBean(UserService.class);
            TaskService taskService = ctx.getBean(TaskService.class);
            CommentService commentService = ctx.getBean(CommentService.class);
            PlatformTransactionManager txManager = ctx.getBean(PlatformTransactionManager.class);

            // start a transaction
            TransactionDefinition def = new DefaultTransactionDefinition();
            TransactionStatus status = txManager.getTransaction(def);
            
        } catch (Exception e) {
            throw new ServletException("Failed to init Spring context", e);
        }
     // In a real app you would inject these via Spring or a context listener
//        taskService = (TaskService) getServletContext().getAttribute("taskService");
//        userService = (UserService) getServletContext().getAttribute("userService");
//        commentService = (CommentService) getServletContext().getAttribute("commentService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) action = "/dashboard";

        switch (action) {
            case "/dashboard":
                showDashboard(request, response);
                break;
            case "/tasks":
                manageTasks(request, response);
                break;
            case "/tasks/updateStatus":
                updateTaskStatus(request, response);
                break;
            case "/comments":
                manageComments(request, response);
                break;
            case "/users":
                manageUsers(request, response);
                break;
            case "/logout":
                logout(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) action = "";

        switch (action) {
            case "/tasks/add":
                addTask(request, response);
                break;
            default:
                doGet(request, response);
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("user");

        request.setAttribute("pendingTasks", taskService.findByStatus("PENDING"));
        request.setAttribute("inProgressTasks", taskService.findByStatus("IN_PROGRESS"));
        request.setAttribute("completedTasks", taskService.findByStatus("COMPLETED"));
        request.setAttribute("delayedTasks", taskService.findByStatus("DELAYED"));
        request.setAttribute("role", "ADMIN");

        request.getRequestDispatcher("/adminDashboard.jsp").forward(request, response);
    }

    private void manageTasks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("user");

        List<Task> tasks = taskService.findByAssignedUserId(admin.getId());
        request.setAttribute("tasks", tasks);
        request.setAttribute("users", userService.getUsers());

        request.getRequestDispatcher("/adminTasks.jsp").forward(request, response);
    }

    private void addTask(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("user");

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        Integer assignedToId = Integer.parseInt(request.getParameter("assignedTo"));

        User assignedTo = userService.findById(assignedToId);

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setAssignedTo(assignedTo);
        task.setAssignedBy(admin);

        taskService.saveTask(task);
        response.sendRedirect(request.getContextPath() + "/admin/tasks");
    }

    private void updateTaskStatus(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        String status = request.getParameter("status");

        Task task = taskService.findById((long)taskId);
        if (task != null) {
            task.setStatus(status);
            taskService.updateTask(task);
        }

        response.sendRedirect(request.getContextPath() + "/admin/tasks");
    }

    private void manageComments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Comment> comments = commentService.listAll();
        request.setAttribute("comments", comments);
        request.getRequestDispatcher("/adminComments.jsp").forward(request, response);
    }

    private void manageUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = userService.getUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/adminUsers.jsp").forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}