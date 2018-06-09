//javascript
var getFunction = "/getFunction";
var getProject = "/getproject";
var getclassfile = "/getclassfile";
var gettestcase = "/gettestcase"
var toRun = "/run";
var getStatus = "/status";

var PROJECT = PROJECT || {};
var GROUP = GROUP || {};
var TIMETOOL = TIMETOOL || {};
var TESTCASE = TESTCASE || {};
var INDEX = INDEX || {};


window.onload = function() {
    /**
    var btn_goToProject = document.getElementById('101');
    btn_goToProject.addEventListener("click", function() {

        window.location.href = "./projectlist.html";

    }, false);
    */

    $(function() {
        $('#tableBody').on('click', 'tr', function() {

            if (!$(this).attr('select')) {
                $(this).css('background', 'LightSteelBlue');
                $(this).attr('select', true);

            } else {
                $(this).css('background', '#fff');
                $(this).removeAttr('select');
                $('#selectall').removeAttr('select');
            }
        });
    });
    /*
        $(function() {
            $("#tableBody").on('click', 'span', function() {


                /**
                if ($(this).attr('id') == "view") {
                    //alert($(this).attr('name'))
                    GROUP.goToFunctionPage($(this).attr('name'))
                }
               

            })
        })
     */

}

var btn_select = document.getElementById("selectall");
btn_select.addEventListener("click", function() {

    INDEX.selectAll('#tableBody', "#selectall");

}, false);

var btn_refresh = document.getElementById("refresh");
btn_refresh.addEventListener("click", function() {

    window.location.href = hostSite + ":" + serverPort + '/index.html';
    //INDEX.getTestCase('#tableBody');

    //$('#selectall').removeAttr('select');

}, false);

var btn_goToProject = document.getElementById('javaFile');
btn_goToProject.addEventListener("click", function() {

    window.location.href = hostSite + ":" + serverPort + "/file.html";

}, false);


var btn_goToProject = document.getElementById('cases');
btn_goToProject.addEventListener("click", function() {

    window.location.href = hostSite + ":" + serverPort + "/index.html";

}, false);


var btn_goToProject = document.getElementById('resultList');
btn_goToProject.addEventListener("click", function() {

    window.location.href = hostSite + ":" + serverPort + "/result.html";

}, false);

/**
 * 
 * {
  "all": true,
  "caseList": [
    "string"
  ],
  "taskName": "string"
}
 * 
 */




var btn_run = document.getElementById("run");
btn_run.addEventListener("click", function() {
    var caseListSize = INDEX.select('#tableBody').length;
    //console.log(caseListSize);
    var runCase = {


        all: false,
        caseList: [],
        taskName: ''

    };
    if (caseListSize > 0) {

        // var caseLsit = INDEX.select('#tableBody');
        runCase.caseList = (INDEX.select('#tableBody'));
        console.log(runCase);


        INDEX.toRunCase('#tableBody', '#myModal', runCase);

    }

}, false);



