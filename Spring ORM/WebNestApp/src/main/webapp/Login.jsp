<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-5">
    <h2 class="text-center">Login</h2>
    <form action="login" method="post" class="col-md-4 offset-md-4">
        <div class="mb-3">
            <label>Email</label>
            <input type="text" name="email" class="form-control" required/>
        </div>
        <div class="mb-3">
            <label>Password</label>
            <input type="password" name="password" class="form-control" required/>
        </div>
        <button type="submit" class="btn btn-primary w-100">Login</button>
        <p class="mt-3 text-center">
            <b>New User? </b><a href="Register.jsp"> Register Here</a>
        </p>
    </form>
</body>
</html>
