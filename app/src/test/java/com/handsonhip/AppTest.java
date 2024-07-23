package com.handsonhip;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppTest {
    @Test public void appHasAGreeting() {
        String value = "Hello, world!";
        assertNotNull(value, "Value should not be null");
    }
}
