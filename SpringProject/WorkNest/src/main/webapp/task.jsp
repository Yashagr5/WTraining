<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Tasks</title>
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
        
        table { 
            width: 100%; 
            border-collapse: collapse; 
        }
        
        th, td { 
            padding: 10px; 
            border: 1px solid #ddd; 
            text-align: center; 
        }
        
        th { 
            background: #2193b0; 
            color: #fff; 
        }
        
        button { 
            margin-top: 15px; 
            width: 100%; 
            padding: 12px; 
            background: #2193b0; 
            border: none; 
            color: #fff; 
            border-radius: 6px;
            cursor: pointer; 
            font-size: 16px;
        }
        
        button:hover { 
            background: #176d7a; 
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Tasks</h1>
        <table>
            <tr><th>Task</th><th>Status</th><th>Due Date</th></tr>
            <tr><td>Sample Task</td><td>In Progress</td><td>2025-09-01</td></tr>
        </table>
        <button>Add Task</button>
    </div>
</body>
</html>
