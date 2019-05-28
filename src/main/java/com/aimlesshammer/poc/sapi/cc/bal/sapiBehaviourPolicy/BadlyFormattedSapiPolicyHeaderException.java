package com.aimlesshammer.poc.sapi.cc.bal.sapiBehaviourPolicy;

public class BadlyFormattedSapiPolicyHeaderException extends Exception {
    public BadlyFormattedSapiPolicyHeaderException(NumberFormatException e) {
        super(e);
    }
}
