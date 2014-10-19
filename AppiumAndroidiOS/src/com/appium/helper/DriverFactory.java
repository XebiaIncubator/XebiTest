package com.appium.helper;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.TestNG;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class DriverFactory {

	public static AppiumDriver driver = null;
	public static WebDriverWait waitVar = null;
	public static int waitTime=20;
	
	@SuppressWarnings( "deprecation" )
    final String outputDIR=TestNG.getDefault().getOutputDirectory();
	public String NewFileNamePath = null;
	public String className = null;
	public String testName = null;
	
	public static String browser="";
	public static Platform currentOS = Platform.getCurrent();
	public static String propertyFileName = null;
	

	@BeforeSuite( alwaysRun=true )
	public static void getDriverInstance() throws Exception
	{
		System.out.println("Inside Before Suite");
		
	    browser=System.getProperty("browser");
	    
	    System.out.println("Browser= "+browser);
	    System.out.println("Platform= "+currentOS);
	    
		createDriver(browser);
	}

	public static void createDriver(final String browserId) throws Exception {
		System.out.println("inside setup method");
		
		if(browserId.equalsIgnoreCase("iOS")){
			returnIOSDriver();
			propertyFileName = "iOS";
		}
		if(browserId.equalsIgnoreCase("android")){
			returnAndroidDriver();
			propertyFileName = "android";
		}
		
        waitVar= new WebDriverWait( driver, waitTime );
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
        
	}
	
	
	public static void returnIOSDriver() throws MalformedURLException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities
				.setCapability(
						"app",
						"/Users/Apple/Library/Developer/Xcode/DerivedData/PayCloud-ffksktfojvhzadaztzhtikjpdhdv/Build/Products/Debug-iphonesimulator/PayCloud.app");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
		capabilities.setCapability("platformVersion", "7.1");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("deviceName", "iPhone Simulator");
		driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);

		waitVar = new WebDriverWait(driver, 90);
		System.out.println("iOS started");
	}

	public static void returnAndroidDriver() throws MalformedURLException{
		// set up appium
		final File classpathRoot = new File(System.getProperty("user.dir"));
		final File appDir = new File(classpathRoot, "../resources/");
		final File app = new File(appDir, "AndroidAppiumPayCloud.apk");

		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "4.4");
		capabilities.setCapability("app", app.getAbsolutePath());

		driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);

		waitVar = new WebDriverWait(driver, 90);
		System.out.println("android started");
	}
	
	
	@AfterSuite( alwaysRun=true)
	public void tearDown() throws Exception {
		//Close the app and simulator
		System.out.println("Inside quit");
		driver.quit();
	}
	

}