<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Task Comments</title>
    <style>
        body { 
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; 
            background: linear-gradient(135deg, #6a11cb, #2575fc); 
            min-height: 100vh; 
            padding: 30px; 
            margin: 0;
        }
        .container { 
            max-width: 850px; 
            margin: 0 auto; 
            background: #fff; 
            padding: 35px; 
            border-radius: 12px; 
            box-shadow: 0 8px 20px rgba(0,0,0,0.25); 
            animation: fadeIn 0.8s ease-in-out; 
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }
        h1 { color: #2575fc; font-size: 28px; margin-bottom: 20px; }
        h2 { color: #444; margin-top: 30px; margin-bottom: 15px; }
        
        /* Form */
        textarea { 
            width: 100%; 
            padding: 12px; 
            border: 1px solid #ddd; 
            border-radius: 6px; 
            resize: vertical; 
            font-size: 14px; 
            margin-bottom: 12px;
        }
        .btn { 
            padding: 10px 20px; 
            background: linear-gradient(to right, #6a11cb, #2575fc); 
            color: #fff; 
            border: none; 
            border-radius: 6px; 
            cursor: pointer; 
            font-weight: bold;
            transition: all 0.3s ease; 
        }
        .btn:hover { 
            background: linear-gradient(to right, #530e9b, #1b5edb); 
            transform: translateY(-2px); 
        }
        
        /* Comments */
        .comment { 
            background: #f9f9f9; 
            border: 1px solid #eee; 
            padding: 15px; 
            margin-bottom: 15px; 
            border-radius: 8px; 
            box-shadow: 0px 2px 5px rgba(0,0,0,0.05); 
            transition: 0.3s;
        }
        .comment:hover { background: #f1f7ff; }
        .comment-header { 
            font-weight: bold; 
            color: #6a11cb; 
            margin-bottom: 5px; 
            font-size: 15px; 
        }
        .comment-date { font-size: 12px; color: #888; margin-bottom: 8px; }
        .comment-content { font-size: 14px; color: #444; }
        
        .back-btn { 
            display: inline-block; 
            margin-top: 20px; 
            text-decoration: none; 
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Comments for: <i>${task.title}</i></h1>
        
        <!-- Add Comment Form -->
        <h2>Add a Comment</h2>
        <form action="${pageContext.request.contextPath}/user/tasks/comments/add" method="post">
            <input type="hidden" name="taskId" value="${task.id}">
            <textarea name="content" placeholder="Write your comment..." rows="4" required></textarea>
            <button type="submit" class="btn">Post Comment</button>
        </form>
        
        <hr>
        
        <!-- Comments List -->
        <h2>All Comments</h2>
        <c:forEach var="comment" items="${comments}">
            <div class="comment">
                <div class="comment-header">${comment.user.name}</div>
                <div class="comment-date">${comment.createdAt}</div>
                <div class="comment-content">${comment.content}</div>
            </div>
        </c:forEach>
        
        <c:if test="${empty comments}">
            <p>No comments yet. Be the first to comment!</p>
        </c:if>
        
        <a href="${pageContext.request.contextPath}/user/tasks" class="btn back-btn">Back to Tasks</a>
    </div>
</body>
</html>
