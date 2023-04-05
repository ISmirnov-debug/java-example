package ru.stqa.selenium.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class StickersIcon {

    private WebDriver driver;
    private WebDriverWait wait;

    
    @Test
    public void StickersDuck() {
        driver.navigate().to("http://localhost/litecart");
        List<WebElement> Ducklist= driver.findElements(By.cssSelector("li.product"));
        for (WebElement product : Ducklist) {

            List<WebElement> Stick = product.findElements(By.cssSelector("div.sticker"));
            Assert.assertEquals(1, Stick.size());
        }
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
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
