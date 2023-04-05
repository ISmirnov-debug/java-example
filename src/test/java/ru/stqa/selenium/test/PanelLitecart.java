package ru.stqa.selenium.test;

import org.junit.After;
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
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class PanelLitecart {

    private WebDriver driver;
    private WebDriverWait wait;


    @Test
    public void PanelTest() throws InterruptedException {

        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));

        List<WebElement> Menu = driver.findElements(By.cssSelector("#app-"));
        for (int e = 0; e < Menu.size(); e++) {
            Menu = driver.findElements(By.cssSelector("#app-"));
            Menu.get(e).click();
            assertTrue(driver.findElement(By.cssSelector("h1")).isDisplayed());

            List<WebElement> Line = driver.findElements(By.cssSelector("#app- ul li a"));
            for (int k = 0; k < Line.size(); k++) {
                Line = driver.findElements(By.cssSelector("#app- ul li a"));
                Line.get(k).click();
                assertTrue(driver.findElement(By.cssSelector("h1")).isDisplayed());
            }
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
        driver = new ChromeDriver(o);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}


