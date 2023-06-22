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

    function updateEmployeeAuth(EmpLevelName) {
        var employeeStatus = document.getElementById('loan-auth-' + EmpLevelName).value;

        if(employeeStatus =="all"){
        //  여기부터 작성하면 됨!

        }

        // var xhr = new XMLHttpRequest();
        // xhr.open("POST", "/ChangeLoanStatus", true);
        // xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        // xhr.send("lendId=" + lendId + "&loanStatus=" + loanStatus);
        //
        // xhr.onreadystatechange = function () {
        //     if (xhr.readyState === 4 && xhr.status === 200) {
        //         updateDropdownStatus(document.getElementById('loan-status-' + lendId), loanStatus);
        //
        //         location.reload();
        //     }
        // };
    }


</script>


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
<%--        <jsp:useBean id="employees" scope="request" class="java.util.ArrayList" type="java.util.List<com.Model.Employee>" />--%>
        <%
            request.setCharacterEncoding("UTF-8");
            List<Employee> employees = (List<Employee>) request.getAttribute("employeeManageResDto");
            if (employees != null) {
                for (Object obj : employees) {
                    if (obj instanceof Employee) {
                        Employee employee = (Employee) obj;
        %>

        <tr>
            <td>
                <%=employee.getEmpName() %>
            </td>
<%--            <td><%=employee.getEmpLevelName() %></td>--%>
            <td>
<%--                전체 권한 비활성화로 세팅--%>
            <select style="appearance: none" disabled id="auth-<%= employee.getEmpName() %>" class="loan-status-<%= employee.getEmpId() %>"
                    onchange="updateEmployeeAuth('<%= employee.getEmpLevelName() %>')">
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
    <button id="updateEmployeeBtn">관리자 직원 수정</button>

    <script>

        //전체 권한 비활성화 상태에서 활성화로 전환 시키기
        <%
            request.setCharacterEncoding("UTF-8");
            employees = (List<Employee>) request.getAttribute("employeeManageResDto");
            if (employees != null) {
                for (Object obj : employees) {
                    if (obj instanceof Employee) {
                        Employee employee = (Employee) obj;


        %>

        //관리자 직원 수정 버튼을 누르면 활성화로 전환됨
        document.getElementById("updateEmployeeBtn").addEventListener("click", function() {
            // "dropdownMenu- 뒤에 employee.getEmpName만 다 설정됨 다른 건 안되는데.. 왜그럴까?
            var element = document.getElementById("auth-<%= employee.getEmpName() %>");
            element.style.appearance = "menulist-button"; //아래 화살표 띄우기
            // element.classList.remove("disabled-dropdown");
            element.disabled  = false; //비활성화 False로 처리
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