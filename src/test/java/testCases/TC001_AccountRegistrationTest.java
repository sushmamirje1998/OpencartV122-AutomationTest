package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {
		logger.info("Starting TC_AccountRegistrationTest");
		try {
		HomePage hp=new HomePage(driver);
		logger.info("Clicked on My Account link");
		hp.clickMyAccount();
		logger.info("Clicked on Register link");
		hp.clickRegister();
		
		AccountRegistrationPage reg=new AccountRegistrationPage(driver);
		logger.info("Providing customer details");
		reg.setFirstName(randomString().toUpperCase());
		reg.setLastName(randomString().toUpperCase());
		reg.setEmail(randomString()+"@gmail.com");
		reg.setTelephone(randomNumber());
		//Catch value in variable, so that it can't generate new password for conf passwd
		String password=randomAlphaNumeric();
		reg.setPassword(password);
		reg.setConfPassword(password);
		reg.setPolicy();
		reg.clickContinue();
		
		logger.info("Validating expected message");
		String confMsg=reg.getConfirmationMsg();
		if(confMsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}else {
			logger.error("Something wrong.. Test failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confMsg, "Your Account Has Been Created!");
		}catch(Exception e) {
			
			Assert.fail();
		}
		
		logger.info("Finished");
		
	}
	

}
