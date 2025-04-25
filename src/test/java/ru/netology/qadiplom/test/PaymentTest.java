package ru.netology.qadiplom.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.sendFormWithValidApprovedCard();
    }

    //           BUG
//
//    @Test public void shouldGetErrorPayNotificationWithDeclinedCard() {
//
//        var paymentPage = mainPage.navigateToPaymentPage();
//        paymentPage.sendFormWithDeclinedCard();
//    }

    @Test
    public void shouldGetErrorPayNotificationForUnregisteredCard() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.sendFormWithInvalidCard();
    }

    @Test
    public void shouldCreditWithValidApprovedCard() {
        var creditPage = mainPage.navigateToCreditPage();
        creditPage.sendFormWithValidApprovedCard();
    }

//    BUG
//
//    @Test
//    public void shouldGetErrorCreditNotificationForDeclinedCard(){
//        var creditPage = mainPage.navigateToCreditPage();
//        creditPage.sendFormWithDeclinedCard();
//    }

    @Test
    public void shouldGetErrorCreditNotificationForUnregisteredCard(){
        var creditPage = mainPage.navigateToCreditPage();
        creditPage.sendFormWithInvalidCard();
    }

}
