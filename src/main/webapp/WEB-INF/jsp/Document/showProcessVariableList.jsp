<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Process Variable List</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../css/main.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="../js/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/modal.js"></script>
    <script src="../js/app/Document/showProcessVariableList.js"></script>
</head>
<body>
<jsp:include page="../Layouts/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../Layouts/sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Process Configuration</h1>
            <ol class="breadcrumb">
                <li><a href="../Document/showDocumentList">Document List</a></li>
                <li><a href="../Document/showDocProcessList?documentId=${document.id}">Process Definition List:
                    &lt${document.documentCode}&gt</a></li>
                <li>Process Variable List: &lt${documentProcess.processDefinitionKey}&gt - &lt${documentProcess.version}&gt</li>
            </ol>

            <div class="panel panel-default">
                <div class="panel-heading clearfix">
                    <div class="pull-left">
                        <button class="btn btn-success" onclick="newProcessVariable(${documentProcess.id});">New</button>
                    </div>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover table-condensed"
                               id="dataLevelList">
                            <thead>
                            <tr class="text-primary">
                                <th>Variable Name</th>
                                <th>Description</th>
                                <th>Data Type</th>
                                <th>Referenced Document Data</th>
                                <th>Value</th>
                                <th>Is Active</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${!empty processVariableList }">
                                <c:forEach items="${processVariableList}" var="t">
                                    <tr>
                                        <td style="width:10%">${t.variableName }</td>
                                        <td style="width:15%">${t.description }</td>
                                        <td style="width:8%">${t.dataType }</td>
                                        <td style="width:24%">
                                            <c:if test="${!empty t.documentDictionary}">
                                        ${t.documentDictionary.documentDataLevel.levelName} - ${t.documentDictionary.columnName}
                                            </c:if>
                                        </td>
                                        <td style="width:20%">${t.value }</td>
                                        <td style="width:8%">${t.isActive }</td>
                                        <td style="width:15%">
                                            <button class="btn btn-xs btn-danger"
                                                    onclick="location.href='../Document/deleteProcessVariable?processVariableId=${t.id}'">
                                            <i class="fa fa-trash-o fa-fw"></i> Delete
                                            </button>
                                            <c:if test="${t.isActive == 'Y'}">
                                                <button class="btn btn-xs btn-warning" onclick="location.href='../Document/activeOpForProVar?processVariableId=${t.id}&&action=inactive'">
                                                    <i class="fa fa-star-o fa-fw"></i> Inactive
                                                </button>
                                            </c:if>
                                            <c:if test="${t.isActive != 'Y'}">
                                            <button class="btn btn-xs btn-success" onclick="location.href='../Document/activeOpForProVar?processVariableId=${t.id}&&action=active'">
                                                <i class="fa fa-star fa-fw"></i> Active
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
                    </div>

                </div>
                <!-- /.panel-body -->
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="processVarModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header modal-primary">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Edit Process Variable</h4>
            </div>
            <form method="post" action="../Document/updateProcessVariable" class="form-horizontal" role="form"
                  id="processVarForm">
                <div class="modal-body">
                    <input type="hidden" name="id" id="id" value="" class="form-control">
                    <input type="hidden" name="documentProcessId" id="documentProcessId" value="" class="form-control">
                    <div class="form-group">
                        <label for="variableName" class="col-sm-3 control-label">Variable Name</label>

                        <div class="col-sm-8"><input type="text" name="variableName" id="variableName" value=""
                                                     class="form-control"></div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-sm-3 control-label">Description</label>

                        <div class="col-sm-8"><input type="text" name="description" id="description" value=""
                                                     class="form-control" ></div>
                    </div>
                    <div class="form-group">
                        <label for="dataType" class="col-sm-3 control-label">Data Type</label>

                        <div class="col-sm-8">
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
                        <label for="valueSource0" class="col-sm-3 control-label">Value Source</label>

                        <div class="col-sm-8">
                            <input type="radio" id="valueSource0" name="sourceType" value="R" checked>Referenced Document Data
                            <input type="radio" id="valueSource1" name="sourceType" value="D">Static Value
                        </div>
                    </div>
                    <div class="form-group" id="valueBlock">
                        <label for="value" class="col-sm-3 control-label">Value</label>

                        <div class="col-sm-8"><input type="text" name="value" id="value" value=""
                                                     class="form-control"></div>
                    </div>
                    <div class="form-group" id="referencedDataColumnBlock">
                        <label for="referencedDataColumnId" class="col-sm-3 control-label">Referenced Data Column</label>

                        <div class="col-sm-8">
                            <select class="form-control" name="referencedDataColumnId" id="referencedDataColumnId">
                                <c:if test="${!empty activeDocDictList }">
                                    <c:forEach items="${activeDocDictList}" var="t">
                                        <option value="${t.id}">${t.documentDataLevel.levelName} - ${t.columnName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-8">
                            <div class="checkbox">
                                <label> <input type="checkbox" name="isActive" id="isActive" value="Y"
                                        >Active
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