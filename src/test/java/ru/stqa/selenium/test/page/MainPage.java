package ru.stqa.selenium.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class MainPage extends Page {

    private static final By FIRST_PRODUCT = By.cssSelector("div#box-most-popular ul li");
    private static final By LINK_LOCATOR = By.xpath("//a[text()='Checkout »']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage Open() {
        driver.navigate().to("http://litecart.stqa.ru/en/");
        return this;
    }

    public MainPage FirstProduct() {
        driver.findElement(FIRST_PRODUCT).click();
        return this;
    }

    public MainPage Checkout() {
        driver.findElement(LINK_LOCATOR).click();
        return this;
    }
}
