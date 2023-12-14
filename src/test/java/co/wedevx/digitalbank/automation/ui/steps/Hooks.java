package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.*;

import java.util.concurrent.TimeUnit;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class Hooks {
    //all before and after hooks are here

    @Before("@Registration")
    public void establishConnectionToDB() {
        DBUtils.establishConnection();
    }


//    @Before("not @Registration")
//    public void the_user_is_on_dbank_homepage() {
//
//        getDriver().get("https://dbank-qa.wedevx.co/bank/login");
//    }


    @Before("not @Registration")
    public void the_user_is_on_dbank_homepage() {

        getDriver().get("http://marianab1244.mydevx.com/bank/account/checking-add");
    }

    @After ("not @NegativeRegistrationCases")
    public void afterEachScenario(Scenario scenario) {
        Driver.takeScreenShot(scenario);
        Driver.closeDriver();
    }

    @AfterAll
    public static void closeConnectionToDB() {
        DBUtils.closeConnection();
    }
}
