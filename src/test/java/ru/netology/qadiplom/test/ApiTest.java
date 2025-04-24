package ru.netology.qadiplom.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.qadiplom.data.ApiHandler;
import ru.netology.qadiplom.data.SqlHandler;

public class ApiTest {


    @Test
    public void shouldSendValidPaymentRequestApprovedCard() {
        Assertions.assertEquals("APPROVED", ApiHandler.sendRequestValidApprovedPayment());
    }

    @Test
    public void shouldSendValidPaymentRequestDeclinedCard() {
        Assertions.assertEquals("DECLINED", ApiHandler.sendRequestValidDeclinedPayment());
    }

    @Test
    public void shouldSendPaymentRequestUnregisteredCard() {
        Assertions.assertEquals("500", ApiHandler.sendRequestUnregisteredPayment());
    }

    @Test
    public void shouldSendValidCreditRequestApprovedCard() {
        Assertions.assertEquals("APPROVED", ApiHandler.sendRequestValidApprovedCredit());
    }

    @Test
    public void shouldSendValidCreditRequestDeclinedCard() {
        Assertions.assertEquals("DECLINED", ApiHandler.sendRequestValidDeclinedCredit());
    }

    @Test
    public void shouldSendCreditRequestUnregisteredCard() {
        Assertions.assertEquals("500", ApiHandler.sendRequestUnregisteredCredit());
    }

    //    Должен возвращать код ошибки 500 если отослать запрос на оплату с пустым полем number
    @Test
    public void shouldSendPaymentRequestWithEmptyNumber() {
        Assertions.assertEquals("500", ApiHandler.sendPaymentRequestWithEmptyNumber());
    }

    /*
    //    Должен возвращать код ошибки 500 если отослать запрос на оплату с пустым полем month
    @Test
    public void shouldSendPaymentRequestWithEmptyMonth() {
        Assertions.assertEquals("500", ApiHandler.sendPaymentRequestWithEmptyMonth());
    }

    @Test
    public void shouldSendPaymentRequestWithEmptyYear() {
        Assertions.assertEquals("500", ApiHandler.sendPaymentRequestWithEmptyYear());
    }

    @Test
    public void shouldSendPaymentRequestWithEmptyHolder() {
        Assertions.assertEquals("500", ApiHandler.sendPaymentRequestWithEmptyHolder());
    }

    @Test
    public void shouldSendPaymentRequestWithEmptyCvc() {
        Assertions.assertEquals("500", ApiHandler.sendPaymentRequestWithEmptyCvc());
    }

     */


//    @Test
   @AfterAll
    public static void sqlConnectionTest() {
        System.out.println("LAST TRANSACTION ID: " + SqlHandler.getLastTransactionId());
    }

//    @AfterAll
//    public static void cleanAllTables() {
//        SqlHandler.cleanAllTables();
//    }
}
