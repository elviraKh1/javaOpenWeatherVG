package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class HeadlessChrome {

    public static void main2(String[] args) {
        ChromeOptions o = new ChromeOptions();
        o.addArguments("headless");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(o);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://openweather.co.uk/storage/app/media/Terms/Openweather_terms_and_conditions_of_sale.pdf");
        System.out.println("Page getCurrentUrl: " + driver.getCurrentUrl());
    }

    public static void main(String[] args) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
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
        driver.close();
    }
}