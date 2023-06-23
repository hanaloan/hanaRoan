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
            var empIdx = selectElements[i].id.split('-')[1];
            // var loanStatus = document.getElementById('loan-status-' + lendId).value;
            updateAuthStatus(selectElements[i]);
        }

        var k='<%= request.getAttribute("empAuthName") %>';
        var updateEmployeeBtn = document.getElementById("updateEmployeeBtn");
        var insertEmployeeBtn = document.getElementById("insertEmployeeBtn");

        if (k==="all"){
            updateEmployeeBtn.disabled=false;
            insertEmployeeBtn.disabled=false;
        }
        console.log(k);




    }

    function updateEmployeeAuth(getEmpIdx) {
        //select 태그에서 선택한 값 가져옴
        <%--var empIdx='<%=session.getAttribute("employee_idx")%>';--%>
        var empIdx=getEmpIdx;
        var empAuthName = document.getElementById('auth-' + getEmpIdx).value;
        <%--console.log(employeeStatus)--%>
        // console.log(getEmpIdx)
        // console.log(typeof empAuthName)

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/UpdateEmpAuth", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send("empIdx=" + empIdx + "&empAuthName=" + empAuthName);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) { // 응답이 완료되었을 때만 실행
                if (xhr.status === 200) { // 성공적인 응답일 경우
                    updateAuthStatus(document.getElementById('auth-' + getEmpIdx), empAuthName);
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
        var row=selectElement.parentNode.parentNode;
        var column=row.getElementsByTagName('td');
        for (var i=1;i<column.length;i++){
            // column[i].style.backgroundColor = '#F0F0F0';
        }
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
            <select style="appearance: none" disabled id="auth-<%= employee.getEmpIdx() %>"
                    onchange="updateEmployeeAuth('<%= employee.getEmpIdx() %>')">
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


<%--    <a href="/jsp/ManageEmployee/InsertEmployee.jsp">관리자 직원 추가</a>--%>
<%--    <button id="updateEmployeeBtn" disabled--%>
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
        // if (employee.getEmpIdx()==1){
        //
        // }


        //관리자 직원 수정 버튼을 누르면 활성화로 전환됨
        document.getElementById("updateEmployeeBtn").addEventListener("click", function() {
            // "dropdownMenu- 뒤에 employee.getEmpName만 다 설정됨 다른 건 안되는데.. 왜그럴까?
            // 왜냐면 내가 애초에 select 함수로 가져온게, name, AuthName이기 때문에!
            // 그러면 authname은 왜 끊기냐면 각 속성에 따라서 쭉 나오니까 처음 그 권한 값이 나오는 애들만 표시가 되는 것임!
            // getEmpName은 값이 다 달라서 이렇게 가져와질 수 있음 근데 동명이인이라면.. 안됨.. 그래서 추가적으로 idx를 가져옴!
            var element = document.getElementById("auth-<%= employee.getEmpIdx() %>");
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