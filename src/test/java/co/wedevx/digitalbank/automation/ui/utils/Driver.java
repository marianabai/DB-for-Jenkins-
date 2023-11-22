package co.wedevx.digitalbank.automation.ui.utils;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;
//the purpose of utility classes is to create a reusable framework
//most of them are static because they do not really belong to an object


//this insures that across the whole project we are using one driver
public class Driver {

    private static WebDriver driver;

    private Driver() {

    }

    public static WebDriver getDriver() {

        if (driver == null) {

            String browser = ConfigReader.getPropertiesValue("digitalbank.browser");

            switch (browser.toLowerCase()) {
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "headless":
                    WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
//                    System.setProperty("webdriver.chrome.driver", "/Users/manajam/Downloads/chromedriver-mac-x64/chromedriver");
                    ChromeOptions options = new ChromeOptions();

                    options.addArguments("--window-size=1928,1888");
                    options.addArguments("disable-extensions");
                    options.setExperimentalOption("useAutomationExtension", false);
                    options.addArguments("--proxy-server='direct://");
                    options.addArguments("--proxy-bypass-list=*");
                    options.addArguments("--start-maximized");
                    options.addArguments("--headless");

                    driver = new ChromeDriver(options);
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
//                    System.setProperty("webdriver.chrome.driver", "/Users/manajam/Downloads/chromedriver-mac-x64/chromedriver");
                    driver = new ChromeDriver();
                    break;
            }
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void takeScreenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            //taking a screenshot
            final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            //adding the screenshot to the report
            scenario.attach(screenshot, "image/png", "screenshot");
        }
    }


    public static void closeDriver() {
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
