/**
 * Created by shawn on 2014/11/16.
 */
function ajaxFileUpload() {
    $.ajaxFileUpload({
        url: '/survey/Survey/uploadExcelFile',
        secureuri: false,
        fileElementId: 'excelFile',
        type: "post",
        dataType: 'json',
        success: function (data) {
            if (data.status == "success") {
                $("#filePath").attr("value", data.filename);
                $('#result').html("文件上传成功");
            } else if (data.status == "empty") {
                $('#result').html("文件不能为空");
            }
            else if (data.status == "error") {
                $('#result').html("出错了！");
            }
        },
        error: function (data) { //服务器响应失败时的处理函数
            $('#result').html('文件上传失败，请重试！！');
        }
    });
}


function generateSurvey() {
    $.ajaxFileUpload({
        url: '/survey/Survey/generateSurvey',
        secureuri: false,
        fileElementId: 'filePath',
        type: "post",
        dataType: 'json',
        success: function (data) {
            if (data.status == "SUCCESS") {
                $('#generateResult').html("Survey创建成功！");
                $('#generateBtn').hide();
            }
            else if (data.status == "ERROR") {
                $('#generateResult').html("Survey创建失败！");
            }
        },
        error: function (data) { //服务器响应失败时的处理函数
            $('#generateResult').html('出错了！！');
        }
    });
}