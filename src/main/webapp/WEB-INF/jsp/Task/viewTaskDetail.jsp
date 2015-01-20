<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Task Detail</title>
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
    <script src="../js/app/Task/viewTaskDetail.js"></script>
</head>
<body>
<jsp:include page="../Layouts/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../Layouts/sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Task Detail</h1>

            <!-- Data Level 0 panal -->
            <div class="panel panel-primary">
                <div class="panel-heading clearfix">
                    ${documentDetail.levelZeroName}
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover table-condensed">
                            <thead>
                            <tr class="text-primary">
                                <!-- Data Level 0 title -->
                                <c:if test="${!empty documentDetail.levelZeroTitle}">
                                    <c:forEach items="${documentDetail.levelZeroTitle}" var="t">
                                        <th>${t}</th>
                                    </c:forEach>
                                </c:if>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <!-- Data Level 0 data -->
                                <c:if test="${!empty documentDetail.levelZeroData }">
                                    <c:forEach items="${documentDetail.levelZeroData}" var="t">
                                        <td>${t}</td>
                                    </c:forEach>
                                </c:if>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div>
                    </div>
                </div>
                <!-- /.panel-body -->
            </div>

            <!-- Data Level 1 panel -->
            <div class="panel panel-primary">
                <div class="panel-heading clearfix">
                    ${documentDetail.levelOneName}
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover table-condensed"
                               id="documentList">
                            <thead>
                            <tr class="text-primary">
                                <!-- Data Level 1 title -->
                                <c:if test="${!empty documentDetail.levelOneTitle}">
                                    <c:forEach items="${documentDetail.levelOneTitle}" var="t">
                                        <th>${t}</th>
                                    </c:forEach>
                                </c:if>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${!empty documentDetail.levelOneData }">
                                <c:forEach items="${documentDetail.levelOneData}" var="t">
                                    <tr>
                                        <c:forEach items="${t}" var="s">
                                            <td>${s}</td>
                                        </c:forEach>
                                    </tr>
                                </c:forEach>
                            </c:if>

                            </tbody>
                        </table>
                    </div>
                    <div>
                    </div>
                </div>
                <!-- /.panel-body -->
            </div>
        </div>
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-sm-4 col-sm-offset-3 col-md-4 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Action
                    <c:if test="${taskDelegateType == 'A' }">
                        <button class="btn btn-xs btn-success" type="button"
                                onclick="action('APPROVE');">
                            <i class="fa fa-thumbs-up fa-fw"></i> Approve
                        </button>
                        <button class="btn btn-xs btn-danger"
                                onclick="action('REJECT');">
                            <i class="fa fa-thumbs-down fa-fw"></i> Reject
                        </button>
                    </c:if>
                    <c:if test="${taskDelegateType != 'A' }">
                        <button class="btn btn-xs btn-success"
                                onclick="action('CLAIM_APPROVE');">
                            <i class="fa fa-thumbs-up fa-fw"></i> Claim & Approve
                        </button>
                        <button class="btn btn-xs btn-danger"
                                onclick="action('CLAIM_REJECT');">
                            <i class="fa fa-thumbs-down fa-fw"></i> Claim & Reject
                        </button>
                    </c:if>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">

                    <form class="form-horizontal center"
                          method="post" id="actionForm">
                        <input type="hidden" id="taskId" name="taskId" value="${taskId}">

                        <div class="form-group">
                            <label for="comment" class="col-sm-2 control-label">Comment</label>

                            <div class="col-sm-8">
                                <textarea class="form-control" id="comment" rows="3"
                                          name="comment" placeholder="Comment"></textarea>
                            </div>
                        </div>
                    </form>
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
                                <th>Sequence</th>
                                <th>Node Name</th>
                                <th>Completion Date</th>
                                <th>User Name</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${!empty documentReplyList}">
                                <c:forEach items="${documentReplyList}" var="t">
                                    <tr>
                                        <td>${t.sequence}</td>
                                        <td>${t.nodeName}</td>
                                        <td>${t.completedDate}</td>
                                        <td>${t.user.userName}</td>
                                        <td>${t.actionValue}</td>
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
</body>
</html>