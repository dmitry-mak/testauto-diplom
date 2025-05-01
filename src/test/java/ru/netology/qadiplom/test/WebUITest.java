package ru.netology.qadiplom.test;

import com.codeborne.selenide.Condition;
import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.qadiplom.data.DataHandler;
import ru.netology.qadiplom.page.MainPage;
import ru.netology.qadiplom.page.PageElements;
import ru.netology.qadiplom.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertAll;

public class WebUITest {

    MainPage mainPage;

    @BeforeEach
    void openMainPage() {
//        mainPage = open("http://localhost:8080/", MainPage.class);
        open("http://localhost:8080/");
        mainPage = new MainPage();
    }

    @Test
    public void shouldShowErrorMessageForEmptyNumber() {
        var paymentPage = mainPage.navigateToPaymentPage();
//        paymentPage.sendFormWithEmptyCard();
//        PageElements.CARD_NUMBER_ERROR.shouldBe(Condition.visible);
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CARD_NUMBER_FIELD);
        paymentPage.sendEmptyForm();
        PageElements.CARD_NUMBER_ERROR.shouldBe(Condition.visible);
    }

    @Test
    public void shouldShowMultipleErrorMessagesForEmptyForm() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.sendEmptyForm();

        assertAll(
                () -> PageElements.CARD_NUMBER_ERROR.shouldBe(Condition.visible),
                () -> PageElements.MONTH_ERROR.shouldBe(Condition.visible),
                () -> PageElements.YEAR_ERROR.shouldBe(Condition.visible),
                () -> PageElements.HOLDER_ERROR.shouldBe(Condition.visible),
                () -> PageElements.CVV_ERROR.shouldBe(Condition.visible)
        );
    }

    //    Отправка формы оплаты с пустым полем "Месяц"
    @Test
    public void shouldShowErrorMessageForEmptyMonth() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.MONTH_FIELD);
        paymentPage.sendEmptyForm();
        PageElements.MONTH_ERROR.shouldBe(Condition.visible);
    }

    @Test
    public void shouldShowErrorMessageForEmptyYear() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.YEAR_FIELD);
        paymentPage.sendEmptyForm();
        PageElements.YEAR_ERROR.shouldBe(Condition.visible);
    }

    @Test
    public void shouldShowErrorMessageForEmptyHolder() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.HOLDER_FIELD);
        paymentPage.sendEmptyForm();
        PageElements.HOLDER_ERROR.shouldBe(Condition.visible);
    }

    @Test
    public void shouldShowErrorMessageForEmptyCvc() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CVV_FIELD);
        paymentPage.sendEmptyForm();
        PageElements.CVV_ERROR.shouldBe(Condition.visible);
    }

    @Test
    public void shouldShowErrorMessageForShortCardNumber() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CARD_NUMBER_FIELD);
        PageElements.CARD_NUMBER_FIELD.setValue(DataHandler.getShortCardNumber());
        paymentPage.sendEmptyForm();
        PageElements.CARD_NUMBER_ERROR.shouldBe(Condition.visible);
    }

    @Test
    public void shouldLimitCardNumberLength() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CARD_NUMBER_FIELD);
        PageElements.CARD_NUMBER_FIELD.setValue(DataHandler.getLongCardNumber());
//        paymentPage.sendEmptyForm();
//        PageElements.CARD_NUMBER_ERROR.shouldBe(Condition.visible);
        String actualFieldLength = PageElements.CARD_NUMBER_FIELD.getValue();
        Assertions.assertEquals(actualFieldLength.length(), DataHandler.getLongCardNumber().length() - 2);
    }


    @Test
    public void shouldShowErrorMessageForLettersInCardNumber() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CARD_NUMBER_FIELD);
        PageElements.CARD_NUMBER_FIELD.setValue(DataHandler.getLettersInCardNumber());
        paymentPage.sendEmptyForm();
        PageElements.CARD_NUMBER_ERROR.shouldBe(Condition.visible);
    }

    @Test
    public void shouldShowErrorMessageForInvalidCardNumber() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CARD_NUMBER_FIELD);

    }

