package ru.netology.qadiplom.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.qadiplom.data.ApiHandler;
import ru.netology.qadiplom.data.SqlHandler;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;

public class SqlTest {

    @AfterEach
    public void cleanTables() {
        SqlHandler.cleanAllTables();
    }


    @Test
    @DisplayName("'payment_entity' table fields should not be null after payment")
    public void paymentEntityFieldsShouldBeNotNull() {
        ApiHandler.sendRequestValidApprovedPayment();
        Map<String, Object> paymentEntityData = SqlHandler.getAllPaymentEntityFields();
        assertAll(
                () -> Assertions.assertNotNull(paymentEntityData.get("id")),
                () -> Assertions.assertNotNull(paymentEntityData.get("amount")),
                () -> Assertions.assertNotNull(paymentEntityData.get("created")),
                () -> Assertions.assertNotNull(paymentEntityData.get("status")),
                () -> Assertions.assertNotNull(paymentEntityData.get("transaction_id"))
        );
    }

    @Test
    @DisplayName("'order_entity' table fields should not be null after payment or credit")
    public void orderRequestEntityFieldsShouldBeNotNull() {
        ApiHandler.sendRequestValidApprovedCredit();
        Map<String, Object> orderEntityData = SqlHandler.getAllOrderEntityFields();
        assertAll(
                () -> Assertions.assertNotNull(orderEntityData.get("id"), "'id' field should not be null"),
                () -> Assertions.assertNotNull(orderEntityData.get("created"), "'created' field should not be null"),
                () -> Assertions.assertNotNull(orderEntityData.get("credit_id"),"'credit_id' field should not be null"),
                () -> Assertions.assertNotNull(orderEntityData.get("payment_id"), "'payment_id' field should not be null")
        );
    }

    @Test
    @DisplayName("'credit_request_entity' table fields should not be null after credit")
    public void creditRequestEntityFieldsShouldBeNotNull() {
        ApiHandler.sendRequestValidApprovedCredit();
        Map<String, Object> creditEntityData = SqlHandler.getAllCreditRequestEntityFields();
        assertAll(
                () -> Assertions.assertNotNull(creditEntityData.get("id")),
                () -> Assertions.assertNotNull(creditEntityData.get("bank_id")),
                () -> Assertions.assertNotNull(creditEntityData.get("created")),
                () -> Assertions.assertNotNull(creditEntityData.get("status"))
        );
    }

    @Test
    @DisplayName("'payment_id' and 'transaction_id' fields should be equal")
    public void transactionIdAndPaymentIdShouldBeEqual() {
        ApiHandler.sendRequestValidApprovedPayment();
        Map<String, Object> paymentEntityData = SqlHandler.getAllPaymentEntityFields();
        Map<String, Object> orderEntityData = SqlHandler.getAllOrderEntityFields();
        Assertions.assertEquals(orderEntityData.get("payment_id"), paymentEntityData.get("transaction_id"));
    }

    @Test
    @DisplayName("'payment_id' and 'bank_id' fields should be equal ")
    public void bankIdAndPaymentIdShouldBeEqual() {
        ApiHandler.sendRequestValidApprovedCredit();
        Map<String, Object> creditEntityData = SqlHandler.getAllCreditRequestEntityFields();
        Map<String, Object> orderEntityData = SqlHandler.getAllOrderEntityFields();
        Assertions.assertEquals(orderEntityData.get("payment_id"), creditEntityData.get("bank_id"));
    }

}

