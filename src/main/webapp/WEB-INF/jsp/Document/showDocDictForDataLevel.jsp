<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Document Dictionary For Data Level</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../css/main.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="../js/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/modal.js"></script>
    <script>
        function update(obj,id){
            $('#id').val(id);
            var tds = $(obj).parent().parent().find('td');
            $('#dataLevel').val(tds.eq(0).text());
            $('#documentDataColumn').val(tds.eq(1).text());
            $('#columnName').val(tds.eq(2).text());
            $('#columnLabel').val(tds.eq(3).text());
            $('#description').val(tds.eq(4).text());
            if(tds.eq(5).text()=="Y"){
                //alert($('#visible').attr("checked"));
                $('#visible').prop("checked",true);
            }else{
                $('#visible').prop("checked",false);
            };

            if(tds.eq(6).text()=="Y"){
                $('#isActive').prop("checked",true);
            }else{
                $('#isActive').prop("checked",false);
            };

            $('#myModal').modal('show');
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
                <li><a href="../Document/showDocumentDataLevel?documentId=${documentDataLevel.document.id}">Document
                    Data Level List: &lt${documentDataLevel.document.documentCode}&gt</a></li>
                <li>Dictionary List: &lt${documentDataLevel.levelName}&gt</li>
            </ol>

            <div class="panel panel-default">
                <div class="panel-heading">
                    Dictionary List
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover table-condensed"
                               id="dataLevelList">
                            <thead>
                            <tr class="text-primary">
                                <th>Document Data Level</th>
                                <th>Document Data Column</th>
                                <th>Column Name</th>
                                <th>Column Label</th>
                                <th>Description</th>
                                <th>Visible</th>
                                <th>Is Active</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${!empty documentDictionaryList }">
                                <c:forEach items="${documentDictionaryList}" var="t">
                                    <tr>
                                        <td style="width:15%">${t.documentDataLevel.dataLevel }</td>
                                        <td style="width:15%">${t.documentDataColumn }</td>
                                        <td style="width:12%">${t.columnName }</td>
                                        <td style="width:12%">${t.columnLabel }</td>
                                        <td style="width:20%">${t.description }</td>
                                        <td style="width:8%">${t.visible }</td>
                                        <td style="width:8%">${t.isActive }</td>
                                        <td style="width:10%">
                                            <button class="btn btn-xs btn-info" onclick="update(this,${t.id});">
                                                <i class="fa fa-edit fa-fw"></i> Edit
                                            </button>
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

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header modal-primary">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Edit Document Dictionary</h4>
            </div>
            <form method="post" action="../Document/updateDocumentDict" class="form-horizontal" role="form">
                <div class="modal-body">
                    <input type="hidden" name="id" id="id" value="" class="form-control">
                    <div class="form-group">
                        <label for="dataLevel" class="col-sm-4 control-label">Data Level</label>
                        <div class="col-sm-8"><input type="text" name="dataLevel" id="dataLevel" value="" class="form-control" disabled></div>
                    </div>
                    <div class="form-group">
                        <label for="documentDataColumn" class="col-sm-4 control-label">Document Data Column</label>
                        <div class="col-sm-8"><input type="text" name="documentDataColumn" id="documentDataColumn" value="" class="form-control" disabled></div>
                    </div>
                    <div class="form-group">
                        <label for="columnName" class="col-sm-4 control-label">Column Name</label>
                        <div class="col-sm-8"><input type="text" name="columnName" id="columnName" value="" class="form-control"></div>
                    </div>
                    <div class="form-group">
                        <label for="columnLabel" class="col-sm-4 control-label">Column Label</label>
                        <div class="col-sm-8"><input type="text" name="columnLabel" id="columnLabel" value="" class="form-control"></div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-sm-4 control-label">Description</label>
                        <div class="col-sm-8"><input type="text" name="description" id="description" value="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-8">
                            <div class="checkbox">
                                <label> <input type="checkbox" name="visible" id="visible" value="Y"
                                               >Visible
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-8">
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