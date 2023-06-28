<%@ page import="com.DAO.EmployeeManagementDao" %>
<%@ page import="com.Model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.HashMap" %>
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
            border: none; /* Remove the border */
            outline: none; /* Remove the outline */
            -webkit-appearance: none; /* Remove the default styling in WebKit browsers */
            -moz-appearance: none; /* Remove the default styling in Mozilla Firefox */
            appearance: none; /* Remove the default styling in other browsers */
            background-color: transparent; /* Set the background color as transparent */
            padding: 0; /* Remove padding */
            /* Add any additional styling as per your requirements */
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


            /*justify-content: center;*/
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
                <p class="mb-4">이 HTML 페이지는 고객 관리를 위한 관리자 대시보드를 나타냅니다. 이 페이지는 고객 정보를 표로 나타내며, 다양한 기능을 제공합니다. 관리자는 고객의
                    신상정보, 연락처, 신용 점수, 소득 및 직업 유형을 확인할 수 있습니다.</p>
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
                            onclick="location.href='/jsp/ManageEmployee/InsertEmployee.jsp'">관리자 직원 추가
                    </button>
                    <button class="btn btn-secondary btn-icon-split ml-auto btn-h" id="updateEmployeeBtn" disabled>관리자
                        직원 수정
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
                                    List<Employee> employees = (List<Employee>) request.getAttribute("employeeManageResDto");
                                    if (employees != null) {
                                        for (Object obj : employees) {
                                            if (obj instanceof Employee) {
                                                Employee employee = (Employee) obj;
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
                                                onchange="updateEmployeeAuth('<%= employee.getEmpIdx() %>', '<%= employee.getEmpName() %>')">
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


<script>
    //전체 권한 비활성화 상태에서 활성화로 전환 시키기
    <%
        request.setCharacterEncoding("UTF-8");
        employees = (List<Employee>) request.getAttribute("employeeManageResDto");
        if (employees != null) {
            for (Object obj : employees) {
                if (obj instanceof Employee) {
                    Employee employee = (Employee) obj;
    %>

    //관리자 직원 수정 버튼을 누르면 활성화로 전환됨
    document.getElementById("updateEmployeeBtn").addEventListener("click", function () {
        var element = document.getElementById("auth-<%= employee.getEmpIdx() %>");
        element.style.appearance = "menulist-button"; //아래 화살표 띄우기
        element.disabled = false; //비활성화 False로 처리
    });
    <%
                }
            }
        }
    %>
</script>
</div>

</body>
</html>