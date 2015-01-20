<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Lookup Value List</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../css/main.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="../js/jquery.js"></script>
    <script src="../js/modal.js"></script>
    <script src="../js/app/Lookup/showLookupValueList.js"></script>
</head>
<body>
<jsp:include page="../Layouts/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../Layouts/sidebar.jsp"></jsp:include>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Lookup Definition</h1>
                <ol class="breadcrumb">
                    <li><a href="../Lookup/showLookupTypeList">Lookup Type List</a></li>
                    <li>Lookup List: &lt${lookupType.lookupTypeCode}&gt</li>
                </ol>
                <input type="hidden" name="valueSource" id="valueSource" value="${lookupType.valueSource}" >
                <div class="panel panel-default">
                    <div class="panel-heading clearfix">
                        <div class="pull-left">
                            <button class="btn btn-success" onclick="newLookupValue();">New</button>
                        </div>
                        <div class="pull-right">
                            <form class="form-inline" role="form" action="../Lookup/showLookupValueList?lookupTypeCode=${lookupType.lookupTypeCode}" method="post">
                            <input type="text" class="form-control" id="lookupCodeLike" name="lookupTypeCodeLike" placeholder="Lookup Search...">
                                <button class="btn btn-md btn-info">
                                     Search
                                </button>
                            </form>
                        </div>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table-condensed"
                                   id="documentList">
                                <thead>
                                <tr class="text-primary">
                                    <th>Lookup Type Code</th>
                                    <th>Description</th>
                                    <th>Data Type</th>
                                    <th>Value</th>
                                    <th>User</th>
                                    <th>Is Active</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${!empty lookupValuePage.list }">
                                    <c:forEach items="${lookupValuePage.list}" var="t">
                                        <tr>
                                            <td style="width:16%">${t.lookupCode }</td>
                                            <td style="width:16%">${t.description }</td>
                                            <td style="width:8%">${t.lookupType.dataType}</td>
                                            <td style="width:20%">${t.value}</td>
                                            <td style="width:15%">${t.user.userName}</td>
                                            <td style="width:10%">${t.isActive}</td>
                                            <td style="width:15%">
                                                <button class="btn btn-xs btn-info" onclick="updateLookupValue(this,${t.id},${t.user.id});">
                                                    <i class="fa fa-pencil fa-fw"></i> Edit
                                                </button>
                                                <button class="btn btn-xs btn-danger" onclick="location.href='../Lookup/deleteLookupValue?lookupValueId=${t.id}'">
                                                    <i class="fa fa-trash-o fa-fw"></i> Delete
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
                                        <c:when test="${lookupValuePage.currentPage == 1}">
                                            <li class="disabled"><span>First</span></li>
                                            <li class="disabled"><span>&laquo;</span></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href="${lookupValuePage.basePageUrl }1">First</a></li>
                                            <li><a href="${lookupValuePage.basePageUrl }${lookupValuePage.currentPage - 1 }">&laquo;</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${lookupValuePage.currentPage - 2 > 0}">
                                        <li>
                                            <a href="${lookupValuePage.basePageUrl }${lookupValuePage.currentPage - 2}">${lookupValuePage.currentPage - 2 }</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${lookupValuePage.currentPage - 1 > 0}">
                                        <li>
                                            <a href="${lookupValuePage.basePageUrl }${lookupValuePage.currentPage - 1}">${lookupValuePage.currentPage - 1 }</a>
                                        </li>
                                    </c:if>
                                    <li class="active"><span>${lookupValuePage.currentPage }</span></li>
                                    <c:if test="${lookupValuePage.currentPage + 1 <= lookupValuePage.totalPage}">
                                        <li>
                                            <a href="${lookupValuePage.basePageUrl }${lookupValuePage.currentPage + 1}">${lookupValuePage.currentPage + 1 }</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${lookupValuePage.currentPage + 2 <= lookupValuePage.totalPage}">
                                        <li>
                                            <a href="${lookupValuePage.basePageUrl }${lookupValuePage.currentPage + 2}">${lookupValuePage.currentPage + 2 }</a>
                                        </li>
                                    </c:if>
                                    <c:choose>
                                        <c:when test="${lookupValuePage.currentPage != lookupValuePage.totalPage}">
                                            <li><a href="${lookupValuePage.basePageUrl }${lookupValuePage.currentPage + 1}">&raquo;</a></li>
                                            <li><a href="${lookupValuePage.basePageUrl }${lookupValuePage.totalPage}">Last</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="disabled"><span>&raquo;</span></li>
                                            <li class="disabled"><span>Last</span></li>
                                        </c:otherwise>
                                    </c:choose>
                                    <li class="disabled"><span>Total:${lookupValuePage.totalPage }</span></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                    <!-- /.panel-body -->
                </div>
            </div>
    </div>
</div>
<div class="modal fade" id="lookupValueModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header modal-primary">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Document Data Level</h4>
            </div>
            <form method="post" action="../Lookup/updateLookupValue" class="form-horizontal" role="form"
                  id="lookupValueForm">
                <div class="modal-body">
                    <input type="hidden" name="id" id="id" value="" class="form-control">
                    <input type="hidden" name="lookupTypeCode" id="lookupTypeCode" value="${lookupType.lookupTypeCode}" class="form-control">
                    <div class="form-group">
                        <label for="lookupTypeCode" class="col-sm-3 control-label">Lookup Code</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="lookupCode"
                                   name="lookupCode" placeholder="LookupCode">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-sm-3 control-label">Description</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="description"
                                   name="description" placeholder="description">
                        </div>
                    </div>
                    <div class="form-group" id="valueBlock">
                        <label for="value" class="col-sm-3 control-label">Value</label>

                        <div class="col-sm-5"><input type="text" name="value" id="value" value=""
                                                     class="form-control"></div>
                    </div>

                    <div class="form-group" id="userBlock">
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