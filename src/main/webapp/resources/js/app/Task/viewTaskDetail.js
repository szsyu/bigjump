/**
 * Created by shawn on 2014/12/27.
 */
function action(actionName){
    var actionUrl;
    var taskId = $('#taskId').val();
    if($.trim(taskId)==''){
        alert("No task Id found,please contact with administrator/MIS!");
        return false;
    }
    if(actionName == 'APPROVE'){
        $('#actionForm').prop('action','../Task/approveTask');
        $('#actionForm').submit();
    }
    if(actionName == 'REJECT'){
        $('#actionForm').prop('action','../Task/rejectTask');
        $('#actionForm').submit();
    }
    if(actionName == 'CLAIM_APPROVE'){
        $('#actionForm').prop('action','../Task/claimAndApproveTask');
        $('#actionForm').submit();
    }
    if(actionName == 'CLAIM_REJECT'){
        $('#actionForm').prop('action','../Task/claimAndRejectTask');
        $('#actionForm').submit();
    }

}