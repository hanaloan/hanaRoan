<%--
  Created by IntelliJ IDEA.
  User: msw45
  Date: 2023-06-21
  Time: 오후 4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Enter Add Employee:</h2>
<form>
    <label for="empID">ID:</label>
    <input type="text" id="empID" name="empID"  placeholder="Parameter 1" required>
    <br>
    <label for="empPW">PW:</label>
    <input type="text" id="empPW" name="empPW" required>
    <br>
    <label for="empName">Name:</label>
    <input type="text" id="empName" name="empName" required>
    <br>

    <form action="${pageContext.request.contextPath}/EmployeeManagement" method="POST">
        <label for="empAuthorityName">권한:</label>
        <select name="empAuthorityName" id="empAuthorityName">
            <option value="all">all</option>
            <option value="managingProducts">managing Products</option>
            <option value="managingCustomers">managing Customers</option>
            <option value="readOnly">read only</option>
            <option value="none">none</option>
        </select>
        <!--                  <input type="submit" value="Submit" />-->
    </form>
    <br>
    <input type="submit" value="Create">
</form>


</body>
</html>
