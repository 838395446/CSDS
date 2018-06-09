package com.tm.csgl.domain.configure;

import java.util.Map;

public class TaskProgressStatusDto {

    private String taskProgressStatusKey;

    private String runningKey;
    private String totalKey;
    private String finished;
    private Map<String,Object> status;

    public TaskProgressStatusDto() {
        this.taskProgressStatusKey = "TaskProgressStatus";
        this.runningKey = "Running";
        this.totalKey="Total";
        this.finished="Finished";
    }

    public String getTaskProgressStatusKey() {
        return taskProgressStatusKey;
    }

    public void setTaskProgressStatusKey(String taskProgressStatusKey) {
        this.taskProgressStatusKey = taskProgressStatusKey;
    }

    public String getRunningKey() {
        return runningKey;
    }

    public void setRunningKey(String runningKey) {
        this.runningKey = runningKey;
    }

    public String getTotalKey() {
        return totalKey;
    }

    public void setTotalKey(String totalKey) {
        this.totalKey = totalKey;
    }

    public Map<String, Object> getStatus() {
        return status;
    }

    public void setStatus(Map<String, Object> status) {
        this.status = status;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }
}
