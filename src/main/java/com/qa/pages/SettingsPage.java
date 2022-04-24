package com.qa.pages;

import com.qa.BaseTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SettingsPage extends BaseTest{
	   
	@AndroidFindBy(accessibility = "settings-title")
	@iOSXCUITFindBy(accessibility="settings-title")
	    public MobileElement pageTitle;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"settings\"]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.CheckBox")
	private MobileElement onlyBitcoinCheckbox;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"settings\"]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView")
	@iOSXCUITFindBy(accessibility="Only show \"Bitcoin\" coins")
	public MobileElement onlyBitcoinLabel;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"settings\"]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView")
	@iOSXCUITFindBy(accessibility="Only show winners")
	public MobileElement onlyWinnersLabel;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"settings\"]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.widget.TextView")
	@iOSXCUITFindBy(accessibility="Only show losers")
	public MobileElement onlyLosersLabel;

	//functions
	
	public String checkPageTitleText() {
    	try {
    		return getAttribute(pageTitle, "text");
    	}catch(Exception e) {
	return getAttribute(pageTitle, "label");
	    }
	}
	
	
	public String checkOnlyBitcoinLabelText() {
	   	try {
    		return getAttribute(onlyBitcoinLabel, "text");
    	}catch(Exception e) {
	return getAttribute(onlyBitcoinLabel, "label");
	    }
	}
	    
	public void pressOnlyBitcoinCheckbox() {
	   click(onlyBitcoinCheckbox);
	   }
	
	public String checkOnlyBitcoinCheckbox() {
	return getAttribute(onlyBitcoinLabel, "checked");
	    }
	
	public String checkOnlyWinnersLabelText() {
	   	try {
    		return getAttribute(onlyWinnersLabel, "text");
    	}catch(Exception e) {
	return getAttribute(onlyWinnersLabel, "label");
	    }
	}
	
	public String checkOnlyLosersLabelText() {
	   	try {
    		return getAttribute(onlyLosersLabel, "text");
    	}catch(Exception e) {
	return getAttribute(onlyLosersLabel, "label");
	    }
	}

}
