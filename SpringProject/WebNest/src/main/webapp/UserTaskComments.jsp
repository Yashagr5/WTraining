<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Task Comments</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
    <style>
        body {
            background: linear-gradient(to right, #2b5876, #4e4376);
            min-height: 100vh;
            padding: 20px;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .container {
            background: #ffffff;
            border-radius: 14px;
            padding: 30px;
            box-shadow: 0 6px 25px rgba(0,0,0,0.2);
        }
        .comment {
            border-bottom: 1px solid #ddd;
            padding: 8px 0;
        }
        .comment:last-child {
            border-bottom: none;
        }
        .comment-user {
            font-weight: 600;
        }
        .comment-text {
            margin-left: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>Comments for Task: <strong>${task.title}</strong></h3>
    <a href="${pageContext.request.contextPath}/UserDashboard" class="btn btn-sm btn-outline-secondary mb-3">⬅ Back to Dashboard</a>

    <!-- Existing Comments -->
    <div>
        <c:choose>
            <c:when test="${not empty task.comments}">
                <c:forEach var="comment" items="${task.comments}">
                    <div class="comment">
                        <span class="comment-user">${comment.user.name}:</span>
                        <span class="comment-text">${comment.text}</span>
                        <span class="text-muted" style="float:right;font-size:0.8rem">${comment.createdAt}</span>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p class="text-muted fst-italic">No comments yet. Be the first to comment!</p>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Add New Comment -->
    <form action="${pageContext.request.contextPath}/user/tasks/addComment" method="post" class="mt-4">
        <input type="hidden" name="taskId" value="${task.id}" />
        <div class="mb-3">
            <textarea name="commentText" class="form-control" rows="3" placeholder="Add your comment..." required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Post Comment</button>
    </form>
</div>
</body>
</html>
