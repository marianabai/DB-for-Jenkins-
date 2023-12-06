package co.wedevx.digitalbank.automation.ui.utils;
//the purpose of utility classes is to create a reusable framework
//most of them are static because they do not really belong to an object

//if we see that some lines of code with Actions and Waits take more than 2 lines we use this class to put them here

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//WebDriver Helper Extension is designed to simplify Java based Selenium/Webdriver tests
//It's built on top of Selenium/WebDriver to make your tests more readable, reusable and maintainable
//by providing easy handling towards browser and advance identifiers
//public class BrowserHelper {
//    //method -> wait until the element is visible
//    public static WebElement waitForVisibilityOfElement(WebDriver driver, WebElement element, Duration timeToWaitInSec) {
//        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
//        return wait.until(ExpectedConditions.visibilityOf(element));
//    }
//
//    //method -> wait until the element is clickable and click on it
//    //we can use WebElement as a return type or void
//    public static WebElement waitUntilClickableAndClick (WebDriver driver, WebElement element, Duration timeToWaitInSec) {
//        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
//        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
//        clickableElement.click();
//        return clickableElement;
//    }
//}
