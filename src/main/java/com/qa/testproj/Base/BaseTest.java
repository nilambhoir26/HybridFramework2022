package com.qa.testproj.Base;


import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.testproj.Pages.AccountsPage;
import com.qa.testproj.Pages.LoginPage;
import com.qa.testproj.Pages.ProductInfoPage;


public class BaseTest{
	
  public BasePage bp;
  public Properties prop;
  public WebDriver driver;
  public LoginPage Loginpg;
  public AccountsPage AcctPage;
  public ProductInfoPage ProductInfopg;
 
   @Parameters("Browser")
   @BeforeTest
   public void setUp(String BrowserName) {
	   
	  bp = new BasePage();
	 prop = bp.init_properties();
	String Browser = prop.getProperty("Browser_Name");
	if(BrowserName!=null) {
		Browser = BrowserName;
	}
	 driver = bp.init_Browser(Browser);
	 Loginpg = new LoginPage(driver);
	 AcctPage = new AccountsPage(driver);
    }
	
	@AfterTest
	public void teardown() {
		driver.quit();
	}	
}
