<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Product List</title>
</head>
<body>
<jsp:include page="../CustomerGNB/CustomerGNB.jsp"></jsp:include>
<a href="productList">전체</a> <!-- Link to show all products -->
<a href="productList?category=1">전세</a>
<a href="productList?category=2">월세</a>
<a href="productList?category=3">담보</a>
<hr>

<% List<?> productList = (List<?>) request.getAttribute("productList");
  if (productList.isEmpty()) { %>
<p>No products found.</p>
<% } else {
  for (Object product : productList) {
    String id = (String) product.getClass().getMethod("getId").invoke(product);
    String name = (String) product.getClass().getMethod("getName").invoke(product);
    String description = (String) product.getClass().getMethod("getDescription").invoke(product);
%>
<p><a href="productDetail?id=<%= id %>"><strong><%= name%></strong></a></p>
<p><%= description %></p>
<hr>
<%     }
} %>
</body>
</html>