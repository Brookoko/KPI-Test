package com.github.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class SingInPage
{
    private final WebDriver driver;

    private final By errors = By.cssSelector("[class=\"flash flash-full flash-error \"]");

    @FindBy(id = "login_field")
    private WebElement loginElement;

    @FindBy(id = "password")
    private WebElement passwordElement;

    @FindBy(css = "[type=\"submit\"][name=\"commit\"]")
    private WebElement signInButton;

    public SingInPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void fillLogin(String login)
    {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        loginElement.sendKeys(login);
    }

    public void fillPassword(String password)
    {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        passwordElement.sendKeys(password);
    }

    public void signIn()
    {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        signInButton.click();
    }

    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }

    public boolean hasErrorMessages()
    {
        return driver.findElements(errors).size() > 0;
    }
}