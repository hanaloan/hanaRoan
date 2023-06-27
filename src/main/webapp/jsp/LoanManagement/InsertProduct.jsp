<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>상품 추가</title>

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
        .submit-style{
            width: 300px;
            height: 50px;
            margin: 0 auto; /* Center align the button */
            display: flex;
            justify-content: center;
            align-items: center;
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

                            <h2>Enter Add Product:</h2>
                            <br></br>
                        </div>


                        <form action="/InsertProduct" method="post" class="user" >
                            <div class="form-group col">
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <label for="productName">상품명:</label>
                                            <input class="form-control form-control-user" type="text" id="productName"
                                                   name="productName" required>
                                        </div>
                                    <div class="col-sm-6">
                                        <label for="productTypeName">상품구분:</label>

                                            <select class="form-control " name="productTypeName"
                                                    id="productTypeName">
                                                <option value="전세대출">전세대출</option>
                                                <option value="월세대출">원세대출</option>
                                                <option value="담보대출">담보대출</option>
                                            </select>

                                    </div>
                                    </div>


                                <label for="productInfo">상품 설명:</label>
                                <input class="form-control form-control-user" type="text" id="productInfo"
                                       name="productInfo" required>

                            <br>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <label for="interestRate">이율:</label>
                                        <input class="form-control form-control-user" type="text" id="interestRate"
                                               name="interestRate" required>
                                    </div>
                                    <div class="col-sm-6">
                                        <label for="overdueInterestRate">연체이율:</label>

                                        <input class="form-control form-control-user" type="text"
                                               id="overdueInterestRate" name="overdueInterestRate" required>
                                    </div>
                                </div>

                                <label for="limit">최대한도:</label>
                                <input class="form-control form-control-user" type="text" id="limit" name="limit"
                                       required>
                                <br>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                <label for="period">대출기한:</label>
                                <input class="form-control form-control-user" type="text" id="period" name="period" placeholder="Year"
                                       required>
                                        </div>
                                        <div class="col-sm-6">
                                <label for="minCredit">최소신용점수:</label>
                                <input class="form-control form-control-user" type="text" id="minCredit"
                                       name="minCredit" placeholder="100~800점" required>
                                        </div>
                                </div>
                                <br>

                                <input type="submit" value="Submit" class="btn btn-primary btn-icon-split btn-lg submit-style" >


                            </div>

                        </form>
<%--                    </div>--%>
<%--                </div>--%>
            </div>
        </div>
    </div>
</div>

<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="/js/demo/datatables-demo.js"></script>
</body>
</html>
