package ru.netology.qadiplom.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private final SelenideElement buyButton = $(".button:not(.button_view_extra)");
    private final SelenideElement creditButton = $(".button_view_extra");
    private final SelenideElement header = $(".heading");


    public PaymentPage navigateToPaymentPage() {
        PageElements.BUY_BUTTON.click();
        return new PaymentPage();
    }

    public CreditPage navigateToCreditPage() {
        PageElements.CREDIT_BUTTON.click();
        return new CreditPage();
    }

}
