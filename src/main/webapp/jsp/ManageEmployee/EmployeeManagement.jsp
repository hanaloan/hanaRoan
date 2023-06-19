<%@ page import="com.DAO.EmployeeManagementDao" %>
<%@ page import="com.Model.Employee" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Petchu
  Date: 2023-06-19
  Time: 오전 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>직원 관리 페이지</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/EmployeeManagement/EmployeeManagement.css">
</head>
<body>

<div>
    <h3>현재 관리자</h3>
    <div class="box" style=" height: 100px; width: 100px">
        <img class="profile" src="${pageContext.request.contextPath}/img/undraw_profile.svg">
    </div>
    <%-- Retrieve logged-in user from session --%>
    <% String loggedInUser = (String) session.getAttribute("loggedInUser"); %>

    <%-- Display logged-in user and perform admin functionality --%>
<%--    <% if (loggedInUser != null && loggedInUser.equals("admin")) { %>--%>
    <p>어서오세요!, 현재 관리자는 <%= loggedInUser %>. 입니다~.</p>
    <p>당신의 권한은 <%
        EmployeeManagementDao empDao = new EmployeeManagementDao();
        Employee cur_emp = empDao.currentEmployee(loggedInUser);

//            for(int i=0;i<list.size();i++){
    %>
        <%=cur_emp.getEmpLevel() %></p>

<%--    &lt;%&ndash; Perform admin functionality here &ndash;%&gt;--%>
<%--    <p>This is the admin functionality.</p>--%>
<%--    <% } else { %>--%>
<%--    <p>Access denied. You need to be logged in as an admin to view this page.</p>--%>
<%--    <% } %>--%>

</div>

<div class="table">
    <table>
        <tr>
            <th>
                관리자 명
            </th>
            <th>
                권한
            </th>
        </tr>
        <%
//            EmployeeManagementDao empDao = new EmployeeManagementDao();
            List<Employee> employees = empDao.selectEmployees();
            for (Employee employee : employees) {
//            for(int i=0;i<list.size();i++){
        %>
        <tr>
            <td><%=employee.getEmpName() %></td>
            <td><%=employee.getEmpLevel() %></td>
        </tr>
        <%
            }
        %>
    </table>
    <button id="popupButton" onclick="openPopup()">관리자 직원 추가</button>

    <script src="../../js/ManageEmployee/AddEmployeeScript.js"></script>

</div>
</body>
</html>
