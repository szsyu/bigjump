<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Lookup Type List</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../css/main.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="../js/jquery.js"></script>
    <script src="../js/modal.js"></script>
    <script src="../js/app/Lookup/showLookupTypeList.js"></script>
</head>
<body>
<jsp:include page="../Layouts/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../Layouts/sidebar.jsp"></jsp:include>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Lookup Definition</h1>

                <div class="panel panel-default">
                    <div class="panel-heading clearfix">
                        <div class="pull-left">
                            <button class="btn btn-success" onclick="newLookupType();">Define</button>
                        </div>
                        <div class="pull-right">
                            <form class="form-inline" role="form" action="../Lookup/showLookupTypeList" method="post">
                            <input type="text" class="form-control" id="lookupTypeCodeLike" name="lookupTypeCodeLike" placeholder="Lookup Type Search...">
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
                                    <th>Lookup Code</th>
                                    <th>Description</th>
                                    <th>Value Source</th>
                                    <th>Data Type</th>
                                    <th>Is Active</th>
                                    <th>Action</th>

                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${!empty lookupTypePage.list }">
                                    <c:forEach items="${lookupTypePage.list}" var="t">
                                        <tr>
                                            <td style="width:20%">${t.lookupTypeCode }</td>
                                            <td style="width:25%">${t.description }</td>
                                            <td style="width:15%">
                                                <c:if test="${t.valueSource == 'U'}">
                                                    User
                                                </c:if>
                                                <c:if test="${t.valueSource == 'D'}">
                                                    Value
                                                </c:if>
                                            </td>
                                            <td style="width:15%">${t.dataType}</td>
                                            <td style="width:10%">${t.isActive }</td>
                                            <td style="width:15%">
                                                <button class="btn btn-xs btn-info" onclick="updateLookupType(this,${t.id});">
                                                    <i class="fa fa-pencil fa-fw"></i> Edit
                                                </button>
                                                <button class="btn btn-xs btn-primary" onclick="location.href='../Lookup/showLookupValueList?lookupTypeCode=${t.lookupTypeCode}'">
                                                    <i class="fa fa-table fa-fw"></i> Lookup Value
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
                                        <c:when test="${lookupTypePage.currentPage == 1}">
                                            <li class="disabled"><span>First</span></li>
                                            <li class="disabled"><span>&laquo;</span></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href="${lookupTypePage.basePageUrl }1">First</a></li>
                                            <li><a href="${lookupTypePage.basePageUrl }${lookupTypePage.currentPage - 1 }">&laquo;</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${lookupTypePage.currentPage - 2 > 0}">
                                        <li>
                                            <a href="${lookupTypePage.basePageUrl }${lookupTypePage.currentPage - 2}">${lookupTypePage.currentPage - 2 }</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${lookupTypePage.currentPage - 1 > 0}">
                                        <li>
                                            <a href="${lookupTypePage.basePageUrl }${lookupTypePage.currentPage - 1}">${lookupTypePage.currentPage - 1 }</a>
                                        </li>
                                    </c:if>
                                    <li class="active"><span>${lookupTypePage.currentPage }</span></li>
                                    <c:if test="${lookupTypePage.currentPage + 1 <= lookupTypePage.totalPage}">
                                        <li>
                                            <a href="${lookupTypePage.basePageUrl }${lookupTypePage.currentPage + 1}">${lookupTypePage.currentPage + 1 }</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${lookupTypePage.currentPage + 2 <= lookupTypePage.totalPage}">
                                        <li>
                                            <a href="${lookupTypePage.basePageUrl }${lookupTypePage.currentPage + 2}">${lookupTypePage.currentPage + 2 }</a>
                                        </li>
                                    </c:if>
                                    <c:choose>
                                        <c:when test="${lookupTypePage.currentPage != lookupTypePage.totalPage}">
                                            <li><a href="${lookupTypePage.basePageUrl }${lookupTypePage.currentPage + 1}">&raquo;</a></li>
                                            <li><a href="${lookupTypePage.basePageUrl }${lookupTypePage.totalPage}">Last</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="disabled"><span>&raquo;</span></li>
                                            <li class="disabled"><span>Last</span></li>
                                        </c:otherwise>
                                    </c:choose>
                                    <li class="disabled"><span>Total:${lookupTypePage.totalPage }</span></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                    <!-- /.panel-body -->
                </div>
            </div>
    </div>
</div>
<div class="modal fade" id="lookupTypeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header modal-primary">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Document Data Level</h4>
            </div>
            <form method="post" action="../Lookup/updateLookupType" class="form-horizontal" role="form"
                  id="lookupTypeForm">
                <div class="modal-body">
                    <input type="hidden" name="id" id="id" value="" class="form-control">

                    <div class="form-group">
                        <label for="lookupTypeCode" class="col-sm-3 control-label">Lookup Type Code</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="lookupTypeCode"
                                   name="lookupTypeCode" placeholder="Lookup Type Code">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-sm-3 control-label">Description</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="description"
                                   name="description" placeholder="description">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="valueSource0" class="col-sm-3 control-label">Value Source</label>

                        <div class="col-sm-5">
                            <label class="checkbox-inline">
                            <input type="radio" id="valueSource0" name="valueSource" value="D" checked>Value
                                </label>
                            <label class="checkbox-inline">
                            <input type="radio" id="valueSource1" name="valueSource" value="U">User
                                </label>
                        </div>
                    </div>

                    <div class="form-group" id="dataTypeBlock">
                        <label for="dataType" class="col-sm-3 control-label">Data Type</label>
                        <div class="col-sm-5">
                            <select class="form-control" name="dataType" id="dataType">
                                <c:if test="${!empty dataTypeList }">
                                    <c:forEach items="${dataTypeList}" var="t">
                                        <option value="${t}">${t}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-8">
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