<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>현재 관리자는 <%= session.getAttribute("username") %>. 입니다~.</p>
    <p>이거는 인덱스인데 신경안쓰셔도 됩니다. :  <%= request.getAttribute("employee_idx") %></p>
</body>
</html>
