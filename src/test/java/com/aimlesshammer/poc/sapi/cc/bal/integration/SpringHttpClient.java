package com.aimlesshammer.poc.sapi.cc.bal.integration;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class SpringHttpClient {
    private final Class<String> STRING_RESPONSE_TYPE = String.class;
    private final RestTemplate restTemplate = createSpringRestTemplate();

    public String get(String requestUrl) {
        return restTemplate.exchange(
                requestUrl,
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                STRING_RESPONSE_TYPE
        ).getBody();
    }

    public void post(String requestUrl) {
        restTemplate.exchange(
                requestUrl,
                HttpMethod.POST,
                new HttpEntity<>(new HttpHeaders()),
                STRING_RESPONSE_TYPE
        );
    }

    public int get_statusCode(String requestUrl) {
        try {
            return restTemplate.exchange(
                    requestUrl,
                    HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()),
                    STRING_RESPONSE_TYPE
            ).getStatusCodeValue();
        } catch (HttpStatusCodeException exception) {
            return exception.getStatusCode().value();
        }
    }

    public long get_timeTakenMs(String requestUrl) {
        long start = System.currentTimeMillis();
        restTemplate.exchange(
                requestUrl,
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                STRING_RESPONSE_TYPE
        );
        long end = System.currentTimeMillis();
        return end - start;
    }

    private RestTemplate createSpringRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }

    public int post_statusCode(String requestUrl) {
        try {
            return restTemplate.exchange(
                    requestUrl,
                    HttpMethod.POST,
                    new HttpEntity<>(new HttpHeaders()),
                    STRING_RESPONSE_TYPE
            ).getStatusCodeValue();
        } catch (HttpStatusCodeException exception) {
            return exception.getStatusCode().value();
        }
    }

    public int get_withHeader_statusCode(String requestUrl, String headerName, String headerValue) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add(headerName, headerValue);
            headers.set(headerName, headerValue);
            HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
            return restTemplate.exchange(
                    requestUrl,
                    HttpMethod.GET,
                    requestEntity,
                    STRING_RESPONSE_TYPE
            ).getStatusCodeValue();
        } catch (HttpStatusCodeException exception) {
            return exception.getStatusCode().value();
        }
    }
}