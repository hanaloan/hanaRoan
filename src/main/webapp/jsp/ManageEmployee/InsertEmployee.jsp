<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>직원 추가</title>
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="/css/InsertEmployee/InsertEmployee.css" rel="stylesheet">
</head>
<body class="bg-gradient-primary">
<div class="container">
    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <div class="p-5">
                <div class="text-center">
                    <h2>Add Employee</h2>
                    <br>
                </div>
                <form action="/InsertEmployee" method="post" class="user">
                    <div class="form-group col">
                        <div class="form-group row">
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <label for="empId">ID:</label>
                                <input class="form-control form-control-user" type="text" id="empId" name="empId"
                                       placeholder="id" required>
                            </div>
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
                                       placeholder="이름" required pattern="[ㄱ-ㅎㅏ-ㅣ가-힣]+" title="한글만 입력해주세요">

                            </div>
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <label for="empAuthorityName">Authority:</label>
                                <select class="form-control " name="empAuthorityName" id="empAuthorityName">
                                    <option value="all">all</option>
                                    <option value="managingProducts">managing Products</option>
                                    <option value="managingCustomers">managing Customers</option>
                                    <option value="readOnly">read only</option>
                                    <option value="none">none</option>
                                </select>
                            </div>
                        </div>
                        <div class="button-container">
                            <a href="/EmployeeManagement"><button type="button" class="btn btn-primary btn-icon-split btn-lg submit-style">돌아가기</button></a>
                            <button type="submit" value="Submit" class="btn btn-primary btn-icon-split btn-lg submit-style">직원등록</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="/js/sb-admin-2.min.js"></script>
<script src="/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>
<script src="/js/demo/datatables-demo.js"></script>
</body>
</html>
