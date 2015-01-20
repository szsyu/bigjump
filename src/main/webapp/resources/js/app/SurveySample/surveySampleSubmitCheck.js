/**
 * Created by shawn on 2014/11/26.
 */
function surveySampleSubmitCheck() {
    var inputs = document.getElementsByTagName("input");//获取所有的input标签对象
    var checkboxArray = [];//初始化空数组，用来存放checkbox对象。
    var checkboxGroup = [];//checkbox,CG_XXX相同的为一组
    var radioArray = [];
    var radioGroup = [];
    var checkboxChecked = true;
    var radioChecked = true;
    for (var i = 0; i < inputs.length; i++) {
        var obj = inputs[i];
        if (obj.type == 'checkbox') {
            var checkboxName = obj.name;
            checkboxArray.push(obj.value);
            if (checkboxGroup.indexOf(checkboxName.substr(0, checkboxName.lastIndexOf('_'))) == -1) {
                checkboxGroup.push(checkboxName.substr(0, checkboxName.lastIndexOf('_')));
            }
        }

        else if (obj.type == 'radio') {
            var radioId = obj.id;
            radioArray.push(obj.id);
            if(radioGroup.indexOf(radioId.substr(0, radioId.lastIndexOf('_'))) == -1){
                radioGroup.push(radioId.substr(0, radioId.lastIndexOf('_')));
            }
        }
    }

    if(radioGroup.length > 0){
        for (var j = 0; j < radioGroup.length; j++) {
            var radioGroupChecked = false;
            for (var k = 0; k < radioArray.length; k++) {
                if (radioArray[k].indexOf(radioGroup[j]) == 0) {
                    //alert(1);
                    if (document.getElementById(radioArray[k]).checked) {
                        radioGroupChecked = true;
                        break;
                    }
                }
            }
            if (!radioGroupChecked) {
                radioChecked = false;
                break;
            }
        }
    }

    if (checkboxGroup.length > 0) {
        for (var j = 0; j < checkboxGroup.length; j++) {
            var checkboxGroupChecked = false;
            for (var k = 0; k < checkboxArray.length; k++) {
                if (checkboxArray[k].indexOf(checkboxGroup[j]) == 0) {
                    if (document.getElementById(checkboxArray[k]).checked) {
                        checkboxGroupChecked = true;
                        break;
                    }
                }
            }
            if (!checkboxGroupChecked) {
                checkboxChecked = false;
                break;
            }
        }
    }

    if(checkboxChecked && radioChecked){
        $("#surveySample").submit();
    }else if(checkboxChecked && !radioChecked){
        alert("您有未完成的单选题(包括判断题)！");
    }else if(!checkboxChecked && radioChecked){
        alert("您有未完成的多选题！");
    }else if(!checkboxChecked && !radioChecked){
        alert("您有未完成的单选题(判断题)和多选题！");
    }
}
