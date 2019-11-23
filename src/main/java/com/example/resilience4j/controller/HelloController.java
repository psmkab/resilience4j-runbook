package com.example.resilience4j.controller;

import com.example.resilience4j.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping(value = HelloController.VERSION)
@RestController
public class HelloController {
//    static final String VERSION = "/v1";
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping(value = "hello")
    public String hello() {
        return helloService.hello();
    }
}
