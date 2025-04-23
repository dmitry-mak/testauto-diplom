package ru.netology.qadiplom.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHandler {

    private static final Faker faker = new Faker();

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


    private static String getValidEnHolder() {
        return faker.name().fullName();
    }

    private static String getValidYear() {
        return LocalDate.now().plusYears(2).format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String getValidMonth() {
        return LocalDate.now().plusMonths(2).format(DateTimeFormatter.ofPattern("MM"));
    }


    private static String getCvc() {
//        return faker.number().digits(3);
//        return faker.numerify("###");
        return String.valueOf(faker.numerify("###"));
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
