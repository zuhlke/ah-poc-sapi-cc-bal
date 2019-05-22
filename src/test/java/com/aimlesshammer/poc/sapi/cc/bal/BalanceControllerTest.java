package com.aimlesshammer.poc.sapi.cc.bal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BalanceController.class)
public class BalanceControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void itGetAllBalancesFromSapiService() throws Exception {
        mvc.perform(get("/customer/10101010/balance"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
                .andExpect(content().string(equalTo("[\n" +
                        "  {\n" +
                        "    \"customerId\": \"10101010\",\n" +
                        "    \"creditCardNumber\": \"1234567890\",\n" +
                        "    \"balance\": \"1234.50\"\n" +
                        "  }\n" +
                        "]")));
    }
}