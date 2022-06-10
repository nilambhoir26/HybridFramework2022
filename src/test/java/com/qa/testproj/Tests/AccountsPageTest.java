package com.qa.testproj.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.testproj.Base.BaseTest;
import com.qa.testproj.Pages.LoginPage;
import com.qa.testproj.Utils.Constants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
    public void Acctpgsetup() {
		AcctPage = Loginpg.doLogin(prop.getProperty("Username"), prop.getProperty("Password"));
	}
	
	@Test(priority = 1)
	public void verifyAccountsPgeTitleTest() {
		Assert.assertEquals(AcctPage.getAccountPgTitle(), Constants.Accounts_Page_Title);
	}
	
	@Test(priority = 2)
	public void verifyAccountHeaderTest() {
		Assert.assertEquals(AcctPage.getHeadertext(), Constants.Accounts_Page_header);
	}
	
	@Test (enabled= false)
	public void verifyAccountPgSectionsdisplaysTest() {
		Assert.assertTrue(AcctPage.getAccountSectionsCount() >= Constants.Accounts_Sections_Count);
	}
	
	@Test(priority =3)
	public void verifyAccountsHeadersTest() {
		Assert.assertEquals(AcctPage.getAcctSection(), Constants.getAccountsHeadersList());
		
	}
	
	@Test(priority = 5)
	public void verifySearchFuncTest() {
		Assert.assertTrue(AcctPage.doSearch(Constants.Search_Text));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
