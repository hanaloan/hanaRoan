<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>상품 신청서</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    <style>
        .submit-style {
            width: 300px;
            height: 50px;
            margin: 0 auto; /* Center align the button */
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .vertical-divider {
            border-left: 1px solid black;
            height: 380px; /* Adjust the height as needed */
        }

        .form-group.col h5 strong {
            display: inline-block;
            width: 200px; /* Adjust the width as needed */
            height: 30px;
        }

        .inputtext{
            font-weight: bold;
            font-size: 21px;

        }



    </style>
</head>
<body class="bg-gradient-primary">

<div class="container">
    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <%--            <div class="col">--%>
            <%--                <div class="col-lg-7">--%>
            <div class="p-5">
                <div class="text-center">

                    <h1>상품 신청서</h1>
                    <br>
                </div>

                <form action="applyProduct" method="post" accept-charset="UTF-8" class="user">
                    <div class="form-group col">


                <div class="form-group row">

                    <div class="form-group col">
                        <h4>[신청 상품 정보]</h4>
                        <br>
                            <div class=" mb-3 mb-sm-0">
                                <h5><strong>상품명:</strong> ${productInfo.loanName}</h5>
                            </div>
                        <div class=" mb-3 mb-sm-0">
                            <h5><strong>상품설명:</strong></h5>
                            <p>${productInfo.loanDescription}</p>
                        </div>
                        <div class=" mb-3 mb-sm-0">
                            <h5><strong>이자율:</strong> ${productInfo.loanInterestRate}%</h5>
                        </div>
                        <div class=" mb-3 mb-sm-0">
                            <h5><strong>연체이자율:</strong> ${productInfo.overdueInterestRate}%</h5>
                        </div>
                        <div class=" mb-3 mb-sm-0">
                            <h5><strong>최소신용점수:</strong>${productInfo.minCredit}점</h5>
                        </div>
                        <div class=" mb-3 mb-sm-0">
                            <h5><strong>대출한도:</strong>${productInfo.lendLimit}원</h5>
                        </div>
                        <div class=" mb-3 mb-sm-0">
                            <h5><strong>대출기간:</strong>${productInfo.lendPeriod}년</h5>
                        </div>
                    </div>
                    <div class="vertical-divider"></div>
                    <div class="form-group col">
                        <h4>[고객 정보]</h4>
                        <br>
                        <div class=" mb-3 mb-sm-0">
                            <h5><strong>신청인:</strong>${customerInfo.customerName}</h5>
                        </div>
                        <div class=" mb-3 mb-sm-0">
                            <h5> <strong>전화번호:</strong>${customerInfo.contactInfo}</h5>
                        </div>
                        <div class=" mb-3 mb-sm-0">
                            <h5><strong>신용점수:</strong>${customerInfo.creditScore}점</h5>
                        </div>
                    </div>
                </div>
                    </div>

                    <input type="hidden" class="customer-idx" name="customerIdx" value=<%=session.getAttribute("customer_Idx")%>>
                    <input type="hidden" class="loan-idx" name="loanIdx" value='<%=request.getParameter("productId")%>'>
                    <label for="lendAmount" class="inputtext">대출금액: </label>
                    <input class="form-control form-control-user" id="lendAmount" name="lendAmount" type="text">
                    <br>
                    <br>
                    <button id="loanApply" class="btn btn-primary btn-icon-split btn-lg submit-style"
                            onclick="return applyProductConfirm('${customerInfo.customerName}', '${customerInfo.creditScore}',
                                    '${productInfo.loanName}', '${productInfo.lendLimit}', '${productInfo.minCredit}')">대출신청</button>
                </form>
            </div>
        </div>
    </div>
</div>




<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="/js/sb-admin-2.min.js"></script>
<script src="/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>
<script src="/js/demo/datatables-demo.js"></script>
</body>
<script>
    function applyProductConfirm(customerName, customerCredit, productName, lendLimit, minCredit){
        var confirmMsg = customerName + " 고객님의 정보로 " + productName + " 상품을 신청하시겠습니까?";
        var userChoice = confirm(confirmMsg);
        var applyAmount = parseFloat(document.getElementById("lendAmount").value);
        var numericLendLimit = parseFloat(lendLimit);

        if(applyAmount > numericLendLimit){
            alert("신청하시려는 상품의 최대 대출 한도는 " + lendLimit + "입니다. 금액을 확인하신 후 다시 신청해주세요.");
            return false;
        }
        else if(customerCredit < minCredit){
            alert("신청하시려는 상품의 최소신용점수는 " + minCredit + "점으로 본 상품을 이용하실 수 없습니다.");
            return false;
        }
        else{
            if(userChoice){
                alert("신청이 완료되었습니다. 메인페이지로 이동합니다.");
                window.location.href = "/applyProduct";
            }
            else{
                return false;
            }
        }
    }
</script>
</html>
