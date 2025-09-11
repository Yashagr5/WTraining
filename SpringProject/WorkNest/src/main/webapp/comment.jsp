<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Comments</title>
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
	        width: 700px; 
	        box-shadow: 0px 4px 10px rgba(0,0,0,0.2); 
        }
        h1 { 
            text-align: center; 
            margin-bottom: 20px; 
            color: #333; 
        }
        
        textarea { 
	        width: 100%; 
	        height: 100px; 
	        padding: 12px; 
	        border: 1px solid #ddd; 
	        border-radius: 6px; 
	        margin-bottom: 10px; 
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
    </style>
</head>
<body>
    <div class="container">
        <h1>Comments</h1>
        <form action="comment" method="post">
            <textarea placeholder="Write your comment..." name="comment"></textarea>
            <button type="submit">Post Comment</button>
        </form>
    </div>
</body>
</html>
