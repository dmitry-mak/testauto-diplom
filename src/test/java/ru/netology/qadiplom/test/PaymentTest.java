package ru.netology.qadiplom.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.qadiplom.data.DataHandler;
import ru.netology.qadiplom.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class PaymentTest {

    MainPage mainPage;

    @BeforeEach
    void openMainPage() {
//        mainPage = open("http://localhost:8080/", MainPage.class);
        open("http://localhost:8080/");
        mainPage = new MainPage();
    }
    @Test
    public void shouldPayWithValidApprovedCard() {
        var cardInfo = DataHandler.getApprovedCard();
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.sendFormWithValidApprovedCard();
    }

    //           BUG
//
//    @Test public void shouldGetErrorNotificationWithDeclinedCard() {
//
//        var paymentPage = mainPage.navigateToPaymentPage();
//        paymentPage.sendFormWithDeclinedCard();
//    }

    @Test
    public void shouldGetErrorNotificationForUnregisteredCard() {
        var cardInfo = DataHandler.getUnregisteredCard();
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.sendFormWithInvalidCard();
    }

    @Test
    public void shouldCreditWithValidApprovedCard(){
        var cardInfo = DataHandler.getApprovedCard();
        var creditPage = mainPage.navigateToCreditPage();
        creditPage.sendFormWithValidApprovedCard();
    }

}
