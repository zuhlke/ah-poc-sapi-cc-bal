package com.aimlesshammer.poc.sapi.cc.bal;

public interface SapiStubBehaviourPolicy {
    void setFailureRate(int rate);

    boolean shouldRandomlyFail();

    void setPerRequestDelayRange(int min, int max);

    void awaitRandomDelay();

    void reset();

    int getFailureRate();

    int getPerRequestMinDelay();

    int getPerRequestMaxDelay();
}
