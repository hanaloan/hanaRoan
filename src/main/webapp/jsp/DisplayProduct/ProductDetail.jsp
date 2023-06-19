<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.Model.Product" %>
<%@ page import="com.DAO.ProductDao" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Item Details</title>
</head>
<body>
<%
    int itemId = Integer.parseInt(request.getParameter("id"));
    ProductDao productDao = new ProductDao();
    Product product = productDao.getItemById(itemId);
%>

<h2><%= product.getName() %></h2>
<p><%= product.getDescription() %></p>
<p>최소신용점수: <%= product.getMinCredit() %></p>
<p>대출최대한도: <%= product.getLendLimit() %></p>
<p>대출최대기간: <%= product.getLoanPeriod() %></p>
<p>이자율: <%= product.getInterestRate() %></p>
<p>상품출시일: <%= product.getStartDate() %></p>
<p>상품마감일: <%= product.getEndDate() %></p>

<button>대출신청</button>
</body>
</html>