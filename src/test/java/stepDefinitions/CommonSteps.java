package stepDefinitions;

import Core.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageObjects.MyAccountPage;

public class CommonSteps {
    private static final Logger logger = LogManager.getLogger(CommonSteps.class);
    MyAccountPage myAccountPage;
    @Given("User launches browser and enters the url")
    public void user_launches_browser_and_enters_the_url() {
        logger.info("Launching browser and entering the url");
    }
    @Then("User clicks Logout")
    public void user_clicks_logout() {
        myAccountPage=new MyAccountPage(DriverFactory.getDriver());
        myAccountPage.clickLogout();
    }
}
