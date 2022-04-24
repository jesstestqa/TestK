package com.qa.listeners;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.qa.BaseTest;

import io.qameta.allure.Attachment;

public class testListener implements ITestListener{
	
	/*public void onTestFailure(ITestResult result) {
		if(result.getThrowable() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			result.getThrowable().printStackTrace(pw);
			System.out.println(sw.toString());
			
		}
		
		BaseTest base = new BaseTest();
		File file = base.getDriver().getScreenshotAs(OutputType.FILE);
		
		Map <String, String> params = new HashMap<String, String>();
		params =  result.getTestContext().getCurrentXmlTest().getAllParameters();
		
		String imagePath = "Screenshots" + File.separator + params.get("platformName") + "_" + params.get("platformVersion") 
		+ "_" + params.get("deviceName") + File.separator + base.getDateTime() + File.separator 
		+  result.getTestClass().getRealClass().getSimpleName() + File.separator + result.getName() + ".png";
		
		String completeImagePath = System.getProperty("user.dir") + File.separator + imagePath;
		
		try {
			FileUtils.copyFile(file, new File(imagePath));
			Reporter.log("screenshot of failure from " + result.getTestClass().getRealClass().getSimpleName() + "-" + result.getMethod().getMethodName());
			Reporter.log("<a href='" + completeImagePath + "'> <img src='" + completeImagePath + "' height='100' width='100' /> </a>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}*/
	//Allure screenshot for failure
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	@Attachment
	public byte[] saveFailureScreenshot(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	
	@Override
	public void onStart(ITestContext iTestContext) {
		iTestContext.setAttribute("WebDriver", BaseTest.getDriver());
	}
	
	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am in onFinish method " + iTestContext.getName());
	}
	
	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
	}
	
	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
		Object testClass = iTestResult.getInstance();
		WebDriver driver = BaseTest.getDriver();
		// Allure ScreenShot and SaveTestLog
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveFailureScreenshot(driver);
		}
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");	
	}
	
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}
}
