
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script>
    window.onload = function() {
        if (!sessionStorage.getItem('loaded')) {
            sessionStorage.setItem('loaded', 'true');
            location.href = '/InsertEmployee';
        }

        // if (window.location.pathname !== '/InsertEmployee') {
        //     location.href = '/InsertEmployee';
        // }

    }
</script>
<h2>Enter Add Employee:</h2>


    <form action="/InsertEmployee" method="post">
        <label for="empId">ID:</label>
        <input type="text" id="empId" name="empId"  placeholder="Parameter 1" required>
        <br>
        <label for="empPw">PW:</label>
        <input type="text" id="empPw" name="empPw" required>
        <br>
        <label for="empName">Name:</label>
        <input type="text" id="empName" name="empName" required>
        <br>
        <label for="empAuthorityName">권한:</label>
        <select name="empAuthorityName" id="empAuthorityName">
            <option value="all">all</option>
            <option value="managingProducts">managing Products</option>
            <option value="managingCustomers">managing Customers</option>
            <option value="readOnly">read only</option>
            <option value="none">none</option>
        </select>

        <br>
        <input type="submit" value="Submit" />

    </form>

</body>
</html>
