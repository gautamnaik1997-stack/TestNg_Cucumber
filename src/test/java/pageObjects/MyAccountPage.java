package pageObjects;

import Utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//h2[normalize-space()='My Account']")
    WebElement label_MyAccount;
    By lnk_LogOut = By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']");

    public boolean confirmMsg() {
        boolean status = label_MyAccount.isDisplayed();
        return status;
    }
    public void clickLogout() {
        WebElement lnkLogOut = WaitHelper.explicitWait(driver, lnk_LogOut, 10);
        lnkLogOut.click();
    }
}
