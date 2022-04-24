package com.qa;

import org.testng.annotations.Test;

import com.qa.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import java.io.*;

public class BaseTest {
	protected static AppiumDriver driver;
	protected static Properties props;
	protected static String dateTime;
	InputStream inputStream;
	TestUtils utils;
	
	public BaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

  @Parameters({"udid", "platformVersion", "deviceName"})
  @BeforeTest
  public void beforeTest(String udid, String platformVersion, String deviceName) throws IOException {
	  utils = new TestUtils();
	  dateTime = utils.getDateTime();
	  try {
		  props = new Properties();
		  String propFileName = "config.properties";
		  inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		  props.load(inputStream);
	  DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	  if(deviceName.equals("Galaxy S21 5G")) {
	  desiredCapabilities.setCapability("platformName", props.getProperty("androidPlatformName"));
	  desiredCapabilities.setCapability("appium:udid", udid);
	  desiredCapabilities.setCapability("platformVersion", platformVersion);
	  desiredCapabilities.setCapability("deviceName", deviceName);
	  desiredCapabilities.setCapability("automationName", props.getProperty("androidAutomationName"));
	  desiredCapabilities.setCapability("appPackage", props.getProperty("androidAppPackage"));
	  desiredCapabilities.setCapability("appActivity", props.getProperty("androidAppActivity"));
	  String appURL = getClass().getResource(props.getProperty("androidAppLocation")).getFile();
	  desiredCapabilities.setCapability("app", appURL);
	  System.out.println(appURL);
	  desiredCapabilities.setCapability("unlockType", props.getProperty("unlockType"));
	  desiredCapabilities.setCapability("unlockKey", props.getProperty("unlockKey"));
	  
	  }else {
		  desiredCapabilities.setCapability("platformName", props.getProperty("iOSPlatformName"));
		  desiredCapabilities.setCapability("deviceName", deviceName);
		  desiredCapabilities.setCapability("automationName", props.getProperty("iOSAutomationName"));
		  desiredCapabilities.setCapability("udid", udid);
		  String appURL = getClass().getResource(props.getProperty("iOSAppLocation")).getFile();
		  desiredCapabilities.setCapability("app", appURL);
		  System.out.println(appURL);
		  desiredCapabilities.setCapability("simulatorStartupTimeout", 180000);
		 

	  }
	  
		URL url = new URL(props.getProperty("appiumURL"));
		driver = new AndroidDriver(url, desiredCapabilities);
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  
  public static AppiumDriver getDriver() {
	  return driver;
  }
  
  public String getDateTime() {
	  return dateTime;
  }
  
  public void waitForVisibility(MobileElement e) {
	  WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
	  wait.until(ExpectedConditions.visibilityOf(e));
  }
  
  public void click(MobileElement e) {
	  waitForVisibility(e);
	  e.click();
	  System.out.println("Clicked button: " + e);
	  Reporter.log("Clicked button: " + e);
  }
  
  public void sendKeys(MobileElement e, String txt) {
	  waitForVisibility(e);
	  e.sendKeys(txt);
	  Reporter.log("entered text: " + txt + " to: " + e);
  }
  
  public String getAttribute(MobileElement e, String attribute) {
	  waitForVisibility(e);
	  return e.getAttribute(attribute);
  }
  
  public void verifyText(String print, String actual, String expected) throws InterruptedException {
      System.out.println("Verify " + print + ":");
      Thread.sleep(1000);
      Assert.assertEquals(actual, expected);
      System.out.println("Actual text: " + actual + " - Expected text: " + expected);
      Reporter.log("Verified that " + print + "." + " Actual text: " + actual + " - Expected text: " + expected);
      Thread.sleep(1000);
  }
  
  public String stripCharactersFromNumberString(String s, String s2) {
	    s = s2.replaceAll("[^\\d.]", "");
	    s = s.replaceAll("$", "");
	    s = s.replaceAll("%", "");
	    s = s.replaceAll("/.", "");
	    return s = s.replaceAll(" ", "");
	  
  }
  
	public void convertToIntandCompare(String s, int a, int b) {
		int day=Integer.parseInt(s);
		  Assert.assertTrue(day > a && day < b);
		  System.out.println("Verified that " + s + " is a number in the correct format.");
		  Reporter.log("Verified that " + s + " is a number in the correct format.");
	}
	
	public void convertToDoubleandCompare(String s, Double a, Double b) {
		Double day=Double.parseDouble(s);
		  Assert.assertTrue(day > a && day < b);
		  System.out.println("Verified that " + s + " is a number in the correct format.");
		  Reporter.log("Verified that " + s + " is a number in the correct format.");
	}
	
 	public void tapByCoordinate(int start, int end) {
		  TouchAction touchAction = new TouchAction(driver);
		  touchAction.tap(PointOption.point(start, end)).perform();
	}
	
	public void closeApp() {
		((InteractsWithApps) driver).terminateApp("com.awesomeapp");
	}

	public void launchApp() {
		driver.activateApp("com.awesomeapp");
	}

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
