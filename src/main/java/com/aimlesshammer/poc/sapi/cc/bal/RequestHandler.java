package com.aimlesshammer.poc.sapi.cc.bal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RequestHandler {
    private final RandomBehaviourPolicy randomBehaviourPolicy;

    public RequestHandler(RandomBehaviourPolicy randomBehaviourPolicy) {
        this.randomBehaviourPolicy = randomBehaviourPolicy;
    }

    public ResponseEntity<String> balance() {
        randomBehaviourPolicy.awaitRandomDelay();

        if (randomBehaviourPolicy.shouldRandomlyFail()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("[\n" +
                "  {\n" +
                "    \"customerId\": \"10101010\",\n" +
                "    \"creditCardNumber\": \"1234567890\",\n" +
                "    \"balance\": \"1234.50\"\n" +
                "  }\n" +
                "]", HttpStatus.OK);
    }

    public ResponseEntity<String> setFailureRate(int rate) {
        randomBehaviourPolicy.setFailureRate(rate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<String> setPerRequestDelayRange(int min, int max) {
        randomBehaviourPolicy.setPerRequestDelayRange(min, max);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
