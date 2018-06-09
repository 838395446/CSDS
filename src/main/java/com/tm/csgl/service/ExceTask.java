package com.tm.csgl.service;

import com.tm.csgl.domain.fortest.RunTaskList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExceTask {
    private static Log log=LogFactory.getLog(ExceTask.class);
    private List<HashMap<String,Object>> TaskResult;

    private String dirPath="./src/test/java/testcase";
    private List<String> directory = new ArrayList<String>();
    private Map<String,Object> testcase =new HashMap<String,Object>();


    public ExceTask() {
    }

    public void runTask(RunTaskList runTaskList) {
        if(runTaskList.getAll()==true){
//
        }else{

            for(String caseName:runTaskList.getCaseList()){


            }
        }
    }



}
