package com.aimlesshammer.poc.sapi.cc.bal.requestHandling;

import com.aimlesshammer.poc.sapi.cc.bal.PocSapiCreditCardBalancesApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.aimlesshammer.poc.sapi.cc.bal.sapiBehaviourPolicy.CustomBehaviourPolicyHeaders.X_POLICY_DELAY_RANGE_HEADER_NAME;
import static com.aimlesshammer.poc.sapi.cc.bal.sapiBehaviourPolicy.CustomBehaviourPolicyHeaders.X_POLICY_FAILURE_RATE_HEADER_NAME;

public class SpringRestControllerLogger {
    private final Logger logger = LoggerFactory.getLogger(SpringRestController.class);

    public void logBalance(Map<String, String> headers, String customerId) {
        log("Requesting balances for customer", customerId);
        logHeader(headers, X_POLICY_FAILURE_RATE_HEADER_NAME);
        logHeader(headers, X_POLICY_DELAY_RANGE_HEADER_NAME);
    }

    public void logSetFailureRate(Integer rate) {
        log("Setting failure rate", rate);
    }

    public void logSetPerRequestDelayRange(int min, int max) {
        log("Setting min per-request delay", min);
        log("Setting max per-request delay", max);
    }

    public void logResetBehaviourPolicy() {
        log("Resetting SAPI behaviour policy");
    }

    private void log(String msg, Object arg) {
        logger.info(logMessageSingleArg(msg), arg);
    }

    private void logHeader(Map<String, String> headers, String headerName) {
        logger.info(logMessageSingleArg("Header(" + headerName + ")"), headers.get(headerName));
    }

    private void log(String msg) {
        logger.info(logMessageNoArgs(msg));
    }

    private String logMessageSingleArg(String msg) {
        return PocSapiCreditCardBalancesApplication.LOG_ID + ": " + msg + ": '{}'";
    }

    private String logMessageNoArgs(String msg) {
        return PocSapiCreditCardBalancesApplication.LOG_ID + ": " + msg;
    }
}
