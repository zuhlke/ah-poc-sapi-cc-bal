package com.aimlesshammer.poc.sapi.cc.bal;

public interface RandomNumberGenerator {
    int randomPercent();

    int randomRange(int min, int max);
}
