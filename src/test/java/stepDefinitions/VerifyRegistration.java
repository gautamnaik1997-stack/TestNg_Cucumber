package stepDefinitions;

import Core.BaseClass;
import Core.DriverFactory;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pageObjects.AccountSuccess;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;

public class VerifyRegistration {
    HomePage homePage;
    RegistrationPage registrationPage;
    AccountSuccess accountSuccess;
    String password = BaseClass.getRandomString();
    @Given("User Clicks on MyAccount then Register button")
    public void user_clicks_on_my_account_then_register_button() {
        homePage=new HomePage(DriverFactory.getDriver());
        homePage.clickMyAccount();
        homePage.clickRegister();
    }
    @When("User navigates to Registration page")
    public void user_navigates_to_registration_page() {
        String actualTitle = BaseClass.getTitle();
        Assert.assertEquals(actualTitle, "Register Account");
        registrationPage=new RegistrationPage(DriverFactory.getDriver());
    }
    @When("User enters valid First Name")
    public void user_enters_valid_first_name() {
        registrationPage.setTxt_FirstName(BaseClass.getRandomString());
    }
    @When("User enters First Name {string}")
    public void user_enters_first_name(String fname) {
        registrationPage.setTxt_FirstName(fname);
    }
    @When("User enters valid Last Name")
    public void user_enters_valid_last_name() {
        registrationPage.setTxt_LastName(BaseClass.getRandomString());
    }
    @When("User enters Last Name {string}")
    public void user_enters_last_name(String lname) {
        registrationPage.setTxt_LastName(lname);
    }
    @When("User enters valid Email")
    public void user_enters_valid_email() {
        registrationPage.setTxt_Email(BaseClass.getRandomEmail());
    }
    @When("User enters Email {string}")
        public void user_enters_email(String email) {
        registrationPage.setTxt_Email(email);
        }
    @When("User enters valid Telephone")
    public void user_enters_telephone() {
       registrationPage.setTxt_Telephone(BaseClass.getRandomPhoneNumber());
    }
    @When("User enters Telephone {string}")
    public void user_enters_telephone(String telephone) {
        registrationPage.setTxt_Telephone(telephone);
    }
    @When("User enters valid Password")
    public void user_enters_valid_password() {
       registrationPage.setTxt_Password(password);
    }
    @When("User enters Password {string}")
    public void user_enters_password(String pwd) {
        registrationPage.setTxt_Password(pwd);
    }
    @When("User confirms valid Password")
    public void user_confirms_valid_password() {
     registrationPage.setTxt_ConfirmPassword(password);
    }
    @When("User confirms Password {string}")
    public void user_confirms_password(String pwd) {
    registrationPage.setTxt_ConfirmPassword(pwd);
    }
    @When("User accepts Privacy Policy")
    public void user_accepts_privacy_policy() {
       registrationPage.setChk_PrivacyPolicy();
    }
    @When("User clicks Register")
    public void user_clicks_register() {
        registrationPage.clickBtn_Register();
    }
    @Then("Registration should be successful and Success message should be displayed")
    public void registration_should_be_successful() {
        accountSuccess=new AccountSuccess(DriverFactory.getDriver());
        boolean actual_msg=accountSuccess.getTxt_SuccessMsg();
        Assert.assertTrue(actual_msg);
    }

    @Then("Warning message should be displayed")
    public void Warning_message_should_be_displayed() {
       boolean actualWarning=registrationPage.getWarning_Msg();
       Assert.assertTrue(actualWarning);
    }

    @But("User account should not be created")
    public void user_account_should_not_be_created() {
        Assert.assertNotEquals(BaseClass.getTitle() ,"Your Account Has Been Created");
    }
}

