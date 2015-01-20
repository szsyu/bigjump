<%--
  Created by IntelliJ IDEA.
  User: shawn
  Date: 2014/12/23
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Login to Big Jump</title>
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
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please Sign In</h3>
                </div>
                <div class="panel-body">
                    <form role="form" action="../j_spring_security_check" method="post" id="loginForm">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="User Name" name="j_username" type="text"
                                       id="userName"
                                       autofocus>

                                <div id="userNameMsg" class="text-danger"></div>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Password" name="j_password" type="password"
                                       id="password"
                                       value="">

                                <div id="passwordMsg" class="text-danger"></div>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input name="_spring_security_remember_me" type="checkbox"
                                           id="_spring_security_remember_me" value="true">Remember Me
                                </label>
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <button type="button" class="btn btn-lg btn-success btn-block" onclick="submitLogin();">
                                Login
                            </button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>
