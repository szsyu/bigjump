/**
 * Created by shawn on 2014/12/24.
 */
function submitLogin() {
    var userName = $.trim($('#userName').val());
    var password = $.trim($('#password').val());
    if (userName == '') {
        $('#userNameMsg').html('Please input your User Name');
    }
    if (password == '') {
        $('#passwordMsg').html('Please input your Password');
    }
    if (userName == '' || password == '') {
        return false;
    }
    if (userName != '' && password != '') {
        $.ajax({
            cache: true,
            type: "POST",
            url: "../Login/preLogin",
            data: $('#loginForm').serialize(),// 你的formid
            async: false,
            error: function (data) {
                alert("Authorization error,please contact admin!");
            },
            success: function (data) {
                if (data.loginStatus == "F") {
                    if (data.resultCode == "NO_USER") {
                        $('#userNameMsg').html('User not exist!');
                    }
                    else if (data.resultCode == "ERROR_PASS") {
                        $('#passwordMsg').html('Error password!');
                    }
                } else if (data.loginStatus == "S") {
                    $("#loginForm").submit();
                }
            }
        });
    }
}