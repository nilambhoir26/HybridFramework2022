package com.qa.testproj.Utils;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties Prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	public OptionsManager(Properties Prop) {
		this.Prop =Prop;
	}
	
	public  ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if(Prop.getProperty("headless").trim().equals("true")) co.addArguments("--headless");
		if(Prop.getProperty("incognito").trim().equals("true")) co.addArguments("--incognito");
		co.addArguments("--disable-dev-shm-usage");
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(Prop.getProperty("headless").trim().equals("true")) fo.addArguments("--headless");
		if(Prop.getProperty("incognito").trim().equals("true")) fo.addArguments("--incognito");
		return fo;
	}

}
