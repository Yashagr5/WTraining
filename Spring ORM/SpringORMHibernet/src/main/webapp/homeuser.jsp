<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2>All Users</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Password</th>
        </tr>
        <c:forEach var="u" items="${userlist}">
            <tr>
                <td>${u.name}</td>
                <td>${u.email}</td>
                <td>${u.password}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>