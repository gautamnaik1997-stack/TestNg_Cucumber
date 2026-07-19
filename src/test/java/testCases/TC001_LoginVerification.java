package testCases;

import Core.BaseClass;
import Core.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC001_LoginVerification extends BaseClass {
    @Test
    public void verifyLoginTest() {
        try {
            logger.info("********** Starting TC001_LoginVerification **********");
            HomePage hp = new HomePage(DriverFactory.getDriver());
            logger.info("********** Clicking Login button from dropdown **********");
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(DriverFactory.getDriver());
            logger.info("********** Setting email and password for login **********");
            lp.setEmail("testuser@eclipse.com");
            lp.setPassword("testuser@123");
            lp.clickLoginBtn();

            MyAccountPage map = new MyAccountPage(DriverFactory.getDriver());
            boolean actualStatus = map.confirmMsg();
            boolean expectedStatus = true;
            Assert.assertEquals(actualStatus, expectedStatus, "Status Mismatched");
            logger.info("********** Successful Login **********");
        }catch(Exception e) {
            logger.debug("TestExecution failed", e);
            Assert.fail(e.getMessage());
        }finally {
            logger.info("********** Ending TC001_LoginVerification **********");
        }
    }
}
