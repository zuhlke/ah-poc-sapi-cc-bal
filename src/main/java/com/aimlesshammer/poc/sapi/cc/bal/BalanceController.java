package com.aimlesshammer.poc.sapi.cc.bal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class BalanceController {
    private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
    private final RequestHandler requestHandler = new RequestHandler();

    @GetMapping(path = "/customer/{customer-id}/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> balance(@PathParam("customer-id") String customerId) {
        logger.info(PocSapiCreditCardBalancesApplication.LOG_ID + ": Requesting balances for customer: '{}'", customerId);
        return requestHandler.handleBalanceRequest();
    }
}
