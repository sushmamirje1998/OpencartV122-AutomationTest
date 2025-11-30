package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//Find all elements of register page
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephon;
	
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtConfirmPassword;
	
	//@FindBy(xpath="//input[@value='0']") WebElement radioSubscribe;
	
	@FindBy(xpath="//input[@name='agree']") WebElement txtPolicy;
	
	@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;
	
	
	//Create action for each element
	public void setFirstName(String fname) {
		txtFirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastname.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String tel) {
		txtTelephon.sendKeys(tel);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfPassword(String cpwd) {
		txtConfirmPassword.sendKeys(cpwd);
	}
	
	public void setPolicy() {
		txtPolicy.click();
	}
	
	
	public void clickContinue() {
		//Sol 1
		btnContinue.click();
		
//		//Sol 2
//		btnContinue.submit();
//		
//		//sol3
//		Actions act=new Actions(driver);
//		act.moveToElement(btnContinue).click().perform();
//		
//		//sol4
//		JavascriptExecutor js=(JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click()", btnContinue);
	}
	
	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		}catch(Exception e) {
			return (e.getMessage());
		}
	}
	

}
