package ru.netology.qadiplom.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.qadiplom.data.DataHandler;
import ru.netology.qadiplom.data.SqlHandler;
import ru.netology.qadiplom.page.MainPage;
import ru.netology.qadiplom.page.PageElements;


import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertAll;

public class WebUITest {

    MainPage mainPage;

    @BeforeEach
    void openMainPage() {
        open("http://localhost:8080/");
        mainPage = new MainPage();
    }

    @AfterAll
    public static void cleanAllTables(){
        SqlHandler.cleanAllTables();
    }

    @Test
    public void shouldShowMultipleErrorMessagesForEmptyForm() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.sendEmptyForm();

        assertAll(() -> PageElements.CARD_NUMBER_ERROR.shouldBe(Condition.visible),
                () -> PageElements.MONTH_ERROR.shouldBe(Condition.visible),
                () -> PageElements.YEAR_ERROR.shouldBe(Condition.visible),
                () -> PageElements.HOLDER_ERROR.shouldBe(Condition.visible),
                () -> PageElements.CVV_ERROR.shouldBe(Condition.visible));
    }

    //  Отправка формы оплаты с пустым полем "Номер карты"
    @Test
    public void shouldShowErrorMessageForEmptyNumber() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CARD_NUMBER_FIELD);
        paymentPage.sendEmptyForm();
        PageElements.CARD_NUMBER_ERROR.shouldBe(Condition.visible);
    }

    //   Ввод менее 16 символов в поле "Номер карты"
    @Test
    public void shouldShowErrorMessageForShortCardNumber() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CARD_NUMBER_FIELD);
        PageElements.CARD_NUMBER_FIELD.setValue(DataHandler.getShortCardNumber());
        paymentPage.sendEmptyForm();
        PageElements.CARD_NUMBER_ERROR.shouldBe(Condition.visible);
    }

    //    Ввод более 16 символов в поле "Номер карты"
    @Test
    public void shouldLimitCardNumberLength() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CARD_NUMBER_FIELD);
        PageElements.CARD_NUMBER_FIELD.setValue(DataHandler.getLongCardNumber());
        String actualFieldLength = PageElements.CARD_NUMBER_FIELD.getValue();
        Assertions.assertEquals(actualFieldLength.length(), DataHandler.getLongCardNumber().length() - 2);
    }

    //    Ввод символов или букв в поле "Номер карты"
    @Test
    public void shouldShowErrorMessageForLettersInCardNumber() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CARD_NUMBER_FIELD);
        PageElements.CARD_NUMBER_FIELD.setValue(DataHandler.getLettersInCardNumber());
        paymentPage.sendEmptyForm();
        PageElements.CARD_NUMBER_ERROR.shouldBe(Condition.visible);
    }

    //    Отправка формы с полем "Номер карты", заполненным номером незарегистрированной карты
    @Test
    public void shouldShowErrorMessageForInvalidCardNumber() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CARD_NUMBER_FIELD);
        PageElements.CARD_NUMBER_FIELD.setValue(DataHandler.getUnregisteredCard().getNumber());
        paymentPage.sendEmptyForm();
        PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15));
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
        String actualFieldLength = PageElements.MONTH_FIELD.getValue();
        Assertions.assertEquals(0, actualFieldLength.length());
    }

    //    Ввод значения больше 12 в поле "Месяц"
    @Test
    public void shouldShowErrorMessageForInvalidMonth() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.MONTH_FIELD);
        PageElements.MONTH_FIELD.setValue(DataHandler.getInvalidMonth());
        paymentPage.sendEmptyForm();
        PageElements.MONTH_ERROR.shouldBe(Condition.visible);
    }

//    //    Ввод значения меньше 1 в поле "Месяц"
//    @Test
//    public void shouldShowErrorMessageForMonthBelowOne() {
//        var paymentPage = mainPage.navigateToPaymentPage();
//        paymentPage.fillFormWithValidApprovedCard();
//        paymentPage.cleanField(PageElements.MONTH_FIELD);
//        PageElements.MONTH_FIELD.setValue(DataHandler.getShortMonth());
//        paymentPage.sendEmptyForm();
//        PageElements.MONTH_ERROR.shouldBe(Condition.visible);
//    }

    //    Отправка формы оплаты с пустым полем "Год"
    @Test
    public void shouldShowErrorMessageForEmptyYear() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.YEAR_FIELD);
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
        String actualFieldLength = PageElements.YEAR_FIELD.getValue();
        Assertions.assertEquals(0, actualFieldLength.length());
    }

    //    Ввод в поле "Год" менее 2 цифр
    @Test
    public void shouldShowErrorMessageForShortYear() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.YEAR_FIELD);
        PageElements.YEAR_FIELD.setValue(DataHandler.getShortYear());
        paymentPage.sendEmptyForm();
        PageElements.YEAR_ERROR.shouldBe(Condition.visible);
    }

    //    Ввод в поле "Год" более 2 цифр
    @Test
    public void shouldLimitYearFieldWithTwoDigits() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.YEAR_FIELD);
        PageElements.YEAR_FIELD.setValue(DataHandler.getLongYear());
        String actualFieldLength = PageElements.YEAR_FIELD.getValue();
        Assertions.assertEquals(DataHandler.getLongYear().length() - 1, actualFieldLength.length());
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

    //    Отправка формы оплаты с пустым полем "Владелец"
    @Test
    public void shouldShowErrorMessageForEmptyHolder() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.HOLDER_FIELD);
        paymentPage.sendEmptyForm();
        PageElements.HOLDER_ERROR.shouldBe(Condition.visible);
    }

