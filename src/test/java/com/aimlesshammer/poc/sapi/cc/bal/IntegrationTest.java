package com.aimlesshammer.poc.sapi.cc.bal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest {
    private final SpringHttpClient springHttpClient = new SpringHttpClient();

    @Before
    public void setUp() {
        PocSapiCreditCardBalancesApplication.start(new String[]{});
    }

    @After
    public void tearDown() {
        PocSapiCreditCardBalancesApplication.stop(0);
    }

    @Test
    public void canGetBalances() {
        String origin = "http://localhost:3001";
        assertEquals(
                "[\n" +
                        "  {\n" +
                        "    \"customerId\": \"10101010\",\n" +
                        "    \"creditCardNumber\": \"1234567890\",\n" +
                        "    \"balance\": \"1234.50\"\n" +
                        "  }\n" +
                        "]",
                springHttpClient.get(origin + "/customer/10101010/balance")
        );
    }
}