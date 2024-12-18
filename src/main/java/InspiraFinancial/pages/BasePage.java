package InspiraFinancial.pages;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.BrowserUtils;
import utils.ConfigReader;
import utils.DriverHelper;

public class BasePage {

    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver= DriverHelper.getDriver();
        driver.get(ConfigReader.readProperty("url"));
        System.out.println("navigate to Page");
    }

    @AfterMethod
    public void tearDown(ITestResult iTestResult){
        if(!iTestResult.isSuccess()){
            BrowserUtils.getScreenShot(driver,"screenShot");
        }
        driver.quit();
    }
}
