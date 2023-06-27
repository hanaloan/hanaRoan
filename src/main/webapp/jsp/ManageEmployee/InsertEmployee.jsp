<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>직원 추가</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">


    <style>
        .submit-style {
            width: 300px;
            height: 50px;
            margin: 0 auto; /* Center align the button */
            display: flex;
            justify-content: center;
            align-items: center;
        }

    </style>


</head>
<body class="bg-gradient-primary">

<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <%--            <div class="col">--%>
            <%--                <div class="col-lg-7">--%>
            <div class="p-5">
                <div class="text-center">

                    <h2>Add Employee</h2>
                    <br></br>
                </div>


                <form action="/InsertEmployee" method="post" class="user">
                    <div class="form-group col">
                        <div class="form-group row">
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <label for="empId">ID:</label>
                                <input class="form-control form-control-user" type="text" id="empId" name="empId"
                                       placeholder="id" required>
                            </div>
                            <%--    <br>--%>
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <label for="empPw">PW:</label>
                                <input class="form-control form-control-user" type="text" id="empPw" name="empPw"
                                       placeholder="password" required>
                            </div>

                        </div>
                    </div>
                    <div class="form-group col">
                        <div class="form-group row">

                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <label for="empName">Name:</label>
                                <input class="form-control form-control-user" type="text" id="empName"
                                       name="empName"
                                       placeholder="name" required>
                            </div>

                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <label for="empAuthorityName">권한:</label>
                                <select class="form-control " name="empAuthorityName" id="empAuthorityName">
                                    <option value="all">all</option>
                                    <option value="managingProducts">managing Products</option>
                                    <option value="managingCustomers">managing Customers</option>
                                    <option value="readOnly">read only</option>
                                    <option value="none">none</option>
                                </select>
                            </div>


                        </div>
                        <br>
                        <input type="submit" value="Submit" class="btn btn-primary btn-icon-split btn-lg submit-style">

                    </div>


                </form>
            </div>
            <script src="/vendor/jquery/jquery.min.js"></script>
            <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

            <!-- Core plugin JavaScript-->
            <script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

            <!-- Custom scripts for all pages-->
            <script src="/js/sb-admin-2.min.js"></script>

            <!-- Page level plugins -->
            <script src="/vendor/datatables/jquery.dataTables.min.js"></script>
            <script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>

            <!-- Page level custom scripts -->
            <script src="/js/demo/datatables-demo.js"></script>

</body>
</html>
