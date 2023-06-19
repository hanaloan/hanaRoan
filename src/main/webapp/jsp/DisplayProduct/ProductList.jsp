<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.Model.Product" %>
<%@ page import="com.DAO.ProductDao" %>
<%@ page import="com.DAO.ProductDao" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Items List</title>
</head>
<body>
<h1>Items List</h1>

<%
  ProductDao productDao = new ProductDao();
  List<Product> products = productDao.getItems();
  for (Product product : products) {
%>
<h2><a href="${pageContext.request.contextPath}/ProductDetail?id=<%= product.getId() %>"><%= product.getName() %></a></h2>
<p><%= product.getDescription() %></p>
<% } %>
</body>
</html>
