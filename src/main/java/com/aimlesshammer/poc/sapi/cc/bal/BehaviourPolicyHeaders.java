package com.aimlesshammer.poc.sapi.cc.bal;

import java.util.Map;

public class BehaviourPolicyHeaders {
    public static final String X_POLICY_FAILURE_RATE_HEADER_NAME = "x-policy-failure-rate";
    public static final String X_POLICY_DELAY_RANGE_HEADER_NAME = "x-policy-delay-range";
    private static final String DEFAULT_FAILURE_RATE_HEADER_VALUE = "0";
    private static final String DEFAULT_DELAY_RANGE_HEADER_VALUE = "0-0";
    public final int failureRate;
    public final int minDelay;
    public final int maxDelay;

    public BehaviourPolicyHeaders(Map<String, String> headers) throws BadlyFormattedSapiPolicyHeaderException {
        try {
            failureRate = Integer.valueOf(headers.getOrDefault(X_POLICY_FAILURE_RATE_HEADER_NAME, DEFAULT_FAILURE_RATE_HEADER_VALUE));
            String delayRange = headers.getOrDefault(X_POLICY_DELAY_RANGE_HEADER_NAME, DEFAULT_DELAY_RANGE_HEADER_VALUE);
            String[] split = delayRange.split("-");
            minDelay = Integer.valueOf(split[0]);
            maxDelay = Integer.valueOf(split[1]);
        } catch (NumberFormatException e) {
            throw new BadlyFormattedSapiPolicyHeaderException(e);
        }
    }
}
