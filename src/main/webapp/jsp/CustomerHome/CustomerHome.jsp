<%@ page import="com.Model.LoginLoanProduct" %>
<%@ page import="com.Model.LoginRecommendationRes" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Home</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <script>
        var counter = 10 * 60;
        var interval = setInterval(function() {
            counter--;
            if (counter <= 0) {
                clearInterval(interval);
                $('#time').text("Session expired");
                return;
            } else {
                var minutes = Math.floor(counter / 60);
                var seconds = counter % 60;
                $('#time').text(minutes + ":" + seconds);
            }
        }, 1000);

        function extendSession() {
            $.ajax({
                url: '${pageContext.request.contextPath}/extendSession',
                type: 'POST',
                success: function() {
                    counter = 10 * 60;
                },
                error: function() {
                    console.log("Error extending the session");
                }
            });
        }
    </script>
</head>
<%@ include file="/jsp/CustomerGNB/CustomerGNB.jsp" %>
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

<%--세션 만료 관련--%>
<div id="top-right">
    <p id="time">10:00</p>
    <button onclick="extendSession()">Extend session</button>
</div>
</body>

</html>