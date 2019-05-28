package com.aimlesshammer.poc.sapi.cc.bal.unit;

import com.aimlesshammer.poc.sapi.cc.bal.BadlyFormattedSapiPolicyHeaderException;
import com.aimlesshammer.poc.sapi.cc.bal.BehaviourPolicyHeaders;
import com.aimlesshammer.poc.sapi.cc.bal.BehaviourPolicyHeadersFactory;
import com.aimlesshammer.poc.sapi.cc.bal.SapiStubBehaviourPolicy;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class BehaviourPolicyHeadersFactoryTest {
    @Test
    public void ifNoPolicyHeadersAreSpecifiedThenItWontOverwriteABehaviourPolicyWithDefaults() throws BadlyFormattedSapiPolicyHeaderException {
        Map<String, String> headers = new HashMap<>();
        BehaviourPolicyHeaders customBehaviourPolicyHeaders = BehaviourPolicyHeadersFactory.behaviourPolicyHeaders(headers);
        SapiStubBehaviourPolicy mockBehaviourPolicy = mock(SapiStubBehaviourPolicy.class);

        customBehaviourPolicyHeaders.configure(mockBehaviourPolicy);

        verify(mockBehaviourPolicy, never()).setFailureRate(0);
        verify(mockBehaviourPolicy, never()).setPerRequestDelayRange(0, 0);
    }
}
