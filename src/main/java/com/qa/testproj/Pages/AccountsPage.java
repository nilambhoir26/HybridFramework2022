package com.qa.testproj.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.testproj.Base.BasePage;
import com.qa.testproj.Utils.ElementUtils;

public class AccountsPage extends BasePage {

	private WebDriver driver;
	ElementUtils elementUtils;

	private By Header = By.cssSelector("div#logo a");
	private By AccountSectionsHeaders = By.cssSelector("div#content h2");
	private By SearchBar = By.cssSelector("div#search input[name ='search']");
	private By SearchBtn = By.cssSelector("div#search button[type='button']");
	private By SerachResults = By.cssSelector(".product-layout .product-thumb");
	private By ResultItems = By.cssSelector(".product-thumb h4 a");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}

	public String getAccountPgTitle() {
		// return driver.getTitle();
		return elementUtils.getTitleOfPage();
	}

	public String getHeadertext() {
//		if (driver.findElement(Header).isDisplayed()) {
//			return driver.findElement(Header).getText();
//		}
//		return null;

		if (elementUtils.doIsDisplayed(Header)) {
			return elementUtils.doGetText(Header);
		}

		return null;

	}

	public int getAccountSectionsCount() {
//		System.out.println(driver.findElements(AccountSectionsHeaders).size());
//		return driver.findElements(AccountSectionsHeaders).size();

		return elementUtils.visbilityofAllElements(AccountSectionsHeaders, 10).size();
	}

	public List<String> getAcctSection() {
		List<String> AccountsSectionList = new ArrayList<>();
//		List<WebElement> AcctList = driver.findElements(AccountSectionsHeaders);

		List<WebElement> AcctList = elementUtils.visbilityofAllElements(AccountSectionsHeaders, 10);

		for (WebElement e : AcctList) {
			AccountsSectionList.add(e.getText());
		}

		return AccountsSectionList;
	}

	public boolean doSearch(String ProductName) {

//		driver.findElement(SearchBar).sendKeys(ProductName);
//		driver.findElement(SearchBtn).click();

		elementUtils.doSendKeys(SearchBar, ProductName);
		elementUtils.doClick(SearchBtn);

//		if(driver.findElements(SerachResults).size()>0) {
//			return true;
//		}
//		return false;
		if (elementUtils.visbilityofAllElements(SerachResults, 10).size() > 0) {
			return true;
		}
		return false;
	}

	public ProductInfoPage selectProduct(String ProductName) {
		List<WebElement> Results = elementUtils.getElements(ResultItems);
		for (WebElement e : Results) {
			if (e.getText().equals(ProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
