<%@ page import="com.Model.CustomerManagement" %>
<%@ page import="java.util.List" %>
<%@ page import="com.Model.CustomerRes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>고객관리</title>

    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="/css/CustomerManagement/style.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <script>
        window.onload = function () {
            if (window.location.pathname !== '/CustomerManagement') {
                location.href = '/CustomerManagement';
            }
        }
    </script>
</head>

<body id="page-top">
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
                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">고객 관리</h1>
                <p class="mb-4">이 HTML 페이지는 고객 관리를 위한 관리자 대시보드를 나타냅니다. 이 페이지는 고객 정보를 표로 나타내며, 다양한 기능을 제공합니다. 관리자는 고객의
                    신상정보, 연락처, 신용 점수, 소득 및 직업 유형을 확인할 수 있습니다.</p>
                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3"
                         style="display: flex; justify-content: space-between; align-items: center;">
                        <h6 class="m-0 font-weight-bold text-primary">고객 정보</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>고객 번호</th>
                                    <th>고객 이름</th>
                                    <th>고객 ID</th>
                                    <th>연락처</th>
                                    <th>주민번호</th>
                                    <th>신용점수</th>
                                    <th>소득</th>
                                    <th>직업 분류</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>고객 번호</th>
                                    <th>고객 이름</th>
                                    <th>고객 ID</th>
                                    <th>연락처</th>
                                    <th>주민번호</th>
                                    <th>신용점수</th>
                                    <th>소득</th>
                                    <th>직업 분류</th>
                                </tr>
                                </tfoot>
                                <%
                                    request.setCharacterEncoding("UTF-8");
                                    List<CustomerRes> customerResList = (List<CustomerRes>) request.getAttribute("customerResList");
                                    if (customerResList != null) {
                                        for (CustomerRes customer : customerResList) {
                                %>
                                <tr>
                                    <td><%= customer.getCusIdx() %>
                                    </td>
                                    <td><%= customer.getName() %>
                                    </td>
                                    <td><%= customer.getCusId() %>
                                    </td>
                                    <td><%= customer.getFormattedContactInfo() %>
                                    </td>
                                    <td><%= customer.getFormattedPersonalId() %>
                                    </td>
                                    <td class="text-right"><%= customer.getCreditScore() %> 점
                                    </td>
                                    <td class="text-right"><%= customer.getFormattedIncome() %> 원
                                    </td>
                                    <td><%= customer.getJobType() %>
                                    </td>
                                </tr>
                                <%
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

<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="/js/sb-admin-2.min.js"></script>
<script src="/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>
<script src="/js/demo/datatables-demo.js"></script>
</body>
</html>