package ru.netology.qadiplom.test;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.qadiplom.page.MainPage;
import ru.netology.qadiplom.page.PageElements;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class NavigationTest {

    MainPage mainPage;

    @BeforeEach
    void openMainPage() {
//        mainPage = open("http://localhost:8080/", MainPage.class);
        open("http://localhost:8080/");
        mainPage = new MainPage();
    }

    //    Загрузка стартовой страницы приложения
    @Test
    public void mainPageShouldBeLoaded() {
        assertAll(() -> PageElements.BUY_BUTTON.shouldBe(Condition.visible),
                () -> PageElements.CREDIT_BUTTON.shouldBe(Condition.visible),
                () -> PageElements.HEADER_MAINPAGE.shouldBe(Condition.visible));

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
