package ru.netology.qadiplom.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.qadiplom.data.ApiHandler;
import ru.netology.qadiplom.data.SqlHandler;

public class ApiTest {

    //  Отправка POST запроса с валидными данными и картой со статусом APPROVED на оплату картой
    @Test
    @DisplayName("Successful payment for the form with valid data and APPROVED status card")
    public void shouldSendValidPaymentRequestApprovedCard() {
        Assertions.assertEquals("APPROVED", ApiHandler.sendRequestValidApprovedPayment());
    }

    //  Отправка POST запроса с валидными данными и картой со статусом DECLINED на оплату картой
    @Test
    @DisplayName("Rejected payment for the form with valid data and DECLINED status card")
    public void shouldSendValidPaymentRequestDeclinedCard() {
        Assertions.assertEquals("DECLINED", ApiHandler.sendRequestValidDeclinedPayment());
    }

    // Отправка POST запроса с валидными данными и номером карты, незарегистрированным в системе
    @Test
    @DisplayName("Should get '500' response code for the form with valid data and unregistered card")
    public void shouldSendPaymentRequestUnregisteredCard() {
        Assertions.assertEquals("500", ApiHandler.sendRequestUnregisteredPayment());
    }

    //    Отправка POST запроса с валидными данными и картой со статусом APPROVED на оплату кредитом
    @Test
    @DisplayName("Successful credit for the form with valid data and APPROVED status card")
    public void shouldSendValidCreditRequestApprovedCard() {
        Assertions.assertEquals("APPROVED", ApiHandler.sendRequestValidApprovedCredit());
    }

    //    Отправка POST запроса с валидными данными и картой со статусом DECLINED на оплату кредитом
    @Test
    @DisplayName("Rejected credit for the form with valid data and DECLINED status card")
    public void shouldSendValidCreditRequestDeclinedCard() {
        Assertions.assertEquals("DECLINED", ApiHandler.sendRequestValidDeclinedCredit());
    }

    //    Отправка POST запроса с валидными данными и номером карты, незарегистрированным в системе
    @Test
    @DisplayName("Should get '500' response code for the form with valid data and unregistered card")
    public void shouldSendCreditRequestUnregisteredCard() {
        Assertions.assertEquals("500", ApiHandler.sendRequestUnregisteredCredit());
    }

    //    Должен возвращать код ошибки 500 при отправке запроса на оплату с пустым полем number
    @Test
    @DisplayName("Should get '500' response code for the payment form with empty 'card' field")
    public void shouldSendPaymentRequestWithEmptyNumber() {
        Assertions.assertEquals("500", ApiHandler.sendPaymentRequestWithEmptyNumber());
    }

    //    Отправка пустого POST запроса на оплату картой
    @Test
    @DisplayName("Should get '500' response code for the empty payment form")
    public void shouldSendPaymentRequestWithEmptyJSON() {
        Assertions.assertEquals("500", ApiHandler.sendEmptyPaymentRequest());
    }

    //    Отправка пустого POST запроса на оплату кредитом
    @Test
    @DisplayName("Should get '500' response code for the empty credit form")
    public void shouldSendCreditRequestWithEmptyJSON() {
        Assertions.assertEquals("500", ApiHandler.sendEmptyCreditRequest());
    }

    //    Должен возвращать код ошибки 500 если отослать запрос на оплату с пустым полем month
    @Test
    @DisplayName("Should send '500' response code for the form with empty 'month' field")
    public void shouldSendPaymentRequestWithEmptyMonth() {
        Assertions.assertEquals("500", ApiHandler.sendPaymentRequestWithEmptyMonth());
    }

    @Test
    @DisplayName("Should send '500' response code for the form with empty 'year' field")
    public void shouldSendPaymentRequestWithEmptyYear() {
        Assertions.assertEquals("500", ApiHandler.sendPaymentRequestWithEmptyYear());
    }

    @Test
    @DisplayName("Should send '500' response code for the form with empty 'holder' field")
    public void shouldSendPaymentRequestWithEmptyHolder() {
        Assertions.assertEquals("500", ApiHandler.sendPaymentRequestWithEmptyHolder());
    }

    @Test
    @DisplayName("Should send '500' response code for the form with empty 'CVC' field")
    public void shouldSendPaymentRequestWithEmptyCvc() {
        Assertions.assertEquals("500", ApiHandler.sendPaymentRequestWithEmptyCvc());
    }

    @AfterAll
    public static void cleanAllTables() {
        SqlHandler.cleanAllTables();
    }
}
