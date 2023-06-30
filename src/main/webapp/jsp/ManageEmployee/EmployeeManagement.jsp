<%@ page import="com.DAO.EmployeeManagementDao" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.Model.EmployeeRes" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>직원 관리</title>

    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    <style>
        select {
            border: none;
            outline: none;
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            background-color: transparent;
            padding: 0;
        }

        .container {
            display: flex;
            align-items: center;
            gap: 20px;
        }

        .button-container {
            text-align: right;
        }

        .btn-h {
            height: 35px;
            width: 150px;
            text-align: center;
            align-items: center;
        }
    </style>

</head>
<body id="page-top">

<script>
    window.onload = function () {
        if (window.location.pathname !== '/EmployeeManagement') {
            location.href = '/EmployeeManagement';
        }

        // 페이지 로드 시 드롭다운 버튼 상태 업데이트
        var selectElements = document.querySelectorAll("select[id^='auth-']");
        for (var i = 0; i < selectElements.length; i++) {
            updateAuthStatus(selectElements[i]);
        }

        var k = '<%= request.getAttribute("empAuthName") %>';
        var updateEmployeeBtn = document.getElementById("updateEmployeeBtn");
        var insertEmployeeBtn = document.getElementById("insertEmployeeBtn");

        if (k === "all") {
            updateEmployeeBtn.disabled = false;
            insertEmployeeBtn.disabled = false;
        }
    }

    function updateEmployeeAuth(getEmpIdx, getEmpName) {
        //select 태그에서 선택한 값 가져옴
        var empIdx = getEmpIdx;
        var empAuthName = document.getElementById('auth-' + getEmpIdx).value;

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/UpdateEmpAuth", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send("empIdx=" + empIdx + "&empAuthName=" + empAuthName);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) { // 응답이 완료되었을 때만 실행
                if (xhr.status === 200) { // 성공적인 응답일 경우
                    updateAuthStatus(document.getElementById('auth-' + getEmpIdx));
                    // alert(empAuthName);
                    if (empAuthName === "none") {
                        alert(getEmpName + " 직원의 권한이 제한되었습니다.");
                    }
                    location.reload();
                } else {
                    console.log('오류 발생: ' + xhr.status);
                }
            }
        };

    }

    //  직원의 권한을 바꾸면 다시 비활성화 상태로 바꿔야 함.
    function updateAuthStatus(selectElement) {
        selectElement.disabled = true;
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
                <h1 class="h3 mb-2 text-gray-800">직원 관리</h1>
                <p class="mb-4">이 페이지는 직원(관리자)의 시스템 접근 권한을 관리하는 페이지입니다. 직원 정보를 표로 나타내며, 직원을 추가 및 접근 권한 수정을 할 수 있습니다.<br>
                    <br>
                    권한은 'all', 'managing Products', 'managing Customers', 'read only', 'none' 이렇게 총 5개로 구분합니다.
                    <p style="font-size: small">
                *  <strong>'all'</strong> 권한은 직원들의 권한 수정이 가능하고, 고객, 상품에 대한 접근까지 가능합니다.<br>
                *  <strong>'managing Products'</strong> 권한은 대출 상품에 대한 접근만 가능하다. 상품 추가, 삭제, 상환 업무 등을 할 수 있습니다.<br>
                *  <strong>'managing Customers'</strong> 권한은 고객 정보에 대한 접근만 가능하다. 대출 상품 신청에 대한 승인 업무 등을 할 수 있습니다. <br>
                *  <strong>'read only'</strong> 권한은 대출 상품, 고객 정보에 대한 읽기만 가능하다. 추가 및 수정 등과 같은 업무는 제한됩니다.<br>
                *  <strong>'none'</strong> 권한은 관리자 페이지에 대해 제한합니다. 해당 권한을 갖고 있는 관리자는 로그인조차 제한됩니다.
            </p>

                </p>
                <h4>현재 관리자</h4>
                <div class="container">

                    <div class="box" style=" height: 80px; width: 80px">
                        <img class="profile" src="/img/undraw_profile.svg">
                    </div>

                    <p>
                    <h5><%= request.getAttribute("empName") %>&nbsp[<%= request.getAttribute("empAuthName") %>] </h5> 님
                    반갑습니다~
                    </p>

                </div>
                <!-- Page Heading -->
                <div class="button-container">
                    <button class="btn btn-secondary btn-icon-split ml-auto fa btn-h" id="insertEmployeeBtn" disabled
                            onclick="location.href='/jsp/ManageEmployee/InsertEmployee.jsp'">직원 등록
                    </button>
                    <button class="btn btn-secondary btn-icon-split ml-auto btn-h" id="updateEmployeeBtn" disabled>접근 권한 부여
                    </button>

                </div>
                <p>

                    <!-- DataTales Example -->
                <div class="card shadow mb-4">

                    <div class="card-header py-3"
                         style="display: flex; justify-content: space-between; align-items: center;">

                        <h6 class="m-0 font-weight-bold text-primary">직원 정보</h6>


                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>
                                        ID
                                    </th>
                                    <th>
                                        관리자 명
                                    </th>
                                    <th>
                                        권한
                                    </th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>
                                        ID
                                    </th>
                                    <th>
                                        관리자 명
                                    </th>
                                    <th>
                                        권한
                                    </th>
                                </tr>
                                </tfoot>
                                <%
                                    request.setCharacterEncoding("UTF-8");
                                    List<EmployeeRes> employees = (List<EmployeeRes>) request.getAttribute("employeeManageResDto");
                                    if (employees != null) {
                                        for (Object obj : employees) {
                                            if (obj instanceof EmployeeRes) {
                                                EmployeeRes employee = (EmployeeRes) obj;
                                %>

                                <tr>
                                    <td>
                                        <%=employee.getEmpIdx() %>
                                    </td>
                                    <td>
                                        <%=employee.getEmpName() %>
                                    </td>
                                    <td>
                                        <%--                전체 권한 비활성화로 세팅--%>
                                        <select style="appearance: none" disabled id="auth-<%= employee.getEmpIdx() %>"
                                                onchange="updateEmployeeAuth('<%= employee.getEmpIdx() %>', '<%= employee.getEmpName() %>', '<%= session.getAttribute("employee_idx") %>')">
                                            <option value="all" <%= employee.getEmpLevelName() != null && employee.getEmpLevelName().equals("all") ? "selected" : "" %>>
                                                all
                                            </option>
                                            <option value="managing Products" <%= employee.getEmpLevelName() != null && employee.getEmpLevelName().equals("managing Products") ? "selected" : "" %>>
                                                managing Products
                                            </option>
                                            <option value="managing Customers" <%= employee.getEmpLevelName() != null && employee.getEmpLevelName().equals("managing Customers") ? "selected" : "" %>>
                                                managing Customers
                                            </option>
                                            <option value="read only" <%= employee.getEmpLevelName() != null && employee.getEmpLevelName().equals("read only") ? "selected" : "" %>>
                                                read only
                                            </option>
                                            <option value="none" <%= employee.getEmpLevelName() != null && employee.getEmpLevelName().equals("none") ? "selected" : "" %>>
                                                none
                                            </option>
                                        </select>
                                    </td>
                                </tr>

                                <%
                                            }
                                        }
                                    }
                                %>
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

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="login.html">Logout</a>
            </div>
        </div>
    </div>
</div>

<script>
    //전체 권한 비활성화 상태에서 활성화로 전환 시키기
    <%
        request.setCharacterEncoding("UTF-8");
        employees = (List<EmployeeRes>) request.getAttribute("employeeManageResDto");
        if (employees != null) {
            for (Object obj : employees) {
                if (obj instanceof EmployeeRes) {
                    EmployeeRes employee = (EmployeeRes) obj;

    %>

    //관리자 직원 수정 버튼을 누르면 활성화로 전환됨
    document.getElementById("updateEmployeeBtn").addEventListener("click", function () {
        var currentUserID = "<%= session.getAttribute("employee_idx") %>"; // Assuming you have the current user's ID stored in a variable

        if ("<%= employee.getEmpIdx() %>" === currentUserID) {
            var element = document.getElementById("auth-<%= employee.getEmpIdx() %>");
            element.style.appearance = "none"; // hide the dropdown arrow
            element.disabled = true; // treat as inactive
        }

        else{
            var element = document.getElementById("auth-<%= employee.getEmpIdx() %>");
            //아래 화살표 띄우기
            element.style.appearance = "-webkit-menulist-button"; // show the dropdown arrow for WebKit browsers (e.g., Chrome, Safari)
            element.style.appearance = "menulist-button"; // show the dropdown arrow for other browsers
            element.disabled = false; //비활성화 False로 처리
        }
    });
    <%
                }
            }
        }
    %>
</script>

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
<%--<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>--%>



</div>

</body>
</html>