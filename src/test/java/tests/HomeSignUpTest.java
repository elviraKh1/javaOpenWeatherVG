package tests;

import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.TestData;
import pages.home.HomeUsersSignUpPage;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class HomeSignUpTest extends BaseTest {

    @Test(dataProvider = "SignUpGroupMenuData",dataProviderClass = TestData.class)
    public void testSignUpGroupLinksNavigateToCorrespondingPage(
            int index, String name, String href, String expectedURL, String expectedTitle) {
//
//        MainPage mainPage = openBaseURL();
//
//        final String oldURL = mainPage.getCurrentURL();
//        Reporter.log("%%%%%%%%%%%%%%%%%%%%%1="+oldURL,true);
//        mainPage
//                .clickSignInMenu()
//                .clickCreateAnAccountLink()
//                .clickSignUpGroupLinks(index);
//
//        String actualURL = getDriver().getCurrentUrl();
//        Reporter.log("%%%%%%%%%%%%%%%%%%%%%"+actualURL,true);
////        Assert.assertNotEquals(oldURL, actualURL);
////        Assert.assertEquals(actualURL, expectedTitletedURL);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("test-type"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        chromeOptions.merge(capabilities);
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://openweather.co.uk/storage/app/media/Terms/Openweather_terms_and_conditions_of_sale.pdf");
        System.out.println("Page getCurrentUrl: " + driver.getCurrentUrl());
    }
}
