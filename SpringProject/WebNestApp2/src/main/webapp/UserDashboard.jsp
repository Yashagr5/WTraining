<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Dashboard</title>
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
        .dashboard-header {
            background: #4e4376;
            color: #fff;
            border-radius: 12px;
            padding: 20px;
            margin-bottom: 20px;
        }
        .dashboard-header h2 {
            margin: 0;
        }
        .stat-card {
            border-radius: 12px;
            padding: 20px;
            text-align: center;
            color: #fff;
            transition: transform 0.2s;
        }
        .stat-card:hover {
            transform: translateY(-5px);
        }
        .stat-card h5 {
            font-weight: 500;
        }
        .bg-pending {
            background: #f39c12;
        }
        .bg-progress {
            background: #3498db;
        }
        .bg-completed {
            background: #2ecc71;
        }
        .bg-total {
            background: #9b59b6;
        }
        .status-badge {
            font-size: 0.8rem;
            padding: 6px 12px;
            border-radius: 8px;
        }
        .table thead {
            background: #4e4376;
            color: #fff;
        }
        .table-hover tbody tr:hover {
            background: rgba(78,67,118,0.08);
        }
        .action-container {
            display: flex;
            gap: 6px;
            flex-wrap: nowrap;
            align-items: center;
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
        <!-- Dashboard Header -->
        <div class="dashboard-header d-flex justify-content-between align-items-center">
            <h2>User Dashboard</h2>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-light btn-sm">Logout</a>
        </div>

        <p class="text-muted">Welcome back, <strong>${user.name}</strong></p>

        <!-- Statistics -->
        <div class="row g-3 mb-4">
            <div class="col-md-3">
                <div class="stat-card bg-total">
                    <h5>Total Tasks</h5>
                    <div class="stat-number fs-4 fw-bold">${tasks != null ? tasks.size() : 0}</div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="stat-card bg-pending">
                    <h5>Pending</h5>
                    <div class="stat-number fs-4 fw-bold">
                        <c:set var="pendingCount" value="0" />
                        <c:forEach var="task" items="${tasks}">
                            <c:if test="${task.status == 'PENDING'}">
                                <c:set var="pendingCount" value="${pendingCount + 1}" />
                            </c:if>
                        </c:forEach>
                        ${pendingCount}
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="stat-card bg-progress">
                    <h5>In Progress</h5>
                    <div class="stat-number fs-4 fw-bold">
                        <c:set var="progressCount" value="0" />
                        <c:forEach var="task" items="${tasks}">
                            <c:if test="${task.status == 'IN_PROGRESS'}">
                                <c:set var="progressCount" value="${progressCount + 1}" />
                            </c:if>
                        </c:forEach>
                        ${progressCount}
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="stat-card bg-completed">
                    <h5>Completed</h5>
                    <div class="stat-number fs-4 fw-bold">
                        <c:set var="completedCount" value="0" />
                        <c:forEach var="task" items="${tasks}">
                            <c:if test="${task.status == 'COMPLETED'}">
                                <c:set var="completedCount" value="${completedCount + 1}" />
                            </c:if>
                        </c:forEach>
                        ${completedCount}
                    </div>
                </div>
            </div>
        </div>

        <!-- Tasks Table -->
        <h4 class="mb-3">Your Tasks</h4>
        <c:choose>
            <c:when test="${not empty tasks}">
                <div class="table-responsive">
                    <table class="table table-hover align-middle">
                        <thead>
                            <tr>
                                <th>Title</th>
                                <th>Description</th>
                                <th>Status</th>
                                <th>Start Date</th>
                                <th>Due Date</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="task" items="${tasks}">
                                <tr>
                                    <td>${task.title}</td>
                                    <td>${task.description}</td>
                                    <td>
                                        <span class="status-badge 
                                            <c:choose>
                                                <c:when test="${task.status == 'COMPLETED'}">bg-success text-white</c:when>
                                                <c:when test="${task.status == 'IN_PROGRESS'}">bg-info text-dark</c:when>
                                                <c:when test="${task.status == 'PENDING'}">bg-warning text-dark</c:when>
                                                <c:otherwise>bg-danger text-white</c:otherwise>
                                            </c:choose>">
                                            ${task.status}
                                        </span>
                                    </td>
                                    <td>${task.startDate}</td>
                                    <td>${task.dueDate}</td>
                                    <td>
                                        <div class="action-container">
                                            <!-- Status Dropdown -->
                                            <select class="form-select form-select-sm" onchange="updateTaskStatus(${task.id}, this.value)">
                                                <option value="PENDING" ${task.status == 'PENDING' ? 'selected' : ''}>Pending</option>
                                                <option value="IN_PROGRESS" ${task.status == 'IN_PROGRESS' ? 'selected' : ''}>In Progress</option>
                                                <option value="COMPLETED" ${task.status == 'COMPLETED' ? 'selected' : ''}>Completed</option>
                                                <option value="DELAYED" ${task.status == 'DELAYED' ? 'selected' : ''}>Delayed</option>
                                            </select>
                                            <!-- Comments Button -->
                                            <a href="${pageContext.request.contextPath}/user/tasks/comments?taskId=${task.id}" 
                                               class="btn btn-sm btn-outline-secondary">💬 Comments</a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <p class="text-center text-muted fst-italic">No tasks assigned yet. Please check back later.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
