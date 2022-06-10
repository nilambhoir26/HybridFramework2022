package com.qa.testproj.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.testproj.Base.BasePage;
import com.qa.testproj.Utils.ElementUtils;

public class LoginPage extends BasePage{
	
	private WebDriver driver;
	ElementUtils elementUtils;
	
	private By header = By.cssSelector("div#logo a");
	private By Username = By.id("input-email");
	private By Password = By.id("input-password");
	private By LoginBtn = By.xpath("//input[@value ='Login' and @type='submit']");
	private By forgotpswd = By.linkText("Forgotten Password22");
	
	
	public  LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}	

	public String getTitleOfLoginPage() {
		//return driver.getTitle();
		String TitleOfPage = elementUtils.getTitleOfPage();
		return TitleOfPage;
	}
	
	public boolean ForgotPswdlinkexist() {
		//return driver.findElement(forgotpswd).isDisplayed();
		return elementUtils.doIsDisplayed(forgotpswd);
	}
	
	public AccountsPage doLogin(String un, String pswd) {
//		driver.findElement(Username).sendKeys(un);
//		driver.findElement(Password).sendKeys(pswd);
//		driver.findElement(LoginBtn).click();
//		return new AccountsPage(driver);
		
		elementUtils.doSendKeys(Username, un);
		elementUtils.doSendKeys(Password, pswd);
		elementUtils.doClick(LoginBtn);
		return new AccountsPage(driver);
	}
	
	
	
			
	

	
	
	
}
