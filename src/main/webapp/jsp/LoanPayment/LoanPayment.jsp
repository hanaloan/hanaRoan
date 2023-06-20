<%@ page import="com.Model.Payment" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Data Display</title>
</head>
<body>
<h1>상환관리</h1>
<form action="data" method="GET">
    <button type="button" name="option1" value="1">전체</button>
    <button type="button" name="option1" value="2">전세</button>
    <button type="button" name="option1" value="3">월세</button>
    <button type="button" name="option1" value="4">담보</button>
</form>

<form action="data" method="GET">
    <button type="button" name="option2" value="1">전체</button>
    <button type="button" name="option2" value="2">연체</button>
    <button type="button" name="option2" value="3">상환중</button>
    <button type="button" name="option2" value="4">상환완료</button>
</form>
<table>
    <thead>
    <tr>
        <th>고객명</th>
        <th>상품구분</th>
        <th>상품명</th>
        <th>대출신청일</th>
        <th>마감기한</th>
        <th>잔액</th>
        <th>지난날짜</th>
    </tr>
    </thead>
    <tbody>
        <% for (Payment payment : (List<Payment>) request.getAttribute("paymentList")) { %>
        <tr>
            <td><%= payment.getCustomerName() %></td>
            <td><%= payment.getProductType() %></td>
            <td><%= payment.getProductName() %></td>
            <td><%= payment.getLendStartDate() %></td>
            <td><%= payment.getPaymentDueDate() %></td>
            <td><%= payment.getDueBalance() %></td>
            <td><%= payment.getPassedDate() %></td>
        </tr>
        <% } %>
    </tbody>
</table>
</body>
</html>
