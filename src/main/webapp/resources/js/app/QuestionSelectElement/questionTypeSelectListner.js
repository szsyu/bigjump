/**
 * Created by shawn on 2014/11/16.
 */
$(document).ready(function () {

    if ($("#questionElementTypeCode").children('option:selected').val() == "IMAGE") {
        $("#imgUploadBlock").show();
        $("#imgPathBlock").show();
    } else if ($("#questionElementTypeCode").children('option:selected').val() == "TEXT") {
        $("#imgUploadBlock").hide();
        $("#imgPathBlock").hide();
    }

    $("#questionElementTypeCode").change(function () {
        //alert($(this).children('option:selected').val());
        if ($(this).children('option:selected').val() == "IMAGE") {
            $("#imgUploadBlock").show();
            $("#imgPathBlock").show();
        } else if ($(this).children('option:selected').val() == "TEXT") {
            $("#imgUploadBlock").hide();
            $("#imgPathBlock").hide();
        }
    })
})