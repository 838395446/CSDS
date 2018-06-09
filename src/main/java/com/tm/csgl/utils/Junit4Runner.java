package com.tm.csgl.utils;

import com.tm.csgl.domain.fortest.TestResultDto;
import com.tm.csgl.domain.fortest.TestTaskStatusDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class Junit4Runner {
    private static Log log = LogFactory.getLog(Junit4Runner.class);

    private Long runCount=0L;

    private Long failureCount=0L;

    private Long ignoreCount=0L;

    private Long runTime=0L;
    private Boolean successful=true;

    private String casePath;

    private String taskName;

    public Junit4Runner(String casePath,String taskName) {
        this.casePath = casePath;
        this.taskName=taskName;
    }


    public TestResultDto runTestMethods(List<String> testMethods) {

        TaskStatusRedisTools taskStatusTools=new TaskStatusRedisTools(this.taskName);
        TestResultDto testResultDto=new TestResultDto(this.taskName);
        TestTaskStatusDto testTaskStatusDto=new TestTaskStatusDto();

        taskStatusTools.setTestTaskStatus(testTaskStatusDto);

        testTaskStatusDto.setTaskName(this.taskName);
        testTaskStatusDto.setTotal((long)testMethods.size());

        taskStatusTools.setTestTaskStatus(testTaskStatusDto);

        for (String testMethod : testMethods) {

            testTaskStatusDto.setRunningName(testMethod);
            taskStatusTools.setTestTaskStatus(testTaskStatusDto);

            try {

                log.info("目标Root路径：" + this.casePath);

                log.info("TestMethod: " + testMethod);

                File file = new File(this.casePath.replace("/test", ""));//类路径(包文件上一层)

                URL url = file.toURI().toURL();

                ClassLoader loader = new URLClassLoader(new URL[]{url});//创建类加载器

                Class<?> cls = null;//加载指定类，注意一定要带上类的包名

                log.info("获取测试用例，当前文件：" + "test" + this.casePath.replace(this.casePath, "").replace("/", "."));
                //log.error("Class Path: " + "test." + testMethod.substring(0, testMethod.lastIndexOf(".")));

                cls = loader.loadClass(testMethod.substring(0, testMethod.lastIndexOf(".")));

                Object clazz = cls.newInstance();//初始化一个实例
                /**
                 for (java.lang.reflect.Method method : obj.getClass().getDeclaredMethods()) {
                 this.caseMethod.add((casePath.replace(this.casePath, "").replace("/", ".") + "." + method.getName()).substring(1));
                 log.info(method.getName());
                 }
                 */
                Junit4Listener listener = new Junit4Listener();

                JUnitCore junitRunner = new JUnitCore();

                junitRunner.addListener(listener);

                Request request = Request.method(clazz.getClass(), testMethod.split("\\.")[testMethod.split("\\.").length - 1]);

                junitRunner.run(request);
                //log.info("最后结果：" + listener.getCaseTestResult().toString());

                if (!listener.getCaseTestResult().getWasSuccessful()) {
                    this.failureCount = this.failureCount + 1;
                    this.successful=false;
                }
                if (!listener.getCaseTestResult().getIsTest()) {
                    this.ignoreCount = this.ignoreCount + 1;
                }


                this.runTime = this.runTime + listener.getCaseTestResult().getRunTime();
                this.runCount = this.runCount + 1;
                testResultDto.addCaseResultDetails(listener.getCaseTestResult());

            } catch (Exception e) {
                e.printStackTrace();
            }
            testTaskStatusDto.setFinished(testTaskStatusDto.getFinished()+1);
            taskStatusTools.setTestTaskStatus(testTaskStatusDto);
        }


        testTaskStatusDto.setIsFinished(true);

        taskStatusTools.setTestTaskStatus(testTaskStatusDto);
        testResultDto.setSuccessful(this.successful);
        testResultDto.setFailureCount(this.failureCount);
        testResultDto.setIgnoreCount(this.ignoreCount);
        testResultDto.setRunCount(this.runCount);
        testResultDto.setRunTime(this.runTime);

        return testResultDto;

    }


}


