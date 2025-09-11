<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Tasks | WebNest App</title>
    <style>
        body { 
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; 
            background: linear-gradient(135deg, #6a11cb, #2575fc);
            height: 100vh; 
            display: flex; 
            justify-content: center; 
            align-items: center; 
            color: #333;
        }
        .container { 
            background: #fff; 
            padding: 35px; 
            border-radius: 12px; 
            width: 750px; 
            box-shadow: 0 8px 20px rgba(0,0,0,0.25); 
            animation: fadeIn 0.8s ease-in-out;
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }
        h1 { 
            text-align: center; 
            margin-bottom: 25px; 
            color: #2575fc; 
            font-size: 32px;
        }
        table { 
            width: 100%; 
            border-collapse: collapse; 
            border-radius: 10px; 
            overflow: hidden; 
        }
        th, td { 
            padding: 14px; 
            text-align: center; 
        }
        th { 
            background: #2575fc; 
            color: #fff; 
            font-size: 16px;
        }
        tr:nth-child(even) { background: #f9f9f9; }
        tr:hover { background: #f1f7ff; transition: 0.3s; }
        td { color: #555; }
        .btn {
            display: block;
            margin: 25px auto 0;
            width: 200px;
            padding: 14px;
            font-size: 16px;
            background: linear-gradient(to right, #6a11cb, #2575fc);
            border: none;
            color: #fff;
            border-radius: 8px;
            cursor: pointer; 
            transition: all 0.3s ease;
            font-weight: bold;
        }
        .btn:hover { 
            background: linear-gradient(to right, #530e9b, #1b5edb); 
            transform: translateY(-2px); 
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>My Tasks</h1>
        <table>
            <tr>
                <th>Task</th>
                <th>Status</th>
                <th>Due Date</th>
            </tr>
            <tr>
                <td>Sample Task</td>
                <td><span style="color:#ff9800; font-weight:bold;">In Progress</span></td>
                <td>2025-09-01</td>
            </tr>
        </table>
        <button class="btn">Add Task</button>
    </div>
</body>
</html>
