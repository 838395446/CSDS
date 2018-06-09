//javascript
var getFunction = "/getFunction";
var getProject = "/getproject";
var getclassfile = "/getclassfile";

var getStatus = "/status";


var PROJECT = PROJECT || {};
var GROUP = GROUP || {};
var TIMETOOL = TIMETOOL || {};
var TESTCASE = TESTCASE || {};


window.onload = function() {

    var btn = document.getElementById("refresh");
    btn.addEventListener("click", function() {

        GROUP.getTestCase('#tableBody');

    }, false);

    /**
    var btn_goToProject = document.getElementById('101');
    btn_goToProject.addEventListener("click", function() {

        window.location.href = "./projectlist.html";

    }, false);
    */
    $(function() {
        $("#tableBody").on('click', 'span', function() {

            if ($(this).attr('id') == "view") {
                //alert($(this).attr('name'))
                GROUP.goToFunctionPage($(this).attr('name'))
            }
            /** 
            if (!$(this).attr('s')) {
                $(this).css('background', 'red');
                $(this).attr('s', true);

            } else {
                $(this).css('background', '#fff');
                $(this).removeAttr('s');
            }
            */
        })
    })


}


var btn_goToProject = document.getElementById('cases');
btn_goToProject.addEventListener("click", function() {

    window.location.href = hostSite + ":" + serverPort + "/index.html";

}, false);



var btn_goToProject = document.getElementById('resultList');
btn_goToProject.addEventListener("click", function() {

    window.location.href = hostSite + ":" + serverPort + "/result.html";

}, false);


GROUP.getTestCase = function(tableBodyId) {

    $.ajax({　　
        url: hostSite + ":" + serverPort + getclassfile, //请求的URL
        　　 //timeout: 500, //超时时间设置，单位毫秒
        　　type: 'get', //请求方式，get或post
        　　data: {}, //请求所传参数，json格式
        　　dataType: 'json', //返回的数据格式
        　　success: function(data) { //请求成功的回调函数
            　　　　
            //alert(data.toString());
            GROUP.drewTables(data, tableBodyId);
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

GROUP.drewTables = function(data, tableBodyId) {

    //var data = null;
    //data_list = JSON.parse(data);
    var table_code = '';
    var table_head = '';
    //alert(data);
    if (data == null || data == '' || data.size == 0 || data.length == 0 || JSON.stringify(data) == '{}') {
        var table_null = ' <div style="text-align:center;font-size:22px;"> <p></p><span class="glyphicon glyphicon-glass"><label><b> 还没有项目，快去建立吧！</b></lable></span><p></p></div>';

        $(tableBodyId).html(table_null);
        PROJECT.projectsum(data, "sum");
    } else {
        //alert(data)
        var i = 1;
        $.each(data, function(key, val) {
            table_code = table_code +
                '<tr id=' + val + '><td>' + i + '</td>' +
                '<td>' + val + '</td>' +
                // '<td>' +
                //'<a href="#"><span class="glyphicon glyphicon-search" name="' + val + '" id="view">查看</span></a>' +

                //'</td>' +
                '</tr>';
            i++;
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
    table_code = '总数： ' + sum;
    $("#sum").html(table_code);


}

GROUP.goToFunctionPage = function(name) {
    $.cookie("project", name);
    window.location.href = "../functionlist.html";
    //$(location).attr('href', './functionlist.html');
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