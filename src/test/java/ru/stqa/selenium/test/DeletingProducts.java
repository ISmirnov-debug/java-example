package ru.stqa.selenium.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class DeletingProducts {

    private WebDriver driver;
    private WebDriverWait wait;

    @Test
    public void shoppingCartTest() throws InterruptedException {

        driver.navigate().to("http://localhost/litecart/en/");
        for (int i = 1; i < 4; i++) {
            driver.findElement(By.cssSelector("div#box-most-popular ul li")).click();
            if (driver.findElements(By.cssSelector("select[name='options[Size]']")).size() == 1) {
                new Select(driver.findElement(By.cssSelector("select[name='options[Size]']"))).selectByValue("Medium");
            }
            //Добавление утенка в корзину
            driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
            wait.until(ExpectedConditions.textToBe(By.cssSelector("div#cart a:nth-child(2) span.quantity"), "" + i));

            driver.navigate().back();
        }
        driver.findElement(By.xpath("//a[text()='Checkout »']")).click();
        //Удаление утят из корзины
        driver.findElement(By.cssSelector("div#box-checkout-cart ul.shortcuts  li.shortcut:nth-child(1)")).click();
        while (driver.findElements(By.cssSelector(("div#box-checkout-cart ul li.item "))).size() != 0) {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.viewport button[name='remove_cart_item']"))).click();
            wait.until(ExpectedConditions.attributeContains(By.cssSelector("div#checkout-cart-wrapper"), "style", "opacity: 1;"));
        }
        wait.until(ExpectedConditions.textToBe(By.cssSelector("div#checkout-cart-wrapper"),
                "There are no items in your cart.\n" +
                        "<< Back"));
    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
    @Before
    public void start() {
        ChromeOptions o = new ChromeOptions();
        o.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(o);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
