package com.qa.testproj.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.testproj.Utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author nilam This is the base page were we initlializing browser &
 *         Properties file
 *
 */
public class BasePage {

	WebDriver driver;
	Properties Prop;
	OptionsManager optionManger;
	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver init_Browser(String Browser) {

		// String Browser = Prop.getProperty("Browser_Name");
		System.out.println("Selected browser for execution is" + Browser);
		highlight = Prop.getProperty("highlight");

		optionManger = new OptionsManager(Prop);
		if (Browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();

			if (Boolean.parseBoolean(Prop.getProperty("remote"))) {
				init_remoteWebDriver("chrome");
			}

			else {
				tlDriver.set(new ChromeDriver(optionManger.getChromeOptions()));
			}
		} else if (Browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if (Browser.equalsIgnoreCase("Firefox")) {
				WebDriverManager.chromedriver().setup();

				if (Boolean.parseBoolean(Prop.getProperty("remote"))) {
					init_remoteWebDriver("Firefox");
				} else {
					tlDriver.set(new FirefoxDriver(optionManger.getFirefoxOptions()));
				}
			} else if (Browser.equalsIgnoreCase("Safari")) {
				// driver = new SafariDriver();
				tlDriver.set(new SafariDriver());
			} else {
				System.out.println("Browser name is not recognisable. Please check browser");
			}

			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
		return getDriver();
	}

	private void init_remoteWebDriver(String Browser) {

		if (Browser.equals("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, optionManger.getChromeOptions());

			try {
				tlDriver.set(new RemoteWebDriver(new URL(Prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		} else if (Browser.equals("Firefox")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionManger.getFirefoxOptions());

			try {
				tlDriver.set(new RemoteWebDriver(new URL(Prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * 
	 * This is the function to initilalise properties file
	 * 
	 * @return
	 * @return
	 */

	public Properties init_properties() {

		Prop = new Properties();
		try {
			// FileInputStream ip = new
			// FileInputStream(System.getProperty("user.dir")+".\\config.properties");
			FileInputStream ip = new FileInputStream(
					".\\src\\main\\java\\com\\qa\\testproj\\Config\\Config.Properties");
			try {
				Prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Prop;

	}

	public String getScreenshot() {

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File Destination = new File(path);
		try {
			FileUtils.copyFile(src, Destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;
	}

}
