<%--
  Created by IntelliJ IDEA.
  User: seonghun.han
  Date: 2023-06-20
  Time: 오후 4:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <script>
        var counter = 10 * 60;
        var interval = setInterval(function() {
            counter--;
            if (counter <= 0) {
                clearInterval(interval);
                $('#time').text("Session expired");
                return;
            } else {
                var minutes = Math.floor(counter / 60);
                var seconds = counter % 60;
                $('#time').text(minutes + ":" + seconds);
            }
        }, 1000);

        function extendSession() {
            $.ajax({
                url: '${pageContext.request.contextPath}/extendSession',
                type: 'POST',
                success: function() {
                    counter = 10 * 60;
                },
                error: function() {
                    console.log("Error extending the session");
                }
            });
        }
    </script>
</head>
<body>
<%@ include file="/jsp/AdminSidebar/AdminSidebar.jsp" %>
<p>어서오세요!, 현재 관리자는 <%= session.getAttribute("username") %>. 입니다~.</p>
<p>어서오세요!, 현재 관리자 인덱스는 <%= session.getAttribute("employee_idx") %>. 입니다~.</p>

<div id="top-right">
    <p id="time">10:00</p>
    <button onclick="extendSession()">Extend session</button>
</div>
</body>
</html>