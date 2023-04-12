package ru.stqa.selenium.test;

import org.jetbrains.annotations.NotNull;
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
import java.util.ArrayList;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Country {

    private WebDriver driver;
    private WebDriverWait wait;


    @Test
    public void Order() {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button")).click();

        List<String> List = driver.findElements(By.cssSelector("table tr.row td:nth-child(5)")).stream().map(e -> e.getText()).collect(Collectors.toList());
        SortingTEST(List);

        for (String country : List) {
            WebElement a = driver.findElement(By.xpath("//a[text()=\"" + country + "\"]"));
            String zone = a.findElement(By.xpath("./../../td[6]")).getText();
            if (!zone.equals("0")) {
                a.click();
                List<String> Zones = driver.findElements(By.xpath("//h2[text()='Zones']/following::table/tbody/tr/td[3]/input[@type='hidden']/..")).stream().map(e -> e.getText()).collect(Collectors.toList());
                driver.navigate().back();
            }
        }
    }

    @Test
    public void GeoOrder() {
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button")).click();

        List<String> Countries = driver.findElements(By.cssSelector("table tr.row td:nth-child(3)")).stream().map(e -> e.getText()).collect(Collectors.toList());

        for (String country : Countries) {
            driver.findElement(By.xpath("//a[text()=\"" + country + "\"]")).click();
            List<String> Zones = driver.findElements(By.xpath("//h2[text()='Zones']/following::table/tbody/tr/td[3]/select/option[@selected='selected']")).stream().map(e -> e.getText()).collect(Collectors.toList());
            SortingTEST(Zones);
            driver.navigate().back();
        }
    }

    private void SortingTEST(@NotNull List<String> aList) {
        List<String> sorting = new ArrayList<>();
        for(int i=0; i < aList.size(); i++){
            sorting.add("");
        }
        Collections.copy(sorting, aList);
        Collections.sort(sorting);
        Assert.assertEquals(aList, sorting);
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