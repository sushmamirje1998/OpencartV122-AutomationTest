package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	WebDriver driver;
	
	//Parent class to all the classes
	//Will receive the driver from child class and initialize the driver
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

}
