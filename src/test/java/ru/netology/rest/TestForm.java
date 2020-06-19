package ru.netology.rest;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("Test successful")
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

    @DisplayName("Test successful")
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

    @DisplayName("Test successful")
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

    @DisplayName("Tests failed")
    @ParameterizedTest
    @ValueSource(strings = {"москва", "Рязань"})
    void shouldNotRegistrationIfCitySmallLatterNotFromTheList(String city) {
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

    @DisplayName("Tests successful")
    @ParameterizedTest
    @ValueSource(strings = {"Moskva", "@#$%^&"})
    void shouldNotRegistrationIfCitySpecialSymbolsAndLatinLetters(String city) {
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

    @DisplayName("Single test successful")
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

    @DisplayName("Tests successful")
    @ParameterizedTest
    @ValueSource(strings = {"", "rttyyuuiio"})
    void shouldNotRegistrationIfDataEmptyDataLatter() {
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

    @DisplayName("Single test successful")
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

    @DisplayName("Tests failed")
    @ParameterizedTest
    @ValueSource(strings = {"@#$%%^^&&", "4567884432", "Ivanova Anna", "Иванова-Петрова Анна-Мария"})
    void shouldNotRegistrationIfNameSpecialSymbolsFiguresLatinLatterDoubleName(String name) {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys(name);
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(Condition.visible, 15000);
    }

    @DisplayName("Test successful")
    @ParameterizedTest
    @ValueSource(strings = {"иванова анна", "ИВАНОВА АННА", "И", "Ивааааааааааааааааааааааанова Анннннннннннннннннннннннннннннна"})
    void shouldNotRegistrationIfNameSmallLatterCapsLockMoreLatter1Latter(String name) {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys(name);
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(Condition.visible, 15000);
    }

    @DisplayName("Single test successful")
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

    @DisplayName("Tests successful")
    @ParameterizedTest
    @ValueSource(strings = {"79109876543", "лорпимакк", "+7987654", "+791098765454321"})
    void shouldNotRegistrationIfPhoneNoPlusPhoneLatter7Figures12Figures(String phone) {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
        form.$(cssSelector("[name=phone]")).sendKeys(phone);
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).waitUntil(Condition.visible, 15000);
    }
}
    
