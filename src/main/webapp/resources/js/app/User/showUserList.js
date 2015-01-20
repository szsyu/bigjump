/**
 * Created by shawn on 2014/12/5.
 */
function updateUser(obj, id) {
    $('#userForm').prop("action", "../User/updateUser");
    $('#myModalLabel').html('Edit User');
    $('#id').val(id);
    var tds = $(obj).parent().parent().find('td');
    $('#userName').val(tds.eq(0).text());
    $('#userName').prop("readOnly", true);
    $('#firstName').val(tds.eq(1).text());
    $('#lastName').val(tds.eq(2).text());
    $('#email').val(tds.eq(3).text());
    if (tds.eq(4).text() == "Y") {
        $('#isActive').prop("checked", true);
    } else {
        $('#isActive').prop("checked", false);
    }
    ;

    $('#userModal').modal('show');
}

function newUser() {
    $('#userForm').prop("action", "../User/saveUser");
    $('#myModalLabel').html('New User');
    $('#id').val('');
    $('#userName').val('');
    $('#userName').prop("readOnly", false);
    $('#firstName').val('');
    $('#lastName').val('');
    $('#email').val('');
    $('#isActive').prop("checked", true);
    $('#userModal').modal('show');
}

function resetPassword(id) {
    $.ajax({
        type: "GET",
        url: "../User/resetPasswordAjax?userId=" + id,
        dataType: "json",
        success: function (data) {
            if (data.status == "SUCCESS") {
                if (data.email == null) {
                    alert("Password Reset Success! New password is " + data.newPassword);
                } else {
                    alert("Password Reset Success! New password sent to " + data.email);
                }
            }else{
                alert("Error occured when password reset, please contact admin!");
            }
        },
        error: function (data) {
            alert("Error occured when password reset, please contact admin!")
        }
    });
}