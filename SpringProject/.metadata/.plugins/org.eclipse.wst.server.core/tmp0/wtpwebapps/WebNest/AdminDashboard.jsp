<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body { 
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; 
            background: linear-gradient(135deg, #6a11cb, #2575fc); 
            min-height: 100vh; 
            padding: 20px; 
        }
        .container { 
            max-width: 1200px; 
            margin: 0 auto; 
            background: #ffffff; 
            padding: 30px; 
            border-radius: 14px; 
            box-shadow: 0px 6px 18px rgba(0,0,0,0.2); 
        }
        h1 { 
            text-align: center; 
            margin-bottom: 30px; 
            color: #444; 
            font-weight: 700;
        }
        .dashboard-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }
        .dashboard-card {
            background: #f1f5fb;
            padding: 20px;
            border-radius: 10px;
            border-left: 5px solid #2575fc;
            transition: transform 0.2s, box-shadow 0.2s;
        }
        .dashboard-card:hover {
            transform: translateY(-5px);
            box-shadow: 0px 6px 16px rgba(0,0,0,0.15);
        }
        .dashboard-card h3 {
            margin-top: 0;
            color: #2575fc;
        }
        .stats {
            font-size: 26px;
            font-weight: bold;
            color: #222;
        }
        .nav-menu {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 15px;
            margin-top: 30px;
        }
        .nav-btn {
            display: block;
            padding: 15px;
            background: #2575fc;
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 8px;
            font-weight: bold;
            transition: background 0.3s, transform 0.2s;
        }
        .nav-btn:hover {
            background: #1b5edb;
            transform: scale(1.03);
        }
        table {
            width: 100%; 
            border-collapse: collapse; 
            margin-bottom: 20px; 
            border-radius: 8px;
            overflow: hidden;
        }
        th {
            background: #2575fc; 
            color: white; 
            padding: 12px; 
            text-align: left;
        }
        td {
            padding: 12px; 
            border-bottom: 1px solid #eaeaea;
        }
        tr:hover {
            background: #f9faff;
        }
        h2 {
            margin-top: 30px;
            color: #444;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Admin Dashboard</h1>
        
        <!-- Statistics Cards -->
        <div class="dashboard-grid">
            <div class="dashboard-card">
                <h3>Pending Tasks</h3>
                <div class="stats">${pendingTasks != null ? pendingTasks.size() : 0}</div>
            </div>
            <div class="dashboard-card">
                <h3>In Progress</h3>
                <div class="stats">${inProgressTasks != null ? inProgressTasks.size() : 0}</div>
            </div>
            <div class="dashboard-card">
                <h3>Completed</h3>
                <div class="stats">${completedTasks != null ? completedTasks.size() : 0}</div>
            </div>
            <div class="dashboard-card">
                <h3>Delayed</h3>
                <div class="stats">${delayedTasks != null ? delayedTasks.size() : 0}</div>
            </div>
        </div>

        <!-- Navigation Menu -->
        <div class="nav-menu">
       	<!--<a href="${pageContext.request.contextPath}/admin/tasks" class="nav-btn">Manage Tasks</a>
       		<a href="${pageContext.request.contextPath}/admin/comments" class="nav-btn">View All Comments</a>
            <a href="${pageContext.request.contextPath}/logout" class="nav-btn">Logout</a>  -->
            <a href="TaskComments.jsp" class="nav-btn">Manage Tasks</a>
            <a href="AdminComments.jsp" class="nav-btn">View All Comments</a>
            <a href="index.jsp" class="nav-btn">Logout</a>
        </div>

        <!-- Task Status Sections -->
        <c:if test="${not empty pendingTasks}">
            <h2>Pending Tasks</h2>
            <table>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Assigned To</th>
                    <th>Due Date</th>
                </tr>
                <c:forEach var="task" items="${pendingTasks}">
                    <tr>
                        <td>${task.title}</td>
                        <td>${task.description}</td>
                        <td>${task.assignedTo.name}</td>
                        <td>${task.dueDate}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <c:if test="${not empty inProgressTasks}">
            <h2>Tasks In Progress</h2>
            <table>
                <tr>
                    <th>Title</th>
                    <th>Assigned To</th>
                    <th>Due Date</th>
                </tr>
                <c:forEach var="task" items="${inProgressTasks}">
                    <tr>
                        <td>${task.title}</td>
                        <td>${task.assignedTo.name}</td>
                        <td>${task.dueDate}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>