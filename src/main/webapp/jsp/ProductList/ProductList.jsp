<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>대출 상품 < 하나론</title>
    <link href="../../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="../../css/sb-admin-2.min.css" rel="stylesheet">
    <script src="../../vendor/jquery/jquery.min.js"></script>
    <script src="../../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="../../vendor/jquery-easing/jquery.easing.min.js"></script>
</head>
<body id="page-top">
<div id="wrapper">
    <%@ include file="/jsp/Components/CustomerSidebar/CustomerSidebar.jsp" %>
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <%@ include file="/jsp/Components/AdminTopbar/AdminTopbar.jsp" %>
            <div class="container-fluid">
                <div class="row">
                    <div class="col justify-content-center">
                        <div class="row">
                            <div class="col text-center my-3 border-bottom">
                                <nav class="navbar navbar-expand-md navbar-light justify-content-center">
                                    <ul class="navbar-nav">
                                        <li class="nav-item">
                                            <a class="nav-link btn btn-primary text-white mx-4"
                                               href="productList" style="width: 150px;">전체</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link btn btn-primary text-white mx-4"
                                               href="productList?category=1" style="width: 150px;">전세</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link btn btn-primary text-white mx-4"
                                               href="productList?category=2" style="width: 150px;">월세</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link btn btn-primary text-white mx-4"
                                               href="productList?category=3" style="width: 150px;">담보</a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                        <div class="row">
                            <div class="container">
                                <%
                                    List<?> productList = (List<?>) request.getAttribute("productList");
                                    int pageSize = 8;
                                    int totalProducts = productList.size();
                                    int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

                                    if (productList.isEmpty()) {
                                %>
                                <p>No products found.</p>
                                <% } else {
                                    int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
                                    int start = (currentPage - 1) * pageSize;
                                    int end = Math.min(start + pageSize, totalProducts);
                                    for (int i = start; i < end; i++) {
                                        Object product = productList.get(i);
                                        String id = (String) product.getClass().getMethod("getId").invoke(product);
                                        String name = (String) product.getClass().getMethod("getName").invoke(product);
                                        String description = (String) product.getClass().getMethod("getDescription").invoke(product);
                                %>
                                <div class="card bord my-2">
                                    <div class="card-body">
                                        <h5 class="card-title"><a href="productDetail?id=<%= id %>"><%= name %>
                                        </a></h5>
                                        <p class="card-text" style="font-size: 15px;"><%= description %>
                                        </p>
                                    </div>
                                </div>
                                <% }
                                    if (totalPages > 1) {
                                %>
                                <footer style="margin: 30px 30px;">
                                    <nav aria-label="Product pagination">
                                        <ul class="pagination justify-content-center">
                                            <%
                                                String queryString = request.getQueryString();
                                                String baseURL = "productList";
                                                if (queryString != null) {
                                                    if (queryString.contains("page")) {
                                                        baseURL += "?" + queryString.substring(0, queryString.indexOf("page"));
                                                    } else {
                                                        baseURL += "?" + queryString + "&";
                                                    }
                                                } else {
                                                    baseURL += "?";
                                                }
                                            %>
                                            <% for (int i = 1; i <= totalPages; i++) { %>
                                            <li class="page-item <% if (currentPage == i) { %>active<% } %>">
                                                <a class="page-link" href="<%= baseURL %>page=<%= i %>"><%= i %></a>
                                            </li>
                                            <% } %>
                                        </ul>
                                    </nav>
                                </footer>
                                <% }
                                } %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/jsp/Components/AdminFooter/AdminFooter.jsp" %>
    </div>

</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>