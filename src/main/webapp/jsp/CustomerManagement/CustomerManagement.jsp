<%@ page import="com.Model.CustomerManagement" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Customer Management</title>
  <link rel="stylesheet" href="/css/CustomerManagement/Style.css" type="text/css">
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
<button onclick="location.href='/CustomerManagement?loanType=월세대출'">월세 대출</button>
<button onclick="location.href='/CustomerManagement?loanType=전세대출'">전세 대출</button>
<button onclick="location.href='/CustomerManagement?loanType=담보대출'">담보 대출</button>
<button onclick="location.href='/CustomerManagement'">전체 대출 보기</button>

<table>
  <tr>
    <th>Customer ID</th>
    <th>Customer Name</th>
    <th>Customer Password</th>
    <th>Contact Info</th>
    <th>Lend ID</th>
    <th>Loan Type</th>
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
    <td><%= customer.getLoanTypeName() %>
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
<a href="/jsp/CustomerManagement/CreateCustomer.jsp"><button>고객 생성</button></a>
<a href="/jsp/CustomerManagement/ApplyLoan.jsp"><button>고객 대출 상품 등록</button></a>
<h1>수정해야 할 것</h1>
<p>end date -> start date에 lend period 더하도록 수정</p>
<p>overdue interest rate 기준 설정 </p>
<p>숫자 및 contanct info 설정(전화번호 010-1234-1234 형식, 금액 1,000,000,000원)</p>
<p>대출 상품 등록 시 유저 최소 신용도 등 자격 조건 체크 + 얼마 대출할건지</p>
<p>pending 상태인 상품만 조회할 수 있는 기능</p>
<p>유저 가입할 때 신용도 등 테이블 조인</p>

</body>
</html>