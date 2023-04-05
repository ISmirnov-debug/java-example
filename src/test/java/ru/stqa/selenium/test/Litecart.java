package ru.stqa.selenium.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Litecart {

    private WebDriver driver;
    private WebDriverWait wait;

    @Test
    public void LitecartLog() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
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