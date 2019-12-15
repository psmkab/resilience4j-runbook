package com.example.resilience4j.service;

import com.example.resilience4j.repository.HelloRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Slf4j
@Service
public class HelloService {
    private final HelloRepository helloRepository;

    public HelloService(
            HelloRepository helloRepository
    ) {
        this.helloRepository = helloRepository;
    }

    @NotNull
    public String random() {
        return String.valueOf(helloRepository.random());
    }

    @NotNull
    public String success() {
        return String.valueOf(helloRepository.success());
    }

    @NotNull
    public String fail() {
        return String.valueOf(helloRepository.fail());
    }
}
