package com.github.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RepositoryPage
{
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By suggestion = By.xpath("//*[@id=\"repo-name-suggestion\"]/strong");
    private final By deletePrompt = By.xpath("//*[contains(@class, \"Box Box--danger\")]/ul/li[last()]/details/summary");
    private final By deleteInput = By.xpath("//*[contains(@class, \"Box-body overflow-auto\")]/form/p/input");
    private final By deleteButton = By.xpath("//form[contains(@action, \"delete\")]/button");

    @FindBy(id = "repository_name")
    private WebElement repositoryName;

    @FindBy(css = "button[type=\"submit\"][class=\"btn btn-primary first-in-line\"]")
    private WebElement createButton;

    public RepositoryPage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, 2);
    }

    public String getSuggestionName()
    {
        return driver.findElement(suggestion).getText();
    }

    public void fillName(String name)
    {
        repositoryName.sendKeys(name);
    }

    public void createRepository()
    {
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(createButton, "disabled", "")));
        createButton.click();
    }

    public void goToSettings()
    {
        String url = driver.getCurrentUrl();
        url += "/settings";
        driver.navigate().to(url);
    }

    public void deletePrompt()
    {
        driver.findElement(deletePrompt).click();
    }

    public void fillDeleteName(String name)
    {
        driver.findElement(deleteInput).sendKeys(name);
    }

    public void deleteRepository()
    {
        WebElement delete = driver.findElement(deleteButton);
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(delete, "disabled", "")));
        delete.click();
    }

    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }
}
