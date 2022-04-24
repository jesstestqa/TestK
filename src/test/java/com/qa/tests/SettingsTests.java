package com.qa.tests;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.BaseTest;
import com.qa.listeners.testListener;
import com.qa.pages.DashboardPage;
import com.qa.pages.MainPage;
import com.qa.pages.SettingsPage;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners({testListener.class})
public class SettingsTests extends BaseTest {
	
    MainPage mainPage;
    DashboardPage dashboardPage;
    SettingsPage settingsPage;
    
    @BeforeMethod
    public void beforeMethod(Method m) {
  	dashboardPage = new DashboardPage();
  	mainPage = new MainPage();
  	settingsPage = new SettingsPage();

  	 System.out.println("\n" + "********* Starting test:" + m.getName() + "*********" + "\n");
  	  
  	 closeApp();
	 launchApp();
    }
    
    @Test
    @Description("Verify that the Settings page loads, that the options are displayed, and that selecting the Bitcoin only option updates the Dashboard")
    @Severity(SeverityLevel.NORMAL)
    public void settingsTest() throws InterruptedException  {
    	
    mainPage.pressSettingsBtn();
    	
    //Verify Settings page is displayed and verify page title
  
    //verifyText("Settings Tab is highlighted in Menu Bar", mainPage.checkSettingsSelected(), "true");
    	
    verifyText("Page Title", settingsPage.checkPageTitleText(), "Application Settings");
    
    //Bitcoin Only checkbox test
    
    verifyText("Only Bitcoin text", settingsPage.checkOnlyBitcoinLabelText(), "Only show \"Bitcoin\" coins");
    
    verifyText("Only winners text", settingsPage.checkOnlyWinnersLabelText(), "Only show winners");
    
    verifyText("Only losers text", settingsPage.checkOnlyLosersLabelText(), "Only show losers");
    
    verifyText("Bitcoin only checkbox is not selected by default", settingsPage.checkOnlyBitcoinCheckbox(), "false");
    
    settingsPage.pressOnlyBitcoinCheckbox();
 
    
    //verifyText("Bitcoin only checkbox is selected", settingsPage.checkOnlyBitcoinCheckbox(), "true");
    
    mainPage.pressDashboardBtn();
    
    //Verify Bitcoin is listed
    verifyText("Bitcoin item is displayed on the dashboard", dashboardPage.checkBitcoinLabelText(), "Bitcoin");
    
    //Verify that non Bitcoin item is not listed
    try {
    	waitForVisibility(dashboardPage.ethereumLabel);
    	Reporter.log("Non bitcoin options are lised but should not be.");
    	Assert.assertTrue(false);
    	
    }catch(Exception e) {
    	System.out.println("Ethereum is no longer listed on the Dashboard.");
    	Reporter.log("Verified that non bitcoin options are not listed.");
    }
    }
    
   
    @Test
    @Description("Intentional failure to test screenshot for fail")
    @Severity(SeverityLevel.NORMAL)
    public void settingFailTest() throws InterruptedException  {
    	
    mainPage.pressSettingsBtn();

    verifyText("Bitcoin only checkbox is not selected by default", settingsPage.checkOnlyBitcoinCheckbox(), "true");
    Reporter.log("This test fails intentionally to test the screenshot functionality for test failures.");
}
    
    
}
