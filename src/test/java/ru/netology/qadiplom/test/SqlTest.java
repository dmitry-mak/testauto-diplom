package ru.netology.qadiplom.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.netology.qadiplom.data.ApiHandler;
import ru.netology.qadiplom.data.SqlHandler;

import java.util.Map;
import java.util.Objects;

public class SqlTest {

    @AfterEach
    public void cleanTables(){
        SqlHandler.cleanAllTables();
    }


    @Test
    public void shouldGetAllPaymentEntityFields() {
        ApiHandler.sendRequestValidApprovedPayment();
        Map<String, Object> paymentEntityData = SqlHandler.getAllPaymentEntityFields();
        for (Map.Entry<String, Object> entry : paymentEntityData.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    @Test
    public void shouldGetAllOrderEntityFields() {
        ApiHandler.sendRequestValidApprovedPayment();
        Map<String, Object> orderEntityData = SqlHandler.getAllOrderEntityFields();
        for (Map.Entry<String, Object> entry : orderEntityData.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    @Test
    public void shouldGetAllCreditRequestEntityFields() {
        ApiHandler.sendRequestValidApprovedCredit();
        Map<String, Object> creditEntityData = SqlHandler.getAllCreditRequestEntityFields();
        for (Map.Entry<String, Object> entry : creditEntityData.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
