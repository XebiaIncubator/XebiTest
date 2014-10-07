package com.appium.pages;

import static org.junit.Assert.assertEquals;
import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.appium.helper.DriverFactory;
import com.appium.utils.PropertyFileReader;

public class PaymentPage extends DriverFactory{
	
	By checkoutButton = null;
	By title = null;
	
	By amountText = null;
	By amountLabel = null;
	
	
	public PaymentPage(AppiumDriver driver) throws InterruptedException{
		Thread.sleep(5000);
		
		PropertyFileReader prop = new PropertyFileReader();
		if(propertyFileName == "android"){
			checkoutButton = By.id(prop.returnPropVal("checkoutButton"));
			title = By.id(prop.returnPropVal("title"));
			
			amountText = By.id(prop.returnPropVal("amountText"));
		}
		
		//wait for next page
		waitVar.until(ExpectedConditions.presenceOfElementLocated(checkoutButton));
		//verifying the title of the screen:
		assertEquals("Take Payment", driver.findElement(title).getText());
	}
	
	public void enterCheckoutAmount(String amount){
		//enter checkout amount:
		driver.findElement(amountText).clear();
		driver.findElement(amountText).sendKeys(amount);
	}
	
	public void verifyEnteredAmount(String amount){
		//verify the text on screen:
		 assertEquals(amount, driver.findElement(amountLabel).getText());
	}
	
	public void clickCheckout(){
		//click on checkout
		 driver.findElement(checkoutButton).click();
	}
	
}
