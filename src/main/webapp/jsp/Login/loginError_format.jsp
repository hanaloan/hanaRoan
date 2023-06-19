<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        .error-box {
            background-color: #f8d7da;
            color: #721c24;
            padding: 10px;
            border: 1px solid #f5c6cb;
            margin-bottom: 10px;
        }
    </style>
    <script>
        function showErrorMessage() {
            var errorBox = document.createElement('div');
            errorBox.className = 'error-box';
            errorBox.textContent = '비밀번호는 영문, 특수문자, 숫자 포함 8자 이상입니다.';
            document.body.appendChild(errorBox);
        }

        window.onload = function() {
            showErrorMessage();
        };
    </script>
</head>
<body>
</body>
</html>