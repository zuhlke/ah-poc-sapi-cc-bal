package com.aimlesshammer.poc.sapi.cc.bal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RequestHandler {
    private final RandomNumberGenerator randomNumberGenerator;
    private int failureRate = 0;

    public RequestHandler(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public ResponseEntity<String> balance() {
        if (randomNumberGenerator.randomPercent() < failureRate) {
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
        failureRate = rate;
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
