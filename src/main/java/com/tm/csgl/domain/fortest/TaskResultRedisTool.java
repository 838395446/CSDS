package com.tm.csgl.domain.fortest;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class TaskResultRedisTool {

    private static Log log = LogFactory.getLog(TaskResultRedisTool.class);
    private static String testInfoListForRedis = "TestTaskList";
    private static String testInfoMapForRedis = "TestResultDetails";
    private static String compileInfoMapForRedis = "CompileResultDetails";
    private String taskName;
    private static Long testListLen = 10L;

    public TaskResultRedisTool(String taskName) {
        this.taskName = taskName;
        log.info("当前任务名称："+ this.taskName);
    }

    public TaskResultRedisTool() {

    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }


    public List<String> getTaskNameList() {
        List<String> list = new ArrayList<String>();

        Long llen;
        Jedis jedis = new Jedis("localhost");
        Gson gson = new Gson();
        log.info("读出测试历史最后10条："  + " - " + jedis.llen(this.testInfoListForRedis));

        if (jedis.llen(this.testInfoListForRedis) <= testListLen) {
            llen = jedis.llen(this.testInfoListForRedis);

        } else {
            llen = testListLen;
        }
        if (jedis.llen(this.testInfoListForRedis) == 0) {
            return list;
        }
        list.addAll(jedis.lrange(this.testInfoListForRedis, 0, llen));
        jedis.close();
        return list;


    }

    public void setTaskNameToList() {

        this.taskName = taskName;
        Jedis jedis = new Jedis("localhost");
        Gson gson = new Gson();
        log.info("任务名称存入测试历史列表：" + this.taskName + " - " + jedis.lpush(this.testInfoListForRedis, this.taskName));
        jedis.close();
    }


    public void setTestResultDetails(TestResultDto testResultDto) {

        Jedis jedis = new Jedis("localhost");
        Gson gson = new Gson();
        log.info("存入测试结果：" + jedis.hset(this.testInfoMapForRedis, this.taskName, gson.toJson(testResultDto)));
        jedis.close();

    }


    public TestResultDto getTestResultDetails() {


        Jedis jedis = new Jedis("localhost");
        Gson gson = new Gson();
        log.info("读出测试结果：" + this.taskName);
        TestResultDto testResultDto = gson.fromJson(jedis.hget(this.testInfoMapForRedis, this.taskName), TestResultDto.class);
        jedis.close();
        log.info(testResultDto.toString());
        return testResultDto;

    }

    public CodeComplieInfo getCompileResultDetails() {

        Jedis jedis = new Jedis("localhost");
        Gson gson = new Gson();
        log.info("读出编译结果：" + this.taskName);
        log.info( gson.fromJson(jedis.hget(this.compileInfoMapForRedis, this.taskName), CodeComplieInfo.class));
        CodeComplieInfo codeComplieInfo = gson.fromJson(jedis.hget(this.compileInfoMapForRedis, this.taskName), CodeComplieInfo.class);
        jedis.close();
        return codeComplieInfo;
    }

    public void setComplieResultDetails(CodeComplieInfo CodeComplieInfo) {

        Jedis jedis = new Jedis("localhost");
        Gson gson = new Gson();
        log.info("存入编译结果：" + jedis.hset(this.compileInfoMapForRedis, this.taskName, gson.toJson(CodeComplieInfo)));
        jedis.close();

    }



}
