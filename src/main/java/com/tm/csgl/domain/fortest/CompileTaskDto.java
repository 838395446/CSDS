package com.tm.csgl.domain.fortest;

public class CompileTaskDto {
    private String TaskName;
    private Long total=0L;
    private Long finished =0L;
    private String runningName;
    private Boolean isFinished=false;


    public CompileTaskDto() {
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



    public void setFinished(Long finished) {
        this.finished = finished;
    }

    public void setIsFinished(Boolean finished) {
        isFinished = finished;
    }

    public Boolean getIsFinished() {
        return this.isFinished;
    }
    public String getRunningName() {
        return runningName;
    }

    public void setRunningName(String runningName) {
        this.runningName = runningName;
    }

    @Override
    public String toString() {
        return "CompileTaskDto{" +
                "TaskName='" + TaskName + '\'' +
                ", total=" + total +
                ", finished=" + finished +
                ", runningName='" + runningName + '\'' +
                ", isFinished=" + isFinished +
                '}';
    }
}
