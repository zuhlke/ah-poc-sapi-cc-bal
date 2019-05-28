package com.aimlesshammer.poc.sapi.cc.bal.randomNumberGenerator;

import com.aimlesshammer.poc.sapi.cc.bal.sapiBehaviourPolicy.SapiStubBehaviourPolicy;

public class RandomBehaviourPolicy implements SapiStubBehaviourPolicy {
    private final RandomNumberGenerator randomNumberGenerator;
    private int failureRate = 0;
    private int minDelay = 0;
    private int maxDelay = 0;

    public RandomBehaviourPolicy(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }
    @Override
    public boolean shouldFail() {
        return randomNumberGenerator.randomPercent() < failureRate;
    }

    @Override
    public void setPerRequestDelayRange(int min, int max) {
        minDelay = min;
        maxDelay = max;
    }

    @Override
    public void awaitResponseDelay() {
        int delayTime = randomNumberGenerator.randomRange(minDelay, maxDelay);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public void reset() {
        failureRate = 0;
        minDelay = 0;
        maxDelay = 0;
    }

    @Override
    public int getFailureRate() {
        return failureRate;
    }

    @Override
    public void setFailureRate(int rate) {
        failureRate = rate;
    }

    @Override
    public int getPerRequestMinDelay() {
        return minDelay;
    }

    @Override
    public int getPerRequestMaxDelay() {
        return maxDelay;
    }
}
