package com.example.resilience4j.repository;

import com.example.resilience4j.HttpCaller;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Retry(name = "helloRep")
@CircuitBreaker(name = "helloRep", fallbackMethod = "fallBackWithRuntimeEx")
@Repository(value = "helloRepository")
public class HelloRepository {
    private final HttpCaller httpCaller;

    public HelloRepository(HttpCaller httpCaller) {
        this.httpCaller = httpCaller;
    }

    public String hello() {
        return "";
    }

    public <T extends Throwable> String fallBackWithRuntimeEx(String _param, T ex) {
        log.error("Cannot call external api w/ reason", ex);
        return "";
    }
}
