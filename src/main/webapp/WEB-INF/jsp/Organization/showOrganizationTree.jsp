<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Organization Tree</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../css/main.css" rel="stylesheet">
    <link href="../css/my.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="../js/jquery.js"></script>
    <script src="../js/modal.js"></script>
    <script src="../js/app/Organization/my.js"></script>
    <script src="../js/app/Organization/showOrganizationTree.js"></script>
</head>
<body>
<jsp:include page="../Layouts/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../Layouts/sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Organization Tree</h1>

            <div class="panel panel-default">
                <div class="panel-heading">
                    Organization Tree
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="tree well">
                        ${treeStr}
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="orgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header modal-primary">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">New Organization</h4>
            </div>
            <form method="post" action="../Organization/addOrganization" class="form-horizontal" role="form"
                  id="organizationForm">
                <div class="modal-body">
                    <input type="hidden" name="parentOrganizationId" id="parentOrganizationId" value="" class="form-control">
                    <input type="hidden" name="masterOrganizationId" id="masterOrganizationId" value="" class="form-control">
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
