package com.aimlesshammer.poc.sapi.cc.bal;

public interface SapiStubBehaviourPolicy {
    boolean shouldFail();

    void setPerRequestDelayRange(int min, int max);

    void awaitResponseDelay();

    void reset();

    int getFailureRate();

    void setFailureRate(int rate);

    int getPerRequestMinDelay();

    int getPerRequestMaxDelay();

    boolean isUsingDefaultBehaviour();
}
