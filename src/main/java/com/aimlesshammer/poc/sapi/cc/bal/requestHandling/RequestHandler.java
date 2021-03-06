package com.aimlesshammer.poc.sapi.cc.bal.requestHandling;

import com.aimlesshammer.poc.sapi.cc.bal.sapiBehaviourPolicy.BehaviourPolicyHeaders;
import com.aimlesshammer.poc.sapi.cc.bal.sapiBehaviourPolicy.SapiStubBehaviourPolicy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RequestHandler {
    private final SapiStubBehaviourPolicy behaviourPolicy;

    public RequestHandler(SapiStubBehaviourPolicy behaviourPolicy) {
        this.behaviourPolicy = behaviourPolicy;
    }

    public ResponseEntity<String> balance(BehaviourPolicyHeaders behaviourPolicyHeaders) {
        behaviourPolicyHeaders.configure(behaviourPolicy);
        return balance();
    }

    public ResponseEntity<String> balance() {
        behaviourPolicy.awaitResponseDelay();

        if (behaviourPolicy.shouldFail()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("[\n{\n\"customerId\":\"10101010\",\n\"creditCardNumber\":\"1234567890\",\n\"balance\":\"1234.50\"\n}\n]", HttpStatus.OK);
    }

    public ResponseEntity<String> setFailureRate(int rate) {
        behaviourPolicy.setFailureRate(rate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<String> setPerRequestDelayRange(int min, int max) {
        behaviourPolicy.setPerRequestDelayRange(min, max);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<String> resetBehaviourPolicy() {
        behaviourPolicy.reset();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
