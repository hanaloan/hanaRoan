<%@ page import="com.Model.CustomerManagement" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>대출 승인 관리</title>
    <link rel="stylesheet" href="/css/CustomerManagement/Style.css" type="text/css">
    <script>
        function updateLoanStatus(lendId) {
            var loanStatus = document.getElementById('loan-status-' + lendId).value;
            createLoanPayment(lendId, loanStatus);

            var xhr = new XMLHttpRequest();
            xhr.open("POST", "/ChangeLoanStatus", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send("lendId=" + lendId + "&loanStatus=" + loanStatus);
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
<body>
<h1>대출승인페이지</h1>


<table>
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
        <td><%= customer.getRepaymentId() %>
        </td>
        <td><%= customer.getPaymentAmount() %>
        </td>
        <td><%= customer.getPaymentStatus() %>
        </td>
    </tr>
    <%
                }
            }
        }
    %>

</table>
<a href="/jsp/CustomerManagement/CustomerManagement.jsp"><button>고객 관리 페이지</button></a>
</body>
</html>