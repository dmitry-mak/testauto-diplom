package ru.netology.qadiplom.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.qadiplom.page.MainPage;

import static com.codeborne.selenide.Selenide.*;

public class NavigationTest {

    MainPage mainPage;

    @BeforeEach
    void openMainPage() {
//        mainPage = open("http://localhost:8080/", MainPage.class);
        open("http://localhost:8080/");
        mainPage = new MainPage();
    }

    @Test
    public void shouldOpenPaymentPage() {
        mainPage.navigateToPaymentPage();
    }


    @Test
    public void shouldOpenCreditPage() {
        mainPage.navigateToCreditPage();
    }
}
