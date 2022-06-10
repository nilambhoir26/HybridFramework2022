package com.qa.testproj.Pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.testproj.Base.BasePage;
import com.qa.testproj.Utils.ElementUtils;

public class ProductInfoPage extends BasePage {
	
	private WebDriver driver;
	ElementUtils elementUtils;
	
	private  By ProductTitle = By.cssSelector("#content h1");
	private  By Productinfo = By.cssSelector("#content .list-unstyled:nth-of-type(1) li");
	private  By PriceDeatils = By.cssSelector("#content .list-unstyled:nth-of-type(2) li");
	private  By QtyDetails = By.id("input-quantity");
	private  By AddToCartBtn = By.id("button-cart");
	private By ImgList = By.cssSelector(".thumbnails li img");
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver= driver;
		elementUtils = new ElementUtils(driver);
	}
	
	public Map<String, String> ProductInformation() {
		
		Map<String, String> ProductInfoMap = new HashMap();
		
		ProductInfoMap.put("ProductHeader", elementUtils.getElement(ProductTitle).getText().trim());
		
		List<WebElement> productMetaData = elementUtils.getElements(Productinfo);
		for( WebElement e: productMetaData ) {
			ProductInfoMap.put(e.getText().split(":")[0].trim(), e.getText().split(":")[1].trim());
		}
		
		List<WebElement> PriceDetails = elementUtils.getElements(PriceDeatils);
		ProductInfoMap.put("Price", PriceDetails.get(0).getText().trim());
		ProductInfoMap.put("Ex-TaxPrice", PriceDetails.get(1).getText().trim());
		
		return ProductInfoMap;
		
	}
	
	public void getQunatity(String qty) {
		elementUtils.doSendKeys(QtyDetails, qty);
	}
	
	public void clickAddToCart() {
		elementUtils.doClick(AddToCartBtn);
	}
	
	public int getImgDetails() {
		return elementUtils.getElements(ImgList).size();
			
		}
	}
	
	
	
	
	
	
	
	


