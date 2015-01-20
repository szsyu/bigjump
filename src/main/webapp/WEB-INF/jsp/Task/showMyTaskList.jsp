<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>My Task List</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
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
            <h1 class="page-header">My Task List</h1>

            <div class="panel panel-default">
                <div class="panel-heading clearfix">
                    <div class="pull-right">
                        <form class="form-inline" role="form" action="../Task/showMyTaskList" method="post">
                            <select class="form-control" name="q_documentCode" id="q_documentCode">
                                <option value="">Choose Document</option>
                                <c:if test="${!empty documentList }">
                                    <c:forEach items="${documentList}" var="t">
                                        <option value="${t.documentCode}">${t.documentCode}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                            <input type="text" class="form-control" id="q_documentNumberLike" name="q_documentNumberLike"
                                   placeholder="Doc Number Search...">
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
                                <th>Document Name</th>
                                <th>Document Number</th>
                                <th>Description</th>
                                <th>Node Name</th>
                                <th>Candicates</th>
                                <th>Quick Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${!empty docAssCanPage.list }">
                                <c:forEach items="${docAssCanPage.list}" var="t">
                                    <tr>
                                        <td style="width:10%">${t.documentMaster.document.documentName }</td>
                                        <td style="width:10%">${t.documentMaster.documentNumber}</td>
                                        <td style="width:10%">${t.documentMaster.description }</td>
                                        <td style="width:15%">${t.nodeName }</td>
                                        <td style="width:10%">${t.user.userName}</td>
                                        <td style="width:30%">
                                            <button class="btn btn-xs btn-info"
                                                    onclick="location.href='../Task/viewTaskDetail?docAssCanId=${t.id}'">
                                                <i class="fa fa-info fa-fw"></i> View Detail
                                            </button>
                                            <button class="btn btn-xs btn-primary" onclick="window.open('../Task/showCurrActImg?taskId=${t.taskId}','_blank');"
                                                    >
                                                <i class="fa fa-image fa-fw"></i> View Process
                                            </button>
                                            <c:if test="${t.delegateType == 'A' }">
                                                <button class="btn btn-xs btn-success"
                                                        onclick="location.href='../Task/approveTask?taskId=${t.taskId}'">
                                                    <i class="fa fa-thumbs-up fa-fw"></i> Approve
                                                </button>
                                                <button class="btn btn-xs btn-danger"
                                                        onclick="location.href='../Task/rejectTask?taskId=${t.taskId}'">
                                                    <i class="fa fa-thumbs-down fa-fw"></i> Reject
                                                </button>
                                            </c:if>
                                            <c:if test="${t.delegateType != 'A' }">
                                                <button class="btn btn-xs btn-success"
                                                        onclick="location.href='../Task/claimAndApproveTask?taskId=${t.taskId}'">
                                                    <i class="fa fa-thumbs-up fa-fw"></i> Claim & Approve
                                                </button>
                                                <button class="btn btn-xs btn-danger"
                                                        onclick="location.href='../Task/claimAndRejectTask?taskId=${t.taskId}'">
                                                    <i class="fa fa-thumbs-down fa-fw"></i> Claim & Reject
                                                </button>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                    <div>
                        <c:if test="${docAssCanPage.allRow > 0 }">
                            <nav>
                                <ul class="pagination">
                                    <c:choose>
                                        <c:when test="${docAssCanPage.currentPage == 1}">
                                            <li class="disabled"><span>First</span></li>
                                            <li class="disabled"><span>&laquo;</span></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href="${docAssCanPage.basePageUrl }1">First</a></li>
                                            <li>
                                                <a href="${docAssCanPage.basePageUrl }${docAssCanPage.currentPage - 1 }">&laquo;</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${docAssCanPage.currentPage - 2 > 0}">
                                        <li>
                                            <a href="${docAssCanPage.basePageUrl }${docAssCanPage.currentPage - 2}">${docAssCanPage.currentPage - 2 }</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${docAssCanPage.currentPage - 1 > 0}">
                                        <li>
                                            <a href="${docAssCanPage.basePageUrl }${docAssCanPage.currentPage - 1}">${docAssCanPage.currentPage - 1 }</a>
                                        </li>
                                    </c:if>
                                    <li class="active"><span>${docAssCanPage.currentPage }</span></li>
                                    <c:if test="${docAssCanPage.currentPage + 1 <= docAssCanPage.totalPage}">
                                        <li>
                                            <a href="${docAssCanPage.basePageUrl }${docAssCanPage.currentPage + 1}">${docAssCanPage.currentPage + 1 }</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${docAssCanPage.currentPage + 2 <= docAssCanPage.totalPage}">
                                        <li>
                                            <a href="${docAssCanPage.basePageUrl }${docAssCanPage.currentPage + 2}">${docAssCanPage.currentPage + 2 }</a>
                                        </li>
                                    </c:if>
                                    <c:choose>
                                        <c:when test="${docAssCanPage.currentPage != docAssCanPage.totalPage}">
                                            <li>
                                                <a href="${docAssCanPage.basePageUrl }${docAssCanPage.currentPage + 1}">&raquo;</a>
                                            </li>
                                            <li>
                                                <a href="${docAssCanPage.basePageUrl }${docAssCanPage.totalPage}">Last</a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="disabled"><span>&raquo;</span></li>
                                            <li class="disabled"><span>Last</span></li>
                                        </c:otherwise>
                                    </c:choose>
                                    <li class="disabled"><span>Total:${docAssCanPage.totalPage }</span></li>
                                </ul>
                            </nav>
                        </c:if>
                    </div>
                </div>
                <!-- /.panel-body -->
            </div>
        </div>
    </div>
</div>
</body>
</html>