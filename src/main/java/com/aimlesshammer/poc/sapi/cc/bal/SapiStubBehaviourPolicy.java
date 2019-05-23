package com.aimlesshammer.poc.sapi.cc.bal;

public interface SapiStubBehaviourPolicy {
    boolean shouldRandomlyFail();

    void setFailureRate(int rate);

    void setPerRequestDelayRange(int min, int max);

    void awaitRandomDelay();
}
