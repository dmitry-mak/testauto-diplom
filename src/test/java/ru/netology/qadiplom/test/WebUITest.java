package ru.netology.qadiplom.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.qadiplom.data.DataHandler;
import ru.netology.qadiplom.data.SqlHandler;
import ru.netology.qadiplom.page.MainPage;
import ru.netology.qadiplom.page.PageElements;
import ru.netology.qadiplom.page.TransferPage;

import java.time.Duration;
import java.util.stream.Stream;

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
    public static void cleanAllTables() {
        SqlHandler.cleanAllTables();
    }

    @Test
    @DisplayName("Multiple error notification for the empty payment form")
    public void shouldShowMultipleErrorMessagesForEmptyForm() {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.sendForm();

        assertAll(() -> PageElements.CARD_NUMBER_ERROR.shouldBe(Condition.visible),
                () -> PageElements.MONTH_ERROR.shouldBe(Condition.visible),
                () -> PageElements.YEAR_ERROR.shouldBe(Condition.visible),
                () -> PageElements.HOLDER_ERROR.shouldBe(Condition.visible),
                () -> PageElements.CVV_ERROR.shouldBe(Condition.visible));
    }

    //    Ввод более 16 символов в поле "Номер карты"
    @Test
    @DisplayName("'Card number' field limit - 16 digits")
    public void shouldLimitCardNumberLength() {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CARD_NUMBER_FIELD);
        PageElements.CARD_NUMBER_FIELD.setValue(DataHandler.getLongCardNumber());
        String actualFieldLength = PageElements.CARD_NUMBER_FIELD.getValue();
        Assertions.assertEquals(actualFieldLength.length(), DataHandler.getLongCardNumber().length() - 2);
    }

    //    Отправка формы с полем "Номер карты", заполненным номером незарегистрированной карты
    @Test
    @DisplayName("Should get error notification for unregistered card")
    public void shouldShowErrorMessageForInvalidCardNumber() {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CARD_NUMBER_FIELD);
        PageElements.CARD_NUMBER_FIELD.setValue(DataHandler.getUnregisteredCard().getNumber());
        paymentPage.sendForm();
        PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    private static Stream<Arguments> negativeCardNumberTestCases() {
        return Stream.of(
                Arguments.arguments("Empty card number field", ""),
                Arguments.arguments("Short card number - less 16 digits", DataHandler.getShortCardNumber()),
                Arguments.arguments("Letters in card number", DataHandler.getLettersInCardNumber())
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("negativeCardNumberTestCases")
    @DisplayName("Should show error message for invalid card number")
    public void shouldShowErrorMessageForInvalidCardNumber(String testCaseName, String cardNumber) {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CARD_NUMBER_FIELD);
        PageElements.CARD_NUMBER_FIELD.setValue(cardNumber);
        paymentPage.sendForm();
        PageElements.CARD_NUMBER_ERROR.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    //    Ввод значения месяца из прошедшего периода (текущий год, месяц меньше текущего)
    @Test
    @DisplayName("Error notification for past month (current year)")
    public void shouldShowErrorMessageForPastMonth() {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.MONTH_FIELD);
        paymentPage.cleanField(PageElements.YEAR_FIELD);
        PageElements.MONTH_FIELD.setValue(DataHandler.getExpiredMonth());
        PageElements.YEAR_FIELD.setValue(DataHandler.getCurrentYear());
        paymentPage.sendForm();
        PageElements.MONTH_ERROR.shouldBe(Condition.visible);
    }

    //    Ввод не цифровых значений в поле "Месяц"
    //    проверяет, что поле не реагирует на ввод при попытке вводить не цифровые значения
    @Test
    @DisplayName("'Month' field accept only digits")
    public void shouldShowErrorMessageForLettersInMonth() {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.MONTH_FIELD);
        PageElements.MONTH_FIELD.setValue(DataHandler.getLettersMonth());
        String actualFieldLength = PageElements.MONTH_FIELD.getValue();
        Assertions.assertEquals(0, actualFieldLength.length());
    }

    //    Ввод значения больше 12 в поле "Месяц"
    @Test
    @DisplayName("Error notification for the month more 12")
    public void shouldShowErrorMessageForInvalidMonth() {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.MONTH_FIELD);
        PageElements.MONTH_FIELD.setValue(DataHandler.getInvalidMonth());
        paymentPage.sendForm();
        PageElements.MONTH_ERROR.shouldBe(Condition.visible);
    }

    private static Stream<Arguments> negativeMonthTestCases() {
        return Stream.of(
                Arguments.arguments("Empty month field", ""),
                Arguments.arguments("Letters in month field", DataHandler.getLettersMonth()),
                Arguments.arguments("Invalid month value bigger 12", DataHandler.getInvalidMonth())
//                Arguments.arguments("Too short month value equals 00", DataHandler.getShortMonth())
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("negativeMonthTestCases")
    @DisplayName("Should show error message for invalid month value")
    public void shouldShowErrorMessageForInvalidMonth(String testCaseName, String month) {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.MONTH_FIELD);
        PageElements.MONTH_FIELD.setValue(month);
        paymentPage.sendForm();
        PageElements.MONTH_ERROR.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    //    Ввод не цифровых значений в поле "Год"
    //    проверяет, что поле не реагирует на ввод при попытке вводить не цифровые значения
    @Test
    @DisplayName("'Year' field should accept only digits")
    public void shouldShowErrorMessageForLettersInYear() {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.YEAR_FIELD);
        PageElements.YEAR_FIELD.setValue(DataHandler.getLettersYear());
        String actualFieldLength = PageElements.YEAR_FIELD.getValue();
        Assertions.assertEquals(0, actualFieldLength.length());
    }

    //    Ввод в поле "Год" более 2 цифр
    @Test
    @DisplayName("Year should be limited by 2 digits")
    public void shouldLimitYearFieldWithTwoDigits() {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.YEAR_FIELD);
        PageElements.YEAR_FIELD.setValue(DataHandler.getLongYear());
        String actualFieldLength = PageElements.YEAR_FIELD.getValue();
        Assertions.assertEquals(DataHandler.getLongYear().length() - 1, actualFieldLength.length());
    }


    private static Stream<Arguments> negativeYearTestCases() {
        return Stream.of(
                Arguments.arguments("Empty year field", ""),
                Arguments.arguments("Short year value smaller 2 digits", DataHandler.getShortYear()),
                Arguments.arguments("Letters in month field", DataHandler.getLettersYear()),
                Arguments.arguments("Year from past period", DataHandler.getExpiredYear()),
                Arguments.arguments("Year value 5 years bigger then now", DataHandler.getFarFutureYear())
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("negativeYearTestCases")
    @DisplayName("Should show error message for invalid year value")
    public void shouldShowErrorMessageForInvalidYear(String testCaseName, String year) {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.YEAR_FIELD);
        PageElements.YEAR_FIELD.setValue(year);
        paymentPage.sendForm();
        PageElements.YEAR_ERROR.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    //    Ввод в поле "Владелец" двойной фамилии через дефис
    @Test
    @DisplayName("Should accept double surname with hyphen ")
    public void shouldProcessValidHolderWithHyphen() {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.HOLDER_FIELD);
        PageElements.HOLDER_FIELD.setValue(DataHandler.getValidHolderWithHyphen());
        paymentPage.sendForm();
        PageElements.SUCCESS_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

//    //    Ввод в поле "Владелец" значения длиннее максимально допустимого (20+)
//    @Test
//    @DisplayName("'Holder' field should be limited by 20 digits")
//    public void shouldLimitLongHolder() {
//        TransferPage paymentPage = mainPage.navigateToPaymentPage();
//        paymentPage.fillFormWithValidApprovedCard();
//        paymentPage.cleanField(PageElements.HOLDER_FIELD);
//        PageElements.HOLDER_FIELD.setValue(DataHandler.getLongHolder());
//        int actualFieldLength = PageElements.HOLDER_FIELD.getValue().length();
//        Assertions.assertEquals(DataHandler.getLongHolder().length() - 1, actualFieldLength);
//    }

    private static Stream<Arguments> negativeHolderTestCases() {
        return Stream.of(
                Arguments.arguments("Empty 'holder' field", "")
//                Arguments.arguments("Holder with non-latin alphabet", DataHandler.getInvalidRuHolder()),
//                Arguments.arguments("Holder with special symbols", DataHandler.getHolderWithSymbols()),
//                Arguments.arguments("Too short 'holder' field value less 2 characters", DataHandler.getShortHolder())
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("negativeHolderTestCases")
    @DisplayName("Should show error message for invalid holder value")
    public void shouldShowErrorMessageForInvalidHolder(String testCaseName, String holder) {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.HOLDER_FIELD);
        PageElements.HOLDER_FIELD.setValue(holder);
        paymentPage.sendForm();
        PageElements.HOLDER_ERROR.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    //    Ввод в поле "CVC/CVV" значения более 3 цифр
    @Test
    @DisplayName("'CVC' field should be limited by 3 digits")
    public void shouldLimitLongCVC() {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CVV_FIELD);
        PageElements.CVV_FIELD.setValue(DataHandler.getLongCvc());
        String actualFieldLength = PageElements.CVV_FIELD.getValue();
        Assertions.assertEquals(actualFieldLength.length(), DataHandler.getLongCvc().length() - 1);
    }

    private static Stream<Arguments> negativeCvcTestCases() {
        return Stream.of(
                Arguments.arguments("Empty 'CVC' field", ""),
                Arguments.arguments("Letters in CVC field", DataHandler.getLettersCVC()),
                Arguments.arguments("Short CVC value less 3 digits", DataHandler.getShortCvc())
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("negativeCvcTestCases")
    @DisplayName("Should show error message for invalid CVC value")
    public void shouldShowErrorMessageForInvalidCvc(String testCaseName, String cvc) {
        TransferPage paymentPage = mainPage.navigateToPaymentPage();
        paymentPage.fillFormWithValidApprovedCard();
        paymentPage.cleanField(PageElements.CVV_FIELD);
        PageElements.CVV_FIELD.setValue(cvc);
        paymentPage.sendForm();
        PageElements.CVV_ERROR.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

//    @Test
//    @DisplayName("Only error notification should be for the payment with declined card")
//    public void shouldShowOnlyErrorNotificationForDeclinedCard() {
//        TransferPage paymentPage = mainPage.navigateToPaymentPage();
//        paymentPage.fillFormWithValidApprovedCard();
//        paymentPage.cleanField(PageElements.CARD_NUMBER_FIELD);
//        PageElements.CARD_NUMBER_FIELD.setValue(DataHandler.getUnregisteredCard().getNumber());
//        paymentPage.sendForm();
//        PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(15));
//        PageElements.CLOSE_NOTIFICATION_BUTTON.click();
//        PageElements.SUCCESS_NOTIFICATION.shouldNot(Condition.visible);
//    }
}
