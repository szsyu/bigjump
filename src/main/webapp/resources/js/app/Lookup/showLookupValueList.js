/**
 * Created by shawn on 2014/12/5.
 */
function updateLookupValue(obj, id ,userId) {

    $('#lookupValueForm').prop("action", "../Lookup/updateLookupValue");
    $('#myModalLabel').html('Edit Lookup Value');
    var tds = $(obj).parent().parent().find('td');
    $('#id').val(id);
    $('#lookupCode').val(tds.eq(0).text());
    $('#lookupCode').prop("readOnly", true);
    $('#description').val(tds.eq(1).text());

    if($('#valueSource').val() == "U"){
        $('#valueBlock').prop("class","form-group hidden");
        $('#userBlock').prop("class","form-group");
        $('#userId').val(userId);

    }else{
        $('#valueBlock').prop("class","form-group");
        $('#userBlock').prop("class","form-group hidden");
        $('#value').val(tds.eq(3).text());
    }

    if (tds.eq(5).text() == "Y") {
        $('#isActive').prop("checked", true);
    } else {
        $('#isActive').prop("checked", false);
    }


    $('#lookupValueModal').modal('show');
}

function newLookupValue() {
    $('#lookupValueForm').prop("action", "../Lookup/saveLookupValue");
    $('#myModalLabel').html('New Lookup Value');
    $('#id').val('');
    $('#lookupCode').val('');
    $('#lookupCode').prop("readOnly", false);
    $('#description').val('');
    if($('#valueSource').val() == "U"){
        $('#valueBlock').prop("class","form-group hidden");
        $('#userBlock').prop("class","form-group");
    }else{
        $('#valueBlock').prop("class","form-group");
        $('#userBlock').prop("class","form-group hidden");
    }
    $('#isActive').prop("checked", true);

    $('#lookupValueModal').modal('show');
}

