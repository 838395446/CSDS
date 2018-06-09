package com.tm.csgl.domain.fortest;

import java.util.ArrayList;
import java.util.List;

public class TestResultDto{
    private List<CaseResultDetails> caseResultDetails ;

    private Long runCount =0L;

    private Long failureCount =0L;

    private Long ignoreCount=0L;

    private Long runTime=0L;
    private Boolean successful=false;

    private String testName="";


    public TestResultDto(String testName) {
        this.testName = testName;
        this.caseResultDetails =  new ArrayList<CaseResultDetails>();
    }

    public void addCaseResultDetails(CaseResultDetails caseResultDetails) {
        this.caseResultDetails.add(caseResultDetails);
    }

    public Long getRunCount() {
        return runCount;
    }

    public void setRunCount(Long runCount) {
        this.runCount = runCount;
    }

    public Long getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(Long failureCount) {
        this.failureCount = failureCount;
    }

    public Long getIgnoreCount() {
        return ignoreCount;
    }

    public void setIgnoreCount(Long ignoreCount) {
        this.ignoreCount = ignoreCount;
    }

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public List getCaseResultDetails() {
        return this.caseResultDetails;
    }

    public void setCaseResultDetails(List caseResultDetails) {
        this.caseResultDetails = caseResultDetails;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Override
    public String toString() {
        return "TestResultDto{" +
                "CaseResultByJunit4Dto=" + caseResultDetails +
                ", runCount=" + runCount +
                ", failureCount=" + failureCount +
                ", ignoreCount=" + ignoreCount +
                ", runTime=" + runTime +
                ", successful=" + successful +
                ", testName='" + testName + '\'' +
                '}';
    }
}