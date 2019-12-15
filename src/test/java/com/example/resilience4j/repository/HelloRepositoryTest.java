package com.example.resilience4j.repository;

import com.example.resilience4j.external.HttpCaller;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        properties = {"spring.config.location=classpath:application.yml"}
)
public class HelloRepositoryTest {
    private HelloRepository helloRepository;
    private Retry retry;
    private CircuitBreaker circuitBreaker;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;
    @Autowired
    private RetryRegistry retryRegistry;
    @Autowired
    private HttpCaller httpCaller;

    @Before
    public void setUp() {
        retry = retryRegistry.retry("httpCaller");
        circuitBreaker = circuitBreakerRegistry.circuitBreaker("httpCaller");

        helloRepository = new HelloRepository(
                httpCaller,
                retry,
                circuitBreaker
        );
    }

    @Test
    public void success() {
        assertTrue(helloRepository.success());
        assertTrue(retry.getMetrics().getNumberOfSuccessfulCallsWithoutRetryAttempt() == 1L);   // check not doing `retry`
    }

    @Test
    public void fail() {
        assertFalse(helloRepository.fail());
        assertTrue(retry.getMetrics().getNumberOfFailedCallsWithRetryAttempt() == 1L);  // check doing `retry`
        assertTrue(retry.getMetrics().getNumberOfFailedCallsWithoutRetryAttempt() == 0L);   // check doing `retry`
    }
}