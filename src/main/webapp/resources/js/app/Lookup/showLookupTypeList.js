/**
 * Created by shawn on 2014/12/5.
 */
function updateLookupType(obj, id) {

    $('#lookupTypeForm').prop("action", "../Lookup/updateLookupType");
    $('#myModalLabel').html('Edit Lookup Type');
    var tds = $(obj).parent().parent().find('td');
    $('#id').val(id);
    $('#lookupTypeCode').val(tds.eq(0).text());
    $('#lookupTypeCode').prop("readOnly", true);
    $('#description').val(tds.eq(1).text());
    if($.trim(tds.eq(2).text())=="User"){
        $('#valueSource1').prop("checked",true);
        $('#valueSource0').prop("checked",false);
        $('#dataTypeBlock').prop("class","form-group hidden");
        $('#dataType').val(tds.eq(3).text());
    }else{
        $('#valueSource0').prop("checked",true);
        $('#valueSource1').prop("checked",false);
        $('#dataTypeBlock').prop("class","form-group");
    }
    if (tds.eq(4).text() == "Y") {
        $('#isActive').prop("checked", true);
    } else {
        $('#isActive').prop("checked", false);
    }


    $('#lookupTypeModal').modal('show');
}

function newLookupType() {
    $('#lookupTypeForm').prop("action", "../Lookup/saveLookupType");
    $('#myModalLabel').html('New Lookup Type');
    $('#id').val('');
    $('#lookupTypeCode').val('');
    $('#lookupTypeCode').prop("readOnly", false);
    $('#valueSource0').prop("checked",true);
    $('#description').val('');
    $('#isActive').prop("checked", true);

    $('#lookupTypeModal').modal('show');
}

$(document).ready(function(){
    $("input[name=valueSource]").each(function(){
        $(this).click(function(){
            var vs = $(this).val();
            if(vs=="U"){
                $('#dataTypeBlock').prop("class","form-group hidden");
            }
            if(vs=="D"){
                $('#dataTypeBlock').prop("class","form-group");
            }
        });
    });
});

