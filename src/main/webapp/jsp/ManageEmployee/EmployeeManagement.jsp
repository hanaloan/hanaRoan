<%@ page import="com.DAO.EmployeeManagementDao" %>
<%@ page import="com.Model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>직원 관리 페이지</title>
    <link rel="stylesheet" href="/css/EmployeeManagement/EmployeeManagement.css">
</head>
<body>
<script>
    window.onload = function() {
        if (!sessionStorage.getItem('loaded')) {
            sessionStorage.setItem('loaded', 'true');
            location.href = '/EmployeeManagement';
        }
    }
</script>

<%--<form action="/EmployeeManagement" method="get">--%>
<%--    <input type="submit" value="Load Employee Data">--%>
<%--</form>--%>

<%@ include file="/jsp/AdminSidebar/AdminSidebar.jsp" %>
<div>
    <h3>현재 관리자</h3>
    <div class="box" style=" height: 100px; width: 100px">
        <img class="profile" src="/img/undraw_profile.svg">
    </div>

    <p>어서오세요!, 현재 관리자는 <%= request.getAttribute("empName") %>. 입니다~.</p>

    <p>당신의 권한은
        <%= request.getAttribute("empAuthName") %></p>

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
            request.setCharacterEncoding("UTF-8");
//            EmployeeManagementDao empDao = new EmployeeManagementDao();
            List<Employee> employees = (List<Employee>) request.getAttribute("employeeManageResDto");

            if (employees != null) {
                for (Object obj : employees) {
                    if (obj instanceof Employee) {
                        Employee employee = (Employee) obj;


        %>
        <tr>
            <td><%=employee.getEmpName() %></td>
            <td><%=employee.getEmpLevelName() %></td>
        </tr>
        <%
                    }
                }
            }
        %>
    </table>
    <a href="/jsp/ManageEmployee/InsertEmployee.jsp">관리자 직원 추가</a>
</div>

</body>
</html>