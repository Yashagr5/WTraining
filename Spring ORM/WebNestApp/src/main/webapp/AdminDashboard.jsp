<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
	<h1>Admin Dashboard</h1>
    <h2>Welcome Admin</h2>
    <h2>Pending Tasks</h2>
<ul>
    <c:forEach var="task" items="${pendingTasks}">
        <li>${task.title} - ${task.status}</li>
    </c:forEach>
</ul>

<h2>In Progress Tasks</h2>
<ul>
    <c:forEach var="task" items="${inProgressTasks}">
        <li>${task.title} - ${task.status}</li>
    </c:forEach>
</ul>
<h2>In Completed Tasks</h2>
<ul>
    <c:forEach var="task" items="${completedTasks}">
        <li>${task.title} - ${task.status}</li>
    </c:forEach>
</ul>
<h2>In Delayed Tasks</h2>
<ul>
    <c:forEach var="task" items="${delayedTasks}">
        <li>${task.title} - ${task.status}</li>
    </c:forEach>
</ul>    
</body>
</html>