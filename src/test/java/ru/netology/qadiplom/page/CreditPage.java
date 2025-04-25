package ru.netology.qadiplom.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.qadiplom.data.DataHandler;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {

    private final SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    //    private final SelenideElement monthField = $(".input__control").shouldHave(Condition.attribute("placeholder", "08"));
    private final SelenideElement monthField = $("input[placeholder='08']");
    private final SelenideElement yearField = $("input[placeholder='22']");
    private final SelenideElement holderField = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvvField = $("input[placeholder='999']");
    private final SelenideElement headerCredit = $(byText("Кредит по данным карты"));
    private final SelenideElement buttonContinue = $$(".button").get(2).shouldHave(Condition.text("Продолжить")).shouldBe(Condition.visible);
    //    private final SelenideElement buttonContinue =  $(byText("Продолжить")).shouldBe(Condition.visible);
    private final SelenideElement successNotification = $(".notification.notification_status_ok");
    private final SelenideElement errorNotification = $(".notification.notification_status_error");

    public CreditPage() {
        headerCredit.shouldBe(Condition.visible);
    }

    public void sendFormWithValidApprovedCard() {
        fillFormWithValidApprovedCard(DataHandler.getApprovedCard());
        buttonContinue.click();
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void sendFormWithInvalidCard() {
        fillFormWithValidApprovedCard(DataHandler.getApprovedCard());
        cardNumberField.contextClick().sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        cardNumberField.setValue(DataHandler.getUnregisteredCard().getNumber());
        buttonContinue.click();
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void sendFormWithDeclinedCard() {
        fillFormWithValidApprovedCard(DataHandler.getApprovedCard());
        cardNumberField.contextClick().sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        cardNumberField.setValue(DataHandler.getDeclinedCard().getNumber());
        buttonContinue.click();
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void fillFormWithValidApprovedCard(DataHandler.CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        holderField.setValue(cardInfo.getHolder());
        cvvField.setValue(cardInfo.getCvc());
    }

}
