package ru.netology.qadiplom.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.qadiplom.data.DataHandler;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {

    //    private final SelenideElement cardNumberField = $(".input__control").shouldHave(Condition.attribute("placeholder", "0000 0000 0000 0000"));
    private final SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    //    private final SelenideElement monthField = $(".input__control").shouldHave(Condition.attribute("placeholder", "08"));
    //    private final SelenideElement monthField = $("input[placeholder='08']");
    private final SelenideElement monthField = $(byText("Месяц")).parent().$(".input__control");
    //    private final SelenideElement yearField = $("input[placeholder='22']");
    private final SelenideElement yearField = $(byText("Год")).parent().$(".input__control");
    private final SelenideElement holderField = $(byText("Владелец")).parent().$(".input__control");
    //    private final SelenideElement cvvField = $("input[placeholder='999']");
    private final SelenideElement cvvField = $(byText("CVC/CVV")).parent().$(".input__control");
    private final SelenideElement headerPayment = $(byText("Оплата по карте"));
    private final SelenideElement buttonContinue = $$(".button").get(2).shouldHave(Condition.text("Продолжить"));
    //    private final SelenideElement buttonContinue =  $(byText("Продолжить")).shouldBe(Condition.visible);
    private final SelenideElement successNotification = $(".notification.notification_status_ok");
    private final SelenideElement errorNotification = $(".notification.notification_status_error");

    private final SelenideElement invalidCardNumber = $("");

//    private final PageElements pageElement = new PageElements();

    public PaymentPage() {
//        headerPayment.shouldBe(Condition.visible);
        PageElements.HEADER_PAYMENT.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void sendFormWithValidApprovedCard() {
        fillFormWithValidApprovedCard(DataHandler.getApprovedCard());
//        buttonContinue.click();
        PageElements.CONTINUE_BUTTON.click();
//        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(10));
//        PageElements.SUCCESS_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void sendFormWithInvalidCard() {
        fillFormWithValidApprovedCard(DataHandler.getApprovedCard());
//        cardNumberField.contextClick().sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
//        cleanField(cardNumberField);
        cleanField(PageElements.CARD_NUMBER_FIELD);
//        cardNumberField.setValue(DataHandler.getUnregisteredCard().getNumber());
        PageElements.CARD_NUMBER_FIELD.setValue(DataHandler.getUnregisteredCard().getNumber());
//        buttonContinue.click();
        PageElements.CONTINUE_BUTTON.click();
//        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(10));
//        PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void sendFormWithDeclinedCard() {
        fillFormWithValidApprovedCard(DataHandler.getApprovedCard());
//        cardNumberField.contextClick().sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
//        cleanField(cardNumberField);
        cleanField(PageElements.CARD_NUMBER_FIELD);
//        cardNumberField.setValue(DataHandler.getDeclinedCard().getNumber());
        PageElements.CARD_NUMBER_FIELD.setValue(DataHandler.getDeclinedCard().getNumber());
//        buttonContinue.click();
        PageElements.CONTINUE_BUTTON.click();
//        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(10));
//        PageElements.ERROR_NOTIFICATION.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

//    Заполнение полей формы данными объекта CardInfo
    public void fillFormWithValidApprovedCard(DataHandler.CardInfo cardInfo) {
//        cardNumberField.setValue(cardInfo.getNumber());
        PageElements.CARD_NUMBER_FIELD.setValue(cardInfo.getNumber());
//        monthField.setValue(cardInfo.getMonth());
        PageElements.MONTH_FIELD.setValue(cardInfo.getMonth());
//        yearField.setValue(cardInfo.getYear());
        PageElements.YEAR_FIELD.setValue(cardInfo.getYear());
//        holderField.setValue(cardInfo.getHolder());
        PageElements.HOLDER_FIELD.setValue(cardInfo.getHolder());
//        cvvField.setValue(cardInfo.getCvc());
        PageElements.CVV_FIELD.setValue(cardInfo.getCvc());
//        buttonContinue.click();
//        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

//    перегруженный метод без параметров. Удобней использовать при тестировании интерфейса и полей
    public void fillFormWithValidApprovedCard(){
        PageElements.CARD_NUMBER_FIELD.setValue(DataHandler.getApprovedCard().getNumber());
        PageElements.MONTH_FIELD.setValue(DataHandler.getApprovedCard().getMonth());
        PageElements.YEAR_FIELD.setValue(DataHandler.getApprovedCard().getYear());
        PageElements.HOLDER_FIELD.setValue(DataHandler.getApprovedCard().getHolder());
        PageElements.CVV_FIELD.setValue(DataHandler.getApprovedCard().getCvc());
    }


    public void sendEmptyForm() {
        PageElements.CONTINUE_BUTTON.click();
    }
    public void cleanField(SelenideElement field) {
        field.contextClick().sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }
}
