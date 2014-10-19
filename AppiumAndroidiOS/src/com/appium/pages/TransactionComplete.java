package com.appium.pages;

import static org.junit.Assert.assertEquals;
import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.appium.helper.DriverFactory;
import com.appium.utils.PropertyFileReader;

public class TransactionComplete extends DriverFactory{
	
	
	By doneButton = null;
	By title = null;
	
	By value = null;
	
	public TransactionComplete(AppiumDriver driver) throws InterruptedException{
		Thread.sleep(5000);
		PropertyFileReader prop = new PropertyFileReader();
		if(propertyFileName == "android"){
			doneButton = By.name(prop.returnPropVal("doneButton"));
			title = By.id(prop.returnPropVal("title"));
			value = By.id(prop.returnPropVal("value"));
		}
		if(propertyFileName == "iOS"){
			doneButton = By.name(prop.returnPropVal("doneButton"));
			title = By.xpath(prop.returnPropVal("title"));
			value = By.xpath(prop.returnPropVal("value"));
		}
		//wait for next screen:
		 waitVar.until(ExpectedConditions.elementToBeClickable(doneButton));
		 //verify screen title:
		 assertEquals("Transaction Complete", driver.findElement(title).getText());
	}
	 
	public void verifyTransactionValue(String Value)
	{
		//verify Transaction value:
		if(propertyFileName == "android"){
			assertEquals(Value, driver.findElement(value).getText());
		}
		else
			assertEquals("$0.02", driver.findElement(value).getText());
		
	}
	 

}
