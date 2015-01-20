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
            <h1 class="page-header">Master Organization</h1>

            <div class="panel panel-default">
                <div class="panel-heading clearfix">
                    <div class="pull-left">
                        <button class="btn btn-success" onclick="newMasterOrganization();">New Master Organization</button>
                    </div>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover table-condensed"
                               id="userList">
                            <thead>
                            <tr class="text-primary">
                                <th>Organization Code</th>
                                <th>Organization Name</th>
                                <th>Description</th>
                                <th>Active</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${!empty masterOrganizationList }">
                                <c:forEach items="${masterOrganizationList}" var="t">
                                    <tr>
                                        <td style="width:15%">${t.orgName }</td>
                                        <td style="width:20%">${t.orgName }</td>
                                        <td style="width:15%">${t.description }</td>
                                        <td style="width:5%">${t.isActive}</td>
                                        <td style="width:25%">
                                            <button class="btn btn-xs btn-info"
                                                    onclick="editMasterOrganization(this,${t.id});">
                                                <i class="fa fa-edit fa-fw"></i> Edit
                                            </button>
                                            <button class="btn btn-xs btn-success"
                                                    onclick="location.href='../Organization/showOrganizationTree?masterOrganizationId=${t.id}'">
                                                <i class="fa fa-user fa-fw"></i> Organization Tree
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>

                </div>
                <!-- /.panel-body -->
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="masterOrganizationModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header modal-primary">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Edit Master Organization</h4>
            </div>
            <form method="post" action="../Organization/updateMasterOrganization" class="form-horizontal" role="form"
                  id="masterOrganizationForm">
                <div class="modal-body">
                    <input type="hidden" name="id" id="id" value="" class="form-control">

                    <div class="form-group">
                        <label for="orgCode" class="col-sm-4 control-label">Org Code</label>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="orgCode"
                                   name="orgCode" placeholder="Organization Code">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="orgName" class="col-sm-4 control-label">Organization Name</label>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="orgName"
                                   name="orgName" placeholder="Organization Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-sm-4 control-label">Description</label>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="description"
                                   name="description" placeholder="Description">
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