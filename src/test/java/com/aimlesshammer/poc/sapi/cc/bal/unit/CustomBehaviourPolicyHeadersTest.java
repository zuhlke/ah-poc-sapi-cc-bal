package com.aimlesshammer.poc.sapi.cc.bal.unit;

import com.aimlesshammer.poc.sapi.cc.bal.sapiBehaviourPolicy.BadlyFormattedSapiPolicyHeaderException;
import com.aimlesshammer.poc.sapi.cc.bal.sapiBehaviourPolicy.CustomBehaviourPolicyHeaders;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.aimlesshammer.poc.sapi.cc.bal.sapiBehaviourPolicy.CustomBehaviourPolicyHeaders.X_POLICY_DELAY_RANGE_HEADER_NAME;
import static com.aimlesshammer.poc.sapi.cc.bal.sapiBehaviourPolicy.CustomBehaviourPolicyHeaders.X_POLICY_FAILURE_RATE_HEADER_NAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomBehaviourPolicyHeadersTest {
    @Test
    public void canReadFailureProbabilityFromHeaders() throws BadlyFormattedSapiPolicyHeaderException {
        Map<String, String> headers = new HashMap<>();
        headers.put(X_POLICY_FAILURE_RATE_HEADER_NAME, "10");

        CustomBehaviourPolicyHeaders customBehaviourPolicyHeaders = new CustomBehaviourPolicyHeaders(headers);

        assertThat(customBehaviourPolicyHeaders.failureRate, equalTo(10));
    }

    @Test
    public void shouldSetFailureRateTo0ByDefault() throws BadlyFormattedSapiPolicyHeaderException {
        Map<String, String> headers = new HashMap<>();
        CustomBehaviourPolicyHeaders customBehaviourPolicyHeaders = new CustomBehaviourPolicyHeaders(headers);

        assertThat(customBehaviourPolicyHeaders.failureRate, equalTo(0));
    }

    @Test
    public void canReadDelayRangeFromHeaders() throws BadlyFormattedSapiPolicyHeaderException {
        Map<String, String> headers = new HashMap<>();
        headers.put(X_POLICY_DELAY_RANGE_HEADER_NAME, "100-500");

        CustomBehaviourPolicyHeaders customBehaviourPolicyHeaders = new CustomBehaviourPolicyHeaders(headers);

        assertThat(customBehaviourPolicyHeaders.minDelay, equalTo(100));
        assertThat(customBehaviourPolicyHeaders.maxDelay, equalTo(500));
    }

    @Test
    public void shouldSetDefaultRequestDelayRangeValues() throws BadlyFormattedSapiPolicyHeaderException {
        Map<String, String> headers = new HashMap<>();
        CustomBehaviourPolicyHeaders customBehaviourPolicyHeaders = new CustomBehaviourPolicyHeaders(headers);

        assertThat(customBehaviourPolicyHeaders.minDelay, equalTo(0));
        assertThat(customBehaviourPolicyHeaders.maxDelay, equalTo(0));
    }

    @Test(expected = BadlyFormattedSapiPolicyHeaderException.class)
    public void shouldThrowAnExceptionWhenRequestDelayRangeFormatIsIncorrect() throws BadlyFormattedSapiPolicyHeaderException {
        Map<String, String> headers = new HashMap<>();
        headers.put(X_POLICY_DELAY_RANGE_HEADER_NAME, "X*Y");

        new CustomBehaviourPolicyHeaders(headers);
    }

    @Test(expected = BadlyFormattedSapiPolicyHeaderException.class)
    public void shouldThrowAnExceptionWhenFailureRateFormatIsIncorrect() throws BadlyFormattedSapiPolicyHeaderException {
        Map<String, String> headers = new HashMap<>();
        headers.put(X_POLICY_FAILURE_RATE_HEADER_NAME, "X*Y");

        new CustomBehaviourPolicyHeaders(headers);
    }
}
