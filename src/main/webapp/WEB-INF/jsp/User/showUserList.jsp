<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>User List</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../css/main.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="../js/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/modal.js"></script>
    <script src="../js/app/User/showUserList.js"></script>
</head>
<body>
<jsp:include page="../Layouts/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../Layouts/sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">User</h1>

            <div class="panel panel-default">
                <div class="panel-heading clearfix">
                    <div class="pull-left">
                        <button class="btn btn-success" onclick="newUser();">New</button>
                    </div>
                    <div class="pull-right">

                        <input type="text" class="form-control" placeholder=" User Search...">

                    </div>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover table-condensed"
                               id="userList">
                            <thead>
                            <tr class="text-primary">
                                <th>User Name</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>E-Mail</th>
                                <th>Active</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${!empty userPage.list }">
                                <c:forEach items="${userPage.list}" var="t">
                                    <tr>
                                        <td style="width:15%">${t.userName }</td>
                                        <td style="width:20%">${t.firstName }</td>
                                        <td style="width:15%">${t.lastName }</td>
                                        <td style="width:20%">${t.email }</td>
                                        <td style="width:5%">${t.isActive }</td>
                                        <td style="width:25%">
                                            <button class="btn btn-xs btn-info" onclick="updateUser(this,${t.id});">
                                                <i class="fa fa-edit fa-fw"></i> Edit
                                            </button>
                                            <button class="btn btn-xs btn-success" onclick="location.href='../User/showRoles4User?userId=${t.id}'">
                                                <i class="fa fa-user fa-fw"></i> Role
                                            </button>
                                            <button class="btn btn-xs btn-warning" onclick="resetPassword(${t.id})">
                                                <i class="fa fa-unlock fa-fw"></i> Password Reset
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                    <div>

                        <nav>
                            <ul class="pagination">
                                <c:choose>
                                    <c:when test="${userPage.currentPage == 1}">
                                        <li class="disabled"><span>First</span></li>
                                        <li class="disabled"><span>&laquo;</span></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="${userPage.basePageUrl }1">First</a></li>
                                        <li><a href="${userPage.basePageUrl }${userPage.currentPage - 1 }">&laquo;</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                <c:if test="${userPage.currentPage - 2 > 0}">
                                    <li>
                                        <a href="${userPage.basePageUrl }${userPage.currentPage - 2}">${userPage.currentPage - 2 }</a>
                                    </li>
                                </c:if>
                                <c:if test="${userPage.currentPage - 1 > 0}">
                                    <li>
                                        <a href="${userPage.basePageUrl }${userPage.currentPage - 1}">${userPage.currentPage - 1 }</a>
                                    </li>
                                </c:if>
                                <li class="active"><span>${userPage.currentPage }</span></li>
                                <c:if test="${userPage.currentPage + 1 <= userPage.totalPage}">
                                    <li>
                                        <a href="${userPage.basePageUrl }${userPage.currentPage + 1}">${userPage.currentPage + 1 }</a>
                                    </li>
                                </c:if>
                                <c:if test="${userPage.currentPage + 2 <= userPage.totalPage}">
                                    <li>
                                        <a href="${userPage.basePageUrl }${userPage.currentPage + 2}">${userPage.currentPage + 2 }</a>
                                    </li>
                                </c:if>
                                <c:choose>
                                    <c:when test="${userPage.currentPage != userPage.totalPage}">
                                        <li><a href="${userPage.basePageUrl }${userPage.currentPage + 1}">&raquo;</a>
                                        </li>
                                        <li><a href="${userPage.basePageUrl }${userPage.totalPage}">Last</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="disabled"><span>&raquo;</span></li>
                                        <li class="disabled"><span>Last</span></li>
                                    </c:otherwise>
                                </c:choose>
                                <li class="disabled"><span>Total:${userPage.totalPage }</span></li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <!-- /.panel-body -->
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header modal-primary">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Document Data Level</h4>
            </div>
            <form method="post" action="../User/editUser" class="form-horizontal" role="form"
                  id="userForm">
                <div class="modal-body">
                    <input type="hidden" name="id" id="id" value="" class="form-control">

                    <div class="form-group">
                        <label for="userName" class="col-sm-4 control-label">Log Name</label>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="userName"
                                   name="userName" placeholder="User Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstName" class="col-sm-4 control-label">First Name</label>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="firstName"
                                   name="firstName" placeholder="First Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastName" class="col-sm-4 control-label">Last Name</label>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="lastName"
                                   name="lastName" placeholder="Last Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-4 control-label">E-Mail</label>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="email"
                                   name="email" placeholder="E-Mail">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-8">
                            <div class="checkbox">
                                <label> <input type="checkbox" name="isActive"
                                               id="isActive" value="Y" checked>Active
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>