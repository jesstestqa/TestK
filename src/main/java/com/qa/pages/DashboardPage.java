package com.qa.pages;

import com.qa.BaseTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DashboardPage extends BaseTest{

	@AndroidFindBy(accessibility = "dashboard-title")
	@iOSXCUITFindBy(accessibility="dashboard-title")
    public MobileElement pageTitle;

	//Bitcoin
    @AndroidFindBy(accessibility = "Coin-btc-name")
    @iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`label == \"Coin-btc\"`]")
    public MobileElement bitcoinLabel;

    @AndroidFindBy(accessibility = "Coin-btc-symbol")
    @iOSXCUITFindBy(accessibility="Coin-btc-symbol")
    private MobileElement bitcoinSymbol;
    
    @AndroidFindBy(accessibility = "Coin-btc-price")
    @iOSXCUITFindBy(accessibility="Coin-btc-price")
    private MobileElement bitcoinPrice;

    @AndroidFindBy(accessibility = "Coin-btc-percentage")
    @iOSXCUITFindBy(accessibility="Coin-btc-percentage")
    private MobileElement bitcoinPercentage;
    
    //Ethereum
    @AndroidFindBy(accessibility = "Coin-eth-name")
    @iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`label == \"Coin-eth\"`]")
    public MobileElement ethereumLabel;

    //functions
    public String checkPageTitleText() {
    	try {
    		return getAttribute(pageTitle, "text");
    	}catch(Exception e) {
        return getAttribute(pageTitle, "label");
    }
    }

    public String checkBitcoinLabelText() {
    	try {
    		return getAttribute(bitcoinLabel, "text");
    	}catch(Exception e) {
        return getAttribute(bitcoinLabel, "label");
    }
    }

    public String checkBitcoinSymbolText() {
    	try {
    		return getAttribute(bitcoinSymbol, "text");
    	}catch(Exception e) {
        return getAttribute(bitcoinSymbol, "label");
    }
    }
    
    public String checkBitcoinPriceText() {
    	try {
    		return getAttribute(bitcoinPrice, "text");
    	}catch(Exception e) {
        return getAttribute(bitcoinPrice, "label");
    }
    }

    public String checkBitcoinPercentageText() {
    	try {
    		return getAttribute(bitcoinPercentage, "text");
    	}catch(Exception e) {
        return getAttribute(bitcoinPercentage, "label");
    }
    }

}
