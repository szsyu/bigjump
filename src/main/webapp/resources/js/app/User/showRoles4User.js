function addRole() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "../User/checkRoleExist4User",
        data: $('#addRole').serialize(),
        async: false,
        error: function (data) {
            alert("Some error occured when check Role eixst for User!");
        },
        success: function (data) {
            if (data.status == "NOT_EXIST") {
                $('#addRole').submit();
            } else if (data.status == "EXIST") {
                alert("Role has exist for User!");
            }
        }
    });
}