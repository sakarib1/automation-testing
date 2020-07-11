package edu.balu.test.automate.tc.performance;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PerformanceTests {
    Logger log = LoggerFactory.getLogger(PerformanceTests.class);

    @Test
    @Order(1)
    @DisplayName("Performance - Test#1")
    public void performanceTestOne() {
        log.info("PerformanceTests.performanceTestOne");
    }

    @Test
    @Order(2)
    @DisplayName("Performance - Test#2")
    public void performanceTestTwo() {
        log.info("PerformanceTests.performanceTestTwo");
    }

    @Test
    @Order(3)
    @DisplayName("Performance - Test#3")
    public void performanceTestThree() {
        log.info("PerformanceTests.performanceTestThree");
    }
}
