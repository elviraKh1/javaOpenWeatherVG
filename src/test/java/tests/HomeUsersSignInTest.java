package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestData;
import pages.home.HomeUsersSignInPage;

import static io.qameta.allure.Allure.step;
@Epic("Tests with sign in method on the sign page - epic is a biggest idea")
public class HomeUsersSignInTest extends BaseTest {

    @Test
    public void testSignOut() {
        String actualSignOutMessage = openBaseURL()
                .signIn()
                .signOut()
                .getNotification();

        Assert.assertEquals(actualSignOutMessage, "You need to sign in or sign up before continuing.");
    }

    @Test
    public void testLogInWithInvalidPassword() {
        final String expectedNoticeMessage = "Invalid Email or password.";
        final String expectedSignInMenuText = "Sign In";
        final String userPassword = "Tester11#";

        final String oldSignInMenuText = openBaseURL()
                .clickSignInMenu()
                .getSignInText();

        HomeUsersSignInPage homeUsersSignInPage = new HomeUsersSignInPage(getDriver());

        homeUsersSignInPage
                .clickClearInputRegularUserEmail()
                .clickClearInputRegularUserPassword(userPassword)
                .clickSubmitButton();

        String actualNoticeMessage = homeUsersSignInPage.getNotification();
        String actualSignInMenuText = homeUsersSignInPage.getSignInText();

        Assert.assertEquals(actualNoticeMessage, expectedNoticeMessage);
        Assert.assertEquals(actualSignInMenuText, oldSignInMenuText);
        Assert.assertEquals(actualSignInMenuText, expectedSignInMenuText);
        takeScreenshot();
    }
    @Attachment
    @Test
    public void testLogInWithPasswordIsCaseSensitive() {
        final String expectedNoticeMessage = "Invalid Email or password.";
        final String expectedSignInMenuText = "Sign In";
        final String userPassword = "tester12#";

        final String oldSignInMenuText = openBaseURL()
                .clickSignInMenu()
                .getSignInText();

        HomeUsersSignInPage homeUsersSignInPage = new HomeUsersSignInPage(getDriver());

        homeUsersSignInPage
                .clickClearInputRegularUserEmail()
                .clickClearInputRegularUserPassword(userPassword)
                .clickSubmitButton();

        String actualNoticeMessage = homeUsersSignInPage.getNotification();
        String actualSignInMenuText = homeUsersSignInPage.getSignInText();

        Assert.assertEquals(actualNoticeMessage, expectedNoticeMessage);
        Assert.assertEquals(actualSignInMenuText, oldSignInMenuText);
        Assert.assertEquals(actualSignInMenuText, expectedSignInMenuText);
        takeScreenshot();
    }


    @Owner("Elvira")
    @Link("https://artoftesting.com/login")
    @Description("Allure report description: testing Login form with different parameters:")
    @Test(dataProvider = "SignInCredentials", dataProviderClass = TestData.class)
    public void testSignInWithInvalidCredentials(String scenario, String userEmail, String userPassword, String expectedNoticeMessage, String expectedSignInMenuText) {
        step("Login ");

        final String oldSignInMenuText = openBaseURL()
                .clickSignInMenu()
                .getSignInText();

        HomeUsersSignInPage homeUsersSignInPage = new HomeUsersSignInPage(getDriver());

        step("Enter data ");
        homeUsersSignInPage
                .clickClearInputRegularUserEmail(userEmail)
                .clickClearInputRegularUserPassword(userPassword)
                .clickSubmitButton();

        step("Get notification ");
        String actualNoticeMessage = homeUsersSignInPage.getNotification();
        String actualSignInMenuText = homeUsersSignInPage.getSignInText();

        step("Asserts ");
        Assert.assertEquals(actualNoticeMessage, expectedNoticeMessage);
        Assert.assertEquals(actualSignInMenuText, oldSignInMenuText);
        Assert.assertEquals(actualSignInMenuText, expectedSignInMenuText);
        takeScreenshot();
    }
}