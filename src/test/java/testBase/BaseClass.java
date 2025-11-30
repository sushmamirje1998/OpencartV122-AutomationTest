package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

//Contains common methods required for all class
//To achieve re-usability and to avoid duplication
public class BaseClass {
	
	public static WebDriver driver; 		//will be static to avoid conflict when we create BaseClass object again it will call driver
	public Logger logger;
	public Properties p;
	@BeforeClass(groups={"Sanity","Regressoin","Master","DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
//			String hubURL="http://192.168.56.1:4444/wd/hub";
			DesiredCapabilities dc=new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows")) {
			dc.setPlatform(Platform.WIN11);
			}else if(os.equalsIgnoreCase("mac")) {
				dc.setPlatform(Platform.MAC);
			}else {
				System.out.println("No matching os");
				return;
			}
			//browser
			switch(br.toLowerCase()) {
			case "chrome" : dc.setBrowserName("chrome"); break;
			case "edge" : dc.setBrowserName("MicrosoftEdge"); break;
//			case "firefox" : dc.setBrowserName("firefox"); break;
			default : System.out.println("Invalid browser name.."); return;	//return used to exit from switch if invalid browser
		}
			driver=new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/hub"),dc);
		}
		
		
		logger=LogManager.getLogger(this.getClass());	//get class name to generate logs from that class
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(br.toLowerCase()) {
				case "chrome" : driver=new ChromeDriver(); break;
				case "edge" : driver=new EdgeDriver(); break;
				case "firefox" : driver=new FirefoxDriver(); break;
				default : System.out.println("Invalid browser name.."); return;	//return used to exit from switch if invalid browser
			}
		}
//		driver=new ChromeDriver();	
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
		driver.get(p.getProperty("appURL"));		//Reading URL from config.properties file
		driver.manage().window().maximize();
	}

	@AfterClass(groups={"Sanity","Regressoin","Master","DataDriven"})
	public void tearDown() {
		driver.quit();
		
	}
	
	public String randomString() {
		String generateString=RandomStringUtils.randomAlphabetic(5);
		return generateString;
	}
	
	public String randomNumber() {
		String generateNumber=RandomStringUtils.randomNumeric(10);
		return generateNumber;
	}
	
	public String randomAlphaNumeric() {
		String generateString=RandomStringUtils.randomAlphabetic(3);
		String generateNumber=RandomStringUtils.randomNumeric(3);
		return (generateString+"@"+generateNumber);
	}

	public String captureScreen(String name) {
		// TODO Auto-generated method stub
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot sc=(TakesScreenshot) driver;
		File sourceFile=sc.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+name+"_"+timeStamp;
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
