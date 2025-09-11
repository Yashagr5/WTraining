package com.company.WebNestApp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.company.WebNestApp.config.AppConfig;
import com.company.WebNestApp.model.Task;
import com.company.WebNestApp.model.User;
import com.company.WebNestApp.service.TaskService;

@WebServlet("/user/tasks/*")
public class UserTaskController extends HttpServlet {

    private TaskService taskService;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            AnnotationConfigApplicationContext ctx =
                    new AnnotationConfigApplicationContext(AppConfig.class);
            taskService = ctx.getBean(TaskService.class);
        } catch (Exception e) {
            throw new ServletException("Failed to init Spring context", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect(request.getContextPath() + "/Login.jsp?error=PleaseLogin");
            return;
        }

        User user = (User) session.getAttribute("loggedUser");

        // Determine action based on URL
        String path = request.getPathInfo(); // e.g., /updateStatus or /comments
        if (path == null) path = "";

        switch (path) {
            case "/updateStatus":
                updateTaskStatus(request, response, user);
                break;
            case "/comments":
                viewComments(request, response, user);
                break;
            default:
                listTasks(request, response, user);
                break;
        }
    }

    private void listTasks(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        request.setAttribute("tasks", taskService.findByAssignedUserId(user.getId()));
        request.getRequestDispatcher("/UserTask.jsp").forward(request, response);
    }

    private void updateTaskStatus(HttpServletRequest request, HttpServletResponse response, User user)
            throws IOException {
        try {
            long taskId = Long.parseLong(request.getParameter("taskId"));
            String status = request.getParameter("status");

            Task task = taskService.findById(taskId);

            if (task != null && task.getAssignedTo().getId() == user.getId()) {
                task.setStatus(status);
                taskService.updateTask(task);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // redirect back to dashboard
        response.sendRedirect(request.getContextPath() + "/UserDashboard");
    }

    private void viewComments(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        long taskId = Long.parseLong(request.getParameter("taskId"));
        Task task = taskService.findById(taskId);

        if (task != null && task.getAssignedTo().getId() == user.getId()) {
            request.setAttribute("task", task);
            request.getRequestDispatcher("/TaskComments.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/UserDashboard?error=InvalidTask");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
