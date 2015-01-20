/**
 * Created by shawn on 2014/11/16.
 */
$(function () {
    $('#startDate').datetimepicker({
        format: 'yyyy-mm-dd',
        minView: 2,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    });
    $('#endDate').datetimepicker({
        format: 'yyyy-mm-dd',
        minView: 2,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    });

});