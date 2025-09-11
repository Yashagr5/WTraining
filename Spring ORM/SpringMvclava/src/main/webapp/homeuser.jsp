<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>
    <h2>User List:</h2>
    <ul>
        <c:forEach var="user" items="${userlist}">
            <li>Name: ${user.name}, Email: ${user.email}</li>
        </c:forEach>
    </ul>
</body>
</html>
