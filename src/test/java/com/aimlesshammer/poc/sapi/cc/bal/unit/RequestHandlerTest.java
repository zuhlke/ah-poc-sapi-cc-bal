package com.aimlesshammer.poc.sapi.cc.bal.unit;

import com.aimlesshammer.poc.sapi.cc.bal.RequestHandler;
import com.aimlesshammer.poc.sapi.cc.bal.SapiStubBehaviourPolicy;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class RequestHandlerTest {
    @Test
    public void whenSapiBehaviourPolicyDoesntCauseFailureItReturns200Ok() {
        SapiStubBehaviourPolicy stubSapiStubBehaviourPolicy = Mockito.mock(SapiStubBehaviourPolicy.class);
        Mockito.when(stubSapiStubBehaviourPolicy.shouldFail()).thenReturn(false);

        RequestHandler requestHandler = new RequestHandler(stubSapiStubBehaviourPolicy);

        assertThat(requestHandler.balance().getStatusCodeValue(), equalTo(200));
    }

    @Test
    public void whenSapiBehaviourPolicyCausesFailureItReturns500InternalServerError() {
        SapiStubBehaviourPolicy stubSapiStubBehaviourPolicy = Mockito.mock(SapiStubBehaviourPolicy.class);
        Mockito.when(stubSapiStubBehaviourPolicy.shouldFail()).thenReturn(true);

        RequestHandler requestHandler = new RequestHandler(stubSapiStubBehaviourPolicy);

        assertThat(requestHandler.balance().getStatusCodeValue(), equalTo(500));
    }

    @Test
    public void whenResettingItCallsTheResetMethodOfTheStubSapiBehaviourPolicy() {
        SapiStubBehaviourPolicy stubSapiStubBehaviourPolicy = Mockito.mock(SapiStubBehaviourPolicy.class);
        RequestHandler requestHandler = new RequestHandler(stubSapiStubBehaviourPolicy);

        requestHandler.resetBehaviourPolicy();

        Mockito.verify(stubSapiStubBehaviourPolicy).reset();
    }
}
