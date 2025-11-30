package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter sparkReporter;	
	public ExtentReports extent;		
	public ExtentTest test;	
	String repName;
	
	public void onStart(ITestContext context) {
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timeStamp+".html";
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+repName);
		sparkReporter.config().setDocumentTitle("OpenCart Automation report");	//Title of report
		sparkReporter.config().setReportName("OpenCart Functional testing");  //Name of the report
		//sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Environment", "QA"); 
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("SubModule", "Customers");
		extent.setSystemInfo("UserName", System.getProperty("user.name"));
		
		//capture os and browser from master.xml file which we have provided
		String os=context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating system", os);
		
		String browser=context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);	
		
		//Add what are the groups from groups.xml include tag, we have executed to report
		List<String> includeGroups=context.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty()) {
			//If group names are available, add them to report
			extent.setSystemInfo("Grouups", includeGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		//Here result contains test case result
		test=extent.createTest(result.getTestClass().getName());	//Get name/results from which class we executed
		test.assignCategory(result.getMethod().getGroups());		//Get groups
		test.log(Status.PASS, "Test case passed is: "+result.getName());	
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());	
		test.assignCategory(result.getMethod().getGroups());	
		test.log(Status.FAIL, "Test case failed is: "+result.getName());
		test.log(Status.INFO, "Test case failed is: "+result.getThrowable().getMessage());//Print error message
		//test.log(Status.FAIL, "Test case failed  because: "+result.getThrowable());
		
		//Attach screenshot of the report
		try {
			String imgPath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			//If screenshot is not available, will get file not found exception
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		//Here result contains test case result
		test=extent.createTest(result.getTestClass().getName());	
		test.assignCategory(result.getMethod().getGroups());	
		test.log(Status.SKIP, "Test case skipped is: "+result.getName());	
		test.log(Status.INFO, "Test case failed is: "+result.getThrowable().getMessage());
	}
	
	//If we don't have this method, above method will take care
	public void onFinish(ITestContext context) {
		extent.flush();
		//To open report automatically when test finishes
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport=new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	

}
