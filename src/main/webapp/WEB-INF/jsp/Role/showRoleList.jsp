<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Role List</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../css/main.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="../js/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/modal.js"></script>
    <script src="../js/app/Role/showRoleList.js"></script>
</head>
<body>
<jsp:include page="../Layouts/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../Layouts/sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Role</h1>

            <div class="panel panel-default">
                <div class="panel-heading clearfix">
                    <div class="pull-left">
                        <button class="btn btn-success" onclick="newRole();">New</button>
                    </div>
                    <div class="pull-right">
                        <input type="text" class="form-control" placeholder=" Role Search...">
                    </div>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover table-condensed"
                               id="userList">
                            <thead>
                            <tr class="text-primary">
                                <th>Role Code</th>
                                <th>Role Name</th>
                                <th>Description</th>
                                <th>Document</th>
                                <th>Active</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${!empty rolePage.list }">
                                <c:forEach items="${rolePage.list}" var="t">
                                    <tr>
                                        <td style="width:20%">${t.roleCode }</td>
                                        <td style="width:20%">${t.roleName }</td>
                                        <td style="width:15%">${t.description }</td>
                                        <td style="width:15%">${t.document.documentCode }</td>
                                        <td style="width:5%">${t.isActive }</td>
                                        <td style="width:15%">
                                            <button class="btn btn-xs btn-info" onclick="updateRole(this,${t.id});">
                                                <i class="fa fa-edit fa-fw"></i> Edit
                                            </button>
                                            <button class="btn btn-xs btn-success">
                                                <i class="fa fa-user fa-fw"></i> User
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
                                    <c:when test="${rolePage.currentPage == 1}">
                                        <li class="disabled"><span>First</span></li>
                                        <li class="disabled"><span>&laquo;</span></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="${rolePage.basePageUrl }1">First</a></li>
                                        <li><a href="${rolePage.basePageUrl }${rolePage.currentPage - 1 }">&laquo;</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                <c:if test="${rolePage.currentPage - 2 > 0}">
                                    <li>
                                        <a href="${rolePage.basePageUrl }${rolePage.currentPage - 2}">${rolePage.currentPage - 2 }</a>
                                    </li>
                                </c:if>
                                <c:if test="${rolePage.currentPage - 1 > 0}">
                                    <li>
                                        <a href="${rolePage.basePageUrl }${rolePage.currentPage - 1}">${rolePage.currentPage - 1 }</a>
                                    </li>
                                </c:if>
                                <li class="active"><span>${rolePage.currentPage }</span></li>
                                <c:if test="${rolePage.currentPage + 1 <= rolePage.totalPage}">
                                    <li>
                                        <a href="${rolePage.basePageUrl }${rolePage.currentPage + 1}">${rolePage.currentPage + 1 }</a>
                                    </li>
                                </c:if>
                                <c:if test="${rolePage.currentPage + 2 <= rolePage.totalPage}">
                                    <li>
                                        <a href="${rolePage.basePageUrl }${rolePage.currentPage + 2}">${rolePage.currentPage + 2 }</a>
                                    </li>
                                </c:if>
                                <c:choose>
                                    <c:when test="${rolePage.currentPage != rolePage.totalPage}">
                                        <li><a href="${rolePage.basePageUrl }${rolePage.currentPage + 1}">&raquo;</a>
                                        </li>
                                        <li><a href="${rolePage.basePageUrl }${rolePage.totalPage}">Last</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="disabled"><span>&raquo;</span></li>
                                        <li class="disabled"><span>Last</span></li>
                                    </c:otherwise>
                                </c:choose>
                                <li class="disabled"><span>Total:${rolePage.totalPage }</span></li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <!-- /.panel-body -->
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="roleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header modal-primary">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Document Data Level</h4>
            </div>
            <form method="post" action="../Role/editRole" class="form-horizontal" role="form"
                  id="roleForm">
                <div class="modal-body">
                    <input type="hidden" name="id" id="id" value="" class="form-control">

                    <div class="form-group">
                        <label for="roleCode" class="col-sm-4 control-label">Role Code</label>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="roleCode"
                                   name="roleCode" placeholder="Role Code">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="roleName" class="col-sm-4 control-label">Role Name</label>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="roleName"
                                   name="roleName" placeholder="First Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-sm-4 control-label">Description</label>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="description"
                                   name="description" placeholder="description">
                        </div>
                    </div>
                    <div class="form-group hidden" id="documentChooseForNew">
                        <label for="documentCodeNew" class="col-sm-4 control-label">Document Code</label>
                        <div class="col-sm-5">
                            <select class="form-control" name="documentCodeNew"
                                    id="documentCodeNew">
                                <c:if test="${!empty activeDocumentList }">
                                    <c:forEach items="${activeDocumentList}" var="t">
                                        <option value="${t.documentCode}">${t.documentCode}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <div class="form-group hidden" id="documentChooseForUpdate">
                        <label for="documentCodeUpdate" class="col-sm-4 control-label">Document Code</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="documentCodeUpdate"
                                   name="documentCodeUpdate" >
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