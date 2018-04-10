package com.company;


import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class TestLoginAPI {

    @Test
    public void positiveTestLoginAPI() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "admin123")
                .when()
                .get("https://diploma-courses.7bits.it/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue());
    }

    @Test
    public void testLoginAPIwithEmptyLogin() {
        given()
                .auth()
                .preemptive()
                .basic("", "admin123")
                .when()
                .get("https://diploma-courses.7bits.it/login")
                .then()
                .statusCode(401)
                .body("token", nullValue());
    }

    @Test
    public void testLoginAPIwithEmptyPassword() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "")
                .when()
                .get("https://diploma-courses.7bits.it/login")
                .then()
                .statusCode(401)
                .body("token", nullValue());
    }

    @Test
    public void testLoginAPIwithIncorrectPassword() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "admin1234")
                .when()
                .get("https://diploma-courses.7bits.it/login")
                .then()
                .statusCode(401)
                .body("token", nullValue());
    }

    @Test
    public void testLoginAPIwithIncorrectLogin() {
        given()
                .auth()
                .preemptive()
                .basic("admin1", "admin")
                .when()
                .get("https://diploma-courses.7bits.it/login")
                .then()
                .statusCode(401)
                .body("token", nullValue());
    }


}
