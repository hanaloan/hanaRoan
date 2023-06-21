<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Item Details</title>
</head>
<body>
<h2>${product.name}</h2>
<p>${product.description}</p>
<p>최소신용점수: ${product.minCredit}</p>
<p>대출최대한도: ${product.lendLimit}</p>
<p>대출최대기간: ${product.loanPeriod}</p>
<p>이자율: ${product.interestRate}</p>
<p>상품출시일: ${product.startDate}</p>
<p>상품마감일: ${product.endDate}</p>

<button>대출신청</button>
</body>
</html>
