<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
<style>
    body {
        background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }
    .login-card {
        background: #fff;
        border-radius: 15px;
        box-shadow: 0 8px 20px rgba(0,0,0,0.2);
        padding: 30px;
        width: 400px;
        max-width: 90%;
        animation: fadeIn 0.8s ease-in-out;
    }
    .login-card h2 {
        font-weight: bold;
        color: #333;
    }
    .form-control {
        border-radius: 10px;
    }
    .btn-primary {
        border-radius: 10px;
        background: #2575fc;
        border: none;
    }
    .btn-primary:hover {
        background: #1a5ed8;
    }
    .register-link {
        font-size: 0.9rem;
    }
    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(-20px); }
        to { opacity: 1; transform: translateY(0); }
    }
</style>
</head>
<body>
    <div class="login-card">
        <h2 class="text-center mb-4">Login</h2>
        <form action="login" method="post">
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="text" name="email" class="form-control" placeholder="Enter your email" required/>
            </div>
            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password" name="password" class="form-control" placeholder="Enter your password" required/>
            </div>
            <button type="submit" class="btn btn-primary w-100">Login</button>
            <p class="text-center mt-3 register-link">
                New User? <a href="Register.jsp">Register Here</a>
            </p>
        </form>
    </div>
</body>
</html>
