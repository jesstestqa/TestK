package com.qa.tests;
import com.qa.BaseTest;
import com.qa.SideMenuPage;
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
public class SlidingMenuTests extends BaseTest{
    MainPage mainPage;
    DashboardPage dashboardPage;
    SideMenuPage sideMenuPage;
    
    @BeforeMethod
    public void beforeMethod(Method m) {
  	dashboardPage = new DashboardPage();
  	mainPage = new MainPage();
  	sideMenuPage = new SideMenuPage();

  	  System.out.println("\n" + "********* Starting test:" + m.getName() + "*********" + "\n");
  	  
    }
    
    @Test
    @Description("Verify that the side menu slides in from the left, that the text on slide menu, that the button and its pop up work, and that the menu can be dismissed")
    @Severity(SeverityLevel.NORMAL)
    public void slidingMenu() throws InterruptedException {
    	
    	sideMenuPage.slideMenu(1);
    	
    	verifyText("Side Menu Title", sideMenuPage.checkPageTitleText(), "Side menu");
    	
    	verifyText("Side Menu Body Text", sideMenuPage.checkSideMenuBodyText(), "Legend says that if you click on the button below, you may get the answer to the Ultimate Question of Life, the Universe, and Everything...");
	
    	sideMenuPage.pressSideMenuBn();
    	Thread.sleep(4000);
    	
    	verifyText("Pop up has appeared", sideMenuPage.checkpopUpText(), "42");
    	
    	sideMenuPage.pressPopUpOkBtn();
    	
    	sideMenuPage.slideMenu(2);
    	
    	try {
    		waitForVisibility(sideMenuPage.pageTitle);
    		Assert.assertTrue(false);
    	}catch(Exception e) {
    		System.out.println("The side menu has been dismissed and is not visible.");
    		Reporter.log("The side menu has been dismissed and is not visible.");
    	}
    	
    }

}
