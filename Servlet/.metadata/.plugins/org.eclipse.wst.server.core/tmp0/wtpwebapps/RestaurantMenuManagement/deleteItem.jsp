<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.util.*, com.menu.menuItem" %>
<%
    int index = Integer.parseInt(request.getParameter("index"));
    List<menuItem> menu = (List<menuItem>) application.getAttribute("menu");
    if (menu != null && index >= 0 && index < menu.size()) {
        menu.remove(index);
    }
    response.sendRedirect("viewMenu.jsp");
%>

</body>
</html>