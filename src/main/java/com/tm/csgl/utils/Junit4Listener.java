package com.tm.csgl.utils;

import com.tm.csgl.domain.fortest.CaseResultDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class Junit4Listener extends RunListener {
    private static Log log = LogFactory.getLog(Junit4Listener.class);

    private CaseResultDetails caseResultDetails = new CaseResultDetails();

    public Junit4Listener() {
    }

    @Override
    public void testRunStarted(Description description) throws Exception {
        super.testRunStarted(description);
        log.info("-------------------------------------------------------------------------------------------------------------");
        log.info("开始执行.......");
        log.info("ClassName: " + description.getClassName());
        log.info("DisplayName: " + description.getDisplayName());
        this.caseResultDetails.setClassName(description.getClassName());
        this.caseResultDetails.setDisplayName(description.getDisplayName());


    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        super.testRunFinished(result);
        log.info("执行完成");
        log.info("花费时间："+result.getRunTime());
        log.info("-------------------------------------------------------------------------------------------------------------");


        this.caseResultDetails.setWasSuccessful(result.wasSuccessful());
        if (result.wasSuccessful() == false) {
            this.caseResultDetails.setFailuresMessage(result.getFailures().get(0).getMessage());
        }
        this.caseResultDetails.setRunTime(result.getRunTime());
       // log.info("花费时间："+this.caseResultByJunit4Dto.getRunTime());
    }

    @Override
    public void testStarted(Description description) throws Exception {
        super.testStarted(description);
        log.info("MethodName: " + description.getMethodName());
    }

    @Override
    public void testFinished(Description description) throws Exception {
        super.testFinished(description);
        caseResultDetails.setIsTest(description.isTest());
        //log.info(description.getMethodName());
        this.caseResultDetails.setMethodName(description.getMethodName());
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        super.testFailure(failure);
        this.caseResultDetails.setTestHeader(failure.getTestHeader());
        this.caseResultDetails.setFailuresMessage(failure.getMessage());
        this.caseResultDetails.setFailuresStackTrace(failure.getException().getStackTrace().toString());
        this.caseResultDetails.setFailuresTrace(failure.getTrace());

    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        super.testAssumptionFailure(failure);
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        super.testIgnored(description);
        log.info("忽略这个用例！");
        this.caseResultDetails.setIsTest(description.isTest());
    }

    public CaseResultDetails getCaseTestResult() {
        return this.caseResultDetails;
    }
}
