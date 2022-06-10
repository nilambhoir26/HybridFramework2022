package com.qa.testproj.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.testproj.Base.BaseTest;
import com.qa.testproj.Pages.AccountsPage;
import com.qa.testproj.Pages.LoginPage;
import com.qa.testproj.Utils.Constants;

public class LoginPageTest extends BaseTest {
	
	
	@Test(priority =1)
	public void verifyLoginPgTitleTest() {
		String titleofPg = Loginpg.getTitleOfLoginPage();
		Assert.assertEquals(titleofPg, Constants.Login_Page_Title);
	}

	@Test(priority =2)
	public void verifyExistanceOfForgotPswdTest() {
		Assert.assertTrue(Loginpg.ForgotPswdlinkexist());
	}
	
	@Test(priority=3)
	public void LoginTest() {
	Loginpg.doLogin(prop.getProperty("Username"), prop.getProperty("Password"));
	
	   
	}
	 
	
}
