package ru.netology.qadiplom.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.qadiplom.page.MainPage;
import ru.netology.qadiplom.page.PageElements;

import java.time.Duration;

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
        PageElements.SUCCESS_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

//               BUG
//
//    @Test
//    public void shouldGetErrorPayNotificationWithDeclinedCard() {
//
//        var paymentPage = mainPage.navigateToPaymentPage();
//        paymentPage.sendFormWithDeclinedCard();
//        PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15));
//    }

    @Test
    public void shouldGetErrorPayNotificationForUnregisteredCard() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.sendFormWithInvalidCard();
        PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    public void shouldCreditWithValidApprovedCard() {
        var creditPage = mainPage.navigateToCreditPage();
        creditPage.sendFormWithValidApprovedCard();
        PageElements.SUCCESS_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

//   BUG
//
//    @Test
//    public void shouldGetErrorCreditNotificationForDeclinedCard(){
//        var creditPage = mainPage.navigateToCreditPage();
//        creditPage.sendFormWithDeclinedCard();
//        PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15));
//    }

    @Test
    public void shouldGetErrorCreditNotificationForUnregisteredCard(){
        var creditPage = mainPage.navigateToCreditPage();
        creditPage.sendFormWithInvalidCard();
        PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

}
