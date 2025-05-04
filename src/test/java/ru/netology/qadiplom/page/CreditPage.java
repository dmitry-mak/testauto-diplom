package ru.netology.qadiplom.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.qadiplom.data.DataHandler;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {

    //    private final SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement cardNumberField = $(byText("Номер карты")).parent().$(".input__control");
    //    private final SelenideElement monthField = $(".input__control").shouldHave(Condition.attribute("placeholder", "08"));
    private final SelenideElement monthField = $("input[placeholder='08']");
    private final SelenideElement yearField = $("input[placeholder='22']");
    private final SelenideElement holderField = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvvField = $("input[placeholder='999']");
    private final SelenideElement headerCredit = $(byText("Кредит по данным карты"));
    private final SelenideElement buttonContinue = $$(".button").get(2).shouldHave(Condition.text("Продолжить"));
    //    private final SelenideElement buttonContinue =  $(byText("Продолжить")).shouldBe(Condition.visible);
    private final SelenideElement successNotification = $(".notification.notification_status_ok");
    private final SelenideElement errorNotification = $(".notification.notification_status_error");

    public CreditPage() {
        PageElements.HEADER_CREDIT.shouldBe(Condition.visible);
    }

    public void sendFormWithValidApprovedCard() {
        fillFormWithValidApprovedCard(DataHandler.getApprovedCard());
        PageElements.CONTINUE_BUTTON.click();
    }

    public void sendFormWithInvalidCard() {
        fillFormWithValidApprovedCard(DataHandler.getApprovedCard());
        cleanField(PageElements.CARD_NUMBER_FIELD);
        PageElements.CARD_NUMBER_FIELD.setValue(DataHandler.getUnregisteredCard().getNumber());
        PageElements.CONTINUE_BUTTON.click();
    }

    public void sendFormWithDeclinedCard() {
        fillFormWithValidApprovedCard(DataHandler.getApprovedCard());
        cleanField(PageElements.CARD_NUMBER_FIELD);
        PageElements.CARD_NUMBER_FIELD.setValue(DataHandler.getDeclinedCard().getNumber());
        PageElements.CONTINUE_BUTTON.click();
    }

    public void fillFormWithValidApprovedCard(DataHandler.CardInfo cardInfo) {
        PageElements.CARD_NUMBER_FIELD.setValue(cardInfo.getNumber());
        PageElements.MONTH_FIELD.setValue(cardInfo.getMonth());
        PageElements.YEAR_FIELD.setValue(cardInfo.getYear());
        PageElements.HOLDER_FIELD.setValue(cardInfo.getHolder());
        PageElements.CVV_FIELD.setValue(cardInfo.getCvc());
    }

    public void cleanField(SelenideElement field) {
        field.contextClick().sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }
}
