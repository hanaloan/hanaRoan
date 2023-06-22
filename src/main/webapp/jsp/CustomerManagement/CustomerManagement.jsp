<%@ page import="com.Model.CustomerManagement" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Customer Management</title>
  <link rel="stylesheet" href="/css/CustomerManagement/Style.css" type="text/css">
</head>
<body>
<script>
  window.onload = function () {
    if (window.location.pathname !== '/CustomerManagement') {
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
    <th>Credit Score</th>
    <th>Income</th>
    <th>Job Type</th>
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
    <td><%= customer.getCreditScore() %>
    </td>
    <td><%= customer.getIncome() %>
    </td>
    <td><%= customer.getJobType() %>
    </td>
  </tr>
  <%
        }
      }
    }
  %>

</table>
<a href="/jsp/LoanApproval/LoanApproval.jsp"><button>대출 승인 페이지</button></a>
</body>
</html>