package dev.frerot.userverse.utils;

import com.github.javafaker.Faker;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@Data
public class RandomUserGenerator {

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String birthdate;
    private String phone;
    private String country;
    private boolean active;
    private LocalDate created_at;
    private LocalDate updated_at;

    // Default constructor
    public RandomUserGenerator() {
        Faker faker = new Faker();
        this.firstname = faker.name().firstName();
        this.lastname = faker.name().lastName();
        this.email = faker.internet().emailAddress();
        this.username = generateRandomUsername();
        this.password = generateRandomPassword();
        this.birthdate = generateRandomBirthdate();
        this.phone = faker.phoneNumber().phoneNumber();
        this.country = generateRandomCountry();
        this.active = faker.random().nextBoolean();
        this.created_at = generateRandomDate();
        this.updated_at = generateRandomUpdatedDate(created_at);
    }

    // Static methods for generating random data
    public static String generateRandomUsername() {
        return new Faker().name().username();
    }

    public static String generateRandomPassword() {
        return new Faker().internet().password();
    }

    public static String generateRandomBirthdate() {
        int age = ThreadLocalRandom.current().nextInt(18, 66);
        return getBirthdateFromAge(age);
    }

    private static String getBirthdateFromAge(int age) {
        int currentYear = LocalDate.now().getYear();
        int birthYear = currentYear - age;
        int month = ThreadLocalRandom.current().nextInt(1, 13);
        int day = ThreadLocalRandom.current().nextInt(1, 29);  // Random day between 1 and 28
        LocalDate birthDate = LocalDate.of(birthYear, month, day);
        return birthDate.format(DateTimeFormatter.ISO_DATE);
    }

    public static String generateRandomCountry() {
        return new Faker().address().country().toUpperCase();
    }

    public static LocalDate generateRandomDate() {
        return LocalDate.now();
    }

    private LocalDate generateRandomUpdatedDate(LocalDate createdAt) {
        return createdAt.plusDays(ThreadLocalRandom.current().nextInt(1, 6));
    }
}
