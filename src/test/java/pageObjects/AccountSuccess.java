package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountSuccess extends BasePage {
    public AccountSuccess(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement txt_SuccessMsg;

    public boolean getTxt_SuccessMsg() {
        return txt_SuccessMsg.isDisplayed();
    }
}
