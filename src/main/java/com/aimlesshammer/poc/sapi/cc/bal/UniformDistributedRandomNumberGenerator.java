package com.aimlesshammer.poc.sapi.cc.bal;

public class UniformDistributedRandomNumberGenerator implements RandomNumberGenerator {
    @Override
    public int randomPercent() {
        int min = 0;
        int max = 99;
        double randomInRange = (Math.random() * ((max - min) + 1)) + min;
        return (int) randomInRange;
    }
}
