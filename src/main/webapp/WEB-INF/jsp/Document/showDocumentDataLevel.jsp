<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Document Data Level</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../css/main.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="../js/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/modal.js"></script>
    <script>
        function updateDataLevel(obj,id){
            $('#dataLevelForm').prop("action","../Document/updateDocumentDataLevel");
            $('#myModalLabel').html('Edit Document Data Level');
            $('#id').val(id);
            var tds = $(obj).parent().parent().find('td');
            $('#dataLevel').val(tds.eq(0).text());
            $('#levelName').val(tds.eq(1).text());
            $('#description').val(tds.eq(2).text());
            if(tds.eq(3).text()=="Y"){
                $('#isActive').prop("checked",true);
            }else{
                $('#isActive').prop("checked",false);
            };

            $('#dataLevelModal').modal('show');
        }

        function newDataLevel(documentId){
            $.ajax({
                type: "GET",
                url: "../Document/getNextDataLevelAjax?documentId=" + ${document.id} ,
                dataType: "json",
                success: function(data){
                      $('#dataLevel').val(data.nextDataLevel);
                      $('#dataLevelForm').prop("action","../Document/saveDocumentDataLevel");
                      $('#isActive').prop("checked",true);
                      $('#myModalLabel').html('New Document Data Level');
                    },
                error: function (data) { //服务器响应失败时的处理函数
                    alert("Get Next Data Level with Failure,please contact admin!");
                }

            });
            $('#dataLevelModal').modal('show');
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
                    <li>Document Data Level List: &lt${document.documentCode}&gt</li>
                </ol>

                <div class="panel panel-primary">
                    <div class="panel-heading">
                         Data Level List
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table-condensed"
                                   id="dataLevelList">
                                <thead>
                                <tr class="text-primary">
                                    <th>Document Data Level</th>
                                    <th>Level Name</th>
                                    <th>Description</th>
                                    <th>Is Active</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${!empty document.documentDataLevelList }">
                                    <c:forEach items="${document.documentDataLevelList}" var="t">
                                        <tr>
                                            <td style="width:15%">${t.dataLevel }</td>
                                            <td style="width:15%">${t.levelName }</td>
                                            <td style="width:20%">${t.description }</td>
                                            <td style="width:5%">${t.isActive }</td>
                                            <td style="width:15%">
                                                <button class="btn btn-xs btn-info" onclick="updateDataLevel(this,${t.id});">
                                                    <i class="fa fa-edit fa-fw"></i> Edit
                                                </button>
                                                <button class="btn btn-xs btn-success" onclick="location.href='../Document/showDocDictForDataLevel?documentDataLevelId=${t.id}'">
                                                    <i class="fa fa-book fa-fw" ></i> Dictionary
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                        <button class="btn btn-success" onclick="newDataLevel(${document.id})">New</button>
                        <div>
                        </div>
                    </div>
                    <!-- /.panel-body -->
                </div>
            </div>
    </div>
</div>

<div class="modal fade" id="dataLevelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header modal-primary">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Document Data Level</h4>
            </div>
            <form method="post" action="../Document/updateDocumentDataLevel" class="form-horizontal" role="form" id="dataLevelForm">
                <div class="modal-body">
                    <input type="hidden" name="id" id="id" value="" class="form-control">
                    <input type="hidden" name="documentId" id="documentId" value="${document.id}" class="form-control">
                    <div class="form-group">
                        <label for="dataLevel" class="col-sm-4 control-label">Data Level</label>
                        <div class="col-sm-8"><input type="text" name="dataLevel" id="dataLevel" value="" class="form-control" readonly></div>
                    </div>
                    <div class="form-group">
                        <label for="levelName" class="col-sm-4 control-label">Level Name</label>
                        <div class="col-sm-8"><input type="text" name="levelName" id="levelName" value="" class="form-control"></div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-sm-4 control-label">Description</label>
                        <div class="col-sm-8"><input type="text" name="description" id="description" value="" class="form-control">
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