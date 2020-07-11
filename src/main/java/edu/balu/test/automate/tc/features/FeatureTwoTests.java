package edu.balu.test.automate.tc.features;

import org.junit.jupiter.api.*;
import org.junit.platform.suite.api.IncludeTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeatureTwoTests {
    Logger log = LoggerFactory.getLogger(FeatureTwoTests.class);

    @Test
    @Order(1)
    @DisplayName("Feature#2 - Test#1")
    public void featureOneTestOne() {
        log.info("FeatureTwoTests.featureOneTestOne");
    }

    @Test
    @Order(2)
    @DisplayName("Feature#2 - Test#2")
    @Tag("smoke")
    public void featureOneTestTwo() {
        log.info("FeatureTwoTests.featureOneTestTwo");
    }

    @Test
    @Order(3)
    @DisplayName("Feature#2 - Test#3")
    public void featureOneTestThree() {
        log.info("FeatureTwoTests.featureOneTestThree");
    }
}
