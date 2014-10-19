package com.appium.pages;

import static org.junit.Assert.assertEquals;
import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.appium.helper.DriverFactory;
import com.appium.utils.PropertyFileReader;

public class SwipePage extends DriverFactory{
	
	By payButton = null;
	By title = null;
	
	public SwipePage(AppiumDriver driver) throws InterruptedException{
		Thread.sleep(5000);
		
		PropertyFileReader prop = new PropertyFileReader();
		if(propertyFileName == "android"){
			payButton = By.id(prop.returnPropVal("payButton"));
			title = By.id(prop.returnPropVal("title"));
		}
		if(propertyFileName == "iOS"){
			payButton = By.name(prop.returnPropVal("payButton"));
			title = By.xpath(prop.returnPropVal("title"));
		}
		//wait for next screen:
		 waitVar.until(ExpectedConditions.elementToBeClickable(payButton));
		 //verify screen title:
		 assertEquals("Payment", driver.findElement(title).getText());
	}
	
	public void clickPay()
	{
		 //click Pay:
		 driver.findElement(payButton).click();
	}


}
