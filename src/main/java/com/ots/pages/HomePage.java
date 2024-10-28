package com.ots.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ots.helper.Utility;

public class HomePage 
{

	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	private By welcomeMsg=By.xpath("//h4[@class='welcomeMessage']");
	
	private By manageOptions=By.xpath("//span[normalize-space()='Manage']");
	
	private By manageCourses=By.xpath("//a[normalize-space()='Manage Courses']");
	
	
	public String getWelcomeMessage()
	{
		String welcomeText=Utility.getElement(driver, welcomeMsg).getText();
		
		return welcomeText;
	}
	
	public void clickOnManageCourses()
	{
		Utility.getElement(driver, manageOptions).click();
		Utility.getElement(driver, manageCourses).click();
	}
	
	
	
}
