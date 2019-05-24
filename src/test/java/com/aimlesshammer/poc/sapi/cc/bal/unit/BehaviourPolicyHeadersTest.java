package com.aimlesshammer.poc.sapi.cc.bal.unit;

import com.aimlesshammer.poc.sapi.cc.bal.BadlyFormattedSapiPolicyHeaderException;
import com.aimlesshammer.poc.sapi.cc.bal.BehaviourPolicyHeaders;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.aimlesshammer.poc.sapi.cc.bal.BehaviourPolicyHeaders.X_POLICY_DELAY_RANGE_HEADER_NAME;
import static com.aimlesshammer.poc.sapi.cc.bal.BehaviourPolicyHeaders.X_POLICY_FAILURE_RATE_HEADER_NAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class BehaviourPolicyHeadersTest {
    @Test
    public void canReadFailureProbabilityFromHeaders() throws BadlyFormattedSapiPolicyHeaderException {
        Map<String, String> headers = new HashMap<>();
        headers.put(X_POLICY_FAILURE_RATE_HEADER_NAME, "10");

        BehaviourPolicyHeaders behaviourPolicyHeaders = new BehaviourPolicyHeaders(headers);

        assertThat(behaviourPolicyHeaders.failureRate, equalTo(10));
    }

    @Test
    public void shouldSetFailureRateTo0ByDefault() throws BadlyFormattedSapiPolicyHeaderException {
        Map<String, String> headers = new HashMap<>();
        BehaviourPolicyHeaders behaviourPolicyHeaders = new BehaviourPolicyHeaders(headers);

        assertThat(behaviourPolicyHeaders.failureRate, equalTo(0));
    }

    @Test
    public void canReadDelayRangeFromHeaders() throws BadlyFormattedSapiPolicyHeaderException {
        Map<String, String> headers = new HashMap<>();
        headers.put(X_POLICY_DELAY_RANGE_HEADER_NAME, "100-500");

        BehaviourPolicyHeaders behaviourPolicyHeaders = new BehaviourPolicyHeaders(headers);

        assertThat(behaviourPolicyHeaders.minDelay, equalTo(100));
        assertThat(behaviourPolicyHeaders.maxDelay, equalTo(500));
    }

    @Test
    public void shouldSetDefaultRequestDelayRangeValues() throws BadlyFormattedSapiPolicyHeaderException {
        Map<String, String> headers = new HashMap<>();
        BehaviourPolicyHeaders behaviourPolicyHeaders = new BehaviourPolicyHeaders(headers);

        assertThat(behaviourPolicyHeaders.minDelay, equalTo(0));
        assertThat(behaviourPolicyHeaders.maxDelay, equalTo(0));
    }

    @Test(expected = BadlyFormattedSapiPolicyHeaderException.class)
    public void shouldThrowAnExceptionWhenRequestDelayRangeFormatIsIncorrect() throws BadlyFormattedSapiPolicyHeaderException {
        Map<String, String> headers = new HashMap<>();
        headers.put(X_POLICY_DELAY_RANGE_HEADER_NAME, "X*Y");

        new BehaviourPolicyHeaders(headers);
    }

    @Test(expected = BadlyFormattedSapiPolicyHeaderException.class)
    public void shouldThrowAnExceptionWhenFailureRateFormatIsIncorrect() throws BadlyFormattedSapiPolicyHeaderException {
        Map<String, String> headers = new HashMap<>();
        headers.put(X_POLICY_FAILURE_RATE_HEADER_NAME, "X*Y");

        new BehaviourPolicyHeaders(headers);
    }
}
