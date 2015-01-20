<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Document List</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
   <%-- <link rel="stylesheet" href="../css/font-awesome.min.css">--%>
    <link href="../font-awesome-4.1.0/css/font-awesome.css" rel="stylesheet" type="text/css">
<%--    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">--%>
    <link href="../css/main.css" rel="stylesheet">
    <!-- Bootstrap DateTimePicker CSS -->
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="../js/jquery.js"></script>
    <!-- Bootstrap DateTimePicker JS -->
    <script src="../js/bootstrap-datetimepicker.min.js"></script>
    <script src="../js/app/Document/documentDatePicker.js"></script>
    <script src="../js/modal.js"></script>
    <script src="../js/app/Document/showDocumentList.js"></script>
</head>
<body>
<jsp:include page="../Layouts/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../Layouts/sidebar.jsp"></jsp:include>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Document Configuration</h1>

                <div class="panel panel-default">
                    <div class="panel-heading clearfix">
                        <div class="pull-left">
                            <button class="btn btn-success" onclick="newDocument();">Define</button>
                        </div>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table-condensed"
                                   id="documentList">
                                <thead>
                                <tr class="text-primary">
                                    <th>Document Code</th>
                                    <th>Document Name</th>
                                    <th>Description</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Is Active</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${!empty documentPage.list }">
                                    <c:forEach items="${documentPage.list}" var="t">
                                        <tr>
                                            <td style="width:10%">${t.documentCode }</td>
                                            <td style="width:10%">${t.documentName }</td>
                                            <td style="width:20%">${t.description }</td>
                                            <td style="width:10%">${t.startDate }</td>
                                            <td style="width:10%">${t.endDate}</td>
                                            <td style="width:5%">${t.isActive }</td>
                                            <td style="width:25%">
                                                <button class="btn btn-xs btn-info" onclick="updateDocument(this,${t.id});">
                                                    <i class="icon-ok"></i> Info
                                                </button>
                                                <button class="btn btn-xs btn-success" onclick="location.href='../Document/showDocumentDataLevel?documentId=${t.id}'">
                                                    <i class="fa fa-edit fa-fw"></i> Data Level
                                                </button>
                                                <button class="btn btn-xs btn-primary" onclick="location.href='../Document/showDocProcessList?documentId=${t.id}'">
                                                    <i class="fa fa-cog fa-fw"></i> Process Definition
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
                                        <c:when test="${documentPage.currentPage == 1}">
                                            <li class="disabled"><span>First</span></li>
                                            <li class="disabled"><span>&laquo;</span></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href="${documentPage.basePageUrl }1">First</a></li>
                                            <li><a href="${documentPage.basePageUrl }${documentPage.currentPage - 1 }">&laquo;</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${documentPage.currentPage - 2 > 0}">
                                        <li>
                                            <a href="${documentPage.basePageUrl }${documentPage.currentPage - 2}">${documentPage.currentPage - 2 }</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${documentPage.currentPage - 1 > 0}">
                                        <li>
                                            <a href="${documentPage.basePageUrl }${documentPage.currentPage - 1}">${documentPage.currentPage - 1 }</a>
                                        </li>
                                    </c:if>
                                    <li class="active"><span>${documentPage.currentPage }</span></li>
                                    <c:if test="${documentPage.currentPage + 1 <= documentPage.totalPage}">
                                        <li>
                                            <a href="${documentPage.basePageUrl }${documentPage.currentPage + 1}">${documentPage.currentPage + 1 }</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${documentPage.currentPage + 2 <= documentPage.totalPage}">
                                        <li>
                                            <a href="${documentPage.basePageUrl }${documentPage.currentPage + 2}">${documentPage.currentPage + 2 }</a>
                                        </li>
                                    </c:if>
                                    <c:choose>
                                        <c:when test="${documentPage.currentPage != documentPage.totalPage}">
                                            <li><a href="${documentPage.basePageUrl }${documentPage.currentPage + 1}">&raquo;</a></li>
                                            <li><a href="${documentPage.basePageUrl }${documentPage.totalPage}">Last</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="disabled"><span>&raquo;</span></li>
                                            <li class="disabled"><span>Last</span></li>
                                        </c:otherwise>
                                    </c:choose>
                                    <li class="disabled"><span>Total:${documentPage.totalPage }</span></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                    <!-- /.panel-body -->
                </div>
            </div>
    </div>
</div>
<div class="modal fade" id="documentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header modal-primary">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Document Data Level</h4>
            </div>
            <form method="post" action="../Document/editDocument" class="form-horizontal" role="form"
                  id="documentForm">
                <div class="modal-body">
                    <input type="hidden" name="id" id="id" value="" class="form-control">

                    <div class="form-group">
                        <label for="documentCode" class="col-sm-4 control-label">Document Code</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="documentCode"
                                   name="documentCode" placeholder="Document Code">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="documentName" class="col-sm-4 control-label">Document Name</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="documentName"
                                   name="documentName" placeholder="Document Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-sm-4 control-label">Description</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="description"
                                   name="description" placeholder="description">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="startDate" class="col-sm-4 control-label">Start
                            Date</label>
                        <div class='form_date col-sm-5'  data-link-format="yyyy-mm-dd">
                            <input type="text" class="form-control" id="startDate"
                                   name="startDate" placeholder="Start Date">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="endDate" class="col-sm-4 control-label">End
                            Date</label>
                        <div class='form_date col-sm-5'  data-link-format="yyyy-mm-dd">
                            <input type="text" class="form-control" id="endDate"
                                   name="endDate" placeholder="End Date">
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