/**
 * Created by shawn on 2014/12/5.
 */

function ajaxZipFileUpload() {
    $.ajaxFileUpload({
        url: '../Document/uploadDiagramZipFile',
        secureuri: false,
        fileElementId: 'zipFile',
        type: "post",
        data: "{documentId:'" + $('#documentId').val() +"'}",
        dataType: 'json',
        success: function (data) {
            if (data.status == "success") {
                $("#filePath").attr("value", data.filename);
                $('#result').html("ZIP file uploaded success!");

            } else if (data.status == "empty") {
                $('#result').html("File cannot be empty");
            }
            else if (data.status == "error") {
                $('#result').html("Error Occured when file uploading,please contact with admin！");
            }
        },
        error: function (data) { //服务器响应失败时的处理函数
            $('#result').html('Error Occured when file uploading,please contact with admin！');
        }
    });
}

