package com.company.WebNestApp.controller;

import java.io.IOException;

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

import com.company.WebNestApp.config.AppConfig;
import com.company.WebNestApp.model.User;
import com.company.WebNestApp.service.UserService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private UserService userService;

    @Override
    public void init() throws ServletException {
    	super.init();
    	try {
    		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
            userService = ctx.getBean(UserService.class);

            PlatformTransactionManager txManager = ctx.getBean(PlatformTransactionManager.class);
            
            TransactionDefinition def = new DefaultTransactionDefinition();
            TransactionStatus status = txManager.getTransaction(def);
            
        } catch (Exception e) {
            throw new ServletException("Failed to init Spring context", e);
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String email = request.getParameter("email");
	        String password = request.getParameter("password");

	        User user = userService.findByEmailAndPassword(email, password);

	        if (user != null) {
	            // store logged-in user in session
	            HttpSession session = request.getSession();
	            session.setAttribute("loggedUser", user);

	            // redirect based on role
	            if ("USER".equalsIgnoreCase(user.getRole())) {
	                response.sendRedirect("UserDashboard.jsp");
	            } else if ("ADMIN".equalsIgnoreCase(user.getRole())) {
	                response.sendRedirect("AdminDashboard.jsp");
	            } else {
	                response.sendRedirect("Login.jsp?error=InvalidRole");
	            }
	        } else {
	            // invalid credentials → back to login
	            response.sendRedirect("Login.jsp?error=InvalidCredentials");
	        }
	    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
