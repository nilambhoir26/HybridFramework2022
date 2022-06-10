package com.qa.testproj.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.testproj.Base.BasePage;

public class ElementUtils {
	
	private WebDriver driver;
	public JavaScriptUtil JsUtil;
	
	public ElementUtils(WebDriver driver) {
		
		this.driver = driver;
		JsUtil = new JavaScriptUtil(this.driver);
	}
	
	
	public WebElement getElement(By Locator) {
		WebElement element = driver.findElement(Locator);
		if(BasePage.highlight.equals("true")) {
			JsUtil.flash(element);
		}
		return element;
	}
	
	public List<WebElement> getElements(By Locator) {
		return driver.findElements(Locator);
	}
	
	public void doSendKeys(By Locator, String Key) {
		getElement(Locator).sendKeys(Key);
	}
	
	public void doClick(By Locator) {
		getElement(Locator).click();
	}
	
	public void doActionSendkeys(By Locator, String Value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(Locator),Value).perform();
	}
	
	public void doActionClick(By Locator) {
		Actions act = new Actions(driver);
		act.click(getElement(Locator)).perform();
	}
	
	public String doGetText(By Locator) {
		return getElement(Locator).getText();
	}
	
	public boolean doIsDisplayed(By Locator) {
		return getElement(Locator).isDisplayed();
	}

	public void doLinkClick(List<WebElement> links, String Text) {
		System.out.println(links.size());
		
		for(WebElement e : links ) {
			System.out.println(e.getText());{
				if(e.getText().equalsIgnoreCase(Text)) {
				   e.click();
				   break;
				}
			}
		
		}
	}
	
	public String getTitleOfPage() {
		return driver.getTitle();
	}
	
	//DropDown Utility
	
	public void selectDropDownByVisbibleText(By Locator, String Text)
	{
		Select select = new Select(getElement(Locator));
		select.selectByVisibleText(Text);
	}
	
	public void selectDropdownByIndex(By Locator, int Index) {
		Select select = new Select(getElement(Locator));
		select.selectByIndex(Index);
	}
	
	public void selectDropdownByValue(By Locator, String Value) {
		Select select = new Select(getElement(Locator));
		select.selectByValue(Value);
	}
	
	public int getDropdownOptionsCount(By Locator) {
		Select select = new Select(getElement(Locator));
		List<WebElement> Options = select.getOptions();
		return Options.size();
	}
	
	public List<String> getDropDownOptionsValue(By Locator) {
		List<String> arrayList = new ArrayList<String>();
		Select select = new Select(getElement(Locator));
		List<WebElement> Options = select.getOptions();
		for(WebElement ele : Options) {
			System.out.println(ele.getText());
			String text = ele.getText();
			arrayList.add(text);
		}
		 return arrayList;
		
	}
	
	public void selectDropDownwithoutselect(By Locator, String text) {
		List<WebElement> countrylist = driver.findElements(Locator);
		
		for (WebElement ele : countrylist) {
			String value = ele.getText();
			
			if(value.equalsIgnoreCase(text)) {
				ele.click();
				break;
			}
		}
	}
	
	//Locator wait utility
	
	public List<WebElement> visbilityofAllElements(By Locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locator));
		
	}
	
	public void getPageLinks(By Locator, int timeOut) {
		visbilityofAllElements(Locator,timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
	}
	
	public WebElement waitForElementPresent(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
	
	public WebElement waitForElementToBeVisible(By locator, int timeOut) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public boolean waitForUrl(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlContains(url));
	}
	
	public Alert waitForAlertToBePresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public WebElement waitForElementToBeClickable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
	
	public WebElement waitForElementWithFluentWait(By locator, int timeOut, int interval) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(interval))
				.ignoring(NoSuchElementException.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
