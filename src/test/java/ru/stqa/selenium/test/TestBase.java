package ru.stqa.selenium.test;

import org.junit.After;
import org.junit.Before;

public class TestBase {

    public App app;

    @Before
    public void start() {

        app = new App();

    }
    @After
    public void stop(){
        app.quit();
        app = null;
    }
    }