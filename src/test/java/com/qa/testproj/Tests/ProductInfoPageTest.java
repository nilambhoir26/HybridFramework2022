package com.qa.testproj.Tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.testproj.Base.BaseTest;
import com.qa.testproj.Pages.LoginPage;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void ProductInfoSetUp() {
		
		AcctPage = Loginpg.doLogin(prop.getProperty("Username"), prop.getProperty("Password"));
	}
	
	@Test
	public void verifyProductInfoTest() {
		
		String ProductName = "Macbook";
		Assert.assertTrue(AcctPage.doSearch(ProductName));
		
		ProductInfopg = AcctPage.selectProduct("MacBook Pro");
		Assert.assertTrue(ProductInfopg.getImgDetails() == 4);
		
		Map<String, String> ProductInfoMap = ProductInfopg.ProductInformation();
		System.out.println(ProductInfoMap);
		
	}
	
	
	
}
