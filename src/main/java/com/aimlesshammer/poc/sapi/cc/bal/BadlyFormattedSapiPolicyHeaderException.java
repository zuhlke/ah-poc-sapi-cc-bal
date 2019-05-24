package com.aimlesshammer.poc.sapi.cc.bal;

public class BadlyFormattedSapiPolicyHeaderException extends Exception {
    public BadlyFormattedSapiPolicyHeaderException(NumberFormatException e) {
        super(e);
    }
}
