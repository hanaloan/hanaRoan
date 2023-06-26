<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Item Details</title>
</head>
<body>
<jsp:include page="../CustomerGNB/CustomerGNB.jsp"></jsp:include>
<h2>${product.name}</h2>
<p>${product.description}</p>
<p>최소신용점수: ${product.minCredit}</p>
<p>대출최대한도: ${product.lendLimit}</p>
<p>대출최대기간: ${product.loanPeriod}</p>
<p>이자율: ${product.interestRate}</p>

<button type="button" name="applyProductRes" onclick="applyConfirm('${product.name}', ${product.id})">대출신청</button>
</body>
<script>
    function applyConfirm(productName, productId) {
        var confirmMsg = productName + " 상품을 신청하시겠습니까?";
        var userChoice = confirm(confirmMsg);
        if (userChoice) {
            window.location.href = "applyProduct?productId=" + productId;
        } else {
            return false;
        }
    }
</script>
</html>
