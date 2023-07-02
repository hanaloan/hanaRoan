<%@ page import="com.Model.DisplayProductDetailRes" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>대출 상품 상세 < 하나론</title>
    <link href="../../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="../../css/sb-admin-2.min.css" rel="stylesheet">
    <script src="../../vendor/jquery/jquery.min.js"></script>
    <script src="../../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="../../vendor/jquery-easing/jquery.easing.min.js"></script>
</head>
<body id="page-top">
<!-- Page Wrapper -->
<div id="wrapper">
    <!-- Sidebar -->
    <%@ include file="/jsp/Components/CustomerSidebar/CustomerSidebar.jsp" %>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">
            <!-- Topbar -->
            <%@ include file="/jsp/Components/CustomerTopbar/CustomerTopbar.jsp" %>
            <% DisplayProductDetailRes product = ((DisplayProductDetailRes) request.getAttribute("product"));
                NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault()); {
                }%>
            <div class="container-fluid">
                <div class="row">
                    <div class="col d-flex justify-content-center align-items-center">
                        <div class="card col-12 p-0">
                            <div class="card-header p-3 bg-info text-white" style="width: 100%;">
                                <div class="p-0 bg-info text-white" style="font-size: 1.5rem; font-weight: bold;"><%=product.getLoanName()%></div>
                            </div>
                            <div class="card-body">
                                <div class="card-title pb-2" style=" border-bottom: 1px solid #858796;">
                                    <h5><%=product.getLoanDescription()%></h5></div>

                                <dl class="row">
                                    <dt class="col-sm-2">최소신용점수</dt>
                                    <dd class="col-sm-10"><%=product.getMinCredit()%>점</dd>
                                </dl>
                                <%
                                    String lendLimit = numberFormat.format(Float.parseFloat(product.getLendLimit().toString()));
                                %>
                                <dl class="row">
                                    <dt class="col-sm-2">최대대출한도</dt>
                                    <dd class="col-sm-10"><%= lendLimit %>원</dd>
                                </dl>
                                <dl class="row">
                                    <dt class="col-sm-2">최대대출기간</dt>
                                    <dd class="col-sm-10"><%=product.getLendPeriod()%>년</dd>
                                </dl>
                                <dl class="row">
                                    <dt class="col-sm-2">이자율</dt>
                                    <dd class="col-sm-10"><%=product.getLoanInterestRate()%>%</dd>
                                </dl>
                                <dl class="row">
                                    <dt class="col-sm-2">연체이자율</dt>
                                    <dd class="col-sm-10"><%=product.getOverdueInterestRate()%>%</dd>
                                </dl>
                                <div class="card-title" style=" border-bottom: 1px solid #858796;"></div>
                                <button type="button" class="btn btn-secondary" name="applyProductRes"
                                        style="margin-top: 10px;"
                                        onclick="applyConfirm('<%=product.getLoanName()%>', '<%=product.getProductId()%>')">대출신청
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function applyConfirm(productName, productId) {
        let confirmMsg = productName + " 상품을 신청하시겠습니까?";
        let userChoice = confirm(confirmMsg);
        if (userChoice) {
            window.location.href = "applyProduct?productId=" + productId;
        } else {
            return false;
        }
    }

</script>
</html>
