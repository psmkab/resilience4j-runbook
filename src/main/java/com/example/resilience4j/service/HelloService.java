package com.example.resilience4j.service;

import com.example.resilience4j.repository.HelloRepository;
import jdk.internal.jline.internal.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HelloService {
    private final HelloRepository helloRepository;

    HelloService(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    @Nullable
    public String hello() {
        return helloRepository.hello();
    }
}
