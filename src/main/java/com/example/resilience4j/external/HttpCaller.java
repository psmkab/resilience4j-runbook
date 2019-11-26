package com.example.resilience4j.external;

import com.example.resilience4j.util.RandomValueGenerator;
import lombok.val;
import org.springframework.stereotype.Component;

@Component(value = "externalApiCaller")
public class HttpCaller {
    private final RandomValueGenerator randomValueGenerator;

    public HttpCaller(
            RandomValueGenerator randomValueGenerator
    ) {
        this.randomValueGenerator = randomValueGenerator;
    }
    /**
     * Now, we don't have any other dependency for calling other msa service.
     * So, If result is true, will return and not trigger retry.
     * But, If result if false, will throw to trigger retry w/ exponential delay.
     *
     * @throws RuntimeException when result is false
     * @return boolean
     */
    public boolean callExternalApi() {
        val result = (boolean) randomValueGenerator.generate();

        if(!result) {
            throw new RuntimeException("Fail to calling external api..");
        }
        return true;
    }
}
