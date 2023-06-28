<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Product List</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    #navBar a.btn {
      padding: 10px 20px; /* Adjust the padding to increase button size */
      font-size: 16px; /* Adjust the font size */
    }
  </style>
</head>
<body>
<%--<jsp:include page="../CustomerGNB/CustomerGNB.jsp"></jsp:include>--%>
<jsp:include page="tmpGNB.jsp"></jsp:include>
<div id="navBar" class="text-center my-5">
  <a href="productList" class="btn btn-primary mx-2">전체</a>
  <a href="productList?category=1" class="btn btn-primary mx-2">전세</a>
  <a href="productList?category=2" class="btn btn-primary mx-2">월세</a>
  <a href="productList?category=3" class="btn btn-primary mx-2">담보</a>
</div>

<div class="container my-5">
<%
  List<?> productList = (List<?>) request.getAttribute("productList");
  int pageSize = 10; // Number of cards per page
  int totalProducts = productList.size();
  int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

  if (productList.isEmpty()) {
%>
<p>No products found.</p>
<% } else {
  // Get the current page from the request parameters (assuming it's a GET request)
  int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
  int start = (currentPage - 1) * pageSize;
  int end = Math.min(start + pageSize, totalProducts);

  for (int i = start; i < end; i++) {
    Object product = productList.get(i);
    String id = (String) product.getClass().getMethod("getId").invoke(product);
    String name = (String) product.getClass().getMethod("getName").invoke(product);
    String description = (String) product.getClass().getMethod("getDescription").invoke(product);
%>
<!-- Display product card -->
<div class="card">
  <div class="card-body">
    <h5 class="card-title"><a href="productDetail?id=<%= id %>"><%= name %></a></h5>
    <p class="card-text"><%= description %></p>
  </div>
</div>
<hr>
<%     }

  // Display pagination if needed
  if (totalPages > 1) {
%>
<nav aria-label="Product pagination">
  <ul class="pagination justify-content-center">
    <% for (int i = 1; i <= totalPages; i++) { %>
    <li class="page-item <% if (currentPage == i) { %>active<% } %>">
      <a class="page-link" href="?page=<%= i %>"><%= i %></a>
    </li>
    <% } %>
  </ul>
</nav>
<%   }
} %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>