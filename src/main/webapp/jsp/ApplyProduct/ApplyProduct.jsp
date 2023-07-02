<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>상품 신청서</title>
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="/css/ApplyProduct/ApplyProduct.css" rel="stylesheet">
</head>
<body class="bg-gradient-primary">
<div class="container">
    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
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
                    <input class="form-control form-control-user" id="lendAmount" name="lendAmount" oninput="validateInput()">
                    <br>
                    <br>
                    <div class="button-container">
                        <a href="productList"><button type="button" class="btn btn-primary btn-lg submit-style">돌아가기</button></a>
                        <button id="loanApply" class="btn btn-primary btn-lg submit-style" type="submit"
                                onclick="return applyProductConfirm('${customerInfo.customerName}', '${customerInfo.creditScore}',
                                        '${productInfo.loanName}', '${productInfo.lendLimit}', '${productInfo.minCredit}')">대출신청</button>
                    </div>

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
<script src="/js/ApplyProduct/ApplyProduct.js"></script>
</body>
</html>
