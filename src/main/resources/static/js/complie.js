//javascript
var toComplie = "/complie";
var getProject = "/getproject";
var getclassfile = "/getclassfile";
var taskstatus = "/status";
var compileresult = "/compileresult";


var COMPILE = COMPILE || {};

var btn_goToProject = document.getElementById('compile');
btn_goToProject.addEventListener("click", function() {

    COMPILE.toComplieJavaFile('#compileInfoTitle');
    // COMPILE.getTasksComplieStatus('#compileInfoTitle');

}, false);


var taskResult = "";

var btn_goToProject = document.getElementById('viewResult');
btn_goToProject.addEventListener("click", function() {

    if ($('#viewResult').attr('disabled') != 'disabled') {
        COMPILE.getTasksComplieResult('#codeInfoBody', taskResult);
    }

}, false);



COMPILE.toComplieJavaFile = function(tableBodyId) {

    $.ajax({　　
        url: hostSite + ":" + serverPort + toComplie, //请求的URL
        　　 //timeout: 500, //超时时间设置，单位毫秒
        　　type: 'get', //请求方式，get或post
        //async: false,
        data: {}, //请求所传参数，json格式
        　　dataType: 'json', //返回的数据格式
        　　success: function(data) { //请求成功的回调函数
            　　　　
            $('#myModal').modal('show');
            //alert(data.toString());
            setIntervalId = setInterval(function() { COMPILE.getTasksComplieStatus(tableBodyId, data.data); }, 1000);
            // COMPILE.drewTables(data.data, tableBodyId);
        },
        　　complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
            　　　　
            if (status == 'timeout') { //超时,status还有success,error等值的情况
                　　　　　
                ajaxTimeoutTest.abort();　　　　　
                alert("超时");

            } else if (XMLHttpRequest.status == 500 || XMLHttpRequest.status == 404) {
                alert("服务出错啦！~~");　　
            }　
        }
    });

}


COMPILE.showModalButton = function() {

    $('#viewResult').removeAttr('disabled');
}


COMPILE.getTasksComplieStatus = function(tableBodyId, taskName) {

    var isDone = 0;

    taskResult = taskName;

    $.ajax({　　
        url: hostSite + ":" + serverPort + taskstatus + '?type=compile&taskName=' + taskName, //请求的URL
        　　 //timeout: 500, //超时时间设置，单位毫秒
        　　type: 'get', //请求方式，get或post
        async: false,
        　　data: {}, //请求所传参数，json格式
        　　dataType: 'json', //返回的数据格式
        　　success: function(data) { //请求成功的回调函数


            $(tableBodyId).html('正在编译...... ' + data.data.finished + '/' + data.data.total);
            if (data.data.isFinished == true) {

                $(tableBodyId).html('<font color="LimeGreen">' + '编译完成!' + '</font>');
                isDone = 1;
            }
            if (isDone == 1) {
                clearInterval(setIntervalId);
                console.info("break!！！！！！！！！！！！！！！！！！！！！！");
                COMPILE.showModalButton();

            }

        },
        　　complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
            　　　　
            if (status == 'timeout') { //超时,status还有success,error等值的情况
                　　　　　
                ajaxTimeoutTest.abort();　　　　　
                alert("超时");　　　
            } else if (XMLHttpRequest.status == 500 || XMLHttpRequest.status == 404) {　
                alert("得，让你玩坏了！~~");
            }　
        }
    });
}





COMPILE.getTasksComplieResult = function(tableBodyId, taskName) {

    $.ajax({　　
        url: hostSite + ":" + serverPort + compileresult + '?taskName=' + taskName, //请求的URL
        　　 //timeout: 500, //超时时间设置，单位毫秒
        　　type: 'get', //请求方式，get或post
        async: false,
        　　data: {}, //请求所传参数，json格式
        　　dataType: 'json', //返回的数据格式
        　　success: function(data) { //请求成功的回调函数
            COMPILE.drewTables(data, tableBodyId);

        },
        　　complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
            　　　　
            if (status == 'timeout') { //超时,status还有success,error等值的情况
                　　　　　
                ajaxTimeoutTest.abort();　　　　　
                alert("超时");　　　
            } else if (XMLHttpRequest.status == 500 || XMLHttpRequest.status == 404) {　
                alert("得，让你玩坏了！~~");
            }　
        }
    });



}





COMPILE.drewTables = function(data, tableBodyId) {

    var table_code = '';
    var table_head = '';

    if (data == null || data == '' || data.size == 0 || data.length == 0 || JSON.stringify(data) == '{}') {
        var table_null = ' <div style="text-align:center;font-size:22px;"> <p></p><span class="glyphicon glyphicon-glass"><label><b>  神马都没有！</b></lable></span><p></p></div>';

        $(tableBodyId).html(table_null);

    } else {
        for (var i = 0; i < data.errofile.length; i++) {
            console.log("errofile:" + data.errofile[i]);

            table_code = table_code +
                '<tr id=' + data.errofile[i] + '><td>' + data.errofile[i] + '</td>' +
                '<td>' + "Fail" + '</td>' +
                '</tr>';
            i++;
        }
        for (var i = 0; i < data.passfile.length; i++) {
            console.log("passfile:" + data.passfile[i]);

            table_code = table_code +
                '<tr id=' + data.passfile[i] + '><td>' + data.passfile[i] + '</td>' +
                '<td>' + "Pass" + '</td>' +
                '</tr>';
            i++;
        }



    }
    $('#codeInfoHead').html('<tr><th style="background-color:#00FF00"><b>' + '成功：' + data.passfile.length + '</b></th> <th style="background-color:red"><b>' + '失败：' + data.errofile.length + '</b></th> </tr>');

    //alert(table_code);
    $(tableBodyId).html(table_code);


}

COMPILE.goToFunctionPage = function(name) {
    $.cookie("project", name);
    window.location.href = "../functionlist.html";
    //$(location).attr('href', './functionlist.html');
}