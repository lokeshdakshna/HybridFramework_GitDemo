package com.ots.helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ots.dataprovider.ConfigReader;

public class Utility 
{

	public static WebElement getElement(WebDriver driver, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true)", element);

		if(ConfigReader.getProperty("highlightElement").equalsIgnoreCase("true"))
		{
			highlightElement(driver, element);
		}
		
		return element;
	}

	public static void highlightElement(WebDriver driver, WebElement element) 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", element);

		try 
		{
			Thread.sleep(500);
		} catch (InterruptedException e) {
			
		}

		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid black;');", element);

	}

	public static void sleep(int seconds) {
		try 
		{
			Thread.sleep(seconds * 1000);

		} catch (InterruptedException e) {

		}
	}

	public static void type(WebDriver driver, By locator, String value) {

		// please complete this method
	}

	public static void type(WebDriver driver, WebElement element, String value) {
		try 
		{
			element.sendKeys(value);

		} catch (Exception e) {
			System.out.println("Not able to type - Trying values using JavaScriptExecutor");

			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("arguments[0].value=arguments[1]", element, value);
		}

	}

	public static void clickElement(WebDriver driver, By locator) {

		// complete this method

	}

	public static void clickElement(WebDriver driver, WebElement ele) {
		
		highlightElement(driver, ele);
		
		try 
		{
			ele.click();

		} catch (Exception e) {
			try {
				System.out.println("JS Click Failed :Trying Click Using Actions Class Click");

				Actions act = new Actions(driver);

				act.moveToElement(ele).click().build().perform();

			} catch (Exception e1) {

				System.out.println("Normal Click Failed :Trying Click Using JavaScriptExecutor");

				JavascriptExecutor js = (JavascriptExecutor) driver;

				js.executeScript("arguments[0].click()", ele);
			}

		}

	}

	public static String captureCurrentDateAndTime() {
		String date = new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy").format(new Date());

		return date;
	}

	
	public static String captureScreenshotAsBase64(WebDriver driver)
	{
	
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		String screenshot=ts.getScreenshotAs(OutputType.BASE64);
		
		return screenshot;
	}
	
	
	
	public static void captureScreenshot(WebDriver driver) {

		try {
			FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
					new File("./Screenshots/Screenshot_" + Utility.captureCurrentDateAndTime() + ".png"));

			System.out.println("Screenshot captured in Screenshots directory");

		} catch (WebDriverException e) {
			System.out.println("Could not take the screenshot " + e.getMessage());

		} catch (IOException e) {
			System.out.println("Could not save the screenshot " + e.getMessage());
		}

	}

	public static void captureScreenshotOfWebElement(WebDriver driver, WebElement ele) {

		try {
			FileHandler.copy(ele.getScreenshotAs(OutputType.FILE),
					new File("./Screenshots/Screenshot_" + Utility.captureCurrentDateAndTime() + ".png"));

			System.out.println("Screenshot captured for webelement in Screenshots directory");

		} catch (WebDriverException e) {
			System.out.println("Could not take the screenshot " + e.getMessage());

		} catch (IOException e) {
			System.out.println("Could not save the screenshot " + e.getMessage());
		}

	}

	public static void selectValueFromList(WebDriver driver, String xpathValue, String value) {

		List<WebElement> allValues = driver.findElements(By.xpath(xpathValue));

		for (WebElement ele : allValues) {
			if (ele.getText().equalsIgnoreCase(value)) {
				ele.click();

				break;
			}
		}

	}

	public static void selectValueFromList(WebDriver driver, By locator, String value) {

		List<WebElement> allValues = driver.findElements(locator);

		for (WebElement ele : allValues) {
			if (ele.getText().equalsIgnoreCase(value)) {
				ele.click();

				break;
			}
		}

	}
}