//    //    Ввод в поле "Владелец" значений буквами не латинского алфавита
//    @Test
//    public void shouldShowErrorMessageForInvalidHolder() {
//        var paymentPage = mainPage.navigateToPaymentPage();
//        paymentPage.fillFormWithValidApprovedCard();
//        paymentPage.cleanField(PageElements.HOLDER_FIELD);
//        PageElements.HOLDER_FIELD.setValue(DataHandler.getInvalidRuHolder());
//        paymentPage.sendEmptyForm();
//        PageElements.HOLDER_ERROR.shouldBe(Condition.visible);
//    }
//
//    //    Ввод в поле "Владелец" значения с использованием специальных символов, исключая дефис
//    @Test
//    public void shouldShowErrorMessageForSymbolsInHolder() {
//        var paymentPage = mainPage.navigateToPaymentPage();
//        paymentPage.fillFormWithValidApprovedCard();
//        paymentPage.cleanField(PageElements.HOLDER_FIELD);
//        PageElements.HOLDER_FIELD.setValue(DataHandler.getHolderWithSymbols());
//        paymentPage.sendEmptyForm();
//        PageElements.HOLDER_ERROR.shouldBe(Condition.visible);
//    }

    //    Ввод в поле "Владелец" двойной фамилии через дефис
    @Test
    public void shouldProcessValidHolderWithHyphen() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.HOLDER_FIELD);
        PageElements.HOLDER_FIELD.setValue(DataHandler.getValidHolderWithHyphen());
        paymentPage.sendEmptyForm();
        PageElements.SUCCESS_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

//    //    Ввод в поле "Владелец" значения длиннее максимально допустимого (20+)
//    @Test
//    public void shouldLimitLongHolder() {
//        var paymentPage = mainPage.navigateToPaymentPage();
//        paymentPage.fillFormWithValidApprovedCard();
//        paymentPage.cleanField(PageElements.HOLDER_FIELD);
//        PageElements.HOLDER_FIELD.setValue(DataHandler.getLongHolder());
//        int actualFieldLength = PageElements.HOLDER_FIELD.getValue().length();
//        Assertions.assertEquals(DataHandler.getLongHolder().length() - 1, actualFieldLength);
//    }

    //     Ввод в поле "Владелец" значения менее 2 букв
    @Test
    public void shouldShowErrorMessageForShotHolder() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.HOLDER_FIELD);
        PageElements.HOLDER_FIELD.setValue(DataHandler.getShortHolder());
        paymentPage.sendEmptyForm();
        PageElements.HOLDER_FIELD.shouldBe(Condition.visible);
    }

    //    Отправка формы оплаты с пустым полем "CVC/CVV"
    @Test
    public void shouldShowErrorMessageForEmptyCvc() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CVV_FIELD);
        paymentPage.sendEmptyForm();
        PageElements.CVV_ERROR.shouldBe(Condition.visible);
    }

    //    Ввод не цифровых значений в поле "CVC/CVV"
    @Test
    public void shouldShowErrorMessageForLettersInCVC() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CVV_FIELD);
        PageElements.CVV_FIELD.setValue(DataHandler.getLettersCVC());
        paymentPage.sendEmptyForm();
        PageElements.CVV_ERROR.shouldBe(Condition.visible);
    }

    //    Ввод в поле "CVC/CVV" значения более 3 цифр
    @Test
    public void shouldLimitLongCVC() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CVV_FIELD);
        PageElements.CVV_FIELD.setValue(DataHandler.getLongCvc());
        String actualFieldLength = PageElements.CVV_FIELD.getValue();
        Assertions.assertEquals(actualFieldLength.length(), DataHandler.getLongCvc().length() - 1);
    }

    //    Ввод в поле "CVC/CVV" значения менее 3 цифр
    @Test
    public void shouldShowErrorMessageForShortCVC() {
        var paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CVV_FIELD);
        PageElements.CVV_FIELD.setValue(DataHandler.getShortCvc());
        paymentPage.sendEmptyForm();
        PageElements.CVV_ERROR.shouldBe(Condition.visible);
    }

//    @Test
//    public void shouldShowOnlyErrorNotificationForDeclinedCard() {
//        var paymentPage = mainPage.navigateToPaymentPage();
//        paymentPage.fillFormWithValidApprovedCard();
//        paymentPage.cleanField(PageElements.CARD_NUMBER_FIELD);
//        PageElements.CARD_NUMBER_FIELD.setValue(DataHandler.getUnregisteredCard().getNumber());
//        paymentPage.sendEmptyForm();
//        PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15));
//        PageElements.CLOSE_NOTIFICATION_BUTTON.click();
//        PageElements.SUCCESS_NOTIFICATION.shouldNot(Condition.visible);
//    }
}
