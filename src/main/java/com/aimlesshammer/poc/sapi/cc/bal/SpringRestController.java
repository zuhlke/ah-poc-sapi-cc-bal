package com.aimlesshammer.poc.sapi.cc.bal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringRestController {
    private static final Logger logger = LoggerFactory.getLogger(SpringRestController.class);
    private final RequestHandler requestHandler = new RequestHandler(new UniformDistributedRandomNumberGenerator());

    @GetMapping(path = "/customer/{customer-id}/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> balance(@PathVariable("customer-id") String customerId) {
        logger.info(logMessageSingleArg("Requesting balances for customer"), customerId);
        return requestHandler.balance();
    }

    @PostMapping("failureRate/{rate}")
    public ResponseEntity<String> setFailureRate(@PathVariable("rate") Integer rate) {
        System.out.println(rate);
        logger.info(logMessageSingleArg("Setting failure rate"), rate);
        return requestHandler.setFailureRate(rate);
    }

    private String logMessageSingleArg(String msg) {
        return PocSapiCreditCardBalancesApplication.LOG_ID + ": " + msg + ": '{}'";
    }
}
