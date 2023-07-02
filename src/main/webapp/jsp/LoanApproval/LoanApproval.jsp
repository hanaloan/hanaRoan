<%@ page import="com.Model.CustomerManagement" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>대출 승인 < 하나론</title>
    <link href="../../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="../../css/sb-admin-2.min.css" rel="stylesheet">
    <link href="../../css/LoanApproval/style.css" rel="stylesheet">
    <link href="../../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <script src="../../vendor/jquery/jquery.min.js"></script>
    <script>const authType = '<%= session.getAttribute("authType") %>';</script>
    <script src="../../js/LoanApproval/LoanApproval.js"></script>
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
                <h1 class="h3 mb-2 text-gray-800">대출 승인 관리</h1>
                <p class="mb-4">이 페이지는 대출 승인을 관리하는 웹 페이지입니다. 고객들의 대출 정보를 테이블로 나타내고, 대출 상태를 드롭다운 버튼을 통해 업데이트할 수 있습니다. 승인된
                    대출에는 상환 정보가 표시되며, 승인되지 않은 대출은 해당 정보가 표시되지 않습니다</p>
                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3"
                         style="display: flex; justify-content: space-between; align-items: center;">
                        <h6 class="m-0 font-weight-bold text-primary">대출 승인 현황</h6>
                        <div><a href="LoanApproval?loanStatus=pending"
                                class="btn btn-light btn-icon-split">
                            <span class="text">PENDING 상태 보기</span>
                        </a>
                            <a href="LoanApproval"
                               class="btn btn-light btn-icon-split">
                                <span class="text">전체 보기</span>
                            </a></div>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable">
                                <thead>
                                <tr>
                                    <th>고객ID</th>
                                    <th>고객명</th>
                                    <th>대출ID</th>
                                    <th>대출 타입</th>
                                    <th>대출 이름</th>
                                    <th>대출 기간</th>
                                    <th>대출 시작일</th>
                                    <th>대출 종료일</th>
                                    <th>대출 금액</th>
                                    <th>대출 상태</th>
                                    <th>상환ID</th>
                                    <th>상환 금액</th>
                                    <th>상환 상태</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>고객ID</th>
                                    <th>고객명</th>
                                    <th>대출ID</th>
                                    <th>대출 타입</th>
                                    <th>대출 이름</th>
                                    <th>대출 기간</th>
                                    <th>대출 시작일</th>
                                    <th>대출 종료일</th>
                                    <th>대출 금액</th>
                                    <th>대출 상태</th>
                                    <th>상환ID</th>
                                    <th>상환 금액</th>
                                    <th>상환 상태</th>
                                </tr>
                                </tfoot>
                                <%
                                    request.setCharacterEncoding("UTF-8");
                                    List<CustomerManagement> customers = (List<CustomerManagement>) request.getAttribute("customerManagementList");
                                    if (customers != null) {
                                        for (Object obj : customers) {
                                            if (obj instanceof CustomerManagement) {
                                                CustomerManagement customer = (CustomerManagement) obj;
                                %>
                                <tr>
                                    <td><%= customer.getCusId() %>
                                    </td>
                                    <td><%= customer.getName() %>
                                    </td>
                                    <td><%= customer.getLendId() %>
                                    </td>
                                    <td><%= customer.getLoanTypeName() %>
                                    </td>
                                    <td><%= customer.getLoanName() %>
                                    </td>
                                    <td><%= customer.getLendPeriod() %>
                                    </td>
                                    <td><%= customer.getStartDate() %>
                                    </td>
                                    <td><%= customer.getEndDate() %>
                                    </td>
                                    <td class="text-right"><%= customer.getFormattedLoanAmount() %> 원
                                    </td>
                                    <td>
                                        <select id="loan-status-<%= customer.getLendId() %>"
                                                <% if (!("all".equals(session.getAttribute("authType")) || "managing Customers".equals(session.getAttribute("authType")))) { %>
                                                disabled
                                                <% } %>
                                                onchange="updateLoanStatus('<%= customer.getLendId() %>', '<%= customer.getLendPeriod() %>')">
                                            <option value="pending" <%= customer.getLoanStatus() != null && customer.getLoanStatus().equals("pending") ? "selected" : "" %>>
                                                Pending
                                            </option>
                                            <option value="approved" <%= customer.getLoanStatus() != null && customer.getLoanStatus().equals("approved") ? "selected" : "" %>>
                                                Approved
                                            </option>
                                            <option value="denied" <%= customer.getLoanStatus() != null && customer.getLoanStatus().equals("denied") ? "selected" : "" %>>
                                                Denied
                                            </option>
                                        </select>
                                    </td>
                                    <% if (customer.getLoanStatus() != null && customer.getLoanStatus().equals("approved")) { %>
                                    <td><%= customer.getRepaymentId() %>
                                    </td>
                                    <td class="text-right"><%= customer.getFormattedPaymentAmount() %> 원
                                    </td>
                                    <td><%= customer.getPaymentStatus() %>
                                    </td>
                                    <% } else { %>
                                    <td>&#8212;</td>
                                    <td>&#8212;</td>
                                    <td>&#8212;</td>
                                    <% } %>
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

<script src="../../vendor/jquery/jquery.min.js"></script>
<script src="../../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../../vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="../../js/sb-admin-2.min.js"></script>
<script src="../../vendor/datatables/jquery.dataTables.min.js"></script>
<script src="../../vendor/datatables/dataTables.bootstrap4.min.js"></script>
<script src="../../js/demo/datatables-demo.js"></script>
</body>
</html>