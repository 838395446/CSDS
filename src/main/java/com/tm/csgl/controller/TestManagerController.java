package com.tm.csgl.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Captain Wang on 18/5/26.
 */

@Controller
public class TestManagerController {

    //
    @GetMapping(value = "/")
    @ApiOperation(value = "平台首页", httpMethod = "GET", notes = "暂无")
    public String returnIndex() {
        return "index.html";
    }



    @GetMapping(value = "/file")
    @ApiOperation(value = "获取文件列表", httpMethod = "GET", notes = "暂无")
    public String showFileList() {
        return "file.html";
    }


    @GetMapping(value = "/testresult")
    @ApiOperation(value = "测试结果列表", httpMethod = "GET", notes = "暂无")
    public String showTestResult() {
        return "result.html";
    }

}
