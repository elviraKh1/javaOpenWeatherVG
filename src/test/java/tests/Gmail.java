package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Gmail extends BaseTest {

    @FindBy(xpath = "//section[@id='how']/h2")
    private WebElement h2Header;
    WebDriver webDriver;
    public Gmail(WebDriver driver){
       this.webDriver=getDriver();
       PageFactory.initElements(webDriver, this);
    }

    public void openPDF(WebDriver driver) {
        driver.get("https://openweather.co.uk/storage/app/media/Terms/Openweather_terms_and_conditions_of_sale.pdf");
    }
}
