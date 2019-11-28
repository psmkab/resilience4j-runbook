package com.example.resilience4j.util;

import java.util.Random;

public interface RandomValueGenerator<T> {
    public T generate();
}
