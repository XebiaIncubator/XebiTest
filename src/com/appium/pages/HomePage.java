package com.appium.pages;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.appium.helper.DriverFactory;
import com.appium.utils.PropertyFileReader;

import static org.junit.Assert.*;

public class HomePage extends DriverFactory{
	
	PropertyFileReader prop = new PropertyFileReader();
	
	By signInButton = null;
	By userName = null;
	By password = null;
	By done = null;
	
	public HomePage(AppiumDriver driver) throws InterruptedException{
		Thread.sleep(5000);
		
		PropertyFileReader prop = new PropertyFileReader();
		if(propertyFileName == "android"){
			signInButton = By.id(prop.returnPropVal("signInButton"));
			userName = By.id(prop.returnPropVal("userName"));
			password = By.id(prop.returnPropVal("password"));
			done = By.name(prop.returnPropVal("done"));
		}
		waitVar.until(ExpectedConditions.elementToBeClickable(signInButton));
		
		assertTrue(driver.findElement(signInButton).isEnabled());
		
	}
	
	public void login(String Username, String Password){
		//enter data in username and password
		driver.findElement(userName).clear();
		driver.findElement(userName).sendKeys(Username);
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(Password);
		if(propertyFileName == "iOS"){
		//click done to get the focus back to main window
		driver.findElement(done).click();
		waitVar.until(ExpectedConditions.presenceOfElementLocated(signInButton));
		}
		//click login
		driver.findElement(signInButton).click();
	}
	

}
