package ru.netology.qadiplom.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.qadiplom.data.ApiHandler;
import ru.netology.qadiplom.data.SqlHandler;

public class ApiTest {

    //  Отправка POST запроса с валидными данными и картой со статусом APPROVED на оплату картой
    @Test
    public void shouldSendValidPaymentRequestApprovedCard() {
        Assertions.assertEquals("APPROVED", ApiHandler.sendRequestValidApprovedPayment());
    }

    //  Отправка POST запроса с валидными данными и картой со статусом DECLINED на оплату картой
    @Test
    public void shouldSendValidPaymentRequestDeclinedCard() {
        Assertions.assertEquals("DECLINED", ApiHandler.sendRequestValidDeclinedPayment());
    }

    //
    @Test
    public void shouldSendPaymentRequestUnregisteredCard() {
        Assertions.assertEquals("500", ApiHandler.sendRequestUnregisteredPayment());
    }

    //    Отправка POST запроса с валидными данными и картой со статусом APPROVED на оплату кредитом
    @Test
    public void shouldSendValidCreditRequestApprovedCard() {
        Assertions.assertEquals("APPROVED", ApiHandler.sendRequestValidApprovedCredit());
    }

    //    Отправка POST запроса с валидными данными и картой со статусом DECLINED на оплату кредитом
    @Test
    public void shouldSendValidCreditRequestDeclinedCard() {
        Assertions.assertEquals("DECLINED", ApiHandler.sendRequestValidDeclinedCredit());
    }

    //
    @Test
    public void shouldSendCreditRequestUnregisteredCard() {
        Assertions.assertEquals("500", ApiHandler.sendRequestUnregisteredCredit());
    }

    //    Должен возвращать код ошибки 500 если отослать запрос на оплату с пустым полем number
    @Test
    public void shouldSendPaymentRequestWithEmptyNumber() {
        Assertions.assertEquals("500", ApiHandler.sendPaymentRequestWithEmptyNumber());
    }

    //    Отправка пустого POST запроса на оплату картой
    @Test
    public void shouldSendPaymentRequestWithEmptyJSON() {
        Assertions.assertEquals("500", ApiHandler.sendEmptyPaymentRequest());
    }

    //    Отправка пустого POST запроса на оплату кредитом
    @Test
    public void shouldSendCreditRequestWithEmptyJSON() {
        Assertions.assertEquals("500", ApiHandler.sendEmptyCreditRequest());
    }

/*    //    Должен возвращать код ошибки 500 если отослать запрос на оплату с пустым полем month
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
    }       */

    @AfterAll
    public static void cleanAllTables() {
        SqlHandler.cleanAllTables();
    }
}
