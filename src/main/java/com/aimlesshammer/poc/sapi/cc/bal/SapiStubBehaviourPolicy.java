package com.aimlesshammer.poc.sapi.cc.bal;

public interface SapiStubBehaviourPolicy {
    boolean shouldFail();

    void setPerRequestDelayRange(int min, int max);

    void awaitRandomDelay();

    void reset();

    int getFailureRate();

    void setFailureRate(int rate);

    int getPerRequestMinDelay();

    int getPerRequestMaxDelay();

    boolean isUsingDefaultBehaviour();
}
