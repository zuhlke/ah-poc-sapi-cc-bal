package com.aimlesshammer.poc.sapi.cc.bal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.aimlesshammer.poc.sapi.cc.bal.BehaviourPolicyHeaders.X_POLICY_DELAY_RANGE_HEADER_NAME;
import static com.aimlesshammer.poc.sapi.cc.bal.BehaviourPolicyHeaders.X_POLICY_FAILURE_RATE_HEADER_NAME;

@RestController
public class SpringRestController {
    private static final Logger logger = LoggerFactory.getLogger(SpringRestController.class);
    private final RequestHandler requestHandler = new RequestHandler(new RandomBehaviourPolicy(new UniformDistributedRandomNumberGenerator()));

    @GetMapping(path = "/customer/{customer-id}/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> balance(@RequestHeader Map<String, String> headers, @PathVariable("customer-id") String customerId) {
        logger.info(logMessageSingleArg("Requesting balances for customer"), customerId);
        logger.info(logMessageSingleArg("Header(" + X_POLICY_FAILURE_RATE_HEADER_NAME + ")"), headers.get(X_POLICY_FAILURE_RATE_HEADER_NAME));
        logger.info(logMessageSingleArg("Header(" + X_POLICY_DELAY_RANGE_HEADER_NAME + ")"), headers.get(X_POLICY_DELAY_RANGE_HEADER_NAME));
        try {
            return requestHandler.balance(new BehaviourPolicyHeaders(headers));
        } catch (BadlyFormattedSapiPolicyHeaderException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("failureRatePc/{rate}")
    public ResponseEntity<String> setFailureRate(@PathVariable("rate") Integer rate) {
        logger.info(logMessageSingleArg("Setting failure rate"), rate);
        return requestHandler.setFailureRate(rate);
    }

    @PostMapping("/perRequestDelayRangeMs")
    public ResponseEntity<String> setPerRequestDelayRange(@RequestParam("min") int min, @RequestParam("max") int max) {
        logger.info(logMessageSingleArg("Setting min per-request delay"), min);
        logger.info(logMessageSingleArg("Setting max per-request delay"), max);
        return requestHandler.setPerRequestDelayRange(min, max);
    }

    @PostMapping("/reset")
    public ResponseEntity<String> setPerRequestDelayRange() {
        logger.info(logMessageNoArgs("Resetting SAPI behaviour policy"));
        return requestHandler.resetBehaviourPolicy();
    }

    private String logMessageNoArgs(String msg) {
        return PocSapiCreditCardBalancesApplication.LOG_ID + ": " + msg;
    }

    private String logMessageSingleArg(String msg) {
        return PocSapiCreditCardBalancesApplication.LOG_ID + ": " + msg + ": '{}'";
    }
}
