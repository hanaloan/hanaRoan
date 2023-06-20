<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateCustomer</title>
</head>
<body>
  <h1>!!!!!Create Customer Page 입니다!!!!!</h1>
  <form action="/CustomerManagement" method="post">
      <label for="name">Name:</label>
      <input type="text" id="name" name="name"><br>

      <label for="contact_info">Contact Info:</label>
      <input type="text" id="contact_info" name="contact_info"><br>

      <label for="cus_id">Customer ID:</label>
      <input type="text" id="cus_id" name="cus_id"><br>

      <label for="password">Password:</label>
      <input type="password" id="password" name="password"><br>

      <input type="submit" value="Create Customer">
  </form>
</body>
</html>
