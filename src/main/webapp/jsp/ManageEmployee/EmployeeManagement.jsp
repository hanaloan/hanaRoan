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

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>



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
            List<Employee> employees = (List<Employee>) request.getAttribute("employeeManageResDto");
            if (employees != null) {
                for (Object obj : employees) {
                    if (obj instanceof Employee) {
                        Employee employee = (Employee) obj;


        %>


        <tr>
            <td><%=employee.getEmpName() %></td>
<%--            <td><%=employee.getEmpLevelName() %></td>--%>
            <td>
            <select style="appearance: none" disabled id="dropdownMenu-<%= employee.getEmpName() %>" class="loan-status-<%= employee.getEmpId() %>"
                    onchange="updateLoanStatus('<%= employee.getEmpLevelName() %>')">
                <option value="all" <%= employee.getEmpLevelName() != null && employee.getEmpLevelName().equals("all") ? "selected" : "" %>>
                    all
                </option>
                <option value="managing Products" <%= employee.getEmpLevelName() != null && employee.getEmpLevelName().equals("managing Products") ? "selected" : "" %>>
                    managing Products
                </option>
                <option value="managing Customers" <%= employee.getEmpLevelName() != null && employee.getEmpLevelName().equals("managing Customers") ? "selected" : "" %>>
                    managing Customers
                </option>
                <option value="read only" <%= employee.getEmpLevelName() != null && employee.getEmpLevelName().equals("read only") ? "selected" : "" %>>
                    read only
                </option>
                <option value="none" <%= employee.getEmpLevelName() != null && employee.getEmpLevelName().equals("none") ? "selected" : "" %>>
                    none
                </option>
            </select>
            </td>
        </tr>

        <%
                    }
                }
            }
        %>
    </table>


    <a href="/jsp/ManageEmployee/InsertEmployee.jsp">관리자 직원 추가</a>
    <button id="dropdownButton">관리자 직원 수정</button>



    <script>

        <%
            request.setCharacterEncoding("UTF-8");
            employees = (List<Employee>) request.getAttribute("employeeManageResDto");
            if (employees != null) {
                for (Object obj : employees) {
                    if (obj instanceof Employee) {
                        Employee employee = (Employee) obj;


        %>

        document.getElementById("dropdownButton").addEventListener("click", function() {
            console.log("버튼 누름")
            // "dropdownMenu- 뒤에 employee.getEmpName만 다 설정됨 다른 건 안되는데.. 왜그럴까?
            var element = document.getElementById("dropdownMenu-<%= employee.getEmpName() %>");
            element.style.appearance = "menulist-button";
            // element.classList.remove("disabled-dropdown");
            element.disabled  = false;
        });
        <%
                    }
                }
            }
        %>
    </script>
</div>

</body>
</html>