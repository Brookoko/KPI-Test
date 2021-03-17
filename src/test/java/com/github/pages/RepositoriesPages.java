package com.github.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RepositoriesPages
{
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By repository = By.id("new_repository");
    private final By newButton = By.linkText("New");

    public RepositoriesPages(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void createNewRepository()
    {
        driver.findElement(newButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(repository));
    }
}
