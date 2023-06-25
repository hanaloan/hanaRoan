<%@ page import="com.Model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>대출 상품 관리</title>
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    window.onload=function (){
        if (window.location.pathname !== '/LoanManagement') {
            location.href = '/LoanManagement';
        }
    }
</script>


<%@ include file="/jsp/AdminSidebar/AdminSidebar.jsp" %>
<div id="navBar">
    <div id="Option">
        <button type="button"><a href="loanManagement">전체</a></button>
        <button type="button"><a href="loanManagement?option1=전세">전세</a></button>
        <button type="button"><a href="loanManagement?option1=월세">월세</a></button>
        <button type="button"><a href="loanManagement?option1=담보">담보</a></button>
    </div>

</div>

<table>
    <thead>
    <tr>
        <th>대출상품번호</th>
        <th>상품구분</th>
        <th>대출상품명</th>
        <th>상품 설명</th>
        <th>이율</th>
        <th>연체이율</th>
        <th>최대한도</th>
        <th>대출기한</th>
        <th>최소신용점수</th>


    </tr>
    </thead>
    <tbody>

    <%
        request.setCharacterEncoding("UTF-8");
        List<Product> products = (List<Product>) request.getAttribute("loanProductsDto");
        if (products != null) {
            for (Object obj : products) {
                if (obj instanceof Product) {
                    Product product = (Product) obj;
    %>
    <tr>
        <td><%= product.getIdx() %></td>
        <td><%= product.getLoanTypeName() %></td>
        <td><%= product.getName() %></td>
        <td><%= product.getDescription() %></td>
        <td><%= product.getInterestRate() %></td>
        <td><%= product.getOverdueInterestRate() %></td>
        <td><%= product.getLendLimit() %></td>
        <td><%= product.getLoanPeriod() %></td>
        <td><%= product.getMinCredit() %></td>


    </tr>
    <%
                }
            }
        }%>
    </tbody>
</table>



</body>
</html>
