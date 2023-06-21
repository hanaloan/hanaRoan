<%@ page import="com.Model.CustomerManagement" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Customer Management</title>
  <script>
    function updateLoanStatus(lendId) {
      console.log(lendId)
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
        xhr.onreadystatechange = function() {
          if (xhr.readyState === 4 && xhr.status === 200) {
            location.reload();
          }
        };
      }
    }
  </script>

</head>
<body>
<script>
  window.onload = function() {
    if (!sessionStorage.getItem('loaded')) {
      sessionStorage.setItem('loaded', 'true');
      location.href = '/CustomerManagement';
    }
  }
</script>


<h1>Customer Management</h1>
<table>
  <tr>
    <th>Customer ID</th>
    <th>Customer Name</th>
    <th>Customer Password</th>
    <th>Contact Info</th>
    <th>Lend ID</th>
    <th>Start Date</th>
    <th>End Date</th>
    <th>Loan Amount</th>
    <th>Loan Status</th>
    <th>Repayment ID</th>
    <th>payment Amount</th>
    <th>payment Status</th>
    <th>overdue Interest Rate</th>
  </tr>
  <%
    request.setCharacterEncoding("UTF-8");
    List<CustomerManagement> customers = (List<CustomerManagement>) request.getAttribute("customerManageResDto");
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
    <td><%= customer.getPassword() %>
    </td>
    <td><%= customer.getContactInfo() %>
    </td>
    <td><%= customer.getLendId() %>
    </td>
    <td><%= customer.getStartDate() %>
    </td>
    <td><%= customer.getEndDate() %>
    </td>
    <td><%= customer.getLoanAmount() %>
    </td>
    <td>
      <select id="loan-status-<%= customer.getLendId() %>" onchange="updateLoanStatus('<%= customer.getLendId() %>')">
        <option value="pending" <%= customer.getLoanStatus() != null && customer.getLoanStatus().equals("pending") ? "selected" : "" %>>Pending</option>
        <option value="approved" <%= customer.getLoanStatus() != null && customer.getLoanStatus().equals("approved") ? "selected" : "" %>>Approved</option>
        <option value="denied" <%= customer.getLoanStatus() != null && customer.getLoanStatus().equals("denied") ? "selected" : "" %>>Denied</option>
        <option value="paid" <%= customer.getLoanStatus() != null && customer.getLoanStatus().equals("paid") ? "selected" : "" %>>Paid</option>
      </select>
    </td>
    <td><%= customer.getRepaymentId() %>
    </td>
    <td><%= customer.getPaymentAmount() %>
    </td>
    <td><%= customer.getPaymentStatus() %>
    </td>
    <td><%= customer.getOverdueInterestRate() %>
    </td>
  </tr>
  <%
        }
      }
    }
  %>

</table>
<a href="/jsp/CustomerManagement/CreateCustomer.jsp">고객 생성</a>
<a href="/jsp/CustomerManagement/ApplyLoan.jsp">고객 대출 상품 등록</a>

</body>
</html>