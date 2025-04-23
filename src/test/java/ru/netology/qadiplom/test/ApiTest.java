package ru.netology.qadiplom.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.qadiplom.data.ApiHandler;

public class ApiTest {

//    @Test
//    public void testApiApprovedCard() {
//        Assertions.assertEquals("APPROVED", ApiHandler.sendValidRequest());
//    }
//
//    @Test
//    public void testApiDeclinedCard() {
//        Assertions.assertEquals("DECLINED", ApiHandler.sendValidRequestDeclinedCard());
//    }
//
//    @Test
//    public void testApiUnregisteredCard() {
//        Assertions.assertEquals("500", ApiHandler.sendValidRequestUnregisteredCard());
//    }

    @Test
    public void shouldSendValidRequestApprovedPayment() {
        Assertions.assertEquals("APPROVED", ApiHandler.sendRequestValidApprovedPayment());
    }

    @Test
    public void shouldSendValidRequestDeclinedPayment() {
        Assertions.assertEquals("DECLINED", ApiHandler.sendRequestValidDeclinedPayment());
    }

    @Test
    public void shouldSendRequestUnregisteredPayment() {
        Assertions.assertEquals("500", ApiHandler.sendRequestUnregisteredPayment());
    }

    @Test
    public void shouldSendValidRequestApprovedCredit() {
        Assertions.assertEquals("APPROVED", ApiHandler.sendRequestValidApprovedCredit());
    }

    @Test
    public void shouldSendValidRequestDeclinedCredit() {
        Assertions.assertEquals("DECLINED", ApiHandler.sendRequestValidDeclinedCredit());
    }

    @Test
    public void shouldSendRequestUnregisteredCredit() {
        Assertions.assertEquals("500", ApiHandler.sendRequestUnregisteredCredit());
    }

}
