package ru.stqa.selenium.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageVie {

    private WebDriver driver;
    private WebDriverWait wait;

    @Test
    public void CorrectPage() {

        driver.navigate().to("http://localhost/litecart");

        WebElement MainPage = driver.findElement(By.xpath("//h3[contains(text(), 'Campaigns')]/../descendant::li"));
        String Product = MainPage.findElement(By.cssSelector("div.name")).getText();

        WebElement PriceMain = MainPage.findElement(By.cssSelector("div.price-wrapper s.regular-price"));
        String PriceValue = PriceMain.getText();

        WebElement DiscPriceMain = MainPage.findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        String DiscPriceValue = DiscPriceMain.getText();

        ViewOrdPrice(PriceMain);
        ViewDiscPrice(DiscPriceMain);
        Comparison(PriceMain, DiscPriceMain);

        MainPage.click();

        String Name = driver.findElement(By.cssSelector("h1.title")).getText();
        WebElement OrdPrice = driver.findElement(By.cssSelector("div#box-product s.regular-price"));
        WebElement DiscPrice = driver.findElement(By.cssSelector("div#box-product strong.campaign-price"));

        ViewOrdPrice(OrdPrice);
        ViewDiscPrice(DiscPrice);
        Comparison(OrdPrice, DiscPrice);

        Assert.assertEquals(Product, Name);
        Assert.assertEquals(PriceValue, OrdPrice.getText());
        Assert.assertEquals(DiscPriceValue, DiscPrice.getText());
    }

    private void ViewOrdPrice(@NotNull WebElement b) {
        Assert.assertEquals("Ordinary price is not strikethroughed.", "line-through", b.getCssValue("text-decoration-line"));
        Color c = Color.fromString(b.getCssValue("color"));
        Assert.assertEquals("Ordinary price is not grey.", c.getColor().getRed(), c.getColor().getGreen());
        Assert.assertEquals("Ordinary price is not grey.", c.getColor().getGreen(), c.getColor().getBlue());
    }

    private void ViewDiscPrice(@NotNull WebElement d) {
        Assert.assertEquals("Discount price is not bold.", "700", d.getCssValue("font-weight"));
        Color c = Color.fromString(d.getCssValue("color"));
        Assert.assertNotEquals("Discount price is not red.", c.getColor().getRed(), c.getColor().getGreen());
        Assert.assertEquals("Discount price is not red.", c.getColor().getGreen(), c.getColor().getBlue());
    }

    private void Comparison(@NotNull WebElement a, @NotNull WebElement c) {
        String ordSize = a.getCssValue("font-size");
        String discSize = c.getCssValue("font-size");
        if (Float.parseFloat(ordSize.substring(0, ordSize.length() - 2)) < Float.parseFloat(discSize.substring(0, discSize.length() - 2)))
            System.out.print("Discount price font size is bigger than Ordinary price font size.");
        else Assert.fail("Discount price font size is smaller than Ordinary price font size.");
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