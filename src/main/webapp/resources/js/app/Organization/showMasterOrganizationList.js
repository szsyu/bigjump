function newMasterOrganization() {
    $('#masterOrganizationForm').prop("action", "../Organization/addMasterOrganization");
    $('#myModalLabel').html('New Master Organization');
    $("#orgCode").val('');
    $("#orgName").val('');
    $("#description").val('');
    $('#isActive').prop("checked", true);
    $('#masterOrganizationModal').modal('show');


}

function editMasterOrganization(obj,masterOrganizationId){
    $('#masterOrganizationForm').prop("action", "../Organization/updateMasterOrganization");
    $('#myModalLabel').html('New Master Organization');
    $('#id').val(masterOrganizationId);
    var tds = $(obj).parent().parent().find('td');
    $("#orgCode").val(tds.eq(0).text());
    $("#orgName").val(tds.eq(1).text());
    $("#description").val(tds.eq(2).text());
    if (tds.eq(3).text() == "Y") {
        $('#isActive').prop("checked", true);
    } else {
        $('#isActive').prop("checked", false);
    }
    $('#masterOrganizationModal').modal('show');
}

