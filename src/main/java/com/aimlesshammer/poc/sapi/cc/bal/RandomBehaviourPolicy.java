package com.aimlesshammer.poc.sapi.cc.bal;

public class RandomBehaviourPolicy implements SapiStubBehaviourPolicy {
    private final RandomNumberGenerator randomNumberGenerator;
    private int failureRate = 0;
    private int minDelay = 0;
    private int maxDelay = 0;
    private boolean usingDefaultBehaviour = true;

    public RandomBehaviourPolicy(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @Override
    public boolean shouldRandomlyFail() {
        return randomNumberGenerator.randomPercent() < failureRate;
    }

    @Override
    public void setPerRequestDelayRange(int min, int max) {
        minDelay = min;
        maxDelay = max;
        usingDefaultBehaviour = false;
    }

    @Override
    public void awaitRandomDelay() {
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
        usingDefaultBehaviour = true;
    }

    @Override
    public int getFailureRate() {
        return failureRate;
    }

    @Override
    public void setFailureRate(int rate) {
        failureRate = rate;
        usingDefaultBehaviour = false;
    }

    @Override
    public int getPerRequestMinDelay() {
        return minDelay;
    }

    @Override
    public int getPerRequestMaxDelay() {
        return maxDelay;
    }

    @Override
    public boolean isUsingDefaultBehaviour() {
        return usingDefaultBehaviour;
    }
}
