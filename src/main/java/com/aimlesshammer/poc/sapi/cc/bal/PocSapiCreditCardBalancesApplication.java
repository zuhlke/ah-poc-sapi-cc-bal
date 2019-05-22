package com.aimlesshammer.poc.sapi.cc.bal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PocSapiCreditCardBalancesApplication {

	public static final String LOG_ID = "ah-poc-sapi-cc-bal";

	public static void main(String[] args) {
		SpringApplication.run(PocSapiCreditCardBalancesApplication.class, args);
	}

}
