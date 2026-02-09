package com.example.postmanecho.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    protected static RequestSpecification requestSpec;

    @BeforeAll
    public static void setUp() {
        // Base configuration for all tests
        RestAssured.baseURI = "https://postman-echo.com";

        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://postman-echo.com")
                .setContentType(ContentType.JSON)
                .build();

        // Enable logging for debugging (optional)
        // RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
