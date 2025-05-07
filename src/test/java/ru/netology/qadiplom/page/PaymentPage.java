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

    public PaymentPage() {
        PageElements.HEADER_PAYMENT.shouldBe(Condition.visible, Duration.ofSeconds(10));
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

    //    Заполнение полей формы данными объекта CardInfo
    public void fillFormWithValidApprovedCard(DataHandler.CardInfo cardInfo) {
        PageElements.CARD_NUMBER_FIELD.setValue(cardInfo.getNumber());
        PageElements.MONTH_FIELD.setValue(cardInfo.getMonth());
        PageElements.YEAR_FIELD.setValue(cardInfo.getYear());
        PageElements.HOLDER_FIELD.setValue(cardInfo.getHolder());
        PageElements.CVV_FIELD.setValue(cardInfo.getCvc());
    }

    //    перегруженный метод без параметров. Удобней использовать при тестировании интерфейса и полей
    public void fillFormWithValidApprovedCard() {
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
