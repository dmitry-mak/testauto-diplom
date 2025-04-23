package ru.netology.qadiplom.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.qadiplom.data.ApiHandler;

public class ApiTest {

    @Test
    public void testApiApprovedCard() {
        Assertions.assertEquals("APPROVED", ApiHandler.sendValidRequest());
    }

    @Test
    public void testApiDeclinedCard() {
        Assertions.assertEquals("DECLINED", ApiHandler.sendValidRequestDeclinedCard());
    }

    @Test
    public void testApiUnregisteredCard() {
        Assertions.assertEquals("400 Bad Request", ApiHandler.sendValidRequestUnregisteredCard());
    }
}
