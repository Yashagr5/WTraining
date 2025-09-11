<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Comments</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            background: linear-gradient(to right, #6a11cb, #2575fc); /* Purple to Blue */
            min-height: 100vh; 
            padding: 20px; 
            margin: 0;
        }
        .container { 
            max-width: 1000px; 
            margin: 0 auto; 
            background: #ffffff; 
            padding: 30px; 
            border-radius: 12px; 
            box-shadow: 0px 6px 20px rgba(0,0,0,0.15); 
        }
        h1 { 
            text-align: center; 
            margin-bottom: 30px; 
            color: #6a11cb; /* Purple */
        }
        .comment { 
            border-left: 5px solid #2575fc; /* Blue accent */
            background: #f5f0ff; 
            padding: 15px; 
            margin-bottom: 15px; 
            border-radius: 8px; 
            transition: transform 0.2s; 
        }
        .comment:hover { 
            transform: translateY(-3px); 
            background: #ece6ff;
        }
        .comment-header { 
            font-weight: bold; 
            color: #6a11cb; 
            margin-bottom: 5px; 
        }
        .comment-task { 
            font-style: italic; 
            color: #555; 
            margin-bottom: 5px; 
        }
        .comment-date { 
            font-size: 12px; 
            color: #888; 
        }
        .comment-content { 
            margin-top: 10px; 
            color: #333;
        }
        .btn { 
            padding: 10px 18px; 
            background: #6a11cb; 
            color: white; 
            border: none; 
            border-radius: 6px; 
            cursor: pointer; 
            text-decoration: none; 
            display: inline-block; 
            transition: background 0.2s; 
        }
        .btn:hover { 
            background: #4b0f91; /* Darker purple */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>All Comments</h1>
        
        <c:forEach var="comment" items="${comments}">
            <div class="comment">
                <div class="comment-header">${comment.user.name}</div>
                <div class="comment-task">On task: ${comment.task.title}</div>
                <div class="comment-date">${comment.createdAt}</div>
                <div class="comment-content">${comment.content}</div>
            </div>
        </c:forEach>
        
        <c:if test="${empty comments}">
            <p style="text-align: center; color: #666; font-style: italic;">
                No comments yet.
            </p>
        </c:if>
        
        <div style="text-align: center; margin-top: 20px;">
            <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
