<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="sidebar">
    <ul id="accordionSidebar" class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"><a
            class="sidebar-brand d-flex align-items-center justify-content-center" href="CustomerHome"><img class="img-fluid" src="/resource/newLogo.png" style="height: 100px; width: 185px;">
        <div class="sidebar-brand-icon rotate-n-15">
        </div>
    </a>
        <li class="nav-item">
            <a class="nav-link" href="CustomerHome">
                <i class="fas fa-fw fa-table"></i>
                <span>홈</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="productList">
                <i class="fas fa-fw fa-table"></i>
                <span>대출상품</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <i class="fas fa-fw fa-table"></i>
                <span>조회</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <i class="fas fa-fw fa-table"></i>
                <span>이체</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/jsp/Components/LogoutModal/LogoutModal.jsp" data-toggle="modal" data-target="#logoutModal">
                <span><strong>로그아웃</strong></span>
            </a>

        </li>
    </ul>
</div>
</body>
</html>

