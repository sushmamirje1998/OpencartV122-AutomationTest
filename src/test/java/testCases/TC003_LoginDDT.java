package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 * Data is valid - login success - test pass - logout
 * 				- login failed - test fail
 * Data is invalid - login success - test fail - logout
 * 				   - login failed - test pass  
 */
public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")
	public void verify_DDT(String email, String pwd, String expResult){
		logger.info("Starting TC003_LoginDDT");
		try {
				//Home page
				HomePage hp=new HomePage(driver);
				logger.info("Click My Account");
				hp.clickMyAccount();
				hp.clickLogin();
				
				//Login page
				//logger.info("Inside login page");
				LoginPage login=new LoginPage(driver);
				login.setEmail(email);
				login.setPassword(pwd);
				login.clickLogin();
				
				//MyAccount check
				MyAccountPage myAcc=new MyAccountPage(driver);
				logger.info("MyAccount check");
				boolean targetPage=myAcc.isMyAccountPageExist();
				/*
				 * Data is valid - login success - test pass - logout
				 * 				- login failed - test fail
				 * Data is invalid - login success - test fail - logout
				 * 				   - login failed - test pass  
				 */
				if(expResult.equalsIgnoreCase("Valid")) {
					if(targetPage==true) {
						Assert.assertTrue(true);
						myAcc.clickLogout();
					}else {
						Assert.assertTrue(false);
					}
				}
				
				if(expResult.equalsIgnoreCase("Invalid")) {
					if(targetPage==true) {
						myAcc.clickLogout();
						Assert.assertTrue(false);
					}else {
						Assert.assertTrue(true);
					}
				}
		}catch(Exception e) {
			Assert.fail();
		}
				logger.info("Finished TC003_LoginDDT");
				
	}

}
