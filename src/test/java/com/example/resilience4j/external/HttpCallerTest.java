package com.example.resilience4j.external;

import com.example.resilience4j.util.RandomBooleanGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HttpCallerTest {
    @Mock
    private RandomBooleanGenerator generator;

    @InjectMocks
    private HttpCaller httpCaller;

    @Test
    public void success() {
        when(generator.generate()).thenReturn(true);

        assertTrue(httpCaller.callExternalApi());
    }

    @Test
    public void fail() {
        when(generator.generate()).thenReturn(false);

        assertThatExceptionOfType(RuntimeException.class);
    }
}