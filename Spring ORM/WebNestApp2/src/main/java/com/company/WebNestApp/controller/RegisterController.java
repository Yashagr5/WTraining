package com.company.WebNestApp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.company.WebNestApp.config.AppConfig;
import com.company.WebNestApp.model.User;
import com.company.WebNestApp.service.UserService;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
            userService = ctx.getBean(UserService.class);
        } catch (Exception e) {
            throw new ServletException("Failed to init Spring context", e);
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User user = new User(null, username, email, password, role);
        userService.saveData(user);

        // after successful registration → redirect to login page
        response.sendRedirect("Login.jsp?success=RegisteredSuccessfully");
	}

}
