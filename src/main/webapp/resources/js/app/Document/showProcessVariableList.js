/**
 * Created by shawn on 2014/12/5.
 */
function updateDocument(obj, id) {

    $('#documentForm').prop("action", "../Document/updateDocument");
    $('#myModalLabel').html('Edit Role');
    $('#id').val(id);
    var tds = $(obj).parent().parent().find('td');
    $('#documentCode').val(tds.eq(0).text());
    $('#documentCode').prop("readOnly", true);
    $('#documentName').val(tds.eq(1).text());
    $('#description').val(tds.eq(2).text());
    $('#startDate').val(tds.eq(3).text());
    $('#endDate').val(tds.eq(4).text());
    if (tds.eq(5).text() == "Y") {
        $('#isActive').prop("checked", true);
    } else {
        $('#isActive').prop("checked", false);
    }


    $('#documentModal').modal('show');
}

function newProcessVariable(docProcessId) {
    $('#processVarForm').prop("action", "../Document/saveProcessVariable");

    $('#myModalLabel').html('New Process Variable');
    $('#id').val('');
    $('#documentProcessId').val(docProcessId);
    $('#variableName').val('');
    $('#variableName').prop("readOnly", false);
    $('#description').val('');
    $("#valueSource0").prop("checked",true);
    $('#valueBlock').prop("class","form-group hidden");
    $('#referencedDataColumnBlock').prop("class","form-group");
    $('#isActive').prop("checked", true);

    $('#processVarModal').modal('show');
}


$(document).ready(function(){
    $("input[name=sourceType]").each(function(){
        $(this).click(function(){
            var vs = $(this).val();
            if(vs=="R"){
                $('#valueBlock').prop("class","form-group hidden");
                $('#referencedDataColumnBlock').prop("class","form-group");
            }
            if(vs=="D"){
                $('#valueBlock').prop("class","form-group");
                $('#referencedDataColumnBlock').prop("class","form-group hidden");
            }
        });
    });
});
