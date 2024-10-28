package com.ots.factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.ots.dataprovider.ConfigReader;

public class BrowserFactory 
{
	static WebDriver driver;
	
	public static void closeAllSession(WebDriver driver)
	{
		driver.quit();
	}
	
	public static void closeActiveSession(WebDriver driver)
	{
		driver.close();
	}
	
	
	// getter method - which will return WebDriver instance
	
	public static WebDriver getDriver()
	{
		return driver;
	}
	
	
	public static WebDriver startBrowser(String appURL) 
	{
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(appURL);

		return driver;

	}

	public static WebDriver startBrowser(String browser, String appURL, boolean cloud) {
		WebDriver driver = null;

		if (cloud) {
			ChromeOptions opt = new ChromeOptions();

			opt.setPlatformName("macOS Catalina");

			opt.setBrowserVersion("121.0");

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("username", "mukeshotwani.50");

			map.put("accessKey", "po9WNsa4fyaDoDaO78fMXMiWr6wlnoXZZllgq3frmg2eJzeVz4");

			map.put("w3c", true);

			map.put("visual", true);

			map.put("network", true);

			map.put("project", "Selenium Tests On Cloud");

			opt.setCapability("LT:Options", map);

			URL hubURL;
			try {
				hubURL = new URL(
						"https://mukeshotwani.50:po9WNsa4fyaDoDaO78fMXMiWr6wlnoXZZllgq3frmg2eJzeVz4@hub.lambdatest.com/wd/hub");

				driver = new RemoteWebDriver(hubURL, opt);

			} catch (MalformedURLException e) {

			}

		}

		else {
			if (browser.equalsIgnoreCase("Chrome")) {
				ChromeOptions opt = new ChromeOptions();

				Map<String, String> map = new HashMap<String, String>();

				String downloadDirectory = System.getProperty("user.dir") + "/downloadedFiles/";

				map.put("download.default_directory", downloadDirectory);

				opt.setExperimentalOption("prefs", map);

				// opt.addArguments("--headless=new");

				// opt.addArguments("--incognito");

				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("Firefox")) {
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("Edge")) {
				driver = new EdgeDriver();
			} else if (browser.equalsIgnoreCase("Safari")) {
				driver = new SafariDriver();
			} else {
				System.out.println("Sorry Currently We Do Not Support " + browser);
			}

		}

		driver.manage().window().maximize();

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(appURL);

		return driver;

	}

	public static WebDriver startBrowser(String browser, String appURL) {
		

		if (browser.equalsIgnoreCase("Chrome")) 
		{
			ChromeOptions opt = new ChromeOptions();

			Map<String, String> map = new HashMap<String, String>();

			String downloadDirectory = System.getProperty("user.dir") + "/DownloadedFiles/";

			map.put("download.default_directory", downloadDirectory);

			opt.setExperimentalOption("prefs", map);
			
			if(ConfigReader.getProperty("headless").equalsIgnoreCase("true"))
			{
				opt.addArguments("--headless=new");
			}

			driver = new ChromeDriver(opt);
			
		} else if (browser.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("Sorry Currently We Do Not Support " + browser);
		}

		driver.manage().window().maximize();

		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("pageLoad"))));

		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("scriptTimeOut"))));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicitWait"))));

		driver.get(appURL);

		return driver;

	}

}
