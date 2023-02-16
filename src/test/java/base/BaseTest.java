package base;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.MainPage;
import utils.ReportUtils;
import utils.TestUtils;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    private final static String BASE_URL = "https://openweathermap.org/";

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public static String getBaseUrl() {

        return BASE_URL;
    }

    @BeforeSuite
    protected void beforeSuite(ITestContext context) {

        Reporter.log(ReportUtils.getReportHeader(context), true);
    }

    @BeforeMethod
    protected void beforeMethod(Method method, ITestResult result) {
        driver = BaseUtils.createDriver();

        Reporter.log(ReportUtils.END_LINE, true);
        Reporter.log("TEST RUN", true);
        Reporter.log(ReportUtils.getClassNameTestName(method, result), true);
    }
    @Attachment
    public byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " + (iTestResult) + " failed");
//        WebDriver driver = BaseClass.getDriver();
//        // Allure ScreenShot and SaveTestLog
//        if (driver instanceof WebDriver) {
//            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
//            saveFailureScreenShot(driver);
//        }
    }
    @AfterMethod
    protected void afterMethod(Method method, ITestResult result) {
        Reporter.log(ReportUtils.getTestStatistics(method, result), true);

        driver.quit();
        webDriverWait = null;
    }

    protected WebDriver getDriver() {

        return driver;
    }

    protected WebDriverWait getWait() {
        if (webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        }

        return webDriverWait;
    }

    public void sleep(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public MainPage openBaseURL() {
        TestUtils.loadBaseUrlPage(getDriver(), getWait());

        if (TestUtils.isH2HeaderExists(getDriver())) {
            Reporter.log("BaseURL page was loaded successfully ", true);
        } else {
            TestUtils.reLoadBaseUrlPage(getDriver(), getWait());
        }

        return new MainPage(getDriver());
    }

    public String getExternalPageTitle() {
        if(getDriver().getTitle().isEmpty()) {
            TestUtils.waitForPageLoaded(getDriver());
        }

        return getDriver().getTitle();
    }

    public String getExternalPageURL() {
        return getDriver().getCurrentUrl();
    }
}
