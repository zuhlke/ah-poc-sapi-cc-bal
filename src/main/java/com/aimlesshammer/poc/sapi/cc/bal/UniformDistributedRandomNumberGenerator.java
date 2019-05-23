package com.aimlesshammer.poc.sapi.cc.bal;

public class UniformDistributedRandomNumberGenerator implements RandomNumberGenerator {
    @Override
    public int randomPercent() {
        return randomRange(0, 99);
    }

    @Override
    public int randomRange(int min, int max) {
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }
}
