package com.aimlesshammer.poc.sapi.cc.bal.unit;

import com.aimlesshammer.poc.sapi.cc.bal.RandomBehaviourConfiguration;
import com.aimlesshammer.poc.sapi.cc.bal.RandomNumberGenerator;
import com.aimlesshammer.poc.sapi.cc.bal.RequestHandler;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class RequestHandlerTest {
    @Test
    public void whenFailureRateIs50ThenDrawingRandomNumber51CausesOk() {
        RandomNumberGenerator stubRandomNumberGenerator = Mockito.mock(RandomNumberGenerator.class);
        Mockito.when(stubRandomNumberGenerator.randomPercent()).thenReturn(51);

        RequestHandler requestHandler = defaultRequestHandlerWithCustomRandomNumberGenerator(stubRandomNumberGenerator);
        requestHandler.setFailureRate(50);
        assertThat(requestHandler.balance().getStatusCodeValue(), equalTo(200));
    }

    @Test
    public void whenFailureRateIs50ThenDrawingRandomNumber49CausesInternalServerError() {
        RandomNumberGenerator stubRandomNumberGenerator = Mockito.mock(RandomNumberGenerator.class);
        Mockito.when(stubRandomNumberGenerator.randomPercent()).thenReturn(49);

        RequestHandler requestHandler = defaultRequestHandlerWithCustomRandomNumberGenerator(stubRandomNumberGenerator);
        requestHandler.setFailureRate(50);
        assertThat(requestHandler.balance().getStatusCodeValue(), equalTo(500));
    }

    private RequestHandler defaultRequestHandlerWithCustomRandomNumberGenerator(RandomNumberGenerator stubRandomNumberGenerator) {
        return new RequestHandler(new RandomBehaviourConfiguration(stubRandomNumberGenerator));
    }
}
