package com.aimlesshammer.poc.sapi.cc.bal;

public class RandomBehaviourPolicy implements SapiStubBehaviourPolicy {
    private final RandomNumberGenerator randomNumberGenerator;
    private int failureRate = 0;
    private int minDelay = 0;
    private int maxDelay = 0;

    public RandomBehaviourPolicy(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @Override
    public void setFailureRate(int rate) {
        failureRate = rate;
    }

    @Override
    public boolean shouldRandomlyFail() {
        return randomNumberGenerator.randomPercent() < failureRate;
    }

    @Override
    public void setPerRequestDelayRange(int min, int max) {
        minDelay = min;
        maxDelay = max;
    }

    @Override
    public void awaitRandomDelay() {
        int delayTime = randomNumberGenerator.randomRange(minDelay, maxDelay);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException ignored) {
        }
    }
}
