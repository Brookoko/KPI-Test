package com.github;

import com.github.pages.HomePage;
import com.github.pages.RepositoriesPages;
import com.github.pages.RepositoryPage;
import com.github.pages.SingInPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class RepositoryTests extends WebDriverTests
{
    private final String url = "https://github.com/";

    private static String username;
    private static String password;

    @BeforeClass
    public static void generateEmail()
    {
        XmlLoader loader = new XmlLoader();
        TestUser user = loader.Load();
        username = user.username;
        password = user.password;
    }

    @Before
    public void openRepositoryPage()
    {
        HomePage homePage = open();
        signIn(homePage);
        openCreateRepositoryPage(homePage);
    }

    private HomePage open()
    {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.open();
        return homePage;
    }

    private void signIn(HomePage homePage)
    {
        homePage.openSignInPage();
        SingInPage singInPage = PageFactory.initElements(driver, SingInPage.class);
        singInPage.fillLogin(username);
        singInPage.fillPassword(password);
        singInPage.signIn();
    }

    private void openCreateRepositoryPage(HomePage homePage)
    {
        homePage.openRepositoriesPage();
        RepositoriesPages page = PageFactory.initElements(driver, RepositoriesPages.class);
        page.createNewRepository();
    }

    @Test
    public void createRepository()
    {
        RepositoryPage page = PageFactory.initElements(driver, RepositoryPage.class);
        String name = createRepositoryWithSuggestion(page);

        String repositoryUrl = url + username + "/" + name;
        Assert.assertEquals(repositoryUrl, page.getCurrentUrl());
    }

    @Test
    public void createRepositoryWithTakenName()
    {
        RepositoryPage page = PageFactory.initElements(driver, RepositoryPage.class);

        page.fillName("KPI-Test");
        page.createRepository();

        String repositoryUrl = url + username + "/KPI-Test";
        Assert.assertEquals(repositoryUrl, page.getCurrentUrl());
    }

    @Test
    public void deleteRepository()
    {
        RepositoryPage page = PageFactory.initElements(driver, RepositoryPage.class);
        String name = createRepositoryWithSuggestion(page);

        page.goToSettings();
        page.deletePrompt();
        page.fillDeleteName(username + "/" + name);
        page.deleteRepository();

        Assert.assertEquals(url, page.getCurrentUrl());
    }

    private String createRepositoryWithSuggestion(RepositoryPage page)
    {
        String name = page.getSuggestionName();
        page.fillName(name);
        page.createRepository();
        return name;
    }
}
