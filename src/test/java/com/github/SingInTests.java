package com.github;

import com.github.pages.HomePage;
import com.github.pages.SingInPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class SingInTests extends WebDriverTests
{
    private final String url = "https://github.com/";

    private static String username;
    private static String email;
    private static String password;

    @BeforeClass
    public static void setup()
    {
        XmlLoader loader = new XmlLoader();
        TestUser user = loader.Load();
        username = user.username;
        email = user.email;
        password = user.password;
    }

    @Before
    public void openSignInPage()
    {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.open();
        homePage.openSignInPage();
    }

    @Test
    public void singInWithUsername()
    {
        SingInPage singInPage = PageFactory.initElements(driver, SingInPage.class);
        singInPage.fillLogin(username);
        singInPage.fillPassword(password);
        singInPage.signIn();

        Assert.assertEquals(url, singInPage.getCurrentUrl());
    }

    @Test
    public void singInWithEmail()
    {
        SingInPage singInPage = PageFactory.initElements(driver, SingInPage.class);
        singInPage.fillLogin(email);
        singInPage.fillPassword(password);
        singInPage.signIn();

        Assert.assertEquals(url, singInPage.getCurrentUrl());
    }

    @Test
    public void singInWrongPassword()
    {
        SingInPage singInPage = PageFactory.initElements(driver, SingInPage.class);
        singInPage.fillLogin(email);
        singInPage.fillPassword("this_is_definitely_password_123");
        singInPage.signIn();

        Assert.assertTrue(singInPage.hasErrorMessages());
    }
}