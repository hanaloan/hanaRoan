<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.Model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
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
            <%@ include file="/jsp/Components/AdminTopbar/AdminTopbar.jsp" %>

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">환영합니다, <%= request.getAttribute("username")%>님!</h1>
                </div>



                <!-- Content Row -->
                <div class="row">
                    <!-- Content Column -->
                    <div class="col-lg-12 mb-6">

                        <!-- 홈페이지 안내 -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6>고객 요약 정보</h6>
                            </div>
                            <div class="card-body">
                                <p>수입: <%= request.getAttribute("income") %>
                                </p>
                                <p>신용점수: <%= request.getAttribute("credit") %>
                                </p>
                                <p>Customer Idx: <%= request.getAttribute("customer_idx") %>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Content Row -->
                <div class="row">

                    <div class="col-lg-12 mb-6">

                        <!-- 홈페이지 안내 -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6>캐러셀</h6>
                            </div>
                            <div class="card-body">
                                <h1>캐러셀이 들어갈 자리입니다</h1>

                            </div>
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

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<%@ include file="/jsp/Components/LogoutModal/LogoutModal.jsp" %>

<!-- Custom scripts for all pages-->
<script src="../../js/sb-admin-2.min.js"></script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<%--<script>--%>
<%--    var currIdx = 0;--%>

<%--    function previousElement(listSize) {--%>
<%--        $('#subs' + String(currIdx)).css('display', 'none');--%>
<%--        currIdx--;--%>
<%--        if (currIdx < 0) {--%>
<%--            currIdx = listSize - 1;--%>
<%--        }--%>
<%--        $('#subs' + String(currIdx)).css('display', '');--%>
<%--    }--%>

<%--    function nextElement(listSize) {--%>
<%--        $('#subs' + String(currIdx)).css('display', 'none');--%>
<%--        currIdx++;--%>
<%--        if (currIdx >= listSize) {--%>
<%--            currIdx = 0;--%>
<%--        }--%>
<%--        $('#subs' + String(currIdx)).css('display', '');--%>
<%--    }--%>

<%--    var currentImageIndex = 1;--%>
<%--    var totalImages = 3; // Update with the total number of images--%>

<%--    function changeImage(direction) {--%>
<%--        currentImageIndex += direction;--%>
<%--        if (currentImageIndex > totalImages) {--%>
<%--            currentImageIndex = 1;--%>
<%--        } else if (currentImageIndex < 1) {--%>
<%--            currentImageIndex = totalImages;--%>
<%--        }--%>

<%--        var imageElement = document.getElementById("sliderImage");--%>
<%--        imageElement.src = "image" + currentImageIndex + ".jpg";--%>
<%--        imageElement.alt = "Image " + currentImageIndex;--%>
<%--    }--%>

<%--    // Show the popups automatically when the page loads--%>
<%--    &lt;%&ndash;$(document).ready(function () {&ndash;%&gt;--%>
<%--    &lt;%&ndash;    $('.carousel').carousel({&ndash;%&gt;--%>
<%--    &lt;%&ndash;        interval: 1000 // Slide interval in milliseconds (1 second)&ndash;%&gt;--%>
<%--    &lt;%&ndash;    });&ndash;%&gt;--%>
<%--    &lt;%&ndash;    $('#subs0').css('display', '');&ndash;%&gt;--%>
<%--    &lt;%&ndash;    <% if (alertRes != null && alertRes.getAlertMessageRes() != null) {&ndash;%&gt;--%>
<%--    &lt;%&ndash;        for (String key : alertRes.getAlertMessageRes().keySet()) { %>&ndash;%&gt;--%>
<%--    &lt;%&ndash;    $("#popup-<%= key %>").modal("show");&ndash;%&gt;--%>
<%--    &lt;%&ndash;    <%  }&ndash;%&gt;--%>
<%--    &lt;%&ndash;    } %>&ndash;%&gt;--%>
<%--    &lt;%&ndash;});&ndash;%&gt;--%>
<%--</script>--%>

</body>
</html>