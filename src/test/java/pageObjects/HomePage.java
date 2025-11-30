package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	//To invoke immediate parent class from child class
	//Constructor
	public HomePage(WebDriver driver) {
		super(driver);		//Passing driver tp parent class constructor
	}
	
	//Elements
	@FindBy(xpath="//span[text()='My Account']") WebElement lnkMyaccount;
	@FindBy(xpath="//a[text()='Register']") WebElement lnkRegister;
	@FindBy(xpath="//a[text()='Login']") WebElement lnkLogin;
	
	//Action
	public void clickMyAccount() {
		lnkMyaccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}

}
