package com.qa.tests;
import com.qa.BaseTest;
import com.qa.listeners.testListener;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.pages.DashboardPage;
import com.qa.pages.MainPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners({testListener.class})
public class IOSDashboardTests extends BaseTest{
    MainPage mainPage;
    DashboardPage dashboardPage;
    
    @BeforeMethod
    public void beforeMethod(Method m) {
  	dashboardPage = new DashboardPage();
  	mainPage = new MainPage();

  	  System.out.println("\n" + "********* Starting test:" + m.getName() + "*********" + "\n");
  	  
   	 //closeApp();
 	 //launchApp();
  	  
    }
    
    @Test
    @Description("Verify that the Dashboard screen loads, and that the Bitcoin card is present")
    @Severity(SeverityLevel.NORMAL)
    public void iOSDashboardTests() throws InterruptedException  {
    
	
    mainPage.pressDashboardBtn();
    
    //Verify Dashboard page is displayed and verify page title
    verifyText("Bitcoin label", dashboardPage.checkPageTitleText(), "dashboard-title");

    
    //Verify Bitcoin label and symbol
    verifyText("Bitcoin label", dashboardPage.checkBitcoinLabelText(), "Coin-btc");

    }

}
