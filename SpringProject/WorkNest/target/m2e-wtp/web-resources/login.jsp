<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #2193b0, #6dd5ed);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            background: #fff;
            padding: 40px;
            border-radius: 10px;
            width: 400px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.2);
            text-align: center;
        }
        h1 { 
            margin-bottom: 20px; 
            color: #333; 
        }
        input {
            width: 100%; 
            padding: 12px; 
            margin: 10px 0; 
            border: 1px solid #ddd; 
            border-radius: 6px; 
        }
        button { 
            width: 100%; 
            padding: 12px; 
            background: #2193b0; 
            border: none; 
            color: #fff; 
            font-size: 16px; 
            border-radius: 6px; 
            cursor: pointer; 
        }
        button:hover { 
            background: #176d7a; 
        }
        a { 
            color: #2193b0; 
            text-decoration: none; 
        }
        a:hover { 
            text-decoration: underline; 
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>LOGIN</h1>
        <form action="login" method="post">
            <input type="text" name="username" placeholder="Username or Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit">LOGIN</button>
        </form>
        <p>New member? <a href="register">Sign up now</a></p>
    </div>
</body>
</html>
