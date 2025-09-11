<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Comments</title>
    <style>
        body {
            font-family: "Segoe UI", Arial, sans-serif;
            background: linear-gradient(135deg, #1d3557, #457b9d, #a8dadc);
            min-height: 100vh;
            padding: 30px;
            margin: 0;
        }

        .container {
            max-width: 900px;
            margin: 0 auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0px 8px 18px rgba(0,0,0,0.2);
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #1d3557;
        }

        .comment {
            border-left: 6px solid #457b9d;
            background: #f8fbfd;
            padding: 15px 20px;
            margin-bottom: 18px;
            border-radius: 10px;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }
        .comment:hover {
            transform: translateY(-3px);
            box-shadow: 0px 6px 14px rgba(0,0,0,0.15);
        }

        .comment-header {
            font-weight: 600;
            font-size: 15px;
            color: #1d3557;
        }

        .comment-task {
            font-size: 13px;
            font-style: italic;
            color: #457b9d;
            margin-top: 2px;
        }

        .comment-date {
            font-size: 12px;
            color: #888;
            margin-top: 5px;
        }

        .comment-content {
            margin-top: 10px;
            font-size: 14px;
            line-height: 1.5;
            color: #333;
        }

        .btn {
            padding: 10px 18px;
            background: #1d3557;
            color: #fff;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            text-decoration: none;
            font-weight: bold;
            transition: background 0.3s;
        }
        .btn:hover {
            background: #457b9d;
        }

        .empty-msg {
            text-align: center;
            color: #666;
            font-style: italic;
            margin-top: 20px;
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
                <div class="comment-date">Posted on: ${comment.createdAt}</div>
                <div class="comment-content">${comment.content}</div>
            </div>
        </c:forEach>
        
        <c:if test="${empty comments}">
            <p class="empty-msg">No comments yet.</p>
        </c:if>
        
        <div style="text-align: center; margin-top: 25px;">
            <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn">⬅ Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
