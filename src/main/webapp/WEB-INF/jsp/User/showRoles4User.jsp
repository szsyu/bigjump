<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Set User's Role</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../css/main.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="../js/jquery.js"></script>
    <script src="../js/modal.js"></script>
    <script src="../js/app/User/showRoles4User.js"></script>
</head>
<body>
<jsp:include page="../Layouts/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../Layouts/sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Role Setup for User</h1>

            <div class="panel panel-default">
                <div class="panel-heading">
                    Role List for User:${user.userName}
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover"
                               id="roleList">
                            <thead>
                            <tr class="text-primary">
                                <th>Role Code</th>
                                <th>Role Name</th>
                                <th>Role Description</th>
                                <th>Document Code</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${!empty user.roles }">
                                <c:forEach items="${user.roles}" var="t">
                                    <tr>
                                        <td style="width:15%">${t.roleCode }</td>
                                        <td style="width:25%">${t.roleName }</td>
                                        <td style="width:25%">${t.description }</td>
                                        <td style="width:20%">${t.document.documentCode }</td>
                                        <td style="width:15%">
                                            <button class="btn btn-xs btn-danger"
                                                    onclick="location.href='../User/removeRole4User?roleId=${t.id}&userId=${user.id}'">
                                                <i class="fa fa-trash-o fa-fw"></i> Delete
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    Add Role
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <form class="form-horizontal center"
                          action="../User/addRole2User" method="post" id="addRole">
                        <input type="hidden" id="userId" name="userId" value="${user.id}">

                        <div class="form-group">
                            <label for="userId" class="col-sm-3 control-label">Role</label>

                            <div class="col-sm-5">
                                <select class="form-control" name="roleId" id="roleId">
                                    <c:if test="${!empty roleList }">
                                        <c:forEach items="${roleList}" var="t">
                                            <option value="${t.id}">${t.document.documentCode}-${t.roleCode}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-10">
                                <button type="button" class="btn btn-primary btn-md"
                                        onclick="addRole();">Add Role
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