<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Item Details</title>
</head>
<body>
<div class="row" id="wrapper">
    <jsp:include page="/jsp/CustomerGNB/CustomerGNB.jsp"></jsp:include>
        <div class="col d-flex justify-content-center align-items-center">
            <div class="card">
                <div class="card-body">
                    <h2>${product.name}</h2>
                    <h5 class="card-title">${product.description}</h5>
                    <p class="card-text">최소신용점수: ${product.minCredit}</p>
                    <p class="card-text">최대대출한도: ${product.lendLimit}원</p>
                    <p class="card-text">최대대출기간: ${product.loanPeriod}년</p>
                    <p>이자율: ${product.interestRate}%</p>
                    <p>연체이자율: ${product.overdueInterestRate}</p>
                    <button type="button" class="btn btn-primary" name="applyProductRes" onclick="applyConfirm('${product.name}', ${product.id})">대출신청</button>
                </div>
            </div>
        </div>
</div>
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
