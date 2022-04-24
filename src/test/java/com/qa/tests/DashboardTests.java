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
public class DashboardTests extends BaseTest{
    MainPage mainPage;
    DashboardPage dashboardPage;

    
    @BeforeMethod
    public void beforeMethod(Method m) {
  	dashboardPage = new DashboardPage();
  	mainPage = new MainPage();

  	  System.out.println("\n" + "********* Starting test:" + m.getName() + "*********" + "\n");
  	  
   	 closeApp();
 	 launchApp();
  	  
    }
    
    @Test()
    @Description("Verify that the Dashboard screen loads, that the title is correct and that the Bitcoin card data is correct")
    @Severity(SeverityLevel.NORMAL)
    public void dashboardTests() throws InterruptedException  {
    
	
    mainPage.pressDashboardBtn();
    
    //Verify Dashboard page is displayed and verify page title
    
    Assert.assertTrue(dashboardPage.checkPageTitleText().contains(" coins"));
    String dashboardTitle = "";
    dashboardTitle = stripCharactersFromNumberString(dashboardTitle, dashboardPage.checkPageTitleText());
    convertToIntandCompare(dashboardTitle, 0, 1000000000);
    System.out.println("Verified that the title includes a number equal to or greater than 0 and \"coins\"");
    Reporter.log("Verified that the title includes a number equal to or greater than 0 and \"coins\"");
    
    //Verify Bitcoin label and symbol
    verifyText("Bitcoin label", dashboardPage.checkBitcoinLabelText(), "Bitcoin");
    verifyText("Bitcoin symbol", dashboardPage.checkBitcoinSymbolText(), "BTC");
    
    //Verify Bitcoin price
    Assert.assertTrue(dashboardPage.checkBitcoinPriceText().contains("$") &&  dashboardPage.checkBitcoinPriceText().contains("."));
    Reporter.log("Verified Bitcoin price format includes the $ character and the . character");
    
    String bitcoinPrice = "";
    bitcoinPrice = stripCharactersFromNumberString(bitcoinPrice, dashboardPage.checkBitcoinPriceText());
    convertToDoubleandCompare(bitcoinPrice, 0.0, 1000000000.0);
    
    //Verify bitcoin percentage
    Assert.assertTrue(dashboardPage.checkBitcoinPercentageText().contains("%") &&  dashboardPage.checkBitcoinPercentageText().contains("."));
    Reporter.log("Verified Bitcoin price format includes the % character and the . character");
    
    String bitcoinPercent = "";
    bitcoinPercent = stripCharactersFromNumberString(bitcoinPercent, dashboardPage.checkBitcoinPercentageText());
    convertToDoubleandCompare(bitcoinPercent, 0.0, 1000000000.0);

}

}
