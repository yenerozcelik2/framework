package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

public class BrowserUtils {

    public static String getText(WebElement element){
        return element.getText().trim();
    }

    public static void selectBy(WebElement location,String value,String methodName){

        Select select=new Select(location);

        switch (methodName){

            case "text":
                select.selectByVisibleText(value);
                break;
            case "value":
                select.selectByValue(value);
                break;
            case "index":
                select.selectByIndex(Integer.parseInt(value));
                break;
            default:
                Assert.fail("The method name is not matching with text,value or index");
        }
    }

    public static String getTitleJS(WebDriver driver){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        return js.executeScript("return document.title").toString().trim();
    }
    public static void clickJS(WebDriver driver,WebElement element){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",element);
    }

    public static void scrollIntoView(WebDriver driver,WebElement element){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)",element);
    }

    public static void switchWindow(WebDriver driver,String title){
      Set<String> allPagesId=driver.getWindowHandles();
      for(String id:allPagesId){
          driver.switchTo().window(id);
          if(driver.getTitle().contains(title)){
              break;
          }
      }
    }
    public static void getScreenShot(WebDriver driver,String packageName){
        File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String location=System.getProperty("src/test/java/"+packageName+"/");
        try {
            FileUtils.copyFile(file,new File(location+System.currentTimeMillis()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static WebElement explicitlyWait(WebDriver driver,WebElement element,String condition){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        condition=condition.toLowerCase();
        if(condition.contains("visibility")){
            element=wait.until(ExpectedConditions.visibilityOf(element));
        }else if(condition.contains("clickable")){
            element=wait.until(ExpectedConditions.elementToBeClickable(element));
        }else{
            Assert.fail("Please provide either visibility or clickable");
        }
        return element;
    }
}
