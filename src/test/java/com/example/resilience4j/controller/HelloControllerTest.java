package com.example.resilience4j.controller;

import com.example.resilience4j.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HelloControllerTest {
    @Mock
    private HelloService helloService;

    @InjectMocks
    private HelloController helloController;

    @Test
    public void get_message_from_service() throws Exception {
        when(helloService.random()).thenReturn("success");
        String result = helloController.random();

        assertEquals("success", result);
    }
}