<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.Model.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>홈 < 하나론 고객</title>

    <link href="../../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
    <link href="../../css/sb-admin-2.min.css" rel="stylesheet">
    <script src="../../vendor/jquery/jquery.min.js"></script>
    <script src="../../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="../../vendor/jquery-easing/jquery.easing.min.js"></script>

</head>
<style>
    .carousel-item {
        height: 300px;
    }

    .carousel-item > img {
        height: 300px;
        object-fit: cover;
    }

    .prev-button,
    .next-button {
        font-size: 24px;
        color: black;
        padding: 8px 12px;
        cursor: pointer;
        margin: 0 5px;
    }

    button {
        all: unset;
    }

    .carousel-item-text {
        position: absolute;
        bottom: 0;
        left: 0;
        width: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        padding: 8px;
        color: #fff;
        font-weight: bold;
        font-size: 16px;
    }

    .carousel-item-text .loan-name {
        margin-right: 10px;
    }

    .carousel-item-text .loan-interest-rate {
        margin-left: 10px;
    }
</style>
<body id="page-top">
<div id="wrapper">
    <%@ include file="/jsp/Components/CustomerSidebar/CustomerSidebar.jsp" %>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <%@ include file="/jsp/Components/CustomerTopbar/CustomerTopbar.jsp" %>

            <!-- Begin Page Content -->
            <div class="container-fluid">
                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">환영합니다. <%= request.getAttribute("username")%>님!</h1>
                </div>


                <!-- 고객요약정보 -->
                <div class="row">
                    <!-- Content Column -->
                    <div class="col-lg-12 mb-6">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-info">고객요약정보</h6>
                            </div>
                            <div class="card-body" style="display: flex">
                                <div class="col-xl-4 col-md-6 mb-4">
                                    <div class="card border-left-info shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                                        수입
                                                    </div>
                                                    <%
                                                        String incomeStr = String.format("%,d", (int) request.getAttribute("income"));
                                                    %>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800"><%= incomeStr %>원
                                                    </div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xl-4 col-md-6 mb-4">
                                    <div class="card border-left-info shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                                        신용점수
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800"><%= request.getAttribute("credit") %>점
                                                    </div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-xl-4 col-md-6 mb-4">
                                    <div class="card border-left-info shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                                        고객번호
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800"><%= request.getAttribute("customer_idx") %>
                                                    </div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="card-body" style="display: flex">
                                <div class="col-xl-8 col-md-6 mb-4">
                                    <div class="card mb-4">
                                        <div class="card-header text-gray-900 font-weight-bold">진행 중인 대출</div>
                                        <div class="card-body"><%
                                            ArrayList<LoginLendProduct> subsProducts = ((LoginPersonalProductRes) request.getAttribute("personalProducts")).getSubscribedProducts();
                                            if (subsProducts.isEmpty() || subsProducts == null) {
                                        %>
                                            <p>이용중이신 상품이 없습니다.</p>
                                            <% } else { %>
                                            <div class="d-flex">
                                                <button class="prev-button"
                                                        onclick="previousElement('<%=subsProducts.size()%>')">
                                                    <span class="icon text-gray-600"><i
                                                            class="fas fa-arrow-left"></i></span>
                                                </button>
                                                <div class="col">
                                                    <% for (int i = 0; i < subsProducts.size(); i++) { %>
                                                    <div id="subs<%=i%>" style="display: none">
                                                        <dl class="row">
                                                            <dt class="col-sm-2">상 품 명</dt>
                                                            <dd class="col-sm-10"><%= subsProducts.get(i).getProductName()%>
                                                            </dd>
                                                        </dl>
                                                        <dl class="row">
                                                            <dt class="col-sm-2">대출금액</dt>
                                                            <%
                                                                BigDecimal lendAmount = subsProducts.get(i).getLendAmount();
                                                                String lendAmountStr = String.format("%,.0f", lendAmount);
                                                            %>
                                                            <dd class="col-sm-10"><%= lendAmountStr %>원
                                                            </dd>
                                                        </dl>
                                                        <dl class="row">
                                                            <dt class="col-sm-2">대출상태</dt>
                                                            <dd class="col-sm-10"><%= subsProducts.get(i).getLendStatus()%>
                                                            </dd>
                                                        </dl>
                                                        <dl class="row mb-0">
                                                            <dt class="col-sm-2">대출기간</dt>
                                                            <dd class="col-sm-10"><%= subsProducts.get(i).getStartDate()%>
                                                                ~ <%= subsProducts.get(0).getEndDate()%>
                                                            </dd>
                                                        </dl>
                                                    </div>
                                                    <% } %>
                                                </div>
                                                <button class="next-button"
                                                        onclick="nextElement('<%=subsProducts.size()%>')">
                                                    <span class="icon text-gray-600"><i class="fas fa-arrow-right"></i></span>
                                                </button>
                                            </div>
                                            <% } %></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 캐러셀 -->
                <div class="row">
                    <div class="col-lg-12 mb-6">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-info">고객 맞춤 추천 상품</h6>
                            </div>
                            <%
                                List<LoginLoanProduct> recommendedProducts = ((LoginRecommendationRes) request.getAttribute("recoRes")).getRecommendedProducts();
                                if (!recommendedProducts.isEmpty() && recommendedProducts != null) {
                            %>
                            <div class="card-body p-0">
                                <div id="carouselExample" class="carousel slide" data-ride="carousel">
                                    <ol class="carousel-indicators m-0">
                                        <li data-target="#carouselExample" data-slide-to="0" class="active"></li>
                                        <%for (int i = 1; i < recommendedProducts.size() && i < 3; i++) {%>
                                        <li data-target="#carouselExample" data-slide-to="<%=i%>"></li>
                                        <% } %>
                                    </ol>
                                    <div class="carousel-inner">
                                        <div class="carousel-item col-lg-12 active px-0">
                                            <img src="<%=recommendedProducts.get(0).getLoanImage()%>"
                                                 class="d-block w-100"
                                                 alt="image0"
>
                                            <p class="carousel-item-text mb-4">
                                                <span class="loan-name"><%=recommendedProducts.get(0).getLoanName()%></span>
                                                <span class="loan-interest-rate"><%=recommendedProducts.get(0).getLoanInterestRate()%>%</span>
                                            </p>
                                        </div>
                                        <%for (int i = 1; i < recommendedProducts.size() && i < 3; i++) {%>
                                        <div class="carousel-item col-lg-12 px-0">
                                            <img src="<%=recommendedProducts.get(i).getLoanImage()%>"
                                                 class="d-block w-100"
                                                 alt="image<%=i%>">
                                            <p class="carousel-item-text mb-4">
                                                <span class="loan-name"><%=recommendedProducts.get(i).getLoanName()%></span>
                                                <span class="loan-interest-rate"><%=recommendedProducts.get(i).getLoanInterestRate()%>%</span>
                                            </p>
                                        </div>
                                        <% } %>
                                    </div>
                                </div>
                                <%}%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%-- 사용자 알림 관련 --%>
        <div class="row">
            <div>
                <%
                    LoginAlertMessageRes alertRes = (LoginAlertMessageRes) request.getAttribute("alertRes");
                    if (alertRes != null) {
                        HashMap<String, ArrayList<String>> alertMessages = alertRes.getAlertMessageRes();

                        if (alertMessages != null) {
                            for (String key : alertMessages.keySet()) {
                                ArrayList<String> msgList = alertMessages.get(key);
                %>
                <div class="modal fade" id="popup-<%= key %>" tabindex="-1" role="dialog"
                     aria-labelledby="popup-<%= key %>-label" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="popup-<%= key %>-label"><%= key %> 알림</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <ul style="padding: 0;">
                                    <%
                                        for (String msg : msgList) {
                                    %>
                                    <h5><%= msg %>
                                    </h5>
                                    <% } %>
                                </ul>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                            }
                        }
                    }
                %>
            </div>
        </div>
    </div>
