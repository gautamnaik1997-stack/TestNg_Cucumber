package stepDefinitions;

import Core.BaseClass;
import Core.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

import java.sql.Driver;

public class VerifyLogin {
    HomePage homePage;
    LoginPage loginPage;
    private static final Logger logger = LogManager.getLogger(VerifyLogin.class);

    @When("User Clicks on MyAccount then Login button")
    public void user_clicks_on_my_account_then_login_button() {
        logger.debug("User clicks on MyAccount then Login button");
        homePage = new HomePage(DriverFactory.getDriver());
        homePage.clickMyAccount();
        homePage.clickLogin();
    }

    @When("User enters email {string} and password {string}")
    public void user_enters_email_and_password(String email, String password) {
        logger.debug("User enters email and password");
        loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.setEmail(email);
        loginPage.setPassword(password);
    }

    @When("User clicks Login")
    public void user_clicks_login() {
        logger.debug("User clicks Login");
        loginPage.clickLoginBtn();
    }

    @Then("The title of the page should be {string}")
    public void the_title_of_the_page_should_be(String title) {
        logger.debug("The title of the page should be " + title);
        Assert.assertEquals(BaseClass.getTitle(), title, "Login Not Successful");
    }
}
