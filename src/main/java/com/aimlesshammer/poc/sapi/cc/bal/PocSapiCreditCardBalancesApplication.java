package com.aimlesshammer.poc.sapi.cc.bal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PocSapiCreditCardBalancesApplication {
    public static final String LOG_ID = "ah-poc-sapi-cc-bal";
    private static ConfigurableApplicationContext context;

    public static void start(String[] args) {
        context = SpringApplication.run(PocSapiCreditCardBalancesApplication.class, args);
    }

    public static void stop(int exitCode) {
        SpringApplication.exit(context, () -> exitCode);
    }
}