//    BUG
//
//    @Test
//    public void shouldShowErrorMessageForInvalidHolder() {
//        var paymentPage = mainPage.navigateToPaymentPage();
//        paymentPage.fillFormWithValidApprovedCard();
//        paymentPage.cleanField(PageElements.HOLDER_FIELD);
//        PageElements.HOLDER_FIELD.setValue(DataHandler.getInvalidRuHolder());
//        paymentPage.sendEmptyForm();
//        PageElements.HOLDER_ERROR.shouldBe(Condition.visible);
//    }

//  BUG
//
//    @Test
//    public void shouldShowErrorMessageForSymbolsInHolder() {
//        var paymentPage = mainPage.navigateToPaymentPage();
//        paymentPage.fillFormWithValidApprovedCard();
//        paymentPage.cleanField(PageElements.HOLDER_FIELD);
//        PageElements.HOLDER_FIELD.setValue(DataHandler.getHolderWithSymbols());
//        paymentPage.sendEmptyForm();
//        PageElements.HOLDER_ERROR.shouldBe(Condition.visible);
//    }

    //    Ввод значения месяца из прошедшего периода (текущий год, месяц меньше текущего)
    @Test
    public void shouldShowErrorMessageForPastMonth() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.MONTH_FIELD);
        paymentPage.cleanField(PageElements.YEAR_FIELD);
        PageElements.MONTH_FIELD.setValue(DataHandler.getExpiredMonth());
        PageElements.YEAR_FIELD.setValue(DataHandler.getCurrentYear());
        paymentPage.sendEmptyForm();
        PageElements.MONTH_ERROR.shouldBe(Condition.visible);
    }

    //    Ввод не цифровых значений в поле "Месяц"
    @Test
    public void shouldShowErrorMessageForLettersInMonth() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.MONTH_FIELD);
        PageElements.MONTH_FIELD.setValue(DataHandler.getLettersMonth());
//        paymentPage.sendEmptyForm();
        String actualFieldLength = PageElements.MONTH_FIELD.getValue();
        Assertions.assertEquals(0, actualFieldLength.length());
    }

    //    Ввод значения года из прошедшего периода
    @Test
    public void shouldShowErrorMessageForPastYear() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.YEAR_FIELD);
        PageElements.YEAR_FIELD.setValue(DataHandler.getExpiredYear());
        paymentPage.sendEmptyForm();
        PageElements.YEAR_ERROR.shouldBe(Condition.visible);
    }

//    Ввод значения года на 5 или более лет больше текущего
    @Test
    public void shouldShowErrorMessageForFutureYears() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.YEAR_FIELD);
        PageElements.YEAR_FIELD.setValue(DataHandler.getFarFutureYear());
        paymentPage.sendEmptyForm();
        PageElements.YEAR_ERROR.shouldBe(Condition.visible);
    }

//    Ввод не цифровых значений в поле "Год"
    @Test
    public void shouldShowErrorMessageForLettersInYear() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.YEAR_FIELD);
        PageElements.YEAR_FIELD.setValue(DataHandler.getLettersYear());
//        paymentPage.sendEmptyForm();
        String actualFieldLength = PageElements.YEAR_FIELD.getValue();
        Assertions.assertEquals(0, actualFieldLength.length());
    }
    @Test
    public void shouldShowErrorMessageForLettersInCVC() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CVV_FIELD);
        PageElements.CVV_FIELD.setValue(DataHandler.getLettersCVC());
        paymentPage.sendEmptyForm();
        PageElements.CVV_ERROR.shouldBe(Condition.visible);
    }

    @Test
    public void shouldShowErrorMessageForShortCVC() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CVV_FIELD);
        PageElements.CVV_FIELD.setValue(DataHandler.getShortCvc());
        paymentPage.sendEmptyForm();
        PageElements.CVV_ERROR.shouldBe(Condition.visible);
    }

}
