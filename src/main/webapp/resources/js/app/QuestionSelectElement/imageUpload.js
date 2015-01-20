/**
 * Created by shawn on 2014/11/16.
 */
function ajaxFileUpload() {
    var questionId = $("#questionId").val();
    $.ajaxFileUpload({
        url: '/survey/QuestionSelectElement/uploadFile?questionId=' + questionId,
        secureuri: false,
        fileElementId: 'file',
        type: "post",
        dataType: 'json',
        success: function (data) {
            if (data.status == "success") {
                $("#imgPath").attr("value", data.filename);
                $('#result').html("图片上传成功");
            } else if (data.status == "empty") {
                $('#result').html("图片不能为空");
            }
            else if (data.status == "error") {
                $('#result').html("出错了！");
            }
        },
        error: function (data) { //服务器响应失败时的处理函数
            $('#result').html('图片上传失败，请重试！！');
        }
    });
}