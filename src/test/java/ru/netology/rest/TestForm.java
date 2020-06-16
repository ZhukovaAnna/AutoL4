package ru.netology.rest;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.module.ModuleFinder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.cssSelector;

public class TestForm {

    LocalDate date = LocalDate.now().plusDays(5);
    String dataOfСalendar = "dd.MM.yyyy";
    DateTimeFormatter DateFormatter = DateTimeFormatter.ofPattern(dataOfСalendar);



    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(date.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(cssSelector(".notification__title")).waitUntil(Condition.visible, 15000);
    }
    @Test
    void shouldSubmit() {
        ChromeDriver driver = new ChromeDriver();

        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=city] input")).sendKeys("Мо");
        WebDriverWait wait = new WebDriverWait(driver,20);
        Select country = new Select(driver.findElement(By.xpath("//*[@id='Москва']")));
        //$(".menu-item").find(byText("Москва")).click();
        form.$(cssSelector(".input__box .icon-button")).click();
        form.$(cssSelector(".input__box .icon-button")).click();
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(date.format(DateFormatter));
        form.$(cssSelector("[name=name]")).sendKeys("Иванова Анна");
        form.$(cssSelector("[name=phone]")).sendKeys("+79109876543");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(byText("Забронировать")).click();
        $(cssSelector(".notification__title")).waitUntil(Condition.visible, 15000);
    }

}
    
