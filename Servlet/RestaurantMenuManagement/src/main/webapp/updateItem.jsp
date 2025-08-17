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
<%@ include file="header.jsp" %>

<%
    int index = Integer.parseInt(request.getParameter("index"));
    List<menuItem> menu = (List<menuItem>) application.getAttribute("menu");
    menuItem item = menu.get(index);

    if ("POST".equalsIgnoreCase(request.getMethod())) {
        item.setName(request.getParameter("name"));
        item.setDescription(request.getParameter("description"));
        item.setCategory(request.getParameter("category"));
        item.setPrice(Double.parseDouble(request.getParameter("price")));
        item.setAvailability(request.getParameter("availability"));

        response.sendRedirect("viewMenu.jsp");
        return;
    }
%>

<h3>Update Menu Item</h3>
<form method="post">
    Name: <input type="text" name="name" value="<%= item.getName() %>" required><br><br>
    Description: <input type="text" name="description" value="<%= item.getDescription() %>" required><br><br>
    Category: <input type="text" name="category" value="<%= item.getCategory() %>" required><br><br>
    Price: <input type="number" step="0.01" name="price" value="<%= item.getPrice() %>" required><br><br>
    Availability: 
    <select name="availability">
        <option value="Available" <%= item.getAvailability().equals("Available") ? "selected" : "" %>>Available</option>
        <option value="Unavailable" <%= item.getAvailability().equals("Unavailable") ? "selected" : "" %>>Unavailable</option>
    </select><br><br>
    <input type="submit" value="Update">
</form>
<a href="viewMenu.jsp">Back</a>
<%@ include file="footer.jsp" %>

</body>
</html>