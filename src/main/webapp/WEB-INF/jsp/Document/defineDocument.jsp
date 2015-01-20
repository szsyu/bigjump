<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Define Document</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/main.css" rel="stylesheet">
    <!-- Bootstrap DateTimePicker CSS -->
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="../js/jquery.js"></script>
    <!-- Bootstrap DateTimePicker JS -->
    <script src="../js/bootstrap-datetimepicker.min.js"></script>
    <script src="../js/app/Document/documentDatePicker.js"></script>
</head>
<body>
<jsp:include page="../Layouts/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../Layouts/sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Document Configuration</h1>

            <div class="panel panel-default">
                <div class="panel-heading">
                    Define Document
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <form class="form-horizontal center"
                          action="../Document/saveDocument" method="post">
                        <div class="form-group">
                            <label for="documentCode" class="col-sm-2 control-label">Document Code</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="documentCode"
                                       name="documentCode" placeholder="Document Code">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="documentName" class="col-sm-2 control-label">Document Name</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="documentName"
                                       name="documentName" placeholder="Document Name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="col-sm-2 control-label">Description</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="description"
                                       name="description" placeholder="Description">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="startDate" class="col-sm-2 control-label">Start
                                Date</label>

                            <div class='form_date col-sm-5'  data-link-format="yyyy-mm-dd">
                                <input type="text" class="form-control" id="startDate"
                                       name="startDate" placeholder="Start Date">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="endDate" class="col-sm-2 control-label">End
                                Date</label>

                            <div class='form_date col-sm-5'  data-link-format="yyyy-mm-dd">
                                <input type="text" class="form-control" id="endDate"
                                       name="endDate" placeholder="End Date">
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