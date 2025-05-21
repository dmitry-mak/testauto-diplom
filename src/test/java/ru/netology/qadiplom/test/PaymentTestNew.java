package ru.netology.qadiplom.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.qadiplom.data.SqlHandler;
//import ru.netology.qadiplom.page.MainPage;
import ru.netology.qadiplom.page.MainPageNew;
import ru.netology.qadiplom.page.PageElements;
import ru.netology.qadiplom.page.TransferPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PaymentTestNew {

    MainPageNew mainPage;

    @BeforeEach
    void openMainPage() {
        open("http://localhost:8080/");
        mainPage = new MainPageNew();
    }

    @BeforeEach
    void cleanTables() {
        SqlHandler.cleanAllTables();
    }

    @Test
    public void shouldPayWithValidApprovedCard() {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.sendFormWithValidApprovedCard();
        assertAll(() -> PageElements.SUCCESS_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15)),
                () -> Assertions.assertEquals("APPROVED", SqlHandler.getPaymentStatus()));
    }

//    @Test
//    public void shouldGetErrorPayNotificationWithDeclinedCard() {
//
//        TransferPage paymentPage = mainPage.navigateToPaymentPage();
//        paymentPage.sendFormWithDeclinedCard();
//        assertAll(
//                () -> PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15)),
//                () -> Assertions.assertEquals("DECLINED", SqlHandler.getPaymentStatus()));
//    }

    @Test
    public void shouldGetErrorPayNotificationForUnregisteredCard() {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.sendFormWithInvalidCard();
        PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    public void shouldCreditWithValidApprovedCard() {
        TransferPage creditPage = mainPage.navigateToCreditPage();
        creditPage.sendFormWithValidApprovedCard();
        assertAll(() -> PageElements.SUCCESS_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15)),
                () -> Assertions.assertEquals("APPROVED", SqlHandler.getCreditStatus()));
    }

//    @Test
//    public void shouldGetErrorCreditNotificationForDeclinedCard() {
//        TransferPage creditPage = mainPage.navigateToCreditPage();
//        creditPage.sendFormWithDeclinedCard();
//        assertAll(
//                () -> PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15)),
//                () -> Assertions.assertEquals("DECLINED", SqlHandler.getCreditStatus()));
//    }

    @Test
    public void shouldGetErrorCreditNotificationForUnregisteredCard() {
        TransferPage creditPage = mainPage.navigateToCreditPage();
        creditPage.sendFormWithInvalidCard();
        PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
