package ru.stqa.selenium.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;
import java.util.Random;

public class AddProduct {

    private WebDriver driver;
    private WebDriverWait wait;

    @Test
    public void addNewDuck() {
        String Name = "New rubber duck" + new Random().nextInt(500);

        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.xpath("//span[text()='Catalog']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Add New Product')]")).click();

        //Заполнение необходимых полей, для продукта.
        driver.findElement(By.xpath("//label[contains(text(),'Enabled')]")).click();
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(Name);
        driver.findElement(By.cssSelector("input[name=code")).sendKeys("RD10");
        driver.findElement(By.cssSelector("input[name=quantity")).sendKeys("10");
        driver.findElement(By.cssSelector("input[name='new_images[]']")).sendKeys(new File("src/test/resources/newDuck.jpg").getAbsolutePath());

        driver.findElement(By.cssSelector("input[name=date_valid_from")).sendKeys("12-01-2023");
        driver.findElement(By.cssSelector("input[name=date_valid_to")).sendKeys("17-04-2024");
        driver.findElement(By.xpath("//a[contains(text(),'Information')]")).click();
        new Select(driver.findElement(By.cssSelector("select[name=manufacturer_id]"))).selectByValue("1");
        driver.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys("А new duck for the site");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("this duck is designed to perform a test task");
        driver.findElement(By.xpath("//a[contains(text(),'Prices')]")).click();
        driver.findElement(By.cssSelector("input[name=purchase_price]")).sendKeys("10");
        new Select(driver.findElement(By.cssSelector("select[name=purchase_price_currency_code]"))).selectByValue("USD");
        driver.findElement(By.cssSelector("input[name='prices[USD]']")).sendKeys("1.500");

        driver.findElement(By.cssSelector("button[name=save]")).click();

        driver.findElement(By.xpath("//span[text()='Catalog']")).click();
        Assert.assertTrue("The product is not in the catalog", driver.findElement(By.xpath("//form[@name='catalog_form']/descendant::table[@class='dataTable']/tbody/descendant::tr/td[3]/a[text()='" + Name + "']")).isDisplayed());
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