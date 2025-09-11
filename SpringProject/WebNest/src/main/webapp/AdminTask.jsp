<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Tasks</title>
    <style>
        /* Background */
        body { 
            font-family: "Segoe UI", sans-serif; 
            background: linear-gradient(135deg, #1d3557, #457b9d, #a8dadc); 
            min-height: 100vh; 
            padding: 40px; 
            margin: 0;
        }

        /* Main container */
        .container { 
            max-width: 1100px; 
            margin: 0 auto; 
            background: #ffffff; 
            padding: 30px; 
            border-radius: 15px; 
            box-shadow: 0px 8px 20px rgba(0,0,0,0.2); 
        }

        h1 { 
            text-align: center; 
            margin-bottom: 30px; 
            color: #1d3557; 
        }

        h2 { 
            margin-top: 20px;
            margin-bottom: 15px;
            color: #457b9d; 
            border-left: 5px solid #1d3557;
            padding-left: 10px;
        }

        /* Form */
        .form-group { margin-bottom: 18px; }
        .form-group label { font-weight: 600; color: #1d3557; margin-bottom: 6px; display: block; }
        .form-group input, 
        .form-group select, 
        .form-group textarea { 
            width: 100%; 
            padding: 10px; 
            border: 1px solid #ccc; 
            border-radius: 8px; 
            font-size: 14px; 
            outline: none;
        }
        .form-group input:focus,
        .form-group textarea:focus,
        .form-group select:focus {
            border-color: #457b9d;
            box-shadow: 0 0 5px rgba(69,123,157,0.5);
        }

        /* Buttons */
        .btn { 
            padding: 10px 18px; 
            background: #1d3557; 
            color: #fff; 
            border: none; 
            border-radius: 8px; 
            cursor: pointer; 
            text-decoration: none; 
            font-size: 14px; 
            font-weight: bold;
            transition: background 0.3s;
        }
        .btn:hover { background: #457b9d; }
        .btn-danger { background: #e63946; }
        .btn-danger:hover { background: #b71c1c; }

        /* Table */
        table { 
            width: 100%; 
            border-collapse: collapse; 
            margin-top: 20px; 
            border-radius: 12px;
            overflow: hidden;
        }
        th, td { 
            padding: 14px 12px; 
            text-align: left; 
            font-size: 14px;
        }
        th { 
            background: #1d3557; 
            color: #fff; 
        }
        tr:nth-child(even) { background-color: #f1f6fa; }
        tr:hover { background-color: #e9f2fb; }

        /* Status badges */
        .status-badge {
            padding: 6px 12px;
            border-radius: 20px;
            color: #fff;
            font-weight: bold;
            font-size: 12px;
        }
        .status-pending { background: #f4a261; }
        .status-inprogress { background: #457b9d; }
        .status-completed { background: #2a9d8f; }
        .status-delayed { background: #e63946; }

        /* Footer link */
        .footer { 
            text-align: center; 
            margin-top: 25px; 
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Admin Task Management</h1>
        
        <!-- Add Task Form -->
        <h2>Add New Task</h2>
        <form action="${pageContext.request.contextPath}/admin/tasks/add" method="post">
            <div class="form-group">
                <label>Title:</label>
                <input type="text" name="title" required>
            </div>
            <div class="form-group">
                <label>Description:</label>
                <textarea name="description" required></textarea>
            </div>
            <div class="form-group">
                <label>Status:</label>
                <select name="status" required>
                    <option value="PENDING">Pending</option>
                    <option value="IN_PROGRESS">In Progress</option>
                    <option value="COMPLETED">Completed</option>
                    <option value="DELAYED">Delayed</option>
                </select>
            </div>
            <div class="form-group">
                <label>Start Date:</label>
                <input type="date" name="startDate" required>
            </div>
            <div class="form-group">
                <label>Due Date:</label>
                <input type="date" name="dueDate" required>
            </div>
            <div class="form-group">
                <label>Assign To:</label>
                <select name="assignedTo" required>
                    <c:forEach var="user" items="${users}">
                        <option value="${user.id}">${user.name} (${user.email})</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn">Add Task</button>
        </form>
        
        <!-- Task List -->
        <h2>All Tasks</h2>
        <table>
            <tr>
                <th>Title</th>
                <th>Description</th>
                <th>Status</th>
                <th>Start Date</th>
                <th>Due Date</th>
                <th>Assigned To</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="task" items="${tasks}">
                <tr>
                    <td style="font-weight:600; color:#1d3557;">${task.title}</td>
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
                    
                    <td>${task.assignedTo.name}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/tasks/updateStatus?taskId=${task.id}&status=COMPLETED" class="btn">Complete</a>
                        <a href="${pageContext.request.contextPath}/admin/tasks/delete?taskId=${task.id}" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <div class="footer">
            <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
