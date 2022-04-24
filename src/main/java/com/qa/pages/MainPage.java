package com.qa.pages;

import com.qa.BaseTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MainPage extends BaseTest{

	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.View/android.view.View[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView")
	@iOSXCUITFindBy(accessibility="Dashboard tab")
	private MobileElement dashboardTab;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Settings\")")
    @iOSXCUITFindBy(accessibility="Settings tab")
    private MobileElement settingsTab;

    // Functions
    public DashboardPage pressDashboardBtn() throws InterruptedException {
        click(dashboardTab);
        return new DashboardPage();
    }
    
	public String checkDashboardTabSelected() {
	return getAttribute(dashboardTab, "selected");
	    }

    public SettingsPage pressSettingsBtn() throws InterruptedException {
        click(settingsTab);
        return new SettingsPage();
    }
    
	public String checkSettingsSelected() {
	return getAttribute(settingsTab, "selected");
	    }

}
