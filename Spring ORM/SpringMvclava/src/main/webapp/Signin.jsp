<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Signin</title>
</head>
<body>
    <div align="center">
        <h2>${msg}</h2>

        <form:form method="POST" modelAttribute="user">
            <table>
                <tr>
                    <td>User name:</td>
                    <td><form:input path="name" /></td>
                </tr>
                <tr>
                    <td>User email:</td>
                    <td><form:input path="email" /></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><form:password path="password" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Submit" />
                    </td>
                </tr>
            </table>
        </form:form>

        <div style="color: red">${error}</div>
    </div>
</body>
</html>
