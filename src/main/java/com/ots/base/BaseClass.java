package com.ots.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.ots.dataprovider.ConfigReader;
import com.ots.factory.BrowserFactory;

public class BaseClass 
{
	public WebDriver driver;
	
	@BeforeClass
	public void setup()
	{
		System.out.println("******** LOG:INFO- Running Setup ********");
		
		System.out.println("******** LOG:INFO- Application will be running on "+ ConfigReader.getProperty("browser")+" ********");
		
		driver=BrowserFactory.startBrowser(ConfigReader.getProperty("browser"),ConfigReader.getProperty("appURL"));

	}
	
	@AfterClass
	public void tearDown()
	{
		System.out.println("******** LOG:INFO- Running TearDown ********");
		
		BrowserFactory.closeAllSession(driver);
	}
	
	

}
