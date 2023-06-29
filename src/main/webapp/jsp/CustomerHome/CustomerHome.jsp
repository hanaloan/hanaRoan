<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.Model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<style>
    .image-container {
        width: 400px;
        height: 300px;
        position: relative;
        overflow: hidden;
    }

    .image-container img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
    .prev-button,
    .next-button {
        font-size: 24px;
        color: #fff;
        background-color: rgba(0, 0, 0, 0.5);
        padding: 8px 12px;
        border-radius: 50%;
        cursor: pointer;
        margin: 0 5px;
    }
</style>
<body>
<div class="row" id="wrapper">
    <%@ include file="/jsp/CustomerGNB/CustomerGNB.jsp" %>
    <div class="col d-flex justify-content-center align-items-center">
        <div class="row">
            <h1>환영합니다, <%= request.getAttribute("username")%>님!</h1>
            <div class="card">
                <h5 class="card-title">고객요약정보</h5>
                <div class="card-body">
                    <p>수입: <%= request.getAttribute("income") %></p>
                    <p>신용점수: <%= request.getAttribute("credit") %></p>
                    <p>Customer Idx: <%= request.getAttribute("customer_idx") %></p>
                </div>
                <div class="container">
                    <div class="col-6 justify-content-center flex-fill">
                        <%
                            ArrayList<LoginLendProduct> subsProducts = ((LoginPersonalProductRes) request.getAttribute("personalProducts")).getSubscribedProducts();
                            if (subsProducts.isEmpty() || subsProducts == null) {
                        %>
                        <p>이용중이신 상품이 없습니다.</p>
                        <% } else { %>
                        <div class="card info-container">
                            <div class="d-flex">
                                <button class="prev-button" onclick="previousElement('<%=subsProducts.size()%>')">&#10094;</button>
                                <div class="col">
                                    <% for (int i = 0; i < subsProducts.size(); i++) { %>
                                    <div id="subs<%=i%>" style="display: none">
                                        <strong><p class="info-name">상품명: <%= subsProducts.get(i).getProductName()%></p></strong>
                                        <p class="info-amount">대출금액: <%= subsProducts.get(i).getLendAmount()%></p>
                                        <p class="info-status">대출상태: <%= subsProducts.get(i).getLendStatus()%></p>
                                        <p class="info-period">대출기간: <%= subsProducts.get(i).getStartDate()%> ~ <%= subsProducts.get(0).getEndDate()%></p>
                                    </div>
                                    <% } %>
                                </div>
                                <button class="next-button" onclick="nextElement('<%=subsProducts.size()%>')">&#10095;</button>
                            </div>
                        </div>
                        <% } %>
                    </div>
                </div>
            </div>
            <%List<LoginLoanProduct> recommendedProducts = ((LoginRecommendationRes)request.getAttribute("recoRes")).getRecommendedProducts();%>
            <div id="carouselExample" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExample" data-slide-to="0" class="active"></li>
                    <%for (int i = 1; i < recommendedProducts.size(); i++) {%>
                    <li data-target="#carouselExample" data-slide-to="<%=i%>"></li>
                    <% } %>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="<%=recommendedProducts.get(0).getLoanImage()%>" class="d-block h-25 w-100" alt="image0">
                        <p><%=recommendedProducts.get(0).getLoanName()%></p>
                        <p><%=recommendedProducts.get(0).getLoanInterestRate()%>%</p>
                    </div>
                    <%for (int i = 1; i < recommendedProducts.size(); i++) {%>
                        <div class="carousel-item">
                            <img src="<%=recommendedProducts.get(i).getLoanImage()%>" class="d-block h-25 w-100" alt="image<%=i%>">
                            <p><%=recommendedProducts.get(i).getLoanName()%></p>
                            <p><%=recommendedProducts.get(i).getLoanInterestRate()%>%</p>
                        </div>
                    <% } %>
                </div>
            </div>
    </div>
</div>


<%-- 사용자 알림 관련 --%>
<div>
    <div>
        <%
            LoginAlertMessageRes alertRes = (LoginAlertMessageRes) request.getAttribute("alertRes");
            if (alertRes != null) {
                HashMap<String, ArrayList<String>> alertMessages = alertRes.getAlertMessageRes();

                if (alertMessages != null) {
                    for (String key : alertMessages.keySet()) {
                        ArrayList<String> msgList = alertMessages.get(key);
        %>
        <div class="modal fade" id="popup-<%= key %>" tabindex="-1" role="dialog" aria-labelledby="popup-<%= key %>-label" aria-hidden="true">
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
                            <h5><%= msg %></h5>
                            <% } %>
                        </ul>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
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


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script>
    var currIdx = 0;
    function previousElement(listSize) {
        $('#subs'+String(currIdx)).css('display', 'none');
        currIdx--;
        if (currIdx < 0) {
            currIdx = listSize-1;
        }
        $('#subs'+String(currIdx)).css('display', '');
    }

    function nextElement(listSize) {
        $('#subs'+String(currIdx)).css('display', 'none');
        currIdx++;
        if (currIdx >= listSize){
            currIdx = 0;
        }
        $('#subs'+String(currIdx)).css('display', '');
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
    $(document).ready(function() {
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