<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Tasks</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            background: linear-gradient(to right, #1e3c72, #2a5298, #007bff); 
            min-height: 100vh; 
            display: flex;
            justify-content: center;
            align-items: start;
            padding: 20px;
        }
        .container { 
            width: 95%;
            max-width: 1000px; 
            background: #fff; 
            padding: 40px; 
            border-radius: 12px; 
            box-shadow: 0px 6px 12px rgba(0,0,0,0.25); 
        }
        h1 { 
            text-align: center; 
            margin-bottom: 30px; 
            color: #2a5298; 
            font-size: 28px;
        }
        table { 
            width: 100%; 
            border-collapse: collapse; 
            margin-bottom: 20px; 
        }
        th, td { 
            padding: 14px 12px; 
            border-bottom: 1px solid #eee; 
            text-align: left; 
        }
        th { 
            background: #007bff; 
            color: #fff; 
            text-transform: uppercase;
            font-size: 14px;
        }
        tr:nth-child(even) { background-color: #f0f6ff; }
        tr:hover { background-color: #e2ecff; transition: 0.3s; }

        /* Status Badge */
        .status-badge {
            padding: 6px 12px;
            border-radius: 20px;
            color: #fff;
            font-weight: bold;
            font-size: 13px;
        }
        .status-pending { background: #ff9800; }
        .status-inprogress { background: #1e88e5; }
        .status-completed { background: #28a745; }
        .status-delayed { background: #dc3545; }

        /* Dropdown */
        .status-dropdown { 
            padding: 6px; 
            border-radius: 6px; 
            border: 1px solid #ddd; 
            font-size: 13px;
        }

        /* Buttons styled in blue */
        .btn { 
            padding: 10px 18px; 
            background: #007bff; 
            color: white; 
            border: none; 
            border-radius: 6px; 
            cursor: pointer; 
            text-decoration: none; 
            font-size: 14px;
            font-weight: bold;
            display: inline-block; 
        }
        .btn:hover { background: #2a5298; }

        .action-container { 
            display: flex; 
            gap: 8px; 
            flex-wrap: nowrap; 
        }
    </style>
    <script>
        function updateTaskStatus(taskId, status) {
            window.location.href = '${pageContext.request.contextPath}/user/tasks/updateStatus?taskId=' + taskId + '&status=' + status;
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>My Tasks</h1>
        
        <table>
            <tr>
                <th>Title</th>
                <th>Description</th>
                <th>Status</th>
                <th>Start Date</th>
                <th>Due Date</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="task" items="${tasks}">
                <tr>
                    <td style="font-weight:bold; color:#2a5298;">${task.title}</td>
                    <td>${task.description}</td>
                    <td>
                        <span class="status-badge 
                            <c:choose>
                                <c:when test="${task.status == 'COMPLETED'}">status-completed</c:when>
                                <c:when test="${task.status == 'IN_PROGRESS'}">status-inprogress</c:when>
                                <c:when test="${task.status == 'PENDING'}">status-pending</c:when>
                                <c:otherwise>status-delayed</c:otherwise>
                            </c:choose>">
                            ${task.status}
                        </span>
                    </td>
                    <td>${task.startDate}</td>
                    <td>${task.dueDate}</td>
                    <td>
                        <div class="action-container">
                            <select class="status-dropdown" onchange="updateTaskStatus(${task.id}, this.value)">
                                <option value="PENDING" ${task.status == 'PENDING' ? 'selected' : ''}>Pending</option>
                                <option value="IN_PROGRESS" ${task.status == 'IN_PROGRESS' ? 'selected' : ''}>In Progress</option>
                                <option value="COMPLETED" ${task.status == 'COMPLETED' ? 'selected' : ''}>Completed</option>
                                <option value="DELAYED" ${task.status == 'DELAYED' ? 'selected' : ''}>Delayed</option>
                            </select>
                            <a href="${pageContext.request.contextPath}/user/tasks/comments?taskId=${task.id}" class="btn">View Comments</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <div style="text-align:center; margin-top:20px;">
            <a href="${pageContext.request.contextPath}/user/dashboard" class="btn">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
