function addChildOrganization(parentOrganizationId,masterOrganizationId) {
    $("#parentOrganizationId").val(parentOrganizationId);
    $("#masterOrganizationId").val(masterOrganizationId);
    $('#orgModal').modal('show');


}

function deleteOrganization(organizationId,masterOrganizationId){
    window.location.href="../Organization/deleteOrganization?organizationId=" + organizationId + "&masterOrganizationId=" + masterOrganizationId;
}
