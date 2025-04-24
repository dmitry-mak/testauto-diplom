package ru.netology.qadiplom.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.qadiplom.data.DataHandler;
import ru.netology.qadiplom.page.MainPage;
import ru.netology.qadiplom.page.PaymentPage;

import static com.codeborne.selenide.Selectors.byText;
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

    /*
    @Test
    public void shouldSendFormWithValidApprovedCard() {
        var cardInfo = DataHandler.getApprovedCard();
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard(cardInfo);
    }

//           BUG
//
//    @Test public void shouldGetErrorNotificationWithDeclinedCard() {
//        var cardInfo = DataHandler.getDeclinedCard();
//        var paymentPage = mainPage.navigateToPaymentPage();
//        paymentPage.fillFormWithDeclinedCard(cardInfo);
//    }

    @Test
    public void shouldGetErrorNotificationForUnregisteredCard() {
        var cardInfo = DataHandler.getUnregisteredCard();
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithInvalidCard(cardInfo);
    }

*/
    @Test
    public void shouldOpenCreditPage() {
        mainPage.navigateToCreditPage();
    }
}
