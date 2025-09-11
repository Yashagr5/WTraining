<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Comments</title>
    <style>
        body { 
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #1e3c72, #2a5298); 
            height: 100vh;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            background: #fff;
            padding: 35px 40px;
            border-radius: 16px;
            width: 720px;
            box-shadow: 0px 6px 18px rgba(0,0,0,0.25);
            animation: fadeIn 0.6s ease-in-out;
        }
        h1 {
            text-align: center;
            margin-bottom: 25px;
            font-size: 26px;
            color: #1e3c72;
        }
        textarea {
            width: 100%;
            height: 120px;
            padding: 14px;
            border: 1px solid #ccc;
            border-radius: 10px;
            margin-bottom: 15px;
            resize: none;
            font-size: 15px;
            transition: border-color 0.3s ease;
        }
        textarea:focus {
            border-color: #2a5298;
            outline: none;
            box-shadow: 0px 0px 6px rgba(42, 82, 152, 0.4);
        }
        button {
            width: 100%;
            padding: 14px;
            background: linear-gradient(90deg, #1e3c72, #2a5298);
            border: none;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            border-radius: 10px;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        button:hover {
            background: linear-gradient(90deg, #2a5298, #1e3c72);
            transform: scale(1.03);
            box-shadow: 0px 4px 10px rgba(30, 60, 114, 0.4);
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(15px); }
            to { opacity: 1; transform: translateY(0); }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Post a Comment</h1>
        <form action="comment" method="post">
            <textarea name="commentText" placeholder="Write your comment..."></textarea>
            <button type="submit">Post Comment</button>
        </form>
    </div>
</body>
</html>
