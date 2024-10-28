package com.ots.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ots.base.BaseClass;
import com.ots.dataprovider.CustomDataProvider;
import com.ots.pages.LoginPage;
import com.ots.pages.SignupPage;

public class RegisterNewUser extends BaseClass
{
	@Test(dataProvider = "NewUsersInfo",dataProviderClass = CustomDataProvider.class)
	public void createNewUser(String uname,String email,String pass,String interest,String gender,String state,String hobbies)
	{
		
		LoginPage login=new LoginPage(driver);
		
		SignupPage signup=login.clickOnNewUserLink();
			
		signup.createNewUser(uname, email, pass, interest, gender, state, hobbies);
		
		Assert.assertTrue(signup.isConfirmMessageDisplayed(),"User creation failed");
	}

}
