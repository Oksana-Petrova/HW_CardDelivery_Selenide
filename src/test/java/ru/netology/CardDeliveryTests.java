package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTests {

    @Test
    void shouldOrderCardDeliverySuccessful1() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String meetingDate = LocalDate.now().plusDays(3).format(formatter);

        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(meetingDate);
        $("[data-test-id=name] input").setValue("Матвеев Максим");
        $("[data-test-id=phone] input").setValue("+79171478956");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();

        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Успешно!"));
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Встреча успешно забронирована на " + meetingDate));
    }

    @Test
    void shouldOrderCardDeliverySuccessful2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String meetingDate = LocalDate.now().plusDays(4).format(formatter);

        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(meetingDate);
        $("[data-test-id=name] input").setValue("Матвеев Максим");
        $("[data-test-id=phone] input").setValue("+79171478956");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();

        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Успешно!"));
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Встреча успешно забронирована на " + meetingDate));
    }

    @Test
    void shouldOrderCardDeliveryInvalid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String meetingDate = LocalDate.now().plusDays(2).format(formatter);

        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(meetingDate);
        $("[data-test-id=name] input").setValue("Иванов Петр Семёнович");
        $("[data-test-id=phone] input").setValue("+79171235689");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();

        $("[data-test-id=date] .input_invalid").shouldBe(visible).shouldHave(text("Заказ на выбранную дату невозможен"));
    }
}
