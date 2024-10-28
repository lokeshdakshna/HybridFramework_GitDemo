package com.ots.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ots.base.BaseClass;
import com.ots.dataprovider.CustomDataProvider;
import com.ots.pages.HomePage;
import com.ots.pages.LoginPage;

public class LoginToApplication extends BaseClass
{

	@Test(priority = 1,dataProvider = "LoginCredentials",dataProviderClass = CustomDataProvider.class)
	public void loginApp(String username,String password)
	{
		LoginPage login=new LoginPage(driver);
		
		HomePage home=login.loginToApplicationAsAdminUser(username, password);
		
		Assert.assertTrue(home.getWelcomeMessage().contains("Welcome"),"Welcome msg did not appear");		
	}

}
