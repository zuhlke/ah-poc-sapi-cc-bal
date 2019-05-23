package com.aimlesshammer.poc.sapi.cc.bal.integration;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class SpringHttpClient {
    private final HttpEntity<Object> DEFAULT_HEADERS = new HttpEntity<>(new HttpHeaders());
    private final Class<String> STRING_RESPONSE_TYPE = String.class;
    private final RestTemplate restTemplate = createSpringRestTemplate();

    public String get(String requestUrl) {
        return restTemplate.exchange(
                requestUrl,
                HttpMethod.GET,
                DEFAULT_HEADERS,
                STRING_RESPONSE_TYPE
        ).getBody();
    }

    public void post(String requestUrl) {
        restTemplate.exchange(
                requestUrl,
                HttpMethod.POST,
                DEFAULT_HEADERS,
                STRING_RESPONSE_TYPE
        );
    }

    public int get_statusCode(String requestUrl) {
        try {
            return restTemplate.exchange(
                    requestUrl,
                    HttpMethod.GET,
                    DEFAULT_HEADERS,
                    STRING_RESPONSE_TYPE
            ).getStatusCodeValue();
        } catch (HttpStatusCodeException exception) {
            return exception.getStatusCode().value();
        }
    }

    private RestTemplate createSpringRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }
}