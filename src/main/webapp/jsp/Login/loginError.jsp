<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login/login.css">
    <style>
        .login-error-box {
            width: 300px;
            height: 100px;
            background-color: #f8d7da;
            color: #721c24;
            text-align: center;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #f5c6cb;
            border-radius: 5px;
        }
    </style>
    <script>
        // 작은 윈도우 창으로 JSP 페이지를 띄우는 함수
        function openWindow(url) {
            window.open(url, "_blank", "width=400,height=200");
        }
    </script>
</head>
<body>
<script>
    // 작은 윈도우 창으로 JSP 페이지를 띄우기
    openWindow("${pageContext.request.contextPath}/jsp/login/loginError.jsp");
</script>
<div class="login-error-box">
    <p>아이디, 비밀번호를 확인해주세요.</p>
</div>
</body>
</html>