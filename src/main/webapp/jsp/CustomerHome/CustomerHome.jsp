<%@ page import="com.Model.LoginLoanProduct" %>
<%@ page import="com.Model.LoginRecommendationRes" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.Model.LoginAlertMessageRes" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Home</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <script>
        var counter = 10 * 60;
        var interval = setInterval(function() {
            counter--;
            if (counter <= 0) {
                clearInterval(interval);
                $('#time').text("Session expired");
                return;
            } else {
                var minutes = Math.floor(counter / 60);
                var seconds = counter % 60;
                $('#time').text(minutes + ":" + seconds);
            }
        }, 1000);

        function extendSession() {
            $.ajax({
                url: '${pageContext.request.contextPath}/extendSession',
                type: 'POST',
                success: function() {
                    counter = 10 * 60;
                },
                error: function() {
                    console.log("Error extending the session");
                }
            });
        }
    </script>
</head>
<%@ include file="/jsp/CustomerGNB/CustomerGNB.jsp" %>
<body>
<h1>Welcome, <%= request.getAttribute("username") %>!</h1>
<p>Income: <%= request.getAttribute("income") %></p>
<p>Credit: <%= request.getAttribute("credit") %></p>
<p>Customer Idx: <%= request.getAttribute("customer_idx") %></p>

<%-- JSP에서 추천 상품 정보를 불러오기 --%>
<%
    List<LoginLoanProduct> recommendedProducts = ((LoginRecommendationRes)request.getAttribute("recoRes")).getRecommendedProducts();
    for (LoginLoanProduct product : recommendedProducts) {
%>
<p>Loan Image: <%= product.getLoanImage() %></p>
<p>Loan Name: <%= product.getLoanName() %></p>
<p>Loan Interest Rate: <%= product.getLoanInterestRate() %></p>
<%
    }
%>

<%--세션 만료 관련--%>
<div id="top-right">
    <p id="time">10:00</p>
    <button onclick="extendSession()">Extend session</button>
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
<script>
    // Show the popups automatically when the page loads
    $(document).ready(function() {
        <% if (alertRes != null && alertRes.getAlertMessageRes() != null) {
            for (String key : alertRes.getAlertMessageRes().keySet()) { %>
        $("#popup-<%= key %>").modal("show");
        <%  }
        } %>
    });
</script>
</body>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</html>