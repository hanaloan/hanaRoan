<%@ page import="com.Model.CustomerManagement" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>대출 승인 관리</title>
    <link rel="stylesheet" href="/css/sb-admin-2.css">
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
    </script>

</head>
<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Start of Sidebar -->
    <%@ include file="/jsp/AdminSidebar/AdminSidebar.jsp" %>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">대출 승인</h1>
                <p class="mb-4">이 페이지는 대출 승인을 관리하는 웹 페이지입니다. 고객들의 대출 정보를 테이블로 나타내고, 대출 상태를 드롭다운 버튼을 통해 업데이트할 수 있습니다. 승인된
                    대출에는 상환
                    정보가 표시되며, 승인되지 않은 대출은 해당 정보가 표시되지 않습니다.</p>
                <a href="/jsp/CustomerManagement/CustomerManagement.jsp">
                    <button>고객 관리 페이지</button>
                </a>
                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">대출 승인 관리</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
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
                                    <td><%= customer.getLoanAmount() %>
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
                                    <td><%= customer.calculatePaymentAmount() %>
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
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2020</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->
    </div>
    <!-- End of Content Wrapper -->
</div>
<!-- End of Page Wrapper -->
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
</body>
</html>