package com.aimlesshammer.poc.sapi.cc.bal.unit;

import com.aimlesshammer.poc.sapi.cc.bal.RandomBehaviourPolicy;
import com.aimlesshammer.poc.sapi.cc.bal.RandomNumberGenerator;
import com.aimlesshammer.poc.sapi.cc.bal.SapiStubBehaviourPolicy;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class RandomBehaviourPolicyTest {
    @Test
    public void whenFailureRateIs50ThenDrawingRandomNumber51DoesntCauseFailure() {
        RandomNumberGenerator stubRandomNumberGenerator = Mockito.mock(RandomNumberGenerator.class);
        Mockito.when(stubRandomNumberGenerator.randomPercent()).thenReturn(51);

        SapiStubBehaviourPolicy sapiStubBehaviourPolicy = new RandomBehaviourPolicy(stubRandomNumberGenerator);
        sapiStubBehaviourPolicy.setFailureRate(50);
        assertFalse(sapiStubBehaviourPolicy.shouldRandomlyFail());
    }

    @Test
    public void whenFailureRateIs50ThenDrawingRandomNumber49CausesFailure() {
        RandomNumberGenerator stubRandomNumberGenerator = Mockito.mock(RandomNumberGenerator.class);
        Mockito.when(stubRandomNumberGenerator.randomPercent()).thenReturn(49);

        SapiStubBehaviourPolicy sapiStubBehaviourPolicy = new RandomBehaviourPolicy(stubRandomNumberGenerator);
        sapiStubBehaviourPolicy.setFailureRate(50);
        assertTrue(sapiStubBehaviourPolicy.shouldRandomlyFail());
    }
}
