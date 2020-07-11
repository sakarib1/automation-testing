package edu.balu.test.automate.tc.txa;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

public class TestBase {

    @BeforeAll
    @DisplayName("Setting up target URLs")
    public static void init(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8085;
        RestAssured.basePath = "/student";
    }
}
