package ru.stqa.selenium.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Random;

public class Registration {

    private WebDriver driver;
    private WebDriverWait wait;
    @Test
    public void RegistrationTest() {
        String email = "Ivan" + new Random().nextInt(500) + "@soft.com";
        String password = "123456789";

        driver.navigate().to("http://localhost/litecart");
        driver.findElement(By.xpath("//a[text()='New customers click here']")).click();

        // Заполнение необходимых полей.
        driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys("Иван");
        driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys("Филимонов");
        driver.findElement(By.cssSelector("input[name=address1]")).sendKeys("Москва, улица Космонавтов");
        driver.findElement(By.cssSelector("input[name=postcode]")).sendKeys("117410");
        driver.findElement(By.cssSelector("input[name=city]")).sendKeys("Москва");
        driver.findElement(By.cssSelector("span[role=presentation]")).click();
        driver.findElement(By.cssSelector("span[class$=dropdown] input[role=textbox]")).sendKeys("Russian Federation" + Keys.ENTER);
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("+7 321 111 1111");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(password);

        driver.findElement(By.cssSelector("button[name=create_account]")).click();

        //Выход после регистрации.
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        //Повторная авторизация на сайте.
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name=login]")).click();

        //Выход из зарегистрованной УЗ.
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
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
