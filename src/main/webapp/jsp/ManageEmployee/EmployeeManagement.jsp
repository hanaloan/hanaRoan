<%@ page import="com.DAO.EmployeeManagementDao" %>
<%@ page import="com.Model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>직원 관리 페이지</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/EmployeeManagement/EmployeeManagement.css">
</head>
<body>
<script>
    window.onload = function() {
        if (!sessionStorage.getItem('loaded')) {
            sessionStorage.setItem('loaded', 'true');
            location.href = '${pageContext.request.contextPath}/EmployeeManagement';
        }
    }
</script>

<%--<form action="${pageContext.request.contextPath}/EmployeeManagement" method="get">--%>
<%--    <input type="submit" value="Load Employee Data">--%>
<%--</form>--%>


<div>
    <h3>현재 관리자</h3>
    <div class="box" style=" height: 100px; width: 100px">
        <img class="profile" src="${pageContext.request.contextPath}/img/undraw_profile.svg">
    </div>
<%--    <% String loggedInUser = request.getAttribute("username").toString(); %>--%>

    <p>어서오세요!, 현재 관리자는 <%= request.getAttribute("empName") %>. 입니다~.</p>


    <p>당신의 권한은 <%
        %>
        <%= request.getAttribute("empAuth") %></p>



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

//            for (Employee employee : employees) {
//            System.out.println("가져옴?");
//            System.out.println(employees);
            if (employees != null) {
                for (Object obj : employees) {
                    if (obj instanceof Employee) {
                        Employee employee = (Employee) obj;


        %>
        <tr>
            <td><%=employee.getEmpName() %></td>
            <td><%=employee.getEmpLevel() %></td>
        </tr>
        <%
                    }
                }
            }
        %>
    </table>
    <button id="popupButton" onclick="openPopup()">관리자 직원 추가</button>

    <script src="../../js/ManageEmployee/AddEmployeeScript.js"></script>

</div>
</body>
</html>