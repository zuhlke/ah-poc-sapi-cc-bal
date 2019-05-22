package com.aimlesshammer.poc.sapi.cc.bal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class BalanceController {
    private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);

    @GetMapping("/customer/{customer-id}/balance")
    public String balance(@PathParam("customer-id") String customerId) {
        logger.info(PocSapiCreditCardBalancesApplication.LOG_ID + ": Requesting balances for customer: '{}'", customerId);
        return "[\n" +
                "  {\n" +
                "    \"customerId\": \"10101010\",\n" +
                "    \"creditCardNumber\": \"1234567890\",\n" +
                "    \"balance\": \"1234.50\"\n" +
                "  }\n" +
                "]";
    }

}
