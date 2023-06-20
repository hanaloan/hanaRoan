<%@ page import="com.Model.CustomerManageDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Customer Management</title>
</head>
<body>
<h1>Customer Management</h1>
<form action="/CustomerManagement" method="post">
  <input type="submit" value="Load Customer Data">
</form>
<table>
  <tr>
    <th>Customer ID</th>
    <th>Customer Name</th>
    <th>Customer Password</th>
    <th>Contact Info</th>
    <th>Start Date</th>
    <th>End Date</th>
    <th>Loan Amount</th>
    <th>Loan Status</th>
    <th>payment Amount</th>
    <th>payment Status</th>
    <th>overdue Interest Rate</th>
  </tr>
  <%
    request.setCharacterEncoding("UTF-8");
    List<CustomerManageDto> customers = (List<CustomerManageDto>) request.getAttribute("customerManageResDto");
    if (customers != null) {
      for (Object obj : customers) {
        if (obj instanceof CustomerManageDto) {
          CustomerManageDto customer = (CustomerManageDto) obj;
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
    <td><%= customer.getStartDate() %>
    </td>
    <td><%= customer.getEndDate() %>
    </td>
    <td><%= customer.getLoanAmount() %>
    </td>
    <td><%= customer.getLoanStatus() %>
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
<form action="/createCustomerInfo" method="post">
  <input type="submit" value="Create Customer Data">
</form>
</body>
</html>