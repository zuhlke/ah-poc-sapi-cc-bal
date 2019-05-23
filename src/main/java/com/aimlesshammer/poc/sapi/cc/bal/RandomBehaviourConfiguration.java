package com.aimlesshammer.poc.sapi.cc.bal;

public class RandomBehaviourConfiguration {
    private final RandomNumberGenerator randomNumberGenerator;
    private int failureRate;
    private int min;
    private int max;

    public RandomBehaviourConfiguration(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    boolean shouldRandomlyFail() {
        return randomNumberGenerator.randomPercent() < failureRate;
    }

    public void setFailureRate(int rate) {
        failureRate = rate;
    }

    public void setPerRequestDelayRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public void awaitRandomDelay() {
        int delayTime = randomNumberGenerator.randomRange(min, max);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException ignored) {
        }
    }
}