INDEX.queryTask = function(modalId, taskType, taskName) {
    var testDone = 0;
    //alert(modalId)

    $.ajax({　　
        url: hostSite + ":" + serverPort + getStatus + '?' + 'type=' + taskType + '&' + 'taskName=' + taskName, //请求的URL
        　　 //timeout: 500, //超时时间设置，单位毫秒
        　　type: 'get', //请求方式，get或post
        　　data: {}, //请求所传参数，json格式
        　　dataType: 'json', //返回的数据格式
        async: false,
        　　success: function(data) { //请求成功的回调函数

            //console.info('data ' + data.data);
            var myModal = '<div>' +
                '<div>';
            if (data.data.isFinished == true) {
                myModal = myModal + '<b><span  style="font-size:16px">任务：' + taskName + ',执行完成！   ' + '<span></b>';
                testDone = 1;
            } else {
                myModal = myModal + '<b><span  style="font-size:16px">任务：' + taskName + ',当前执行：   ' + data.data.runningName + '<span></b>';
            }

            myModal = myModal + '<p></p>' +
                '<b><span id="compileInfoTitle" style="font-size:16px">执行进度   ' + data.data.finished + '/' + data.data.total + '<span></b>' +
                '<p></p>' +
                '</div>' +

                ' </div>' +

                '<div class="progress">' +
                '<div class="progress-bar progress-bar-striped active" id="jindutiao" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width:' + GetPercent(data.data.finished, data.data.total) + '">' +
                ' <span class="sr-only" id="jindutiaospan">45% Complete</span>' +
                '</div>' +
                '</div>';

            $('#modal_data').html(myModal);
            console.info("isDone: " + testDone);

            if (testDone == 1) {
                clearInterval(setIntervalId);
                console.info("break!！！！！！！！！！！！！！！！！！！！！！");

            }


        },
        　　complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数

            if (status == 'timeout') { //超时,status还有success,error等值的情况
                　　　　　
                ajaxTimeoutTest.abort();　　　　　
                alert("超时");
                // $(tableBody).html(' <div style="text-align:center;font-size:22px;"> <p></p><span class="glyphicon glyphicon-glass"><label><b> 连接超时，刷新试试！</b></lable></span><p></p></div>');　　　　
            } else if (XMLHttpRequest.status == 500 || XMLHttpRequest.status == 404) {
                //$(tableBody).html(' <div style="text-align:center;font-size:22px;"> <p></p><span class="glyphicon glyphicon-warning-sign"><label><b> 服务器异常！</b></lable></span><p></p></div>');　　
            }　
        }
    });
}


INDEX.toRunCase = function(tableBodyId, modalId, postData) {
    $('#myModal').modal('show');

    $.ajax({　　
        url: hostSite + ":" + serverPort + toRun, //请求的URL
        　　 //timeout: 500, //超时时间设置，单位毫秒
        　　type: 'post', //请求方式，get或post
        　　data: JSON.stringify(postData), //请求所传参数，json格式
        　　dataType: 'text/json', //返回的数据格式
        contentType: "application/json; charset=utf-8",
        　success: function(data) { //请求成功的回调函数
            //alert(data.toString());
            //INDEX.drewTables(data, tableBodyId);
        },
        complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
            if (XMLHttpRequest.status == 200) {

                // alert(XMLHttpRequest.responseText);

                setIntervalId = setInterval(function() { INDEX.queryTask(modalId, 'test', XMLHttpRequest.responseText); }, 1000);


            } else {
                alert(XMLHttpRequest.status);
            }　　　

            /*
            if (status == 'timeout') { //超时,status还有success,error等值的情况
                　　　　　
                ajaxTimeoutTest.abort();　　　　　
                alert("超时");
                $(tableBody).html(' <div style="text-align:center;font-size:22px;"> <p></p><span class="glyphicon glyphicon-glass"><label><b> 连接超时，刷新试试！</b></lable></span><p></p></div>');　　　　
            } else if (XMLHttpRequest.status == 500 || XMLHttpRequest.status == 404) {
                $(tableBody).html(' <div style="text-align:center;font-size:22px;"> <p></p><span class="glyphicon glyphicon-warning-sign"><label><b> 服务器异常！</b></lable></span><p></p></div>');　　
            }　
            */
        }
    });


}


