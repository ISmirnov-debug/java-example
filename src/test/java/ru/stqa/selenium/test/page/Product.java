package ru.stqa.selenium.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
public class Product extends Page{

    private static final By PRODUCT_SIZE = By.cssSelector("select[name='options[Size]']");
    private static final By PRODUCT_BUTTON = By.cssSelector("button[name=add_cart_product]");
    private static final By CART_PRODUCTS_COUNT = By.cssSelector("div#cart a:nth-child(2) span.quantity");

    public Product(WebDriver driver) {
        super(driver);
    }
    public Product addProductToCart() {
        if(driver.findElements(PRODUCT_SIZE).size() ==1)
            new Select(driver.findElement(PRODUCT_SIZE)).selectByValue("Medium");
        driver.findElement(PRODUCT_BUTTON).click();
        return this;
    }
    public Product CartUpdate(int productsCount) {
        wait.until(ExpectedConditions.textToBe(CART_PRODUCTS_COUNT, "" + productsCount));
        driver.findElement(PRODUCT_BUTTON).click();
        return this;
    }
}