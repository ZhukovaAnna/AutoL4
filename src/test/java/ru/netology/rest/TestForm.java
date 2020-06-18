package ru.netology.rest;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.cssSelector;

public class TestForm {

    LocalDate data = LocalDate.now().plusDays(5);
    DateTimeFormatter DateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate now = LocalDate.now();

    @Test
    void shouldRegistrationForm() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(cssSelector(".notification__title")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldSearchFromTheList() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Ка");
        $$(".menu-item").find(exactText("Казань")).click();
        form.$(cssSelector(".input__box .icon-button")).click();
        form.$(cssSelector(".input__box .icon-button")).click();
        form.$(cssSelector(".input__box .icon-button")).click();
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(cssSelector(".notification__title")).waitUntil(Condition.visible, 15000);
    }

    @ParameterizedTest
    @ValueSource(strings = {"москва", "Moskva", "@#$%^&", "Рязань"})
    void shouldNotRegistrationIfCitySmallLatter(String city) {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys(city);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Доставка в выбранный город недоступна")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfCityEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Поле обязательно для заполнения")).waitUntil(Condition.visible, 15000);
    }

//    @Test
//    void shouldNotRegistrationIfCityLatinLetters() {
//        open("http://localhost:9999");
//        SelenideElement form = $("[action]");
//        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Moskva");
//        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
//        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
//        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
//        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
//        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
//        form.$(cssSelector("[data-test-id=agreement]")).click();
//        form.$(byText("Забронировать")).click();
//        $(byText("Доставка в выбранный город недоступна")).waitUntil(Condition.visible, 15000);
//    }

//    @Test
//    void shouldNotRegistrationIfCitySpecialSymbols() {
//        open("http://localhost:9999");
//        SelenideElement form = $("[action]");
//        form.$(cssSelector("[data-test-id=city] input")).sendKeys("@#$%^&");
//        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
//        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
//        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
//        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
//        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
//        form.$(cssSelector("[data-test-id=agreement]")).click();
//        form.$(byText("Забронировать")).click();
//        $(byText("Доставка в выбранный город недоступна")).waitUntil(Condition.visible, 15000);
//    }

//    @Test
//    void shouldNotRegistrationIfCityNotFromTheList() {
//        open("http://localhost:9999");
//        SelenideElement form = $("[action]");
//        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Рязань");
//        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
//        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
//        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
//        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
//        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
//        form.$(cssSelector("[data-test-id=agreement]")).click();
//        form.$(byText("Забронировать")).click();
//        $(byText("Доставка в выбранный город недоступна")).waitUntil(Condition.visible, 15000);
//    }

    @Test
    void shouldNotRegistrationIfDataToday() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(now.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Заказ на выбранную дату невозможен")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfDataEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Неверно введена дата")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfDataLatter() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys("rttyyuuiio");
        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Неверно введена дата")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfNameEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Поле обязательно для заполнения")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfNameSmallLatter() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("иванова анна");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfNameSpecialSymbols() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("@#$%%^^&&");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfNameFigures() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("4567884432");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfNameLatinLatter() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("Ivanova Anna");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfNameCapsLock() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("ИВАНОВА АННА");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfNameMoreLatter() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("Ивааааааааааааааааааааааанова Анннннннннннннннннннннннннннннна");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfName1Latter() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("И");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfDoubleName() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("Иванова-Петрова Анна-Мария");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(cssSelector(".notification__title")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfPhoneEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
        form.$(cssSelector("[name=phone]")).sendKeys("");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Поле обязательно для заполнения")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfPhoneNoPlus() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
        form.$(cssSelector("[name=phone]")).sendKeys("79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfPhoneLatter() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
        form.$(cssSelector("[name=phone]")).sendKeys("лорпимакк");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfPhone7Figures() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
        form.$(cssSelector("[name=phone]")).sendKeys("+7987654");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfPhone12Figures() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
        form.$(cssSelector("[name=phone]")).sendKeys("+791098765454321");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).waitUntil(Condition.visible, 15000);
    }
}
    
