package com.example.resilience4j.service;

import com.example.resilience4j.repository.HelloRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HelloService {
    private final HelloRepository helloRepository;

    public HelloService(
            HelloRepository helloRepository
    ) {
        this.helloRepository = helloRepository;
    }

    @Nullable
    public String hello() {
        return helloRepository.hello();
    }
}
