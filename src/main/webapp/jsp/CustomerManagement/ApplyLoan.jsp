<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>상품 등록하는 페이지</title>
    <script>
        window.onload = function() {
            var today = new Date();
            var startDateInput = document.getElementById("start_date");
            startDateInput.value = formatDate(today);
        }

        function formatDate(date) {
            var year = date.getFullYear();
            var month = String(date.getMonth() + 1).padStart(2, "0");
            var day = String(date.getDate()).padStart(2, "0");
            return year + "-" + month + "-" + day;
        }
    </script>
</head>
<body>
<h1>!!! 고객의 대출상품을 등록하는 페이지 입니다 !!!</h1>
<form action="/ApplyLoan" method="post">
    <label for="customer_idx">Customer ID:</label>
    <input type="text" id="customer_idx" name="customer_idx" required><br>

    <label for="loan_idx">Loan ID:</label>
    <input type="text" id="loan_idx" name="loan_idx" required><br>

    <label for="start_date">Start Date:</label>
    <input type="date" id="start_date" name="start_date" required><br>

    <label for="end_date">End Date:</label>
    <input type="date" id="end_date" name="end_date" required><br>

    <label for="loan_amount">Loan Amount:</label>
    <input type="text" id="loan_amount" name="loan_amount" required><br>

    <label for="loan_status">Loan Status:</label>
    <select id="loan_status" name="loan_status" required>
        <option value="pending" selected>Pending</option>
    </select><br>

    <input type="submit" value="Apply Loan">
</form>

</body>
</html>
