package pageObjects;

import Utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']") WebElement txt_FirstName;
    @FindBy(xpath = "//input[@id='input-lastname']") WebElement txt_LastName;
    @FindBy(xpath = "//input[@id='input-telephone']") WebElement txt_Telephone;
    @FindBy(xpath = "//input[@id='input-email']") WebElement txt_Email;
    @FindBy(xpath = "//input[@id='input-password']") WebElement txt_Password;
    @FindBy(xpath = "//input[@id='input-confirm']") WebElement txt_ConfirmPassword;
    @FindBy(xpath = "//input[@name='agree']") WebElement chk_PrivacyPolicy;
    @FindBy(xpath = "//input[@value='Continue']") WebElement btn_Register;
    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']") WebElement warning_Msg;

    public void setTxt_FirstName(String firstName) {
        txt_FirstName.sendKeys(firstName);
    }

    public void setTxt_LastName(String lastName) {
        txt_LastName.sendKeys(lastName);
    }
    public void setTxt_Email(String email) {
        txt_Email.sendKeys(email);
    }
    public void setTxt_Telephone(String telephone) {
        txt_Telephone.sendKeys(telephone);
    }
    public void setTxt_Password(String password) {
        txt_Password.sendKeys(password);
    }
    public void setTxt_ConfirmPassword(String password) {
        txt_ConfirmPassword.sendKeys(password);
    }
    public void setChk_PrivacyPolicy() {
        chk_PrivacyPolicy.click();
    }
    public void clickBtn_Register() {
        btn_Register.click();
    }
    public boolean getWarning_Msg() {
       WebElement msg = WaitHelper.explicitWait(driver, warning_Msg, 10);
       return msg.isDisplayed();
    }
}
