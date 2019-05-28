package com.aimlesshammer.poc.sapi.cc.bal;

import java.util.Map;

public class BehaviourPolicyHeadersFactory {
    public static BehaviourPolicyHeaders behaviourPolicyHeaders(Map<String, String> headers) throws BadlyFormattedSapiPolicyHeaderException {
        if (!headers.containsKey(CustomBehaviourPolicyHeaders.X_POLICY_FAILURE_RATE_HEADER_NAME) && !headers.containsKey(CustomBehaviourPolicyHeaders.X_POLICY_DELAY_RANGE_HEADER_NAME)) {
            return new EmptyBehaviourPolicy();
        }
        return new CustomBehaviourPolicyHeaders(headers);
    }
}
