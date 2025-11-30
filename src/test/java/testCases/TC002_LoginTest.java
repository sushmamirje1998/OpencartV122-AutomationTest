package testCases;

import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity", "Master"})
	public void verify_login() {
		logger.info("Starting test case");
		try {
		//Home page
		HomePage hp=new HomePage(driver);
		logger.info("Click My Account");
		hp.clickMyAccount();
		logger.info("Click Login");
		hp.clickLogin();
		
		//Login page
		//logger.info("Inside login page");
		LoginPage login=new LoginPage(driver);
		logger.info("Pass email");
		login.setEmail(p.getProperty("email"));
		logger.info("Type password");
		login.setPassword(p.getProperty("password"));
		login.clickLogin();
		
		//MyAccount check
		MyAccountPage myAcc=new MyAccountPage(driver);
		logger.info("MyAccount check");
		boolean targetPage=myAcc.isMyAccountPageExist();
		Assert.assertTrue(targetPage);
		}catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("Finish login");
	}

}
