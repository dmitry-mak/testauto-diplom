package ru.netology.qadiplom.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiHandler {

    public static RequestSpecification specification = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static String sendValidRequest() {
        String statusRequest = given()
                .spec(specification)
                .body(DataHandler.getApprovedCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200)
                .extract()
                .path("status");

        return statusRequest;
    }

    public static String sendValidRequestDeclinedCard() {
        String statusRequest = given()
                .spec(specification)
                .body(DataHandler.getDeclinedCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200)
                .extract()
                .path("status");

        return statusRequest;
    }

    public static String sendValidRequestUnregisteredCard() {
        String statusRequest = given()
                .spec(specification)
                .body(DataHandler.getUnregisteredCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(500)
                .extract()
//                .response()
//                .asString();
                .path("message");

        return statusRequest;
    }

}
