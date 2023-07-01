<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>대시보드 < 하나론</title>

    <link href="../../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
    <link href="../../css/sb-admin-2.min.css" rel="stylesheet">
    <script src="../../vendor/jquery/jquery.min.js"></script>
    <script src="../../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="../../vendor/jquery-easing/jquery.easing.min.js"></script>

</head>
<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <%@ include file="/jsp/Components/AdminSidebar/AdminSidebar.jsp" %>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <%@ include file="/jsp/Components/AdminTopbar/AdminTopbar.jsp" %>

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                </div>

                <!-- Content Row -->
                <div class="row">
                    <% NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
                       String loanAmount = numberFormat.format(Float.parseFloat(request.getAttribute("loanAmount").toString()));
                       String overdueLoanAmount = numberFormat.format(Float.parseFloat(request.getAttribute("overdueLoanAmount").toString()));
                       String countPendingLends = request.getAttribute("countPendingLends").toString();
                       Float overduePercentage = Float.parseFloat(request.getAttribute("overduePercentage").toString());
                       Map<String, String> ratioOfLoanType = (Map<String, String>) request.getAttribute("ratioOfLoanType");
                       String monthlyRentPercentage = ratioOfLoanType.get("월세대출") == null ? "0" : ratioOfLoanType.get("월세대출");
                       String yearlyRentPercentage = ratioOfLoanType.get("전세대출") == null ? "0" :  ratioOfLoanType.get("전세대출");
                       String collateralRentPercentage = ratioOfLoanType.get("담보대출") == null ? "0" : ratioOfLoanType.get("담보대출");

                    %>

                    <!-- 총 대출 금액 (진행 중) Card -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            총 대출 금액 (진행 중)
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">₩ <span
                                                id="loanAmount"><%= loanAmount %></span></div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 총 연체 금액 Card  -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                            총 연체 금액
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">₩ <span
                                                id="overdueLoanAmount"><%= overdueLoanAmount %></span></div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 연체 고객 비율 Card -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">연체 고객 비율
                                        </div>
                                        <div class="row no-gutters align-items-center">
                                            <div class="col-auto">
                                                <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800"><span
                                                        id="getOverduePercentage"><%= overduePercentage %></span> %
                                                </div>
                                            </div>
                                            <div class="col">
                                                <div class="progress progress-sm mr-2">
                                                    <div id="card-pg-bar" class="progress-bar bg-info"
                                                         role="progressbar"
                                                         style="width: <%= overduePercentage %>%" aria-valuenow="50" aria-valuemin="0"
                                                         aria-valuemax="100"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 승인 대기 중 Card -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <a href="../../LoanApproval?loanStatus=pending" style="text-decoration: none;">
                            <div class="card border-left-warning shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                승인 대기 중
                                            </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800"><span
                                                    id="getCountPendingLends"><%= countPendingLends %></span></div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-comments fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>

                <!-- Content Row -->

                <div class="row">
                    <!-- Area Chart -->
                    <div class="col-xl-4 col-lg-4">
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">주간 PV</h6>
                                <div class="dropdown no-arrow">
                                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink1"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                         aria-labelledby="dropdownMenuLink">
                                        <div class="dropdown-header">Dropdown Header:</div>
                                        <a class="dropdown-item" href="#">Action</a>
                                        <a class="dropdown-item" href="#">Another action</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#">Something else here</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Card Body -->
                            <div class="card-body">
                                <div class="chart-area">
                                    <canvas id="pvChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Area Chart -->
                    <div class="col-xl-4 col-lg-4">
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">주간 UV</h6>
                                <div class="dropdown no-arrow">
                                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink2"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                         aria-labelledby="dropdownMenuLink">
                                        <div class="dropdown-header">Dropdown Header:</div>
                                        <a class="dropdown-item" href="#">Action</a>
                                        <a class="dropdown-item" href="#">Another action</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#">Something else here</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Card Body -->
                            <div class="card-body">
                                <div class="chart-area">
                                    <canvas id="uvChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Pie Chart -->
                    <div class="col-xl-4 col-lg-4">
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">상환 연체 비율</h6>
                                <div class="dropdown no-arrow">
                                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                         aria-labelledby="dropdownMenuLink">
                                        <div class="dropdown-header">Dropdown Header:</div>
                                        <a class="dropdown-item" href="#">Action</a>
                                        <a class="dropdown-item" href="#">Another action</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#">Something else here</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Card Body -->
                            <div class="card-body">
                                <div class="chart-pie pt-4 pb-2">
                                    <canvas id="myPieChart"></canvas>
                                </div>
                                <div class="mt-4 text-center small">
                                        <span class="mr-2">
                                            <i class="fas fa-circle text-primary"></i> 상환 중
                                        </span>
                                    <span class="mr-2">
                                            <i class="fas fa-circle text-success"></i> 연체 중
                                        </span>
                                    <span class="mr-2">
                                            <i class="fas fa-circle text-info"></i> 상환 완료
                                        </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Content Row -->
                <div class="row">
                    <!-- Content Column -->
                    <div class="col-lg-6 mb-4">

                        <!-- Project Card Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">상품별 신청 비율</h6>
                            </div>
                            <div class="card-body">
                                <h4 class="small font-weight-bold">월세 대출<span
                                        id="monthlyRentPercentage" class="float-right"><%= monthlyRentPercentage %>%</span></h4>
                                <div class="progress mb-4">
                                    <div id="monthlyRentRentBar" class="progress-bar bg-danger" role="progressbar"
                                         style="width: <%= monthlyRentPercentage%>%"
                                         aria-valuenow="<%= monthlyRentPercentage %>" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                                <h4 class="small font-weight-bold">전세 대출<span
                                        id="yearlyRentPercentage" class="float-right"><%= yearlyRentPercentage%>%</span></h4>
                                <div class="progress mb-4">
                                    <div id="monthlyRentBar" class="progress-bar bg-warning" role="progressbar"
                                         style="width: <%= yearlyRentPercentage%>%"
                                         aria-valuenow="<%= yearlyRentPercentage%>" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                                <h4 class="small font-weight-bold">담보 대출<span id="collateralRentPercentage"
                                                                              class="float-right"><%= collateralRentPercentage %>%</span></h4>
                                <div class="progress mb-4">
                                    <div id="collateralRentBar" class="progress-bar" role="progressbar"
                                         style="width: <%= collateralRentPercentage %>%"
                                         aria-valuenow="<%= collateralRentPercentage %>" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col-lg-6 mb-4">

                        <!-- 홈페이지 안내 -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">하나론 이용 안내</h6>
                            </div>
                            <div class="card-body">
                                <div class="text-center">

                                </div>
                                <p>하나론의 대출 관리 기능은 직원들에게 업무 효율성을 높이고, 고객에게 편리하고 안정적인 대출 서비스를 제공하는데 앞장서고 있습니다.<br/> <br/>문의나
                                    도움이 필요한 경우 언제든지 하나론 홈페이지의 전화번호나 고객센터로 문의해 주시기 바랍니다.</p>
                                <a rel="nofollow" href="#">☏ 02-0000-0000</a><br/>
                                <a rel="nofollow" href="#">하나론 고객센터 바로가기 &rarr;</a>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <%@ include file="/jsp/Components/AdminFooter/AdminFooter.jsp" %>

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Custom scripts for all pages-->
    <script src="../../js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="../../vendor/chart.js/Chart.js"></script>

    <!-- Page level custom scripts -->
    <script src="../../js/Chart/UVChart.js"></script>
    <script src="../../js/Chart/RepaymentRatioChart.js"></script>

</body>
</html>