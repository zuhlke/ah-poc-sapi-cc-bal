package com.aimlesshammer.poc.sapi.cc.bal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RequestHandler {
    private boolean failing = false;

    public ResponseEntity<String> balance() {
        if (failing) {
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
        failing = true;
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
