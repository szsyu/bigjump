<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/main.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../Layouts/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../Layouts/sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Dashboard</h1>

            <div class="panel panel-default">
                <div class="panel-heading">
                    Add User
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <form class="form-horizontal center"
                          action="../User/saveUser" method="post">
                        <div class="form-group">
                            <label for="logName" class="col-sm-2 control-label">Log Name</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="logName"
                                       name="logName" placeholder="Log Name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="firstName" class="col-sm-2 control-label">First Name</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="firstName"
                                       name="firstName" placeholder="First Name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="col-sm-2 control-label">Last Name</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="lastName"
                                       name="lastName" placeholder="Last Name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">E-Mail</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="email"
                                       name="email" placeholder="E-Mail">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-8">
                                <div class="checkbox">
                                    <label> <input type="checkbox" name="isActive"
                                                   id="isActive" value="Y" checked>Active
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-primary btn-lg"
                                        onclick="form.submit()">Save
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>