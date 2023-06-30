<%@ page import="java.util.List" %>
<%@ page import="com.Model.ProductRes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>대출 상품 관리</title>

    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../../css/LoanManagement/LoanManagement.css">
    <style>


        .button-container {
            /*display: flex;*/
            text-align: right;
            gap: 20px;
        }
        .btn-h {
            height: 35px;
            width: 150px;
            text-align: center;
            align-items: center;
        }
        .btn-w {
            height: 25px;
            width: 60px;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
        }
    </style>

</head>
<body id="page-top">
<script>
    window.onload = function () {
        if (window.location.pathname !== '/LoanManagement') {
            location.href = '/LoanManagement';
        }
    }

</script>
<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Start of Sidebar -->
    <%@ include file="/jsp/Components/AdminSidebar/AdminSidebar.jsp" %>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <%@ include file="/jsp/Components/AdminTopbar/AdminTopbar.jsp" %>

            <!-- Begin Page Content -->
            <div class="container-fluid">
                <h1 class="h3 mb-2 text-gray-800">대출 상품 관리</h1>
                <p class="mb-4">이 페이지는 대출 상품을 관리할 수 있는 페이지입니다. 이 페이지에서는 대출 상품의 정보를 확인할 수 있으며, 추가 및 삭제 기능을 제공합니다. 관리자는 대출상품의
                    상품명, 상품 세부 설명, 이율, 연체 이율, 최대한도, 대출 기한 및 최소 신용점수를 확인할 수 있습니다. 각 상품 구분에 따라 전세, 월세, 담보 대출 상품만 선택하여 보실 수 있습니다.</p>

                <div class="button-container">

                    <button id="insertProductBtn"
                            class="btn btn-secondary btn-icon-split mr-auto fa btn-h"
                            disabled
                            onclick="location.href='/jsp/LoanManagement/InsertProduct.jsp'">
                        등록
                    </button>

                    <script>


                        var k = '<%= session.getAttribute("authType") %>';
                        var InsertProductBtn = document.getElementById("insertProductBtn");
                        if (k === "all" || k==="managing Products") {
                            InsertProductBtn.disabled = false;
                        }


                    </script>
                    <p></p>
                </div>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">

                    <div class="card-header py-3"
                         style="display: flex; justify-content: space-between; align-items: center;">

                        <h6 class="m-0 font-weight-bold text-primary">상품 정보</h6>

                        <div id="navBar">
                            <span class="option-label">상품종류 :
                                <div id="primaryOption" class="btn-group" role="group">
                                    <button type="button"><a href="LoanManagement">전체</a></button>
                                    <button type="button"><a href="LoanManagement?option1=전세대출">전세</a></button>
                                    <button type="button"><a href="LoanManagement?option1=월세대출">월세</a></button>
                                    <button type="button"><a href="LoanManagement?option1=담보대출">담보</a></button>
                                </div>
                            </span>


                        </div>

                    </div>
                    <div class="card-body">
                        <div class="table-responsive">


                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>대출상품번호</th>
                                    <th>상품구분</th>
                                    <th>대출상품명</th>
                                    <th>상품 설명</th>
                                    <th>이율</th>
                                    <th>연체이율</th>
                                    <th>최대한도</th>
                                    <th>대출기간 [년]</th>
                                    <th>최소신용점수</th>
                                    <th>삭제 작업</th>


                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>대출상품번호</th>
                                    <th>상품구분</th>
                                    <th>대출상품명</th>
                                    <th>상품 설명</th>
                                    <th>이율</th>
                                    <th>연체이율</th>
                                    <th>최대한도</th>
                                    <th>대출기간</th>
                                    <th>최소신용점수</th>
                                    <th>삭제 작업</th>
                                </tr>
                                </tfoot>
                                <tbody>

                                <%
                                    request.setCharacterEncoding("UTF-8");
                                    List<ProductRes> products = (List<ProductRes>) request.getAttribute("loanProductsDto");
                                    if (products != null && !products.isEmpty()) {
                                        for (ProductRes product : products) {
                                %>
                                <tr>
                                    <td><%= product.getIdx() %>
                                    </td>
                                    <td><%= product.getLoanTypeName() %>
                                    </td>
                                    <td><%= product.getName() %>
                                    </td>
                                    <td><%= product.getDescription() %>
                                    </td>
                                    <td><%= product.getInterestRate() %>
                                    </td>
                                    <td><%= product.getOverdueInterestRate() %>
                                    </td>
                                    <td><%= product.getLendLimit() %>
                                    </td>
                                    <td><%= product.getLoanPeriod() %>
                                    </td>
                                    <td><%= product.getMinCredit() %>
                                    </td>
                                    <td>
                                        <form action="/LoanManagement" method="POST">
                                            <input type="hidden" name="productId" value="<%= product.getIdx() %>">
                                            <button class="btn mr-auto fa btn-outline-danger btn-w" type="submit"
                                                    onclick="return confirm('정말 해당 상품을 삭제하시겠습니까?')">
                                                삭제
                                            </button>
                                        </form>
                                    </td>

                                </tr>
                                <%
                                    }
                                } else {
                                %>
                                <tr>
                                    <td colspan="9">No loan products available.</td>
                                </tr>
                                <% }%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <%@ include file="/jsp/Components/AdminFooter/AdminFooter.jsp" %>

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->
<!-- Bootstrap core JavaScript-->

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
