<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-5">
    <h2 class="text-center">Register</h2>
    <form action="register" method="post" class="col-md-4 offset-md-4">
        <div class="mb-3">
            <label>Username</label>
            <input type="text" name="username" class="form-control" required/>
        </div>
        <div class="mb-3">
            <label>Email</label>
            <input type="text" name="email" class="form-control" required/>
        </div>
        <div class="mb-3">
            <label>Password</label>
            <input type="password" name="password" class="form-control" required/>
        </div>
        <div class="mb-3">
            <label>Role</label>
            <select name="role" class="form-control">
                <option value="USER">User</option>
                <option value="ADMIN">Admin</option>
            </select>
        </div>
        <button type="submit" class="btn btn-success w-100">Register</button>
        <p class="mt-3 text-center">
            <b>If Already Login  </b><a href="Login.jsp"> Login Here</a>
        </p>
    </form>

</body>
</html>