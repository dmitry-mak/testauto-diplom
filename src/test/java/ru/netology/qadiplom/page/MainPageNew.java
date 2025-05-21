package ru.netology.qadiplom.page;

public class MainPageNew {

    public TransferPage navigateToPaymentPage() {
        PageElements.BUY_BUTTON.click();
        return new TransferPage(TransferPage.TransactionType.PAYMENT);
    }

    public TransferPage navigateToCreditPage() {
        PageElements.CREDIT_BUTTON.click();
        return new TransferPage(TransferPage.TransactionType.CREDIT);
    }
}