/*

INDEX.toCodeComplie = function(tableBodyId) {

    $.ajax({　　
        url: hostSite + ":" + serverPort + toCodeComplie, //请求的URL
        　　 //timeout: 500, //超时时间设置，单位毫秒
        　　type: 'get', //请求方式，get或post
        　　data: {}, //请求所传参数，json格式
        　　dataType: 'json', //返回的数据格式

        　　success: function(data) { //请求成功的回调函数
            　
            alert("dfd");
            INDEX.drewTablesForComplie(data, tableBodyId);　　　
            //alert(data.toString());
            //$(tableBody).html(' <div style="text-align:center;font-size:22px;"> <p></p><span class="glyphicon glyphicon-warning-sign"><label><b>已完成源码编译！</b></lable></span><p></p></div>');　　
            //alert(data);
        },
        　　complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
            　　　　
            if (status == 'timeout') { //超时,status还有success,error等值的情况
                　　　　　
                ajaxTimeoutTest.abort();　　　　　
                alert("超时");
                $(tableBody).html(' <div style="text-align:center;font-size:22px;"> <p></p><span class="glyphicon glyphicon-glass"><label><b> 连接超时，刷新试试！</b></lable></span><p></p></div>');　　　　
            } else if (XMLHttpRequest.status == 500 || XMLHttpRequest.status == 404) {
                $(tableBody).html(' <div style="text-align:center;font-size:22px;"> <p></p><span class="glyphicon glyphicon-warning-sign"><label><b> 服务器异常！</b></lable></span><p></p></div>');　　
            }　
        }
    });

}
*/
INDEX.getTestCase = function(tableBodyId) {

    $.ajax({　　
        url: hostSite + ":" + serverPort + gettestcase, //请求的URL
        　　 //timeout: 500, //超时时间设置，单位毫秒
        type: 'get', //请求方式，get或post
        　　data: {}, //请求所传参数，json格式
        　　dataType: 'json', //返回的数据格式
        //crossDomain: true,

        success: function(data) { //请求成功的回调函数
            　
            //alert(data.toString());
            INDEX.drewTables(data, tableBodyId);
        },
        　　complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
            　　　　
            if (status == 'timeout') { //超时,status还有success,error等值的情况
                　　　　　
                ajaxTimeoutTest.abort();　　　　　
                alert("超时");
                $(tableBody).html(' <div style="text-align:center;font-size:22px;"> <p></p><span class="glyphicon glyphicon-glass"><label><b> 连接超时，刷新试试！</b></lable></span><p></p></div>');　　　　
            } else if (XMLHttpRequest.status == 500 || XMLHttpRequest.status == 404) {
                $(tableBody).html(' <div style="text-align:center;font-size:22px;"> <p></p><span class="glyphicon glyphicon-warning-sign"><label><b> 服务器异常！</b></lable></span><p></p></div>');　　
            }

        },
        error: function(xhr, ajaxOptions, thrownError) {
            //On error do this
            console.info("error.");
            if (xhr.status == 500) {
                //alert(data);
                //alert(ajaxOptions);
                $(tableBody).html(' <div style="text-align:center;font-size:16px;"> <p></p><span><label><b>异常，状态码：' + xhr.status + '  信息：' + ajaxOptions + '< /b></lable> </span><p></p>< /div>');　
            } else {
                $(tableBody).html(' <div style="text-align:center;font-size:16px;"> <p></p><span ><label><b>异常，状态码：' + xhr.status + '  信息：' + thrownError + '</b></lable > </span><p></p> </div>');　

            }
        }
    });

}
flightHandler = function(data) {
        alert("sdfsdf");
    }
    /*
    INDEX.drewTablesForComplie = function(data, tableBodyId) {


        //alert(data);
        if (data == null || data == '' || data.size == 0 || data.length == 0 || JSON.stringify(data) == '{}') {
            var table_null = ' <div style="text-align:center;font-size:22px;"> <p></p><span class="glyphicon glyphicon-glass"><label><b> 出错啦！</b></lable></span><p></p></div>';

            $(tableBodyId).html(table_null);
        } else {
            var passFile = data["passfile"];
            var failFile = data["errofile"];
            var table_code = '';

            if (failFile.length > 0) {
                $('#trname').css('background', 'red'); //table_code = ' <table class="table table-hover" style="background: red;"><thead><tr><th>编译失败</th><th>编译失败总数: ' + failFile.length + '</th></tr></thead><tbody id="tableBody" value="click me">';
                $('#thname1').text('编译失败');
                $('#thname2').text('失败个数：' + failFile.length);
            } else {
                //table_code = ' <table class="table table-hover" "><thead><tr><th>编译完成</th><th>总数: ' + failFile.length + '</th></tr></thead><tbody id="tableBody" value="click me">';
                $('#trname').css('background', 'green');
                $('#thname1').text('编译完成');
                $('#thname2').text('个数：' + passFile.length);

            }
            for (var i = 0; i < failFile.length; i++) {


                table_code = table_code +
                    '<tr class="case" id=' + failFile[i] + '><td>' + (i + 1) + '</td>' +
                    '<td>' + failFile[i] + '</td>' +
                    '</tr>';
                //console.log("failfile" + failFile[i]);
                // table_code = table_code + '<tr class="case" id="' + passFile[i] + '"><td>' + +'</td> < td > ' + passFile[i] + ' < /td>' + '</tr > ';

            }
            //console.log("passfile" + passFile)
            for (var i = 0; i < passFile.length; i++) {
                table_code = table_code +
                    '<tr class="case" id=' + passFile[i] + '><td>' + (i + 1) + '</td>' +
                    '<td>' + passFile[i] + '</td>' +
                    '</tr>';

            }

            //table_code = table_code + ' </tbody></table>';
            //alert(table_code);

            console.log(table_code);
            $(tableBodyId).html(table_code);

            //$(tableBodyId).html(table_code);
            table_code = 'Java文件总数： ' + (failFile.length + passFile.length);
            $("#sum").html(table_code);

        }
    }

    */

