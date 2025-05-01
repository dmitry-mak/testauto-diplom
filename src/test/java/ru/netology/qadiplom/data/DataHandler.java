package ru.netology.qadiplom.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHandler {

    private static final Faker faker = new Faker();
    private static final Faker ruFaker = new Faker(new Locale("ru"));

    private DataHandler() {
    }

    public static CardInfo getApprovedCard() {

        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidEnHolder(), getCvc());
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidEnHolder(), getCvc());
    }

    public static CardInfo getUnregisteredCard() {
        return new CardInfo(getUnregisteredCardNumber(), getValidMonth(), getValidYear(), getValidEnHolder(), getCvc());
    }

    public static CardInfo getFormWithEmptyNumber() {
        return new CardInfo("", getValidMonth(), getValidYear(), getValidEnHolder(), getCvc());
    }

    public static CardInfo getFormWithEmptyMonth() {
        return new CardInfo(getApprovedCardNumber(), "", getValidYear(), getValidEnHolder(), getCvc());
    }

    public static CardInfo getFormWithEmptyYear() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "", getValidEnHolder(), getCvc());
    }

    public static CardInfo getFormWithEmptyHolder() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "", getCvc());
    }

    public static CardInfo getFormWithEmptyCvc() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidEnHolder(), "");
    }

    private static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    private static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    private static String getUnregisteredCardNumber() {
//        return faker.numerify("#### #### #### ####");
        return String.valueOf(faker.numerify("#### #### #### ####"));
    }

    public static String getShortCardNumber() {
        return String.valueOf(faker.numerify("#### #### #### ###"));
    }

    public static String getLongCardNumber() {
        return String.valueOf(faker.numerify("#### #### #### #### #"));
    }

    public static String getLettersInCardNumber() {
        return String.valueOf(faker.letterify("#### #### #### ####"));
    }


    private static String getValidEnHolder() {
        return faker.name().fullName();
    }

    public static String getInvalidRuHolder() {
        return ruFaker.name().fullName();
    }

    public static String getHolderWithSymbols() {
        String[] symbols = {"_", ".", "!", "@", "#", "$", "%", "^", "&", "*", "+"};
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String symbol = symbols[new java.util.Random().nextInt(symbols.length)];
        return firstName + symbol + lastName;
    }

    private static String getValidYear() {
        return LocalDate.now().plusYears(2).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getExpiredYear() {
        return LocalDate.now().minusYears(2).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getLettersYear() {
        return String.valueOf(faker.letterify("##"));
    }

    public static String getFarFutureYear() {
        return String.valueOf(LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy")));
    }

    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String getValidMonth() {
        return LocalDate.now().plusMonths(2).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getExpiredMonth() {
        return LocalDate.now().minusMonths(2).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getLettersMonth() {
        return String.valueOf(faker.letterify("##"));
    }


    private static String getCvc() {
//        return faker.number().digits(3);
//        return faker.numerify("###");
        return String.valueOf(faker.numerify("###"));
    }

    public static String getLettersCVC() {

        return String.valueOf(faker.letterify("###"));
    }

    public static String getShortCvc() {
        return String.valueOf(faker.numerify("##"));
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CardInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }


}
