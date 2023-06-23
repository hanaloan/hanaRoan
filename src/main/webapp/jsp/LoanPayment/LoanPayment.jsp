<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Data Display</title>
    <style>
        /* CSS styling for the navigation bar */
        #navBar {
            background-color: #f2f2f2;
            padding: 10px;
            text-align: center;
        }

        #navBar button {
            margin: 5px;
        }

        #navBar a {
            color: black;
            text-decoration: none;
        }

        /* CSS styling for the table */
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
</head>
<body>
<div id="navBar">
    <div id="primaryOption">
        <button type="button"><a href="loanPayment">전체</a></button>
        <button type="button"><a href="loanPayment?option1=전세">전세</a></button>
        <button type="button"><a href="loanPayment?option1=월세">월세</a></button>
        <button type="button"><a href="loanPayment?option1=담보">담보</a></button>
    </div>
    <div id="subOption">
        <button type="button"><a href="loanPayment?option1=${param.option1}&option2=전체">전체</a></button>
        <button type="button"><a href="loanPayment?option1=${param.option1}&option2=상환중">상환중</a></button>
        <button type="button"><a href="loanPayment?option1=${param.option1}&option2=연체">연체</a></button>
        <button type="button"><a href="loanPayment?option1=${param.option1}&option2=상환완료">상환완료</a></button>
    </div>
</div>

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
        <th>상환상태</th>
        <th>잔액관리</th>
        <th>연체관리</th>
    </tr>
    </thead>
    <tbody>
    <% String authType = request.getAttribute("authType").toString(); %>
    <% List<?> paymentList = (List<?>) request.getAttribute("paymentList");
        for (Object payment : paymentList) {
            String customerName = (String) payment.getClass().getMethod("getCustomerName").invoke(payment);
            String productType = (String) payment.getClass().getMethod("getProductType").invoke(payment);
            String productName = (String) payment.getClass().getMethod("getProductName").invoke(payment);
            Date lendStartDate = (Date) payment.getClass().getMethod("getLendStartDate").invoke(payment);
            Date paymentDueDate = (Date) payment.getClass().getMethod("getPaymentDueDate").invoke(payment);
            BigDecimal dueBalance = (BigDecimal) payment.getClass().getMethod("getDueBalance").invoke(payment);
            int passedDate = Integer.parseInt(payment.getClass().getMethod("getPassedDate").invoke(payment).toString());
            String paymentStatus = (String) payment.getClass().getMethod("getPaymentStatus").invoke(payment);
            int paymentId = (int) payment.getClass().getMethod("getPaymentId").invoke(payment);
    %>
    <tr>
        <td><%= customerName %></td>
        <td><%= productType %></td>
        <td><%= productName %></td>
        <td><%= lendStartDate %></td>
        <td><%= paymentDueDate %></td>
        <td><%= dueBalance %></td>
        <td>
            <% if(!paymentStatus.equals("상환완료")) { %>
                <% if(passedDate < 0){ %>
                    0
                <% } else { %>
                    <%= passedDate %>
                <% } %>
            <% } else { %>
                -
            <% } %>
        </td>
        <td><%= paymentStatus %></td>
        <td>
            <form action="loanPayment" method="post" accept-charset="UTF-8">
                <input type="hidden" class="payment-id" name="paymentId" value="<%= paymentId %>">
                <input type="hidden" class="payment-status" name="paymentStatus" value="<%= paymentStatus %>">
                <button type="submit" name="deductBalance" <% if (paymentStatus.equals("상환완료")) { %>disabled<% } %>
                        onclick="return confirmDeductBalance('<%= authType %>', '<%= passedDate%>');">
                    잔액차감
                </button>
            </form>
        </td>
        <td>
            <form action="loanPayment" method="post" accept-charset="UTF-8">
                <input type="hidden" class="payment-id" name="paymentId" value="<%= paymentId %>">
                <input type="hidden" class="passed-date" name="passedDate" value="<%= passedDate %>">
                <button type="submit" name="handleOverdue" <% if (paymentStatus.equals("상환완료")) { %>disabled<% } %>
                        onclick="return confirmHandleOverdue('<%= authType %>', '<%= paymentStatus %>', '<%= passedDate %>');">
                    연체관리
                </button>
            </form>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
<script src="../../js/LoanPayment/LoanPayment.js"></script>
</body>
</html>
