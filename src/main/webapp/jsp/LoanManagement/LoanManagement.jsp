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


    // function deleteProduct(productId) {
    //     if (confirm("Are you sure you want to delete this product?")) {
    //         $.ajax({
    //             url: '/LoanManagement?productId=' + productId,
    //             type: 'DELETE',
    //             success: function () {
    //                 // location.reload();
    //                 // window.location.href = '/LoanManagement';
    //             }
    //         });
    //     }
    // }
</script>


<%@ include file="/jsp/AdminSidebar/AdminSidebar.jsp" %>
<div id="navBar">
    <div id="Option">
        <button type="button"><a href="LoanManagement">전체</a></button>
        <button type="button"><a href="LoanManagement?option1=전세대출">전세</a></button>
        <button type="button"><a href="LoanManagement?option1=월세대출">월세</a></button>
        <button type="button"><a href="LoanManagement?option1=담보대출">담보</a></button>
    </div>

    <button id="insertProductBtn" onclick="location.href='/jsp/LoanManagement/InsertProduct.jsp'">
        추가
    </button>

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
        <th>삭제 작업</th>


    </tr>
    </thead>
    <tbody>

    <%
        request.setCharacterEncoding("UTF-8");
        List<Product> products = (List<Product>) request.getAttribute("loanProductsDto");
        if (products != null && !products.isEmpty()) {
            for (Product product : products) {
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
        <td>
            <form action="/LoanManagement" method="POST">
                <input type="hidden" name="productId" value="<%= product.getId() %>">
                <button type="submit" onclick="return confirm('Are you sure you want to delete this product?')">삭제</button>
            </form>        </td>


    </tr>
    <%
            }
        }else{


            %>
    <tr>
        <td colspan="9">No loan products available.</td>
    </tr>

    <% }%>


    </tbody>
</table>



</body>
</html>
