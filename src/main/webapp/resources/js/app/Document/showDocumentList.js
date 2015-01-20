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

function newDocument() {
    $('#documentForm').prop("action", "../Document/saveDocument");
    $('#myModalLabel').html('New Role');
    $('#id').val('');
    $('#documentCode').val('');
    $('#documentCode').prop("readOnly", false);
    $('#documentName').val('');
    $('#description').val('');
    $('#startDate').val('');
    $('#endDate').val('');
    $('#isActive').prop("checked", true);

    $('#documentModal').modal('show');
}