</div>
</div>
</div>

<!-- Footer -->
<%@ include file="/jsp/Components/AdminFooter/AdminFooter.jsp" %>

<script>
    var currIdx = 0;

    function previousElement(listSize) {
        $('#subs' + String(currIdx)).css('display', 'none');
        currIdx--;
        if (currIdx < 0) {
            currIdx = listSize - 1;
        }
        $('#subs' + String(currIdx)).css('display', '');
    }

    function nextElement(listSize) {
        $('#subs' + String(currIdx)).css('display', 'none');
        currIdx++;
        if (currIdx >= listSize) {
            currIdx = 0;
        }
        $('#subs' + String(currIdx)).css('display', '');
    }

    var currentImageIndex = 1;
    var totalImages = 3; // Update with the total number of images

    function changeImage(direction) {
        currentImageIndex += direction;
        if (currentImageIndex > totalImages) {
            currentImageIndex = 1;
        } else if (currentImageIndex < 1) {
            currentImageIndex = totalImages;
        }

        var imageElement = document.getElementById("sliderImage");
        imageElement.src = "image" + currentImageIndex + ".jpg";
        imageElement.alt = "Image " + currentImageIndex;
    }

    // Show the popups automatically when the page loads
    $(document).ready(function () {
        $('.carousel').carousel({
            interval: 1000 // Slide interval in milliseconds (1 second)
        });
        $('#subs0').css('display', '');
        <% if (alertRes != null && alertRes.getAlertMessageRes() != null) {
            for (String key : alertRes.getAlertMessageRes().keySet()) { %>
        $("#popup-<%= key %>").modal("show");
        <%  }
        } %>
    });
</script>
</body>
</html>