INDEX.drewTables = function(data, tableBodyId) {

    //var data = null;
    //data_list = JSON.parse(data);
    var table_code = '';
    var table_head = '';
    //alert(data);
    if (data == null || data == '' || data.size == 0 || data.length == 0 || JSON.stringify(data) == '{}') {
        var table_null = ' <div style="text-align:center;font-size:22px;"> <p></p><span class="glyphicon glyphicon-glass"><label><b> 还没有项目，快去建立吧！</b></lable></span><p></p></div>';

        $(tableBodyId).html(table_null);
        PROJECT.projectsum(val, "sum");
    } else {
        //alert(data)
        $.each(data, function(key, val) {
            table_code = table_code +
                '<tr class="case" id=' + val + '><td>' + (key + 1) + '</td>' +
                '<td>' + val + '</td>' +
                //'<td>' +
                //'<a href="#"><span class="glyphicon glyphicon-search" name="' + val + '" id="view">查看</span></a>' +

                //'</td>' +
                '</tr>';
        });
        //alert(table_code);
        $(tableBodyId).html(table_code);
        PROJECT.projectsum(data, "sum");
    }
}

PROJECT.projectsum = function(data, tableBody) {
    var table_code = '';
    var sum = 0;
    $.each(data, function(key, val) {

        sum = sum + 1;

    })
    table_code = '用例总数： ' + sum;
    $("#sum").html(table_code);


}

GROUP.goToFunctionPage = function(name) {
    $.cookie("project", name);
    window.location.href = "../functionlist.html";
    //$(location).attr('href', './functionlist.html');
}

INDEX.getSelectCase = function(parse) {
    $("div").children(".selected")
}

INDEX.selectAll = function(parse, buttonId) {

    if (!$(buttonId).attr('select')) {
        $(buttonId).attr('select', true);
        $(parse).children(".case").attr('select', true);
        $(parse).children(".case").css('background', 'LightSteelBlue')
    } else {
        $(buttonId).removeAttr('select');
        $(parse).children(".case").css('background', '#fff')
        $(parse).children(".case").removeAttr('select');
    }
    INDEX.select(parse);
}


INDEX.select = function(parse) {
    var caselist = new Array();
    var trList = $(parse).children("tr")
    for (var i = 0; i < trList.length; i++) {
        var tdArr = trList.eq(i).attr('select');

        if (trList.eq(i).attr('select')) {
            caselist.push(trList.eq(i).attr('id'));
        };
    }

    //console.log(caselist);
    return caselist;
}



/**
 * ===========================================================================
 * 其他工具函数
 */

TIMETOOL.changeYYMMDD = function(unixTime, timeZone) {
    if (typeof(timeZone) == 'number') {
        unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
    }
    var time = new Date(unixTime);
    var ymdhis = "";
    ymdhis += time.getFullYear() + "年";
    ymdhis += (time.getMonth() + 1) + "月";
    ymdhis += time.getUTCDate() + "日";

    ymdhis += " " + TIMETOOL.addZero(time.getHours()) + ":";
    ymdhis += addZero(time.getMinutes()) + ":";
    ymdhis += addZero(time.getSeconds());

    return ymdhis;

}
TIMETOOL.addZero = function(second) {

    if (second < 10) {
        return "0" + second;
    } else {
        return second;
    }
}

function GetPercent(num, total) {
    num = parseFloat(num);
    total = parseFloat(total);
    if (isNaN(num) || isNaN(total)) {
        return "-";
    }
    return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00 + "%");
}


function sleep(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime) return;
    }
}