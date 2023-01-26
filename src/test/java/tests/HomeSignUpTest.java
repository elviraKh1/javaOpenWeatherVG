package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.TestData;
import pages.home.HomeUsersSignUpPage;

public class HomeSignUpTest extends BaseTest {

    @Test(dataProvider = "SignUpGroupMenuData",dataProviderClass = TestData.class)
    public void testSignUpGroupLinksNavigateToCorrespondingPage(
            int index, String name, String href, String expectedURL, String expectedTitle) {

        MainPage mainPage = openBaseURL();

        final String oldURL = mainPage.getCurrentURL();
        Reporter.log("%%%%%%%%%%%%%%%%%%%%%1="+oldURL,true);
        mainPage
                .clickSignInMenu()
                .clickCreateAnAccountLink()
                .clickSignUpGroupLinks(index);

        String actualURL = getDriver().getCurrentUrl();
        Reporter.log("%%%%%%%%%%%%%%%%%%%%%"+actualURL,true);
//        Assert.assertNotEquals(oldURL, actualURL);
//        Assert.assertEquals(actualURL, expectedTitletedURL);
    }
}
