<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Home</title>
</head>
<body>
<h1>Welcome, <%= request.getAttribute("username") %>!</h1>
<p>Income: <%= request.getAttribute("income") %></p>
<p>Credit: <%= request.getAttribute("credit") %></p>
<p>Customer Idx: <%= request.getAttribute("customer_idx") %></p>
</body>
</html>