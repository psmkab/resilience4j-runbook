package com.example.resilience4j.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigurationProperties;
import io.github.resilience4j.common.retry.configuration.RetryConfigurationProperties;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class Resilience4jConfig {

    private static final String HTTP_CALLER = "httpCaller";

    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final RetryRegistry retryRegistry;

    public Resilience4jConfig(
            CircuitBreakerRegistry circuitBreakerRegistry,
            RetryRegistry retryRegistry
    ) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
        this.retryRegistry = retryRegistry;
    }

    @Bean
    public CircuitBreaker httpCallerCircuitBreaker() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(HTTP_CALLER);
        log.info("[{}] is initialized, {}", HTTP_CALLER, circuitBreaker);
        return circuitBreaker;
    }

    @Bean
    public Retry httpCallerRetry() {
        Retry retry = retryRegistry.retry(HTTP_CALLER);
        log.info("[{}] is initialized, {}", HTTP_CALLER, retry);
        return retry;
    }
}
