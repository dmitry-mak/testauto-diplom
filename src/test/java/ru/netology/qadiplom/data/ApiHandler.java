package ru.netology.qadiplom.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiHandler {

    private static final String BASE_URI = "http://localhost";
    private static final String PAYMENT = "/api/v1/pay";
    private static final String CREDIT = "/api/v1/credit";

    public static RequestSpecification specification = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
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
                .post(PAYMENT)
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
                .post(PAYMENT)
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
                .post(PAYMENT)
                .then()
                .statusCode(500)
                .extract()
                .path("status").toString();

        return statusRequest;
    }


    public static String sendRequest(String endpoint, Object cardInfo, int statusCode) {
        var status = given()
                .spec(specification)
                .body(cardInfo)
                .when()
                .post(endpoint)
                .then()
                .statusCode(statusCode)
                .extract()
                .path("status");
        return String.valueOf(status);
    }

    public static String sendRequestValidApprovedPayment() {
        String result = sendRequest(PAYMENT, DataHandler.getApprovedCard(), 200);
        return result;
    }

    public static String sendRequestValidDeclinedPayment() {
        String result = sendRequest(PAYMENT, DataHandler.getDeclinedCard(), 200);
        return result;
    }

    public static String sendRequestUnregisteredPayment() {
        String result = sendRequest(PAYMENT, DataHandler.getUnregisteredCard(), 500);
        return result;
    }

    public static String sendRequestValidApprovedCredit() {
        String result = sendRequest(CREDIT, DataHandler.getApprovedCard(), 200);
        return result;
    }

    public static String sendRequestValidDeclinedCredit() {
        String result = sendRequest(CREDIT, DataHandler.getDeclinedCard(), 200);
        return result;
    }

    public static String sendRequestUnregisteredCredit() {
        String result = sendRequest(CREDIT, DataHandler.getUnregisteredCard(), 500);
        return result;
    }

    public static String sendPaymentRequestWithEmptyNumber() {
        String result = sendRequest(PAYMENT, DataHandler.getFormWithEmptyNumber(), 500);
        return result;
    }

    public static String sendPaymentRequestWithEmptyMonth() {
        String result = sendRequest(PAYMENT, DataHandler.getFormWithEmptyMonth(), 500);
        return result;
    }

    public static String sendPaymentRequestWithEmptyYear() {
        String result = sendRequest(PAYMENT, DataHandler.getFormWithEmptyYear(), 500);
        return result;
    }

    public static String sendPaymentRequestWithEmptyHolder() {
        String result = sendRequest(PAYMENT, DataHandler.getFormWithEmptyHolder(), 500);
        return result;
    }

    public static String sendPaymentRequestWithEmptyCvc() {
        String result = sendRequest(PAYMENT, DataHandler.getFormWithEmptyCvc(), 500);
        return result;
    }

    public static String sendCreditRequestWithEmptyCvc(){
        String result = sendRequest(CREDIT, DataHandler.getFormWithEmptyCvc(), 500);
        return result;
    }

    public static String sendEmptyPaymentRequest() {
        String emptyJson = "{}";
        String result = sendRequest(PAYMENT, emptyJson, 500);

        return result;
    }

    public static String sendEmptyCreditRequest() {
        String emptyJson = "{}";
        String result = sendRequest(CREDIT, emptyJson, 500);
        return result;
    }

}
