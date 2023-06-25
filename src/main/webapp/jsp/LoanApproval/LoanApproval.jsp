<%@ page import="com.Model.CustomerManagement" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>대출 승인 < 하나론</title>

    <!-- Custom fonts for this template -->
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">

    <link href="/css/LoanApproval/style.css" rel="stylesheet">


    <!-- Custom styles for this page -->
    <link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    <!-- Bootstrap core JavaScript-->
    <script src="/vendor/jquery/jquery.min.js"></script>

    <script>
        function updateLoanStatus(lendId) {
            var loanStatus = document.getElementById('loan-status-' + lendId).value;

            if (loanStatus == "approved") {
                createLoanPayment(lendId, loanStatus);
            }

            var xhr = new XMLHttpRequest();
            xhr.open("POST", "/ChangeLoanStatus", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send("lendId=" + lendId + "&loanStatus=" + loanStatus);

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    updateDropdownStatus(document.getElementById('loan-status-' + lendId), loanStatus);

                    location.reload();
                }
            };
        }


        function createLoanPayment(lendId, loanStatus) {
            if (loanStatus == "approved") {
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "/CreateLoanPayment", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.send("lendId=" + lendId);

                // 작업이 완료되면 페이지를 새로고침
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        location.reload();
                    }
                };
            }
        }

        window.onload = function () {
            if (window.location.pathname !== '/LoanApproval') {
                location.href = '/LoanApproval';
            }

            // 페이지 로드 시 드롭다운 버튼 상태 업데이트
            var selectElements = document.querySelectorAll("select[id^='loan-status-']");
            for (var i = 0; i < selectElements.length; i++) {
                var lendId = selectElements[i].id.split('-')[2];
                var loanStatus = document.getElementById('loan-status-' + lendId).value;
                updateDropdownStatus(selectElements[i], loanStatus);
            }
        }

        function updateDropdownStatus(selectElement, loanStatus) {
            if (loanStatus === 'pending') {
                selectElement.disabled = false;
            } else {
                selectElement.disabled = true;
                if (loanStatus === 'denied') {
                    var row = selectElement.parentNode.parentNode;
                    var cells = row.getElementsByTagName('td');
                    for (var i = 7; i < cells.length; i++) {
                        cells[i].style.backgroundColor = '#F0F0F0'; // 회색으로 변경
                        cells[i].style.color = '#999999'; // 글자색 변경
                    }
                }
            }
        }

        $(document).ready(function() {
            var table = $('#dataTable').DataTable();

            function attachDropdownHandlers() {
                table.$('select[id^="loan-status-"]').off('change').change(function() {
                    var lendId = $(this).attr('id').split('-')[2];
                    var loanStatus = $(this).val();
                    updateLoanStatus(lendId, loanStatus);
                    updateDropdownStatus(this, loanStatus);
                });
            }

            table.on('init.dt', attachDropdownHandlers);
            table.on('draw.dt', attachDropdownHandlers); // 'page.dt' 대신 'draw.dt' 이벤트를 사용합니다.
        });
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
                <h1 class="h3 mb-2 text-gray-800">대출 승인 관리</h1>
                <p class="mb-4">이 페이지는 대출 승인을 관리하는 웹 페이지입니다. 고객들의 대출 정보를 테이블로 나타내고, 대출 상태를 드롭다운 버튼을 통해 업데이트할 수 있습니다. 승인된
                    대출에는 상환 정보가 표시되며, 승인되지 않은 대출은 해당 정보가 표시되지 않습니다</p>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3"
                         style="display: flex; justify-content: space-between; align-items: center;">
                        <h6 class="m-0 font-weight-bold text-primary">대출 승인 현황</h6>
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
                                    <th>대출 시작일</th>
                                    <th>대출 종료일</th>
                                    <th>Loan Amount</th>
                                    <th>Loan Status</th>
                                    <th>Repayment ID</th>
                                    <th>payment Amount</th>
                                    <th>payment Status</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Customer ID</th>
                                    <th>Customer Name</th>
                                    <th>Lend ID</th>
                                    <th>Loan Type</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Loan Amount</th>
                                    <th>Loan Status</th>
                                    <th>Repayment ID</th>
                                    <th>payment Amount</th>
                                    <th>payment Status</th>
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
                                    <td><%= customer.getStartDate() %>
                                    </td>
                                    <td><%= customer.getEndDate() %>
                                    </td>
                                    <td class="text-right"><%= customer.getFormattedLoanAmount() %> 원
                                    </td>
                                    <td>
                                        <select id="loan-status-<%= customer.getLendId() %>"
                                                onchange="updateLoanStatus('<%= customer.getLendId() %>')">
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

</body>
</html>