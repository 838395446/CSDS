package com.tm.csgl.controller;

import com.google.gson.Gson;
import com.tm.csgl.dao.JedisConfigureDao;
import com.tm.csgl.dao.TestFrameEnvDao;
import com.tm.csgl.domain.configure.JavaPathDto;
import com.tm.csgl.domain.configure.ResponseInfo;
import com.tm.csgl.domain.fortest.CodeComplieInfo;
import com.tm.csgl.domain.fortest.RunTaskList;
import com.tm.csgl.domain.fortest.TaskResultRedisTool;
import com.tm.csgl.domain.fortest.TestResultDto;
import com.tm.csgl.service.*;
import com.tm.csgl.utils.MakeIdTolls;
import com.tm.csgl.utils.Searcher;
import com.tm.csgl.utils.TaskStatusRedisTools;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Captain Wang on 18/5/26.
 */
@CrossOrigin
@RestController
public class WebApiController {
    private static Log log = LogFactory.getLog(WebApiController.class);

    private JavaPathDto javaPathDto;

    @Autowired
    private JedisConfigureDao jedisConfigureDao;

    @Autowired
    TestFrameEnvDao testFrameEnvDao;

    private MakeIdTolls makeIdTolls = new MakeIdTolls();


    public JavaPathDto getJavaPathDto() {

        Jedis jedis = new Jedis(jedisConfigureDao.getHost());
        Gson gson = new Gson();
        log.info("取出jedis：" + jedis.get(testFrameEnvDao.getEnvKey()));
        javaPathDto = gson.fromJson(jedis.get(testFrameEnvDao.getEnvKey()), JavaPathDto.class);
        jedis.close();
        return javaPathDto;
    }


    @GetMapping(value = "/compileresult")
    @ApiOperation(value = "获取任务状态", httpMethod = "GET", notes = "暂无1")
    public CodeComplieInfo getCompileResult(@RequestParam(value = "taskName") String taskName) {

        TaskResultRedisTool taskResultRedisTool = new TaskResultRedisTool(taskName);

        return taskResultRedisTool.getCompileResultDetails();

    }


    GetFiles getFiles = new GetFiles();

    @GetMapping(value = "/getclassfile")
    @ApiOperation(value = "获取用例文件", httpMethod = "GET", notes = "暂无1")
    public List<String> getlassFile() {

        JavaPathDto javaPathDto = getJavaPathDto();

        List<String> testcase = new ArrayList<>();

        String rootPath = javaPathDto.getCasePath();
        Searcher searcher = new Searcher(rootPath);
        for (String caseName : searcher.getJavaFile()) {
            testcase.add(caseName.replace(rootPath, ""));
        }
        return testcase;


    }

    @GetMapping(value = "/gettestcase")
    @ApiOperation(value = "获取用例", httpMethod = "GET", notes = "暂无")
    public List<String> gettestCase() {

        GetTestCase getTestCase = new GetTestCase(System.getProperty("user.dir")+"/src/main/java/test");
        log.info(System.getProperty("user.dir")+"/src/main/java/test");
        return getTestCase.GetTestMethods();

    }


    @GetMapping(value = "/status")
    @ApiOperation(value = "获取任务状态", httpMethod = "GET", notes = "暂无")
    public ResponseInfo getStatus(@RequestParam(value = "type") String type, @RequestParam(value = "taskName") String taskName) {
        ResponseInfo responseInfo = new ResponseInfo();
        TaskStatusRedisTools taskStatusRedisTools = new TaskStatusRedisTools();

        if (type.equals("test")) {
            taskStatusRedisTools.setTaskName(taskName);
            responseInfo.setData(taskStatusRedisTools.getTestTaskStatus());
        } else if (type.equals("compile")) {
            taskStatusRedisTools.setTaskName(taskName);
            responseInfo.setData(taskStatusRedisTools.getCompiliTaskStatus());
        }
        if (responseInfo.getData() == null) {
            responseInfo.setData("");
        }

        return responseInfo;
    }


    @GetMapping(value = "/complie")
    @ApiOperation(value = "编译", httpMethod = "GET", notes = "暂无")
    public ResponseInfo codeComplie() {
        String fileDir = "./src/main/java/test";
        String option = "-sourcepath";
        String junit4Path="/Users/wangwei/Desktop/csgl/lib/junit-4.12.jar:/src/main/java/test/tools";
        String taskName = makeIdTolls.makeRandName("COMPILE");
        JavaPathDto javaPathDto = getJavaPathDto();
        Searcher searcher = new Searcher(fileDir);

        ResponseInfo responseInfo = new ResponseInfo();

        if (searcher.getJavaFile().size() > 0) {
            //searcher.getClass();
            CodeCompile codeCompile = new CodeCompile(taskName, option, junit4Path, searcher.getJavaFile());
            Thread compileThread = new Thread(codeCompile);
            compileThread.start();
            responseInfo.setData(taskName);

        } else {
            responseInfo.setMsg("错误：没有可编译的文件！");
        }


        return responseInfo;

    }


    @PostMapping("/run")
    @Transactional
    @ResponseBody
    @ApiOperation(value = "执行用例", httpMethod = "POST", notes = "执行用例")
    public String runCase(@RequestBody RunTaskList runTaskList) {
        //ResponseInfo responseInfo=new ResponseInfo();
        MakeIdTolls makeIdTolls = new MakeIdTolls();
        String taskName = makeIdTolls.makeRandName("TEST");


        log.info("post参数：" + runTaskList.toString());

        if (runTaskList.getAll() == true || runTaskList.getCaseList().size() == 0) {


        } else {

            RunTestThread runTestThread = new RunTestThread(runTaskList, getJavaPathDto().getDependencePath(), getJavaPathDto().getCasePath(), taskName);

            Thread testThread = new Thread(runTestThread);
            testThread.start();
            /**

             String caseClass = getJavaPathDto().getCasePath();
             String dependencePath = getJavaPathDto().getDependencePath();
             List<String> methodName = new ArrayList<String>();
             for (String name : runTaskList.getCaseList()) {
             log.info("接收到：" + "test." + name);
             methodName.add("test." + name);
             }

             Junit4Runner junit4Runner = new Junit4Runner(getJavaPathDto().getCasePath(), runTaskList.getTaskName());

             TestResultDto testResultDto = junit4Runner.runTestMethods(methodName);

             saveTestInfo.setTaskInfoToRedis(testResultDto);

             return  testResultDto;
             */
        }

        return taskName;
    }


    @GetMapping("/task")
    @ApiOperation(value = "查询测试记录", httpMethod = "GET", notes = "查询用例列表")

    public List<TestResultDto> getTestResultList() {
        GetTestResultList getTestResultList = new GetTestResultList();

        return getTestResultList.getTestResultList();
    }


}
