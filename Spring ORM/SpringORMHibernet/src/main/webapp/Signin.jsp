<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="POST" action="user" modelAttribute="user" name="submitForm">

<div align="center">
            <table>
                <tr>
                    <td>User name</td>
                    <td><input type="text" name="name" value="${user.name} }" /></td>
                </tr>
                <tr>
                    <td>User email</td>
                    <td><input type="text" name="email" value="${user.email}" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" value="${user.password}" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Submit" /></td>
                </tr>
            </table>
            <div style="color: red">${error}</div>

        </div>
</form>
</body>
</html>