package ru.stqa.selenium.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
public class MessagesInLog {

    private WebDriver driver;
    private WebDriverWait wait;
    @Test
    public void test() {
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button")).click();

        List<WebElement> Quantity = driver.findElements(By.xpath("//*[@id='content']//td[3]/a[contains(@href, 'product_id')]"));
        for (int a = 0; a < Quantity.size(); a++){
            List<WebElement> Products = driver.findElements(By.xpath("//*[@id='content']//td[3]/a[contains(@href, 'product_id')]"));
            Products.get(a).click();
            for (LogEntry b: driver.manage().logs().get("browser").getAll()
            ) {
                System.out.print(b + " Номер ссылки = " + a );
            }
            driver.navigate().back();
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
