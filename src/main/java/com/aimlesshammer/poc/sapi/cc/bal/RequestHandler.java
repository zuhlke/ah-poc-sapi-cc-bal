package com.aimlesshammer.poc.sapi.cc.bal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RequestHandler {
    private final RandomNumberGenerator randomNumberGenerator;
    private int failureRate = 0;
    private int min;
    private int max;

    public RequestHandler(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public ResponseEntity<String> balance() {
        awaitRandomDelay();

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

    public ResponseEntity<String> setPerRequestDelayRange(int min, int max) {
        this.min = min;
        this.max = max;
        return null;
    }

    private void awaitRandomDelay() {
        int delayTime = randomNumberGenerator.randomRange(min, max);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException ignored) {
        }
    }
}
