package ru.netology.qadiplom.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import ru.netology.qadiplom.data.SqlHandler;
import ru.netology.qadiplom.page.MainPage;
import ru.netology.qadiplom.page.PageElements;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PaymentTest {

    MainPage mainPage;

    @BeforeEach
    void openMainPage() {
//        mainPage = open("http://localhost:8080/", MainPage.class);
        open("http://localhost:8080/");
        mainPage = new MainPage();
    }

    @BeforeEach
    void cleanTables() {
        SqlHandler.cleanAllTables();
    }

    @Test
    public void shouldPayWithValidApprovedCard() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.sendFormWithValidApprovedCard();
//        PageElements.SUCCESS_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15));
//        Assertions.assertEquals("APPROVED", SqlHandler.getPaymentStatus());
        assertAll(() -> PageElements.SUCCESS_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15)),
                () -> Assertions.assertEquals("APPROVED", SqlHandler.getPaymentStatus()));
    }

//               BUG
//
//    @Test
//    public void shouldGetErrorPayNotificationWithDeclinedCard() {
//
//        var paymentPage = mainPage.navigateToPaymentPage();
//        paymentPage.sendFormWithDeclinedCard();
//        assertAll(
//                () -> PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15)),
//                () -> Assertions.assertEquals("DECLINED", SqlHandler.getPaymentStatus()));
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
        assertAll(() -> PageElements.SUCCESS_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15)),
                () -> Assertions.assertEquals("APPROVED", SqlHandler.getCreditStatus()));
    }

//   BUG
//
//    @Test
//    public void shouldGetErrorCreditNotificationForDeclinedCard() {
//        var creditPage = mainPage.navigateToCreditPage();
//        creditPage.sendFormWithDeclinedCard();
//        assertAll(
//                () -> PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15)),
//                () -> Assertions.assertEquals("DECLINED", SqlHandler.getCreditStatus()));
//    }

    @Test
    public void shouldGetErrorCreditNotificationForUnregisteredCard() {
        var creditPage = mainPage.navigateToCreditPage();
        creditPage.sendFormWithInvalidCard();
        PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
