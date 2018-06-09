package com.tm.csgl.domain.fortest;

public class CaseResultDetails {

    private String className="";

    private String displayName="";

    private String methodName="";

    private Boolean isTest=false;

    private Boolean wasSuccessful=false;

    private String failuresStackTrace="";

    private String failuresMessage="";

    private String testHeader="";

    private String failuresTrace="";

    private Long runTime=0L;

    public CaseResultDetails() {


    }

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Boolean getIsTest() {
        return isTest;
    }

    public void setIsTest(Boolean test) {
        isTest = test;
    }

    public Boolean getWasSuccessful() {
        return wasSuccessful;
    }

    public void setWasSuccessful(Boolean wasSuccessful) {
        this.wasSuccessful = wasSuccessful;
    }

    public String getFailuresStackTrace() {
        return failuresStackTrace;
    }

    public void setFailuresStackTrace(String failuresStackTrace) {
        this.failuresStackTrace = failuresStackTrace;
    }

    public String getFailuresMessage() {
        return failuresMessage;
    }

    public void setFailuresMessage(String failuresMessage) {
        this.failuresMessage = failuresMessage;
    }

    public String getTestHeader() {
        return testHeader;
    }

    public void setTestHeader(String testHeader) {
        this.testHeader = testHeader;
    }


    public String getFailuresTrace() {
        return failuresTrace;
    }

    public void setFailuresTrace(String failuresTrace) {
        this.failuresTrace = failuresTrace;
    }

    @Override
    public String toString() {
        return "CaseResultByJunit4Dto{" +
                "className='" + className + '\'' +
                ", displayName='" + displayName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", isTest=" + isTest +
                ", wasSuccessful='" + wasSuccessful + '\'' +
                ", failuresStackTrace='" + failuresStackTrace + '\'' +
                ", failuresMessage='" + failuresMessage + '\'' +
                ", testHeader='" + testHeader + '\'' +
                ", failuresTrace='" + failuresTrace + '\'' +
                ", runTime=" + runTime +
                '}';
    }
}
