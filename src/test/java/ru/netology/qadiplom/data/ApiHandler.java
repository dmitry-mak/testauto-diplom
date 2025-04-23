package ru.netology.qadiplom.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHandler {

        public static RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(8080)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        public static String sendValidRequest (){
//            Map <String, String> dataForRequest= new HashMap<>();
//            dataForRequest.put("number", "4444 4444 4444 4441");

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



}
