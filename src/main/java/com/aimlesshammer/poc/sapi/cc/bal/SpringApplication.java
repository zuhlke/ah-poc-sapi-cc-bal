package com.aimlesshammer.poc.sapi.cc.bal;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringApplication {
    public static final String LOG_ID = "ah-poc-sapi-cc-bal";
    private static ConfigurableApplicationContext context;

    public static void start(String[] args) {
        context = org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }

    public static void stop(int exitCode) {
        org.springframework.boot.SpringApplication.exit(context, () -> exitCode);
    }
}