package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCheckingPage extends BaseManuPage{

    public CreateCheckingPage (WebDriver driver) {
        super(driver);
    }

    @FindBy(id="Standard Checking")
    private WebElement standardCheckingAccountTypeRadioButton;

    @FindBy(id="Interest Checking")
    private WebElement interestCheckingAccountTypeRadioButton;

    @FindBy(id="Individual")
    private WebElement individualOwnershipTypeRadioButton;

    @FindBy(id="Joint")
    private WebElement jointOwnershipTypeRadioButton;

    @FindBy(id="name")
    private WebElement nameAccountInputTxt;

    @FindBy(id="openingBalance")
    private WebElement depositAmountInputTxt; //openingBalanceTxtButton

    @FindBy(id="newCheckingSubmit")
    private WebElement submitBtn;


    public void createNewChecking (List<NewCheckingAccountInfo> checkingAccountInfoList) {
        NewCheckingAccountInfo testDataForOneCheckingAccount = checkingAccountInfoList.get(0);
        //the user clicks on a checking button
        checkingBtn.click();
        //the user clicks on a new checking button
        newCheckingBtn.click();
        assertEquals(ConfigReader.getPropertiesValue("digitalbank.createNewCheckingurl"), getDriver().getCurrentUrl(), "new Checking Button did not take to the " + ConfigReader.getPropertiesValue("digitalbank.createNewCheckingurl"));
        //the user selects an account type
        if(testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Standard Checking")) {
            standardCheckingAccountTypeRadioButton.click();
        }
        else if(testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Interest Checking")) {
            interestCheckingAccountTypeRadioButton.click();
        }
        else {
            throw new NoSuchElementException("Invalid checking account type option provided. Only supports Standard Checking and Interest Checking");
        }

        //the user selects an ownership
        if(testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Individual")) {
            individualOwnershipTypeRadioButton.click();
        }
        else if(testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Joint")) {
            jointOwnershipTypeRadioButton.click();
        }
        else {
            throw new NoSuchElementException("Invalid ownership type option provided. Only supports Individual and Joint");
        }
        //the user gives a name to an account
        nameAccountInputTxt.sendKeys(testDataForOneCheckingAccount.getAccountName());
        //the user makes the initial deposit
        depositAmountInputTxt.sendKeys(String.valueOf(testDataForOneCheckingAccount.getInitialDepositAmount()));
        //the user clicks on a submit button
        submitBtn.click();
    }
}
