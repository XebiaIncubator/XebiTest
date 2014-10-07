package com.appium.test;

import io.appium.java_client.AppiumDriver;

import org.testng.annotations.Test;

import com.appium.helper.DriverFactory;
import com.appium.pages.HomePage;
import com.appium.pages.PaymentPage;
import com.appium.pages.SwipePage;
import com.appium.pages.TransactionComplete;

public class testandroid extends DriverFactory{
	
	@Test(groups="android",alwaysRun=true)
	public void testandroidcase() throws InterruptedException{
		HomePage hp = new HomePage(driver);
		hp.login("Shankar", "Shankar");
		
		PaymentPage pp = new PaymentPage(driver);
		pp.enterCheckoutAmount("2");
		pp.clickCheckout();
		
		SwipePage sp = new SwipePage(driver);
		sp.clickPay();
		
		TransactionComplete tc = new TransactionComplete(driver);
		tc.verifyTransactionValue("$2.0");
		
		
	}

}
