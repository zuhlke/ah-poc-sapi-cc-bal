package com.aimlesshammer.poc.sapi.cc.bal;

public class RandomBehaviourPolicy implements SapiStubBehaviourPolicy {
    private final RandomNumberGenerator randomNumberGenerator;
    private int failureRate;
    private int min;
    private int max;

    public RandomBehaviourPolicy(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @Override
    public boolean shouldRandomlyFail() {
        return randomNumberGenerator.randomPercent() < failureRate;
    }

    @Override
    public void setFailureRate(int rate) {
        failureRate = rate;
    }

    @Override
    public void setPerRequestDelayRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void awaitRandomDelay() {
        int delayTime = randomNumberGenerator.randomRange(min, max);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException ignored) {
        }
    }
}
