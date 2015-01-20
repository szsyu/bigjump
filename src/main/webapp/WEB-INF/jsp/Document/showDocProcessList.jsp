<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Document Process List</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../css/main.css" rel="stylesheet">
    <!-- Bootstrap DateTimePicker CSS -->
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="../js/jquery.js"></script>
    <script src="../js/ajaxfileupload.js"></script>
    <!-- Bootstrap DateTimePicker JS -->
    <script src="../js/bootstrap-datetimepicker.min.js"></script>
    <script src="../js/app/Document/documentDatePicker.js"></script>
    <script src="../js/modal.js"></script>
    <!--script src="../js/app/Document/showDocProcessList.js"></script -->
    <script>
        function ajaxZipFileUpload() {
            $.ajaxFileUpload({
                url: '../Document/uploadDiagramZipFile?documentId=' + ${document.id},
                secureuri: false,
                fileElementId: 'zipFile',
                type: "post",
                dataType: 'json',
                success: function (data) {
                    if (data.status == "success") {
                        $("#zipPath").attr("value", data.filename);
                        $('#result').html("ZIP file uploaded success!");
                        $('#zipPathBlock').prop("class","form-group");
                    } else if (data.status == "empty") {
                        $('#result').html("File cannot be empty");
                        alert("File cannot be empty");
                    }
                    else if (data.status == "error") {
                        $('#result').html("Error Occured when file uploading,please contact with admin！");
                        alert("Error Occured when file uploading,please contact with admin！");
                    }
                },
                error: function (data) { //服务器响应失败时的处理函数
                    $('#result').html('Error Occured when file uploading,please contact with admin！');
                    alert("Error Occured when file uploading,please contact with admin！");
                }
            });
        }
    </script>
</head>
<body>
<jsp:include page="../Layouts/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../Layouts/sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Document Configuration</h1>
            <ol class="breadcrumb">
                <li><a href="../Document/showDocumentList">Document List</a></li>
                <li>Document Process Definition List: &lt${document.documentCode}&gt</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">
                    Process Definition
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover table-condensed"
                               id="documentList">
                            <thead>
                            <tr class="text-primary">
                                <th>Process Definition Key</th>
                                <th>Process Definition ID</th>
                                <th>Process Name</th>
                                <th>Version</th>
                                <th>Is Current</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${!empty documentProcessPage.list }">
                                <c:forEach items="${documentProcessPage.list}" var="t">
                                    <tr
                                            <c:if test="${t.isCurrentVersion == 'Y'}">
                                            class="bg-info"
                                            </c:if>
                                            >
                                        <td style="width:15%">${t.processDefinitionKey }</td>
                                        <td style="width:20%">${t.processDefinitionId }</td>
                                        <td style="width:20%">${t.processName }</td>
                                        <td style="width:10%">${t.version }</td>
                                        <td style="width:10%">${t.isCurrentVersion }</td>
                                        <td style="width:20%">
                                            <button class="btn btn-xs btn-success"
                                                    onclick="location.href='../Document/showProcessVariableList?documentProcessId=${t.id}'">
                                                <i class="fa fa-table fa-fw"></i> Process Variable
                                            </button>
                                            <c:if test="${t.isCurrentVersion != 'Y'}">
                                            <button class="btn btn-xs btn-primary"
                                                    onclick="location.href='../Document/setAsCurrentProcess?documentProcessId=${t.id}'">
                                                <i class="fa fa-check-circle fa-fw"></i> Set As Current Version
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

                        <nav>
                            <ul class="pagination">
                                <c:choose>
                                    <c:when test="${documentProcessPage.currentPage == 1}">
                                        <li class="disabled"><span>First</span></li>
                                        <li class="disabled"><span>&laquo;</span></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="${documentProcessPage.basePageUrl }1">First</a></li>
                                        <li>
                                            <a href="${documentProcessPage.basePageUrl }${documentProcessPage.currentPage - 1 }">&laquo;</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                <c:if test="${documentProcessPage.currentPage - 2 > 0}">
                                    <li>
                                        <a href="${documentProcessPage.basePageUrl }${documentProcessPage.currentPage - 2}">${documentProcessPage.currentPage - 2 }</a>
                                    </li>
                                </c:if>
                                <c:if test="${documentProcessPage.currentPage - 1 > 0}">
                                    <li>
                                        <a href="${documentProcessPage.basePageUrl }${documentProcessPage.currentPage - 1}">${documentProcessPage.currentPage - 1 }</a>
                                    </li>
                                </c:if>
                                <li class="active"><span>${documentProcessPage.currentPage }</span></li>
                                <c:if test="${documentProcessPage.currentPage + 1 <= documentProcessPage.totalPage}">
                                    <li>
                                        <a href="${documentProcessPage.basePageUrl }${documentProcessPage.currentPage + 1}">${documentProcessPage.currentPage + 1 }</a>
                                    </li>
                                </c:if>
                                <c:if test="${documentProcessPage.currentPage + 2 <= documentProcessPage.totalPage}">
                                    <li>
                                        <a href="${documentProcessPage.basePageUrl }${documentProcessPage.currentPage + 2}">${documentProcessPage.currentPage + 2 }</a>
                                    </li>
                                </c:if>
                                <c:choose>
                                    <c:when test="${documentProcessPage.currentPage != documentProcessPage.totalPage}">
                                        <li>
                                            <a href="${documentProcessPage.basePageUrl }${documentProcessPage.currentPage + 1}">&raquo;</a>
                                        </li>
                                        <li>
                                            <a href="${documentProcessPage.basePageUrl }${documentProcessPage.totalPage}">Last</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="disabled"><span>&raquo;</span></li>
                                        <li class="disabled"><span>Last</span></li>
                                    </c:otherwise>
                                </c:choose>
                                <li class="disabled"><span>Total:${documentProcessPage.totalPage }</span></li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <!-- /.panel-body -->
            </div>
            <!--/.panel -->
            <div class="panel panel-default">
                <div class="panel-heading clearfix">
                    Process Deployment
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <form method="post" action="../Document/deployProcessByZip" class="form-horizontal" role="form"
                          id="zipFileForm">
                        <input type="hidden" id="documentId" name="documentId" value="${document.id}" />
                        <div class="form-group" id="zipUploadBlock">
                            <label for="zipFile" class="col-sm-2 control-label">Choose Diagram ZIP</label>
                            <div class="col-sm-5">
                                <input type="file" id="zipFile" name="zipFile">
                                <input type="button" class="btn btn-sm btn-success" value="Upload" onclick="ajaxZipFileUpload();"/>
                                <div id="result" class="text-info"></div>
                            </div>
                        </div>
                        <div class="form-group hidden" id="zipPathBlock">
                            <label for="zipPath" class="col-sm-2 control-label">Zip File Path</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="zipPath"
                                       name="zipPath" placeholder="Zip Path" readonly>
                            </div>
                            <button class="btn btn-md btn-success"
                                    onclick="form.submit();">
                                <i class="fa fa-check-circle fa-fw"></i> Deploy
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>