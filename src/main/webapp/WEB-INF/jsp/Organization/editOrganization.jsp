<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Master Organization List</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../css/main.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="../js/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/modal.js"></script>
    <script src="../js/app/Organization/showMasterOrganizationList.js"></script>
</head>
<body>
<jsp:include page="../Layouts/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../Layouts/sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Edit Organization</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-4 col-sm-offset-3 col-md-4 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Edit Organization
                    <!-- /.panel-heading -->
                    <div class="panel-body">

                        <form class="form-horizontal center"
                              method="post" id="organizationForm">
                            <input type="hidden" id="organizationId" name="organizationId" value="${organization.id}">

                            <div class="form-group">
                                <label for="orgCode" class="col-sm-4 control-label">Org Code</label>

                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="orgCode"
                                           name="orgCode" placeholder="Organization Code" value="organization.orgCode"
                                           readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="orgName" class="col-sm-4 control-label">Organization Name</label>

                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="orgName"
                                           name="orgName" placeholder="Organization Name" value="organization.orgName">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="description" class="col-sm-4 control-label">Description</label>

                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="description"
                                           name="description" placeholder="Description"
                                           value="organization.description">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-8">
                                    <div class="checkbox">
                                        <c:set var="isActiveChecked" scope="page"></c:set>
                                        <c:if test="${organization.isActive = 'Y'}">
                                            <c:set var="isActiveChecked" value="checked"></c:set>
                                        </c:if>
                                        <label> <input type="checkbox" name="isActive"
                                                       id="isActive" value="Y" ${isActiveChecked}>Active
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
        </div>
        <!-- Document Reply -->
        <div class="col-sm-6 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Document Reply
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Manager</th>
                                <th>Is Active</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${!empty managerList}">
                                <c:forEach items="${managerList}" var="t">
                                    <tr>
                                        <td>${t.manager.userName}</td>
                                        <td>${t.isActive}</td>
                                        <td>
                                            <button class="btn btn-xs btn-success"
                                                    onclick="location.href='../Organization/disableEnableOrgManager?orgManagerId=${t.id}'">
                                                <i class="fa fa-table fa-fw"></i> Disable/Enable
                                            </button>
                                            <button class="btn btn-xs btn-success"
                                                    onclick="location.href='../Document/deleteOrgManager?orgManagerId=${t.id}'">
                                                <i class="fa fa-table fa-fw"></i> Delete
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /Document Reply-->
        <!-- /.col-lg-6 -->
    </div>
</div>
<div class="modal fade" id="orgManagerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header modal-primary">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Edit Master Organization</h4>
            </div>
            <form method="post" action="../Organization/updateOrgManager" class="form-horizontal" role="form"
                  id="masterOrganizationForm">
                <div class="modal-body">
                    <input type="hidden" name="organizationId" id="orgManager_organizationId" value=""
                           class="form-control">

                    <div class="form-group">
                        <label for="description" class="col-sm-3 control-label">Org Information</label>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="orgManager_orgName"
                                   name="orgManager_orgName" value="" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="userId" class="col-sm-3 control-label">User</label>
                        <div class="col-sm-5">
                            <select class="form-control" name="userId" id="userId">
                                <c:if test="${!empty activeUserList }">
                                    <c:forEach items="${activeUserList}" var="t">
                                        <option value="${t.id}">${t.userName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-5">
                            <div class="checkbox">
                                <label> <input type="checkbox" name="isActive"
                                               id="orgManager_isActive" value="Y" checked>Active
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>