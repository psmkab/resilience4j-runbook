package com.example.resilience4j.repository;

import com.example.resilience4j.external.HttpCaller;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.Retry;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.function.Supplier;

@Slf4j
@Repository(value = "helloRepository")
public class HelloRepository {
    private final HttpCaller httpCaller;
    private final Retry retry;
    private final CircuitBreaker circuitBreaker;

    public HelloRepository(
            @Qualifier(value = "externalApiCaller") HttpCaller httpCaller,
            Retry retry,
            CircuitBreaker circuitBreaker
    ) {
        this.httpCaller = httpCaller;
        this.retry = retry;
        this.circuitBreaker = circuitBreaker;
    }

    public boolean random() {
        return callExternalApi(httpCaller::callExternalApi);
    }

    public boolean success() {
        return callExternalApi(httpCaller::callExternalApiWithAlwaysSuccess);
    }

    public boolean fail() {
        return callExternalApi(httpCaller::callExternalApiWithAlwaysFail);
    }

    private boolean callExternalApi(Supplier<Boolean> supplier) {
        val caller = Decorators
                .ofSupplier(supplier)
                .withRetry(retry)
                .withCircuitBreaker(circuitBreaker)
                .decorate();

        return Try.ofSupplier(caller)
                .recover(e -> {
                    log.info("Error occurs.. ", e);
                    return false;
                })
                .get();
    }
}
