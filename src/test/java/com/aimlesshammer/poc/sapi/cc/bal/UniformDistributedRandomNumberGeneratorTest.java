package com.aimlesshammer.poc.sapi.cc.bal;

import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class UniformDistributedRandomNumberGeneratorTest {
    // Forgive me for I am lazy
    // - Rob
    @Test
    public void generatesNumberWithAVeryApproximatelyUniformDistribution() {
        UniformDistributedRandomNumberGenerator uniformDistributedRandomNumberGenerator = new UniformDistributedRandomNumberGenerator();
        HashMap<Integer, Integer> buckets = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            buckets.put(i, 0);
        }

        for (int i = 0; i < 10000; i++) {
            int k = uniformDistributedRandomNumberGenerator.randomPercent();
            Integer x = buckets.get(k);
            buckets.put(k, x + 1);
        }

        // Numbers 0-99 with 10K tries => Should be approximately 100 entries in each bucket
        // Lets go with asserting that there's between 80-120 in each
        // This has not been an exercise in science.
        for (int i = 0; i < 100; i++) {
            assertThat(buckets.get(i), lessThan(130));
            assertThat(buckets.get(i), greaterThan(70));
        }
    }
}