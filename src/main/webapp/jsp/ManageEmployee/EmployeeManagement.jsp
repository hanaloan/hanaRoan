<%@ page import="com.DAO.EmployeeManagementDao" %>
<%@ page import="com.Model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.HashMap" %>
<html>
<head>
    <title>직원 관리 페이지</title>
    <link rel="stylesheet" href="/css/EmployeeManagement/EmployeeManagement.css">
</head>
<body>

<script>
    window.onload = function() {
        if (window.location.pathname !== '/EmployeeManagement') {
            location.href = '/EmployeeManagement';
        }

        // 페이지 로드 시 드롭다운 버튼 상태 업데이트
        var selectElements = document.querySelectorAll("select[id^='auth-']");
        for (var i = 0; i < selectElements.length; i++) {
            updateAuthStatus(selectElements[i]);
        }

        var k='<%= request.getAttribute("empAuthName") %>';
        var updateEmployeeBtn = document.getElementById("updateEmployeeBtn");
        var insertEmployeeBtn = document.getElementById("insertEmployeeBtn");

        if (k==="all"){
            updateEmployeeBtn.disabled=false;
            insertEmployeeBtn.disabled=false;
        }
    }

    function updateEmployeeAuth(getEmpIdx, getEmpName) {
        //select 태그에서 선택한 값 가져옴
        var empIdx=getEmpIdx;
        var empAuthName = document.getElementById('auth-' + getEmpIdx).value;

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/UpdateEmpAuth", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send("empIdx=" + empIdx + "&empAuthName=" + empAuthName);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) { // 응답이 완료되었을 때만 실행
                if (xhr.status === 200) { // 성공적인 응답일 경우
                    updateAuthStatus(document.getElementById('auth-' + getEmpIdx));
                    // alert(empAuthName);
                    if (empAuthName==="none"){
                        alert(getEmpName+" 직원의 권한이 제한되었습니다.");
                    }
                    location.reload();
                } else {
                    console.log('오류 발생: ' + xhr.status);
                }
            }
        };

    }

//  직원의 권한을 바꾸면 다시 비활성화 상태로 바꿔야 함.
    function updateAuthStatus(selectElement){
        selectElement.disabled=true;
    }
</script>




<%@ include file="/jsp/AdminSidebar/AdminSidebar.jsp" %>
<div>
    <h3>현재 관리자</h3>
    <div class="box" style=" height: 100px; width: 100px">
        <img class="profile" src="/img/undraw_profile.svg">
    </div>

    <p>어서오세요!, 현재 관리자는 <%= request.getAttribute("empName") %>. 입니다~.</p>

    <p>당신의 인덱스는 <%=session.getAttribute("employee_idx")%> 입니다</p>
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
            <td>
                <%=employee.getEmpName() %>
            </td>
            <td>
<%--                전체 권한 비활성화로 세팅--%>
            <select style="appearance: none" disabled id="auth-<%= employee.getEmpIdx() %>"
                    onchange="updateEmployeeAuth('<%= employee.getEmpIdx() %>', '<%= employee.getEmpName() %>')">
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

    <button id="insertEmployeeBtn" disabled onclick="location.href='/jsp/ManageEmployee/InsertEmployee.jsp'">관리자 직원 추가</button>
    <button id="updateEmployeeBtn" disabled>관리자 직원 수정</button>


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
            var element = document.getElementById("auth-<%= employee.getEmpIdx() %>");
            element.style.appearance = "menulist-button"; //아래 화살표 띄우기
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