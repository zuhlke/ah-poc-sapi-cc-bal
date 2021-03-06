package com.aimlesshammer.poc.sapi.cc.bal.integration;

import com.aimlesshammer.poc.sapi.cc.bal.SpringbootApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.aimlesshammer.poc.sapi.cc.bal.sapiBehaviourPolicy.CustomBehaviourPolicyHeaders.X_POLICY_FAILURE_RATE_HEADER_NAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest {
    private final SpringHttpClient springHttpClient = new SpringHttpClient();
    private final String origin = "http://localhost:3001";
    private final String requestUrl = origin + "/customer/10101010/balance";

    @Before
    public void beforeEach() {
        SpringbootApplication.start(new String[]{});
    }

    @After
    public void afterEach() {
        SpringbootApplication.stop(0);
    }

    @Test
    public void canGetBalances() {
        assertEquals(
                "[\n{\n\"customerId\":\"10101010\",\n\"creditCardNumber\":\"1234567890\",\n\"balance\":\"1234.50\"\n}\n]",
                springHttpClient.get(requestUrl)
        );
    }

    @Test
    public void canSetFailureRateAt100Pc() {
        springHttpClient.post(origin + "/failureRatePc/100");
        assertEquals(500, springHttpClient.get_statusCode(requestUrl));
        assertEquals(500, springHttpClient.get_statusCode(requestUrl));
        assertEquals(500, springHttpClient.get_statusCode(requestUrl));
    }

    @Test
    public void canSetRandomDelayAt100ms() {
        int delayAmountMs = 100;

        // Warm up
        springHttpClient.get(requestUrl);
        springHttpClient.get(requestUrl);

        long normal_t1 = timedRequest();
        long normal_t2 = timedRequest();
        long normal_t3 = timedRequest();
        long meanNormalTime = (normal_t1 + normal_t2 + normal_t3) / 3;

        springHttpClient.post(origin + "/perRequestDelayRangeMs?min=" + delayAmountMs + "&max=" + delayAmountMs);

        long delayed_t1 = timedRequest();
        long delayed_t2 = timedRequest();
        long delayed_t3 = timedRequest();
        long meanDelayedTime = (delayed_t1 + delayed_t2 + delayed_t3) / 3;

        long difference = meanDelayedTime - meanNormalTime;

        // The difference in the mean response times should be at least 70ms slower, and no more than 130ms slower
        // This has not been an exercise in science
        assertThat(difference, greaterThan(70L));
        assertThat(difference, lessThan(130L));
    }

    @Test
    public void resetEndpointReturns200() {
        assertThat(springHttpClient.post_statusCode(origin + "/reset"), equalTo(200));
    }

    @Test
    public void returns500IfFailureRateHeaderIsSetTo100() {
        assertThat(springHttpClient.get_withHeader_statusCode(requestUrl, X_POLICY_FAILURE_RATE_HEADER_NAME, "100"), equalTo(500));
    }

    @Test
    public void returns400IfDelayRangeIsSetWithHeaderButFormattingIsWrong() {
        assertThat(springHttpClient.get_withHeader_statusCode(requestUrl, X_POLICY_FAILURE_RATE_HEADER_NAME, "100_200"), equalTo(400));
    }

    private long timedRequest() {
        return springHttpClient.get_timeTakenMs(requestUrl);
    }
}