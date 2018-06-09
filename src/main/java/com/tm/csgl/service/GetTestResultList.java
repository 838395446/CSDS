package com.tm.csgl.service;

import com.tm.csgl.domain.fortest.TaskResultRedisTool;
import com.tm.csgl.domain.fortest.TestResultDto;

import java.util.ArrayList;
import java.util.List;

public class GetTestResultList {

    private String taskName;


    public GetTestResultList() {

    }

    public  List<TestResultDto> getTestResultList(){

        List<String> taskNameList=new ArrayList<String>();

        TaskResultRedisTool taskResultRedisTool=new TaskResultRedisTool();

        taskNameList.addAll(taskResultRedisTool.getTaskNameList());

        List<TestResultDto> testResultDto=new ArrayList<TestResultDto>();

        for(String name:taskNameList){
            taskResultRedisTool.setTaskName(name);
            testResultDto.add(taskResultRedisTool.getTestResultDetails());
        }

        return testResultDto;

    }

}
