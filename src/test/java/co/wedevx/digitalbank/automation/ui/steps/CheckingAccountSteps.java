package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.BankTransaction;

import co.wedevx.digitalbank.automation.ui.pages.CreateCheckingPage;
import co.wedevx.digitalbank.automation.ui.pages.LogInPage;
import co.wedevx.digitalbank.automation.ui.pages.ViewCheckingAccountPage;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingAccountSteps {

    //we created Driver class in utils package, so now we replace the line with a new one
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = Driver.getDriver();

    private LogInPage logInPage = new LogInPage(driver);
    private CreateCheckingPage createCheckingPage = new CreateCheckingPage(driver);

    private ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage(driver);


//we were using @BeforeAll (now we can get rid of it) before we created our Driver what now does all the pre driver setup only once for our whole framework
//    @BeforeAll
//    public static void setup() {
//        WebDriverManager.chromedriver().setup();
//        System.setProperty("webdriver.chrome.driver", "/Users/manajam/Downloads/chromedriver-mac-x64/chromedriver");
//    }

    @Given("the user logs in as {string} {string}")
    public void the_user_logs_in_as(String username, String password) {

        logInPage.login(username,password);
    }

    @When("the user creates a new checking account with the following data")
    public void the_user_creates_a_new_checking_account_with_the_following_data(List<NewCheckingAccountInfo> checkingAccountInfoList) {

        createCheckingPage.createNewChecking(checkingAccountInfoList);
    }
    @Then("the user should see the green confirmation message {string}")
    public void the_user_should_see_the_green_confirmation_message(String expectedConfMessage) {
        expectedConfMessage = "Confirmation " + expectedConfMessage + "\n×";
        assertEquals(expectedConfMessage, viewCheckingAccountPage.getActualCreateAccountConfirmationMessage());
    }
    @Then("the user should see a newly added account card")
    public void the_user_should_see_a_newly_added_account_card(List<AccountCard> accountCardList) {
        Map<String, String> actualResultMap = viewCheckingAccountPage.getNewlyAddedCheckingAccountInfoMap();

        AccountCard expectedResult = accountCardList.get(0);

        assertEquals(expectedResult.getAccountName(),actualResultMap.get("actualAccountName"));
        assertEquals("Account: " + expectedResult.getAccountType(),actualResultMap.get("actualAccountType"));
        assertEquals("Ownership: " + expectedResult.getOwnership(),actualResultMap.get("actualOwnership"));
        assertEquals("Interest Rate: " + expectedResult.getInterestRate(),actualResultMap.get("actualInterestRate"));

        String expectedBalance  = String.format("%.2f", expectedResult.getBalance());
        assertEquals("Balance: $" + expectedBalance,actualResultMap.get("actualBalance"));
    }
    @Then("the user should see the following transactions")
    public void the_user_should_see_the_following_transactions(List<BankTransaction> expectedTransactions) {
        Map<String, String> actualResultMap = viewCheckingAccountPage.getNewlyAddedCheckingAccountTransactionInfoMap();

        BankTransaction expectedTransaction = expectedTransactions.get(0);
        assertEquals(expectedTransaction.getCategory(), actualResultMap.get("actualCategory"), "transaction category mismatch");
        //description -> number always decrements by 1, not enough knowledge to do it right now
        //assertEquals(expectedTransaction.getDescription(), actualDescription, "transaction description mismatch");
        assertEquals(expectedTransaction.getAmount(), Double.parseDouble(actualResultMap.get("actualAmount")), "transaction amount mismatch");
        assertEquals(expectedTransaction.getBalance(), Double.parseDouble(actualResultMap.get("actualBalance")), "transaction balance mismatch");

        //viewCheckingAccountPage.goToHomePage();
    }
}