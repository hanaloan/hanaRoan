<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="description" content="" />
  <meta name="author" content="" />
  <title></title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <style>
    html, body, #wrapper {
      height: 100%;
      margin: 0;
      padding: 0;
    }

    #sidebar-wrapper {
      padding:0;
      height: 100%;
    }
  </style>
</head>
<body>
  <div class="col-2 d-flex container-fluid" style="width: 14%;" id="sidebar-wrapper">
    <div class="border-end bg-blue">
      <div class="sidebar-heading border-bottom bg-light">
        <a href="CustomerHome"><img class="img-fluid" src="/resource/newLogo.png" style="height: 100px; width: 185px;"></a>
      </div>
      <div id="linkList" class="list-group list-group-flush flex-grow-1">
        <a class="list-group-item list-group-item-action list-group-item-light p-3 font-weight-bold" href="/CustomerHome">홈</a>
        <a class="list-group-item list-group-item-action list-group-item-light p-3 font-weight-bold" href="productList">대출상품</a>
        <a class="list-group-item list-group-item-action list-group-item-light p-3 font-weight-bold" href="#!">조회</a>
        <a class="list-group-item list-group-item-action list-group-item-light p-3 font-weight-bold" href="#!">이체</a>
        <a class="list-group-item list-group-item-action list-group-item-light p-3 font-weight-bold" href="/jsp/CustomerHome/CustomerHomeRenew.jsp">뉴홈</a>
        <div style="position: absolute; bottom: 0;">
          <div class="list-group-item list-group-item-light p-3">
            <p id="time">10:00</p></div>
          <button class="btn btn-primary container-fluid mb-2">세션연장</button>
          <a href="/LogoutController"><button class="btn btn-primary container-fluid">로그아웃</button></a>
        </div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script>
  var counter = 10 * 60;
  var interval = setInterval(function() {
    counter--;
    if (counter <= 0) {
      clearInterval(interval);
      $('#time').text("Session expired");
      return;
    } else {
      var minutes = Math.floor(counter / 60);
      var seconds = counter % 60;
      $('#time').text(minutes + ":" + seconds);
    }
  }, 1000);

  function extendSession() {
    $.ajax({
      url: '${pageContext.request.contextPath}/extendSession',
      type: 'POST',
      success: function() {
        counter = 10 * 60;
      },
      error: function() {
        console.log("Error extending the session");
      }
    });
  }
</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>