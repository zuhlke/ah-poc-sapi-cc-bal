package com.aimlesshammer.poc.sapi.cc.bal.integration;

import com.aimlesshammer.poc.sapi.cc.bal.PocSapiCreditCardBalancesApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.aimlesshammer.poc.sapi.cc.bal.BehaviourPolicyHeaders.X_POLICY_FAILURE_RATE_HEADER_NAME;
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
        PocSapiCreditCardBalancesApplication.start(new String[]{});
    }

    @After
    public void afterEach() {
        PocSapiCreditCardBalancesApplication.stop(0);
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
        // Warm up
        springHttpClient.get(requestUrl);
        springHttpClient.get(requestUrl);

        long initialMs = springHttpClient.get_timeTakenMs(requestUrl);
        springHttpClient.post(origin + "/perRequestDelayRangeMs?min=100&max=100");
        long delayedMs = springHttpClient.get_timeTakenMs(requestUrl);
        long difference = delayedMs - initialMs;

        // The second request should be at least 80ms slower, and no more than 120ms slower
        // This has not been an exercise in science
        assertThat(difference, greaterThan(80L));
        assertThat(difference, lessThan(120L));
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
    public void returns400IfDelayRangeIsSetWithWrongFormatting() {
        assertThat(springHttpClient.get_withHeader_statusCode(requestUrl, X_POLICY_FAILURE_RATE_HEADER_NAME, "100_200"), equalTo(400));
    }
}