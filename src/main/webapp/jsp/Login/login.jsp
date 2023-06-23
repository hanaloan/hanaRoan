<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Login</title>
    <!-- 부트스트랩 CDN 링크 추가 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/login/login.css">

    <style>
        .login-form h2 {
            margin-bottom: 20px;
        }

        .error-box {
            margin-top: 35px;

        }
    </style>

</head>
<body>
<div class="container">
    <div class="logo_img">

    </div>
    <div class="login-form">
        <h2 class="text-center">Login</h2>



        <!-- 오류 메시지 출력 -->
        <c:if test="${not empty errorMessage}">
            <div class="error-box">
                <span class="error-message">${errorMessage}</span>
            </div>
        </c:if>


        <form action="/login" method="post">

            <div class="form-group">
                <label>
                    <input type="radio" id="userTypeUser" name="userType" value="user" checked>
                    User
                </label>
                <label>
                    <input type="radio" id="userTypeAdmin" name="userType" value="admin">
                    Admin
                </label>
            </div>

            <div class="form-group">
                <input type="hidden" id="userTypeHidden" name="userType" value="user">
            </div>

            <div class="form-group">
                <label for="username">ID</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block" value="Login">Log in</button>
            </div>
        </form>
    </div>
</div>


<!-- 부트스트랩 및 jQuery CDN 스크립트 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    // 폼과 라디오 버튼, 그리고 숨겨진 필드를 참조
    var form = document.getElementById('loginForm');
    var userTypeUser = document.getElementById('userTypeUser');
    var userTypeAdmin = document.getElementById('userTypeAdmin');
    var userTypeHidden = document.getElementById('userTypeHidden');

    // 폼이 제출될 때 실행될 이벤트 핸들러를 설정
    form.onsubmit = function(e) {
        // 관리자 라디오 버튼이 선택된 경우 숨겨진 필드의 값을 변경
        if (userTypeAdmin.checked) {
            userTypeHidden.value = "admin";
        }
        // 그렇지 않은 경우 (사용자 라디오 버튼이 선택된 경우) 숨겨진 필드의 값을 유지
        else if (userTypeUser.checked) {
            userTypeHidden.value = "user";
        }
    }
</script>

</body>
</html>