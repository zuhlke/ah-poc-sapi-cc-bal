package com.aimlesshammer.poc.sapi.cc.bal.integration;

import com.aimlesshammer.poc.sapi.cc.bal.randomNumberGenerator.UniformDistributedRandomNumberGenerator;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class UniformDistributedRandomNumberGeneratorTest {
    // Forgive me
    // - Rob
    @Test
    public void generatesNumberWithAVeryApproximatelyUniformDistribution() {
        UniformDistributedRandomNumberGenerator uniformDistributedRandomNumberGenerator = new UniformDistributedRandomNumberGenerator();

        int retries = 100; // To protect from flakiness
        for (int i = 0; i < retries; i++) {
            assertUniformityOfRandomNumberGenerator(uniformDistributedRandomNumberGenerator);
        }
    }

    private void assertUniformityOfRandomNumberGenerator(UniformDistributedRandomNumberGenerator uniformDistributedRandomNumberGenerator) {
        HashMap<Integer, Integer> buckets = new HashMap<>();
        for (int i = 1; i < 101; i++) {
            buckets.put(i, 0);
        }

        for (int i = 0; i < 10000; i++) {
            int k = uniformDistributedRandomNumberGenerator.randomPercent();
            Integer x = buckets.get(k);
            buckets.put(k, x + 1);
        }

        // Numbers 0-99 with 10K tries => Should be approximately 100 entries in each bucket
        // The median number of bucket entries should be between 80 and 120
        // This has not been an exercise in science.
        int median = median(buckets, 100);
        assertThat(median, lessThan(120));
        assertThat(median, greaterThan(80));
    }

    @Test
    public void medianTest() {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 7);
        map.put(2, 9);
        map.put(3, 12);
        map.put(4, 13);
        map.put(5, 16);

        int median = median(map, 5);

        assertThat(median, equalTo(12));
    }

    private int median(HashMap<Integer, Integer> buckets, int size) {
        Integer[] values = new Integer[size];
        for (int i = 1; i < size + 1; i++) {
            values[i - 1] = buckets.get(i);
        }
        Arrays.sort(values);
        return (values[size / 2] + values[(size - 1) / 2]) / 2;
    }
}