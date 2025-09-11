<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
<style>
    body {
        background: linear-gradient(135deg, #ff512f 0%, #dd2476 100%);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }
    .register-card {
        background: #fff;
        border-radius: 15px;
        box-shadow: 0 8px 20px rgba(0,0,0,0.2);
        padding: 30px;
        width: 450px;
        max-width: 95%;
        animation: fadeIn 0.8s ease-in-out;
    }
    .register-card h2 {
        font-weight: bold;
        color: #333;
    }
    .form-control, .form-select {
        border-radius: 10px;
    }
    .btn-success {
        border-radius: 10px;
        background: #28a745;
        border: none;
    }
    .btn-success:hover {
        background: #218838;
    }
    .login-link {
        font-size: 0.9rem;
    }
    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(-20px); }
        to { opacity: 1; transform: translateY(0); }
    }
</style>
</head>
<body>
    <div class="register-card">
        <h2 class="text-center mb-4">Create Account</h2>
        <form action="register" method="post">
            <div class="mb-3">
                <label class="form-label">Username</label>
                <input type="text" name="username" class="form-control" placeholder="Enter your username" required/>
            </div>
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" name="email" class="form-control" placeholder="Enter your email" required/>
            </div>
            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password" name="password" class="form-control" placeholder="Enter your password" required/>
            </div>
            <div class="mb-3">
                <label class="form-label">Role</label>
                <select name="role" class="form-select">
                <option value="USER">Select option</option>
                    <option value="USER">User</option>
                    <option value="ADMIN">Admin</option>
                </select>
            </div>
            <button type="submit" class="btn btn-success w-100">Register</button>
            <p class="text-center mt-3 login-link">
                Already have an account? <a href="Login.jsp">Login Here</a>
            </p>
        </form>
    </div>
</body>
</html>
