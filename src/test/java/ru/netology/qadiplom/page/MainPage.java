package ru.netology.qadiplom.page;

public class MainPage {

    public TransferPage navigateToPaymentPage() {
        PageElements.BUY_BUTTON.click();
        return new TransferPage(TransferPage.TransactionType.PAYMENT);
    }

    public TransferPage navigateToCreditPage() {
        PageElements.CREDIT_BUTTON.click();
        return new TransferPage(TransferPage.TransactionType.CREDIT);
    }
}
