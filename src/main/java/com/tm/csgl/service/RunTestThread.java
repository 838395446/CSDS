package com.tm.csgl.service;

import com.tm.csgl.domain.fortest.RunTaskList;
import com.tm.csgl.domain.fortest.TaskResultRedisTool;
import com.tm.csgl.domain.fortest.TestResultDto;
import com.tm.csgl.utils.Junit4Runner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class RunTestThread implements Runnable {
    private static Log log = LogFactory.getLog(RunTestThread.class);

    private String casePath;

    private String dependencePath;

    private List<String>taskList =new ArrayList<String>();

    private Boolean allTest;

    private String taskName;



    public RunTestThread(RunTaskList runTaskList, String dependencePath,String casePath,String taskName) {
        this.casePath = casePath;
        this.dependencePath = dependencePath;
        this.taskList.addAll(runTaskList.getCaseList());
        this.allTest = runTaskList.getAll();
        this.taskName=taskName;
        log.info("post参数：" + runTaskList.toString());
    }

    @Override
    public void run() {
        runTestTask();

    }

    public void runTestTask() {


        TaskResultRedisTool taskResultRedisTool = new TaskResultRedisTool(this.taskName);
        taskResultRedisTool.setTaskNameToList();

        if (this.allTest == true) {

        } else {
            //String caseClass = getJavaPathDto().getCasePath();
            //String dependencePath = getJavaPathDto().getDependencePath();
            List<String> methodName = new ArrayList<String>();

            for (String name : this.taskList) {

                log.info("接收到：" + "test." + name);

                methodName.add("test." + name);

            }

            Junit4Runner junit4Runner = new Junit4Runner(casePath, taskName);

            TestResultDto testResultDto = junit4Runner.runTestMethods(methodName);

            taskResultRedisTool.setTestResultDetails(testResultDto);

            log.info("执行测试完成！");
        }

    }
}
