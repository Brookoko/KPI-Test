package com.github.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePage
{
    private final String url = "https://github.com/";
    private final String repositoriesUrl = "https://github.com/Brookoko?tab=repositories&type=source";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By singInLocator = By.linkText("Sign in");

    private final By singIn = By.id("login");
    private final By repositories = By.id("user-repositories-list");

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void open()
    {
        driver.get(url);
    }

    public void openSignInPage()
    {
        driver.findElement(singInLocator).click();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(singIn));
    }

    public void openRepositoriesPage()
    {
        driver.navigate().to(repositoriesUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(repositories));
    }
}