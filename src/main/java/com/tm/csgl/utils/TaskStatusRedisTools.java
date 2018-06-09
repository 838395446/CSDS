package com.tm.csgl.utils;

import com.google.gson.Gson;
import com.tm.csgl.domain.fortest.CompileTaskDto;
import com.tm.csgl.domain.fortest.TestTaskStatusDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;

public class TaskStatusRedisTools {
    private static Log log = LogFactory.getLog(TaskStatusRedisTools.class);

    private String compileStatus = "CompileStatus";
    private String testStatus = "TestStatus";
    private String taskName="";

    public TaskStatusRedisTools(String taskName) {
        this.taskName = taskName;
    }
    public TaskStatusRedisTools() {

    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }




    public CompileTaskDto getCompiliTaskStatus() {
        log.info("任务名称：" +this.taskName);
        Jedis jedis = new Jedis("localhost");
        Gson gson = new Gson();
        log.info("取出编译任务状态：" + jedis.hget(this.compileStatus, this.taskName));
        CompileTaskDto CompileTaskDto = gson.fromJson(jedis.hget(this.compileStatus,this.taskName), CompileTaskDto.class);
        jedis.close();
        return CompileTaskDto;
    }

    public void setCompiliTaskStatus(CompileTaskDto compileTaskDto) {
        log.info("任务名称：" +this.taskName);
        Jedis jedis = new Jedis("localhost");
        Gson gson = new Gson();
        log.info("存入编译任务状态：" + jedis.hset(this.compileStatus, this.taskName, gson.toJson(compileTaskDto)) + "\n" + compileTaskDto.toString());
        jedis.close();
    }


    public TestTaskStatusDto getTestTaskStatus() {
        log.info("任务名称：" +this.taskName);
        Jedis jedis = new Jedis("localhost");
        Gson gson = new Gson();
        log.info("读出测试任务状态：" + jedis.hget(this.testStatus,this.taskName));
        TestTaskStatusDto testTaskStatusDto = gson.fromJson(jedis.hget(this.testStatus, this.taskName), TestTaskStatusDto.class);
        jedis.close();
        return testTaskStatusDto;
    }


    public void setTestTaskStatus(TestTaskStatusDto testTaskStatusDto) {
        log.info("任务名称：" +this.taskName);
        Jedis jedis = new Jedis("localhost");
        Gson gson = new Gson();
        log.info("存入测试任务状态：" + jedis.hset(this.testStatus,  this.taskName,gson.toJson(testTaskStatusDto)) + "\n" + testTaskStatusDto.toString());
        jedis.close();
    }

}
