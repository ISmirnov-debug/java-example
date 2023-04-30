package ru.stqa.selenium.test;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class LinksInNewWindow {

    private WebDriver driver;
    private WebDriverWait wait;
    @Test
    public void openPageInNewWindowTest() {

        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button")).click();

    // Открытие ссылок с иконками
        driver.findElement(By.cssSelector("table tr.row td:nth-child(7) a")).click();
        List<WebElement> icons = driver.findElements(By.cssSelector("i.fa-external-link"));
        for (WebElement link : icons) {
            String shape = driver.getWindowHandle();
            link.click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> allWindows = driver.getWindowHandles();
            for (String check : allWindows) {
                if (!check.equals(shape)) {
                    driver.switchTo().window(check);
                    driver.close();
                }
                driver.switchTo().window(shape);
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


