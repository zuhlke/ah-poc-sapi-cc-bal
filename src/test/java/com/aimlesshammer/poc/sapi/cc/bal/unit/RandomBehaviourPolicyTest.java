package com.aimlesshammer.poc.sapi.cc.bal.unit;

import com.aimlesshammer.poc.sapi.cc.bal.RandomBehaviourPolicy;
import com.aimlesshammer.poc.sapi.cc.bal.RandomNumberGenerator;
import com.aimlesshammer.poc.sapi.cc.bal.SapiStubBehaviourPolicy;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
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

    @Test
    public void resettingCausesParamsToResetToInitialValues() {
        RandomNumberGenerator dummyRNG = Mockito.mock(RandomNumberGenerator.class);

        SapiStubBehaviourPolicy sapiStubBehaviourPolicy = new RandomBehaviourPolicy(dummyRNG);
        sapiStubBehaviourPolicy.setFailureRate(50);
        sapiStubBehaviourPolicy.setPerRequestDelayRange(100, 200);

        sapiStubBehaviourPolicy.reset();

        assertThat(sapiStubBehaviourPolicy.getFailureRate(), equalTo(0));
        assertThat(sapiStubBehaviourPolicy.getPerRequestMinDelay(), equalTo(0));
        assertThat(sapiStubBehaviourPolicy.getPerRequestMaxDelay(), equalTo(0));
    }
}
