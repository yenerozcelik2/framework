package InspiraFinancial.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.BrowserUtils;

import java.util.List;

import static utils.BrowserUtils.explicitlyWait;

public class LoginPage {

    public LoginPage(WebDriver driver){


        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id='search-box-input']")
    WebElement searchBox;
    @FindBy(xpath = "//div[@id='SearchAndMerchSearchTab']//*[@aria-label='submit search']")
    WebElement searchButton;

    @FindBy(xpath = "//p[.='Price']//parent::div")
    WebElement priceRangeButton;

    @FindBy(xpath = "//*[@placeholder='Enter min']")
    WebElement priceMinLimit;

    @FindBy(xpath = "//*[@placeholder='Enter max']")
    WebElement priceMaxLimit;

    @FindBy(xpath = "//*[@data-dd-action-name='priceWithMonthlyPayment_doneBtn']")
    WebElement doneButton;

    @FindBy(xpath = "//*[@class='bp-Homecard__Price--value']")
    List<WebElement> searchPrices;


    public void searchAddress(WebDriver driver,String address) throws InterruptedException {
        Thread.sleep(3000);
        explicitlyWait(driver,searchBox,"visibility");
        searchBox.sendKeys(address + Keys.ENTER);
        explicitlyWait(driver,searchBox,"clickable");
        searchButton.click();
        Thread.sleep(3000);
        System.out.println("login successfully");


    }

    public void setPriceRange(String min, String max) throws InterruptedException {
        priceRangeButton.click();
        Thread.sleep(3000);
        priceMinLimit.sendKeys(min);
        priceMaxLimit.sendKeys(max);
        Thread.sleep(3000);
        doneButton.click();
        Thread.sleep(10000);
        for (WebElement searchPrice:searchPrices) {
            System.out.println(searchPrice.getText());

            // Remove '$' and ',' to extract the numeric part
            String numericPrice = searchPrice.getText().replace("$", "").replace(",", "");

            // Convert the numeric part to an integer
            int price = Integer.parseInt(numericPrice);
            int minPrice = Integer.parseInt(min);
            int maxPrice = Integer.parseInt(max);

                Assert.assertTrue(minPrice < price && price < maxPrice);


        }
    }





}
