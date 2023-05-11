package ru.stqa.selenium.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.selenium.test.page.Cart;
import ru.stqa.selenium.test.page.MainPage;
import ru.stqa.selenium.test.page.Product;

public class App {

    private WebDriver driver;

    private MainPage mainPage;
    private Product productPage;
    private Cart cartPage;

    public App() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new Product(driver);
        cartPage = new Cart(driver);
    }
    public void quit() {
        driver.quit();
        driver = null;
    }
    public void fillCartWithProducts(int productsCount) {
        for (int i = 1; i < productsCount + 1; i++) {
            mainPage
                    .Open()
                    .FirstProduct();
            productPage.addProductToCart()
                    .CartUpdate(i)
                    .navigateBack();
        }
    }
    public void openCartAndDeleteAllProducts() {
        mainPage.Checkout();
        cartPage.selectFirstProduct().deleteAllProducts();
    }
}
