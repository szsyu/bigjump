<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Add Role</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/main.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../Layouts/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../Layouts/sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Role</h1>
            <ol class="breadcrumb">
                <li><a href="../Role/showRoleList">Role</a></li>
                <li>Add Role</li>
            </ol>

            <div class="panel panel-default">
                <div class="panel-heading">
                    Add Role
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <form class="form-horizontal center"
                          action="../Role/saveRole" method="post">
                        <div class="form-group">
                            <label for="roleCode" class="col-sm-2 control-label">Role Code</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="roleCode"
                                       name="roleCode" placeholder="Role Code">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="roleName" class="col-sm-2 control-label">Role Name</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="roleName"
                                       name="roleName" placeholder="Role Name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="col-sm-2 control-label">Description</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="description"
                                       name="v" placeholder="Description">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="documentCode" class="col-sm-2 control-label">Document</label>

                            <div class="col-sm-5">
                                <select class="form-control" name="documentCode"
                                        id="documentCode">
                                    <c:if test="${!empty activeDocumentList }">
                                        <c:forEach items="${activeDocumentList}" var="q">
                                            <option value=""></option>
                                            <option value="${q.documentCode}">${q.documentName}</option>
                                        </c:forEach>
                                    </c:if>

                                </select>
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