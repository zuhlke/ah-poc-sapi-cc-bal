package com.aimlesshammer.poc.sapi.cc.bal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpringRestController {
    private static final Logger logger = LoggerFactory.getLogger(SpringRestController.class);
    private final RequestHandler requestHandler = new RequestHandler(new RandomBehaviourPolicy(new UniformDistributedRandomNumberGenerator()));

    @GetMapping(path = "/customer/{customer-id}/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> balance(@PathVariable("customer-id") String customerId) {
        logger.info(logMessageSingleArg("Requesting balances for customer"), customerId);
        return requestHandler.balance();
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

    private String logMessageSingleArg(String msg) {
        return PocSapiCreditCardBalancesApplication.LOG_ID + ": " + msg + ": '{}'";
    }
}
