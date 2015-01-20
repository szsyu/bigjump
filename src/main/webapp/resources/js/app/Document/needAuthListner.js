/**
 * Created by shawn on 2014/11/16.
 */
$(document).ready(function () {

    if ($("#needAuth").is(":checked")) {
        $("#needAuthConfirm").show();
    } else if (!$("#needAuth").is(":checked")) {
        $("#needAuthConfirm").hide();
    }

    $("#needAuth").change(function () {
        if ($("#needAuth").is(":checked")) {
            $("#needAuthConfirm").show();
        } else if (!$("#needAuth").is(":checked")) {
            $("#needAuthConfirm").hide();
        }
    })
})