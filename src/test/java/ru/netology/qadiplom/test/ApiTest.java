package ru.netology.qadiplom.test;

import org.junit.jupiter.api.Test;
import ru.netology.qadiplom.data.ApiHandler;

public class ApiTest {

    @Test
    public void testApi(){

//        ApiHandler.sendValidRequest();
        System.out.println(ApiHandler.sendValidRequest());
    }
}
