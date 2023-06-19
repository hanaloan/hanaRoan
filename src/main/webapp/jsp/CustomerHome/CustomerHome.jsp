<%@ page import="com.Model.LoginLoanProduct" %>
<%@ page import="com.Model.LoginRecommendationRes" %>
<%@ page import="java.util.List" %>
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

<%-- JSP에서 추천 상품 정보를 불러오기 --%>
<%
    List<LoginLoanProduct> recommendedProducts = ((LoginRecommendationRes)request.getAttribute("recoRes")).getRecommendedProducts();
    for (LoginLoanProduct product : recommendedProducts) {
%>
<p>Loan Image: <%= product.getLoanImage() %></p>
<p>Loan Name: <%= product.getLoanName() %></p>
<p>Loan Interest Rate: <%= product.getLoanInterestRate() %></p>
<%
    }
%>
</body>
</html>