package com.aimlesshammer.poc.sapi.cc.bal.requestHandling;

import com.aimlesshammer.poc.sapi.cc.bal.randomNumberGenerator.RandomBehaviourPolicy;
import com.aimlesshammer.poc.sapi.cc.bal.randomNumberGenerator.UniformDistributedRandomNumberGenerator;
import com.aimlesshammer.poc.sapi.cc.bal.sapiBehaviourPolicy.BadlyFormattedSapiPolicyHeaderException;
import com.aimlesshammer.poc.sapi.cc.bal.sapiBehaviourPolicy.BehaviourPolicyHeadersFactory;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SpringRestController {
    private final SpringRestControllerLogger logger = new SpringRestControllerLogger(LoggerFactory.getLogger(SpringRestController.class));
    private final RequestHandler requestHandler = new RequestHandler(new RandomBehaviourPolicy(new UniformDistributedRandomNumberGenerator()));

    @GetMapping(path = "/customer/{customer-id}/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> balance(@RequestHeader Map<String, String> headers, @PathVariable("customer-id") String customerId) {
        logger.logBalance(headers, customerId);
        try {
            return requestHandler.balance(BehaviourPolicyHeadersFactory.behaviourPolicyHeaders(headers));
        } catch (BadlyFormattedSapiPolicyHeaderException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("failureRatePc/{rate}")
    public ResponseEntity<String> setFailureRate(@PathVariable("rate") Integer rate) {
        logger.logSetFailureRate(rate);
        return requestHandler.setFailureRate(rate);
    }

    @PostMapping("/perRequestDelayRangeMs")
    public ResponseEntity<String> setPerRequestDelayRange(@RequestParam("min") int min, @RequestParam("max") int max) {
        logger.logSetPerRequestDelayRange(min, max);
        return requestHandler.setPerRequestDelayRange(min, max);
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetBehaviourPolicy() {
        logger.logResetBehaviourPolicy();
        return requestHandler.resetBehaviourPolicy();
    }

}
