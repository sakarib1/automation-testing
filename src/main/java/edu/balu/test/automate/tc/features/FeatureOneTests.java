package edu.balu.test.automate.tc.features;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FeatureOneTests {
    Logger log = LoggerFactory.getLogger(FeatureOneTests.class);

    @Test
    @Order(1)
    @DisplayName("Feature#1 - Test#1")
    public void featureOneTestOne() {
        log.info("FeatureOneTests.featureOneTestOne");
    }

    @Test
    @Order(2)
    @DisplayName("Feature#1 - Test#2")
    public void featureOneTestTwo() {
        log.info("FeatureOneTests.featureOneTestTwo");
    }

    @Test
    @Order(3)
    @DisplayName("Feature#1 - Test#3")
    public void featureOneTestThree() {
        log.info("FeatureOneTests.featureOneTestThree");
    }
}
