package ru.netology.qadiplom.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

private final SelenideElement buyButton = $(".button:not(.button_view_extra)");
private final SelenideElement creditButton= $(".button_view_extra");
private final SelenideElement header =$(".heading");


//public void open(){
//    Selenide.open("http://localhost:8080/");
//}

public PaymentPage navigateToPaymentPage() {
    buyButton.click();
    return new PaymentPage();
}

public CreditPage navigateToCreditPage() {
    creditButton.click();
    return new CreditPage();
}

public void clickBuyButton() {
    buyButton.click();
}

}
