<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Exception Occurs</title>

    <!-- Bootstrap Core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/main.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="../js/jquery.js"></script>
    <script src="../js/app/Login/login.js"></script>
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="../Login/login">Big Jump</a>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-danger">
                <div class="panel-heading">
                    <h3 class="panel-title">Exception Occurs</h3>
                </div>
                <div class="panel-body">
                        Some exception occurs,please contact with administrator!
                    <div>${exception}</div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>
