/**
 * Created by shawn on 2014/12/5.
 */
function updateRole(obj, id) {

    $('#roleForm').prop("action", "../Role/updateRole");
    $('#myModalLabel').html('Edit Role');
    $('#id').val(id);
    var tds = $(obj).parent().parent().find('td');
    $('#roleCode').val(tds.eq(0).text());
    $('#roleCode').prop("readOnly", true);
    $('#roleName').val(tds.eq(1).text());
    $('#description').val(tds.eq(2).text());
    $('#documentCodeUpdate').val(tds.eq(3).text());
    $('#documentCodeUpdate').prop("readOnly",true);
    if (tds.eq(4).text() == "Y") {
        $('#isActive').prop("checked", true);
    } else {
        $('#isActive').prop("checked", false);
    }

    $('#documentChooseForNew').prop("class","form-group hidden");
    $('#documentChooseForUpdate').prop("class","form-group");

    $('#roleModal').modal('show');
}

function newRole() {
    $('#roleForm').prop("action", "../Role/saveRole");
    $('#myModalLabel').html('New Role');
    $('#id').val('');
    $('#roleCode').val('');
    $('#roleCode').prop("readOnly", false);
    $('#roleName').val('');
    $('#description').val('');
    $('#isActive').prop("checked", true);

    $('#documentChooseForNew').prop("class","form-group");
    $('#documentChooseForUpdate').prop("class","form-group hidden");
    $('#roleModal').modal('show');
}

