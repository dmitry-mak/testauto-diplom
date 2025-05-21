package ru.netology.qadiplom.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PageElements {

    public static final SelenideElement HOLDER_FIELD = $(byText("Владелец")).parent().$(".input__control");
    public static final SelenideElement CARD_NUMBER_FIELD = $(".input__control[placeholder='0000 0000 0000 0000']");
    public static final SelenideElement MONTH_FIELD = $(".input__control[placeholder='08']");
    public static final SelenideElement YEAR_FIELD = $(".input__control[placeholder='22']");
    public static final SelenideElement CVV_FIELD = $(".input__control[placeholder='999']");

    public static final SelenideElement BUY_BUTTON = $(".button:not(.button_view_extra)").shouldBe(Condition.visible);
    public static final SelenideElement CREDIT_BUTTON = $(".button_view_extra").shouldBe(Condition.visible);
    public static final SelenideElement CONTINUE_BUTTON = $$(".button").findBy(text("Продолжить"));

    public static final SelenideElement SUCCESS_NOTIFICATION = $(".notification.notification_status_ok");
    public static final SelenideElement ERROR_NOTIFICATION = $(".notification.notification_status_error");
    public static final SelenideElement CLOSE_NOTIFICATION_BUTTON = $(".notification_status_error .notification__closer");

    public static final SelenideElement HEADER_CREDIT = $(byText("Кредит по данным карты"));
    public static final SelenideElement HEADER_PAYMENT = $(byText("Оплата по карте"));
    public static final SelenideElement HEADER_MAINPAGE = $$(".heading").findBy(text("Путешествие дня"));

    public static final SelenideElement CARD_NUMBER_ERROR = $$(".input_invalid").findBy(text("Номер карты")).$(".input__sub");
    public static final SelenideElement MONTH_ERROR = $$(".input_invalid").findBy(text("Месяц")).$(".input__sub");
    public static final SelenideElement YEAR_ERROR = $$(".input_invalid").findBy(text("Год")).$(".input__sub");
    public static final SelenideElement HOLDER_ERROR = $$(".input_invalid").findBy(text("Владелец")).$(".input__sub");
    public static final SelenideElement CVV_ERROR = $$(".input_invalid").findBy(text("CVC/CVV")).$(".input__sub");


    public PageElements() {
    }
}
