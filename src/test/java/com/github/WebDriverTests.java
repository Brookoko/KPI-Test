package com.github;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public class WebDriverTests
{
    private static ChromeDriverService service;
    protected WebDriver driver;

    @BeforeClass
    public static void createAndStartService() throws IOException
    {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("src/main/resources/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @AfterClass
    public static void stopService()
    {
        service.stop();
    }

    @Before
    public void createDriver()
    {
        driver = new RemoteWebDriver(service.getUrl(), new ChromeOptions());
    }

    @After
    public void quitDriver()
    {
        driver.quit();
    }
}