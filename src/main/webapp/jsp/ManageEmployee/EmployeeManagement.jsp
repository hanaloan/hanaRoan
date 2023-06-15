<%--
  Created by IntelliJ IDEA.
  User: msw45
  Date: 2023-06-15
  Time: 오전 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.Model.Employee"%>
<%@ page import="com.DAO.EmployeeManagementDao"%>

<%
    request.setCharacterEncoding("UTF-8");
//list 불러오기
    ArrayList<EmployeeManagementDao> list = new ArrayList<EmployeeManagementDao>();
    list = (ArrayList<EmployeeManagementDao>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
    <title>직원 관리 페이지</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/EmployeeManagement/EmployeeManagement.css">
</head>
<body>
<%--<%@include file="../TopMenu.jsp"%>--%>
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
            EmployeeManagementDao empDao = new EmployeeManagementDao();
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
</div>

</body>
</html>
