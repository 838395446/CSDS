//javascript
var getFunction = "/getFunction";
var getProject = "/getproject";
var getclassfile = "/getclassfile";
var gettestcase = "/gettestcase";
var toCodeComplie = "/toCodeComplie";

var getTestResultList = "/task";



var TESTRESULT = TESTRESULT || {};



window.onload = function() {



    /**
    var btn_goToProject = document.getElementById('101');
    btn_goToProject.addEventListener("click", function() {

        window.location.href = "./projectlist.html";

    }, false);
   

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
    }); *
    */
}

var btn_goToProject = document.getElementById('javaFile');
btn_goToProject.addEventListener("click", function() {

    window.location.href = hostSite + ":" + serverPort + "/file.html";

}, false);

var btn_goToProject = document.getElementById('cases');
btn_goToProject.addEventListener("click", function() {

    window.location.href = hostSite + ":" + serverPort + "/index.html";

}, false);

/*
var btn_complier = document.getElementById("complier");
btn_complier.addEventListener("click", function() {

    INDEX.toCodeComplie('#tableBody');

}, false);

*/


TESTRESULT.getTestListForResult = function(tableBodyId) {

    $.ajax({　　
        url: hostSite + ":" + serverPort + getTestResultList, //请求的URL
        　　 //timeout: 500, //超时时间设置，单位毫秒
        　　type: 'get', //请求方式，get或post
        　　data: {}, //请求所传参数，json格式
        　　dataType: 'json', //返回的数据格式

        　　success: function(data) { //请求成功的回调函数
            　
            TESTRESULT.drewTablesForResult(data, tableBodyId);　　　
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

flightHandler = function(data) {
        alert("sdfsdf");
    }
    //accordion
TESTRESULT.drewTablesForResult = function(data, tableBodyId) {


    //alert(data);
    if (data == null || data == '' || data.size == 0 || data.length == 0 || JSON.stringify(data) == '{}') {
        var table_null = ' <div style="text-align:center;font-size:22px;"> <p></p><span class="glyphicon glyphicon-glass"><label><b> 出错啦！</b></lable></span><p></p></div>';

        $(tableBodyId).html(table_null);
    } else {

        var table_code = "";
        // alert(data.length);
        for (var i = 0; i < data.length; i++) {


            table_code = table_code + '<div class="panel panel-default"><per>'
            if (data[i].successful == false) {

                table_code = table_code + ' <div class="panel-heading" style="background-color:Orange;"> ';

            } else {
                table_code = table_code + '<div class="panel heading" style="background-color:LimeGreen;">';

            }
            table_code = table_code + '<h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#' + 'list' + (i + 1) + '" aria-expanded="false" class="collapsed">【任务】' +
                data[i].testName + '：【总数】：' + data[i].runCount + '   【错误】：' + data[i].failureCount + ' 【忽略】：' + data[i].ignoreCount + ' 【运行时间】：' + data[i].runTime + ' 【通过】：' + data[i].successful +
                ' </a>' +
                '</h4>' +
                '</div>' +
                '<div id="' + 'list' + (i + 1) + '" class="panel-collapse collapse" aria-expanded="false" style>' +
                ' <div class="panel-body">';




            var caseLen = data[i].caseResultDetails.length;
            for (var j = 0; j < caseLen; j++) {
                //able_code = table_code + '<table >';
                table_code = table_code + '<table class="table table-bordered">';
                if (data[i].caseResultDetails[j].wasSuccessful) {
                    table_code = table_code + '<thead  style="background-color:LimeGreen;">' +
                        ' <tr><th>' + 'No.' + (j + 1) + '</th><th></th> </tr></thead><tbody>';
                } else {
                    table_code = table_code + '<thead  style="background-color:Orange;">' +
                        ' <tr><th>' + 'No.' + (j + 1) + '</th><th></th> </tr></thead><tbody>';
                }


                $.each(data[i].caseResultDetails[j], function(key, val) {
                    //  console.log(key + " - " + val);
                    table_code = table_code + '<tr><td>' + key + '</td>' +
                        '<td>' + val + '</td></tr>';
                });

                table_code = table_code + ' </tbody></table> ';
            }
            table_code = table_code + ' </div></div></per></div>';



        }

        $(tableBodyId).html(table_code);

    }


}


TESTRESULT.goToFunctionPage = function(indexId) {
    $.cookie("project", name);
    window.location.href = "../index.html";
    window.location.href = "../file.html";
}

TESTRESULT.getSelectCase = function(parse) {
    $("div").children(".selected")
}

TESTRESULT.selectAll = function(parse, buttonId) {

    if (!$(buttonId).attr('select')) {
        $(buttonId).attr('select', true);
        $(parse).children(".case").attr('select', true);
        $(parse).children(".case").css('background', 'LightSteelBlue')
    } else {
        $(buttonId).removeAttr('select');
        $(parse).children(".case").css('background', '#fff')
        $(parse).children(".case").removeAttr('select');
    }
}


TESTRESULT.select = function(parse) {
    var caselist = new Array();
    var trList = $(parse).children("tr")
    for (var i = 0; i < trList.length; i++) {
        var tdArr = trList.eq(i).attr('select');

        if (trList.eq(i).attr('select')) {
            caselist.push(trList.eq(i).attr('id'));
        };
    }
    console.log(caselist);
}



/**
 * ===========================================================================
 * 其他工具函数
 */