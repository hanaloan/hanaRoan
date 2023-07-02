<%@ page import="java.util.List" %>
<%@ page import="com.Model.Payment"%>
<%@ page import="com.Model.PaymentRes" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Data Display</title>
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/LoanPayment/LoanPayment.css">
</head>
<body id="page-top">
<div id="wrapper">
    <%@ include file="/jsp/Components/AdminSidebar/AdminSidebar.jsp" %>
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <%@ include file="/jsp/Components/AdminTopbar/AdminTopbar.jsp" %>
            <div id="mainContent" class="container-fluid">
                <h1 class="h3 mb-2 text-gray-800">상환 관리</h1>
                <p class="mb-4">이 페이지는 대출 상환을 관린하는 페이지입니다. 고객명, 상품 구분, 상품명, 대출 신청일, 마감기한, 잔액, 지난 날짜, 상환 상태 등의 고객들의 대출 상품 상환 내역을 조회할 수 있으며,
                    상환기한을 기준으로 잔액차감 및 연체전환 기능을 제공합니다. 또한 마스터 혹은 대출 관리 권한을 가진 관리자만이 본 페이지에서 기능을 조작할 수 있습니다.</p>
                <div class="card shadow mb-4">
                    <div class="card-header py-3"
                         style="display: flex; justify-content: space-between; align-items: center;">
                        <div id="navBar">
                            <span class="option-label">상품종류 :
                                <div id="primaryOption" class="btn-group" role="group">
                                    <button type="button" class="btn btn-primary"><a href="loanPayment">전체</a></button>
                                    <button type="button" class="btn btn-primary"><a href="loanPayment?option1=전세">전세</a></button>
                                    <button type="button" class="btn btn-primary"><a href="loanPayment?option1=월세">월세</a></button>
                                    <button type="button" class="btn btn-primary"><a href="loanPayment?option1=담보">담보</a></button>
                                </div>
                            </span>

                            <span class="option-label">상환상태 :
                                <div id="subOption" class="btn-group" role="group">
                                    <button type="button" class="btn btn-secondary"><a href="loanPayment?option1=${param.option1}&option2=전체">전체</a></button>
                                    <button type="button" class="btn btn-secondary"><a href="loanPayment?option1=${param.option1}&option2=상환중">상환중</a></button>
                                    <button type="button" class="btn btn-secondary"><a href="loanPayment?option1=${param.option1}&option2=연체">연체</a></button>
                                    <button type="button" class="btn btn-secondary"><a href="loanPayment?option1=${param.option1}&option2=상환완료">상환완료</a></button>
                                </div>
                            </span>
                        </div>
                    </div>
                    <div class="card-body">
                        <table class="table table-bordered" id="dataTable">
                            <thead>
                                <tr>
                                    <th>고객명</th>
                                    <th>상품구분</th>
                                    <th>상품명</th>
                                    <th>대출시작일</th>
                                    <th>상환기한</th>
                                    <th>잔액</th>
                                    <th>연체일수</th>
                                    <th>상환상태</th>
                                    <th>잔액관리</th>
                                    <th>연체관리</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>고객명</th>
                                    <th>상품구분</th>
                                    <th>상품명</th>
                                    <th>대출시작일</th>
                                    <th>상환기한</th>
                                    <th>잔액</th>
                                    <th>연체일수</th>
                                    <th>상환상태</th>
                                    <th>잔액관리</th>
                                    <th>연체관리</th>
                                </tr>
                            </tfoot>
                            <% String authType = session.getAttribute("authType").toString();
                                ArrayList<Payment> paymentList = ((PaymentRes) request.getAttribute("paymentList")).getPaymentRes();
                                if (paymentList.isEmpty() || paymentList == null) { %>
                            <tr>
                                <td colspan="10">
                                    <h3>데이터가 없습니다.</h3>
                                </td>
                            </tr>
                            <% } else {
                                    for (Payment payment : paymentList) {
                                            int paymentId = payment.getPaymentId();
                                            int passedDate = Integer.parseInt(payment.getPassedDate());
                                            String paymentStatus = payment.getPaymentStatus();
                                %>
                                    <tr>
                                        <td><%= payment.getCustomerName() %></td>
                                        <td><%= payment.getProductType() %></td>
                                        <td><%= payment.getProductName() %></td>
                                        <td><%= payment.getLendStartDate() %></td>
                                        <td><%= payment.getPaymentDueDate() %></td>
                                        <td><%= payment.getDueBalance() %></td>
                                        <td>
                                            <% if (!paymentStatus.equals("상환완료")) {
                                                if (passedDate < 0) { %>
                                            0
                                            <% } else { %>
                                            <%= passedDate %>
                                            <% }
                                            } else { %>
                                            -
                                            <% } %>
                                        </td>
                                        <td><%= paymentStatus %></td>
                                        <td>
                                            <form action="/DeductBalance" method="post" accept-charset="UTF-8">
                                                <input type="hidden" class="payment-id" name="paymentId" value="<%= paymentId %>">
                                                <button type="submit" class="deductBalanceBtn" name="deductBalance" <% if (paymentStatus.equals("상환완료")) { %>disabled<% } %>
                                                        onclick="return confirmDeductBalance('<%= authType %>', '<%= passedDate%>');">
                                                    잔액차감
                                                </button>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="/HandleOverdue" method="post" accept-charset="UTF-8">
                                                <input type="hidden" class="payment-id" name="paymentId" value="<%= paymentId %>">
                                                <button type="submit" class="overdueBtn" name="handleOverdue" <% if (paymentStatus.equals("상환완료")) { %>disabled<% } %>
                                                        onclick="return confirmHandleOverdue('<%= authType %>', '<%= paymentStatus %>', '<%= passedDate %>');">
                                                    연체관리
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                    <% } %>
                            <% } %>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/jsp/Components/AdminFooter/AdminFooter.jsp" %>
    </div>
</div>
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="/js/sb-admin-2.min.js"></script>
<script src="/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>
<script src="/js/demo/datatables-demo.js"></script>
<script src="/js/LoanPayment/LoanPayment.js"></script>
</body>
</html>
