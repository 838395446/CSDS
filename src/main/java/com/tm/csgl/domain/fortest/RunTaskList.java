package com.tm.csgl.domain.fortest;

import java.util.List;

public class RunTaskList {
    private String taskName;
    private Boolean all;
    private List<String> caseList;

    public RunTaskList() {
        this.all=false;
    }

    public RunTaskList(Boolean all, List<String> caseList) {
        this.all = all;
        this.caseList = caseList;
    }
    public RunTaskList(List<String> caseList) {
        this.all = false;
        this.caseList = caseList;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getAll() {
        return all;
    }

    public void setAll(Boolean all) {
        this.all = all;
    }

    public List<String> getCaseList() {
        return caseList;
    }

    public void setCaseList(List<String> caseList) {
        this.caseList = caseList;
    }

    @Override
    public String toString() {
        return "RunTaskList{" +
                "taskName='" + taskName + '\'' +
                ", all=" + all +
                ", caseList=" + caseList +
                '}';
    }
}
