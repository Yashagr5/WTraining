<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User DashBoard</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
    <h2>Welcome, ${user.username}</h2>
    <a href="logout" class="btn btn-danger float-end">Logout</a>
    <hr/>

    <h3>Your Tasks</h3>
    <ul>
    <c:forEach var="task" items="${assignedTasks}">
        <li>${task.title} - ${task.status}
            <form action="/user/updateTaskStatus" method="post">
                <input type="hidden" name="taskId" value="${task.id}">
                <select name="status">
                    <option value="In Progress">In Progress</option>
                    <option value="Completed">Completed</option>
                </select>
                <button type="submit">Update</button>
            </form>
            <form action="/user/addComment" method="post">
                <input type="hidden" name="taskId" value="${task.id}">
                <textarea name="content" placeholder="Add a comment"></textarea>
                <button type="submit">Add Comment</button>
            </form>
        </li>
    </c:forEach>
</ul>
</body>
</html>