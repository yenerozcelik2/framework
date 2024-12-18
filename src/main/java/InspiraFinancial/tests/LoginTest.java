package InspiraFinancial.tests;

import InspiraFinancial.pages.BasePage;
import InspiraFinancial.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BasePage {

    @Test
    public void validateLogin() throws InterruptedException {
        System.out.println("login successfully");
       LoginPage login= new LoginPage(driver);
       login.searchAddress(driver,"Oak Park");
       login.setPriceRange("100000","200000");

    }

}
