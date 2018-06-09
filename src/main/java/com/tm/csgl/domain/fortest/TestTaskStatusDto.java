package com.tm.csgl.domain.fortest;

public class TestTaskStatusDto {

    private String TaskName;
    private Long total=0L;
    private Long finished =0L;
    private String runningName;
    private Boolean isFinished=false;

    public TestTaskStatusDto() {
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getFinished() {
        return finished;
    }

    public void setIsFinished(Boolean finished) {
        isFinished = finished;
    }

    public Boolean getIsFinished() {
        return this.isFinished;
    }

    public void setFinished(Long finished) {
        this.finished = finished;
    }

    public String getRunningName() {
        return runningName;
    }

    public void setRunningName(String runningName) {
        this.runningName = runningName;
    }

    @Override
    public String toString() {
        return "TestTaskStatusDto{" +
                "TaskName='" + TaskName + '\'' +
                ", total=" + total +
                ", finished=" + finished +
                ", runningName='" + runningName + '\'' +
                ", isFinished=" + isFinished +
                '}';
    }
}
