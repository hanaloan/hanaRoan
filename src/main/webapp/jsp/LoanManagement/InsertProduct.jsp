<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>상품 추가</title>
</head>
<body>
<h2>Enter Add Employee:</h2>


<form action="/InsertProduct" method="post">
    <label for="productName">상품명:</label>
    <input type="text" id="productName" name="productName" required>
    <br>
    <label for="productType">상품구분:</label>
    <select name="productName" id="productType">
        <option value="전세대출">전세대출</option>
        <option value="원세대출">원세대출</option>
        <option value="담보대출">담보대출</option>
    </select>
    <br>
    <label for="productInfo">상품 설명:</label>
    <input type="text" id="productInfo" name="productInfo" required>
    <br>
    <label for="interestRate">이율:</label>
    <input type="text" id="interestRate" name="interestRate" required>
    <br>
    <label for="overdueInterestRate">연체이율:</label>
    <input type="text" id="overdueInterestRate" name="overdueInterestRate" required>
    <br>
    <label for="limit">최대한도:</label>
    <input type="text" id="limit" name="limit" required>
    <br>
    <label for="period">대출기한:</label>
    <input type="text" id="period" name="period" required>
    <br>
    <label for="minCredit">최소신용점수:</label>
    <input type="text" id="minCredit" name="minCredit" required>

    <br>
    <input type="submit" value="Submit" />

</form>
</body>
</html>
