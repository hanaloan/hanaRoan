<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="/css/sb-admin-2.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <script>
        var counter = 10 * 60;
        var interval = setInterval(function () {
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
                success: function () {
                    counter = 10 * 60;
                },
                error: function () {
                    console.log("Error extending the session");
                }
            });
        }
    </script>
</head>
<body>
<%--<%@ include file="/jsp/AdminSidebar/AdminSidebar.jsp" %>--%>
<p>어서오세요!, 현재 관리자는 <%= session.getAttribute("username") %>. 입니다~.</p>
<p>어서오세요!, 현재 관리자 인덱스는 <%= session.getAttribute("employee_idx") %>. 입니다~.</p>
<%--수정하는 부분--%>
<div id="wrapper">
    <%@ include file="/jsp/AdminSidebar/AdminSidebar.jsp" %>
<%--    <ul id="accordionSidebar" class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"><a--%>
<%--            class="sidebar-brand d-flex align-items-center justify-content-center" href="/jsp/DashBoard/DashBoard.jsp">--%>
<%--        <div class="sidebar-brand-icon rotate-n-15">--%>
<%--            <i class="fas fa-laugh-wink"></i>--%>
<%--        </div>--%>
<%--        <div class="sidebar-brand-text mx-3">대시보드</div>--%>
<%--    </a>--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link" href="/jsp/ManageEmployee/EmployeeManage.jsp">--%>
<%--                <i class="fas fa-fw fa-table"></i>--%>
<%--                <span>직원관리</span></a>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link" href="/jsp/CustomerManagement/CustomerManagement.jsp">--%>
<%--                <i class="fas fa-fw fa-table"></i>--%>
<%--                <span>고객관리</span></a>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link" href="/jsp/Loan리Management/Loan리Management.jsp">--%>
<%--                <i class="fas fa-fw fa-table"></i>--%>
<%--                <span>대출관리</span></a>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link" href="/jsp/LoanPayment/LoanPayment.jsp">--%>
<%--                <i class="fas fa-fw fa-table"></i>--%>
<%--                <span>상환관리</span></a>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link" href="/jsp/Report/Report.jsp">--%>
<%--                <i class="fas fa-fw fa-table"></i>--%>
<%--                <span>보고서</span></a>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link" href="/jsp/AdminSystemSetting/AdminSystemSetting.jsp">--%>
<%--                <i class="fas fa-fw fa-table"></i>--%>
<%--                <span>시스템 설정</span></a>--%>
<%--        </li>--%>

<%--    </ul>--%>
    <!-- End of Sidebar -->
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">컨텐츠 래퍼</div>
</div>

<div id="top-right">
    <p id="time">10:00</p>
    <button onclick="extendSession()">Extend session</button>
</div>
</body>
</html>