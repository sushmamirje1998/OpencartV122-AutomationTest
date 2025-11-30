package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmailAddress;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath="//input[@value='Login']") WebElement btnLogin;
	
	//Parameters email, paswd passed from test case
	public void setEmail(String email) {
		txtEmailAddress.clear();
		txtEmailAddress.sendKeys(email);
	}
	
	public void setPassword(String paswd) {
		txtPassword.clear();
		txtPassword.sendKeys(paswd);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}

}
