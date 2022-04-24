package com.qa;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.testng.Reporter;
import org.testng.annotations.Parameters;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

@Parameters({"deviceName"})
public class SideMenuPage extends BaseTest {
	
	@AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"sidemenu\"]/android.view.ViewGroup/android.widget.TextView[1]")
	@iOSXCUITFindBy(accessibility="Side menu")
	public MobileElement pageTitle;
	
	@AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"sidemenu\"]/android.view.ViewGroup/android.widget.TextView[2]")
	@iOSXCUITFindBy(accessibility="Legend says that if you click on the button below, you may get the answer to the Ultimate Question of Life, the Universe, and Everything...")
	private MobileElement sideMenuBodyText;
	
	@AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"sidemenu\"]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
	@iOSXCUITFindBy(accessibility="Get the ultimate answer")
	private MobileElement sideMenuBtn;
	
	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView")
	@iOSXCUITFindBy(accessibility="42")
	private MobileElement popUpText;
	
	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button")
	@iOSXCUITFindBy(accessibility="OK")
	private MobileElement popUpOkBtn;
	
	//functions
	
	public void slideMenu(int a) {
		Dimension size = driver.manage().window().getSize();

		int startY =  (size.height / 2);
		int endY = (startY);
		int startX = 0;
		int endX = 0;

		switch (a) {
		case 1:
			startX =   (int) (size.width * .05);
			 endX = (int) (size.width * .6);
			System.out.println("Swiped to open menu.");
			Reporter.log("Swiped to open menu.");
			break;
		 case 2:
			startX =   (int) (size.width * .6);
			endX = (int) (size.width * .1);
			 System.out.println("Swiped to close menu.");
			 Reporter.log("Swiped to close menu.");
			 break;
		}

		TouchAction t = new TouchAction(driver);
		t.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
				.moveTo(PointOption.point(endX, endY)).release().perform();
	}
	
	public String checkPageTitleText() {
	try {
		return getAttribute(pageTitle, "text");
	}catch(Exception e) {
		return getAttribute(pageTitle, "label");
	    }
	}
	
	public String checkSideMenuBodyText() {
		try {
		return getAttribute(sideMenuBodyText, "text");
		}catch(Exception e) {
			return getAttribute(sideMenuBodyText, "label");
		}
	}
	
	public SideMenuPage pressSideMenuBn() {
		click(sideMenuBtn);
		return this;
	}
	
	public String checkpopUpText() {
		try {
		return getAttribute(popUpText, "text");
		}catch(Exception e) {
	return getAttribute(popUpText, "label");
	}
	}
	
	public SideMenuPage pressPopUpOkBtn() {
		click(popUpOkBtn);
		return this;
	}

}
