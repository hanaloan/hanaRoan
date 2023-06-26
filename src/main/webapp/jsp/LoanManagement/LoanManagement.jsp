
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

</body>
</html>
