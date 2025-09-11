<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to WebNestApp</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
    <style>
        body {
            background: linear-gradient(to right, #2b5876, #4e4376);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .welcome-container {
            background: #ffffff;
            border-radius: 14px;
            padding: 50px;
            box-shadow: 0 6px 25px rgba(0,0,0,0.2);
            text-align: center;
        }
        .welcome-container h1 {
            color: #4e4376;
            margin-bottom: 20px;
        }
        .welcome-container p {
            font-size: 1.1rem;
            color: #555;
            margin-bottom: 30px;
        }
        .btn-custom {
            background: #4e4376;
            color: #fff;
            border-radius: 8px;
            padding: 10px 25px;
            transition: background 0.3s;
        }
        .btn-custom:hover {
            background: #2b5876;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="welcome-container">
        <h1>Welcome to WebNestApp</h1>
        <p>Your project management solution. Login or register to get started.</p>
        <a href="${pageContext.request.contextPath}/Login.jsp" class="btn btn-custom me-2">Login</a>
        <a href="${pageContext.request.contextPath}/Register.jsp" class="btn btn-custom">Register</a>
    </div>
</body>
</html>
