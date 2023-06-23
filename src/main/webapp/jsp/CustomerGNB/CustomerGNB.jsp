<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

    <style>
        /* Custom CSS */
        .navbar {
            background-color: #add8e6; /* 연한 하늘색 */
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg">
    <a class="navbar-brand" href="#">
        <img src="logo.png" width="30" height="30" class="d-inline-block align-top" alt="Logo">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="CustomerHome">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/productList">Products</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="LogoutController">Logout</a>
            </li>
        </ul>
    </div>
</nav>

<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>