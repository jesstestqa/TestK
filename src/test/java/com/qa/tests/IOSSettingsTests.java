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
public class IOSSettingsTests extends BaseTest {
	
    MainPage mainPage;
    DashboardPage dashboardPage;
    SettingsPage settingsPage;
    
    @BeforeMethod
    public void beforeMethod(Method m) {
  	dashboardPage = new DashboardPage();
  	mainPage = new MainPage();
  	settingsPage = new SettingsPage();

  	 System.out.println("\n" + "********* Starting test:" + m.getName() + "*********" + "\n");
  	  
  	 //closeApp();
	 //launchApp();
    }
    
    @Test
    @Description("Verify that the Settings screen loads, that the 3 settings options populate with correct text")
    @Severity(SeverityLevel.NORMAL)
    public void iOSSettingsTest() throws InterruptedException  {
    	
    mainPage.pressSettingsBtn();
    	
    //Verify Settings page is displayed and verify page title
    	
    verifyText("Page Title", settingsPage.checkPageTitleText(), "settings-title");
    
    //Bitcoin Only checkbox test
    
    verifyText("Only Bitcoin text", settingsPage.checkOnlyBitcoinLabelText(), "Only show \"Bitcoin\" coins");
    
    verifyText("Only winners text", settingsPage.checkOnlyWinnersLabelText(), "Only show winners");
    
    verifyText("Only losers text", settingsPage.checkOnlyLosersLabelText(), "Only show losers");
    
    }
    
   
    @Test
    @Description("Intentional failure to test screenshot for fail")
    @Severity(SeverityLevel.NORMAL)
    public void IOSSettingFailTest() throws InterruptedException  {
    	
    mainPage.pressSettingsBtn();

    verifyText("Only Bitcoin text", settingsPage.checkOnlyBitcoinLabelText(), "error");
    Reporter.log("This test fails intentionally to test the screenshot functionality for test failures.");
}
    
    
}
