package edu.balu.test.automate.tc.integration;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationTests {

    Logger log = LoggerFactory.getLogger(IntegrationTests.class);

    @Test
    @Order(1)
    @DisplayName("Integration - Test#1")
    public void integrationTestOne() {
        log.info("IntegrationTests.integrationTestOne");
    }

    @Test
    @Order(2)
    @DisplayName("Integration - Test#2")
    public void integrationTestTwo() {
        log.info("IntegrationTests.integrationTestTwo");
    }

    @Test
    @Order(3)
    @DisplayName("Integration - Test#3")
    public void integrationTestThree() {
        log.info("IntegrationTests.integrationTestThree");
    }
}
