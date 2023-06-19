<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Login</title>
    <!-- 부트스트랩 CDN 링크 추가 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login/login.css">

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


        <form action="${pageContext.request.contextPath}/login" method="post">
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



</body>
</html>