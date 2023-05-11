package ru.stqa.selenium.test;

import org.junit.Test;



    public class DeletingProducts extends TestBase{

        @Test
        public void test() {
            app.fillCartWithProducts(3);
            app.openCartAndDeleteAllProducts();
        }
    }

