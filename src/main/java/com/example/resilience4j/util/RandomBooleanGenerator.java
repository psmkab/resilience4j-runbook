package com.example.resilience4j.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomBooleanGenerator implements RandomValueGenerator<Boolean> {
    @Override
    public Boolean generate() {
        return new Random().nextBoolean();
    }
}
