package com.speckyfox.TestUtilities;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.speckyfox.BaseClass.TestBase;



public class TestUtility extends TestBase 
{
	
	//To Take Screenshot at End Of Test.
	public static void takeScreenshotAtEndOfTest() throws IOException 
	{
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/FailedTestsScreenshots/" + System.currentTimeMillis() + ".png"));
	}

	//Explicit Wait to Click on WebElement.
	public static void clickOn(WebDriver driver, WebElement element, int timeout) 
	{
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	//Explicit Wait to Send Data to WebElement.
	public static void sendKeys(WebDriver driver, WebElement element, int timeout, String value) 
	{
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}

	//Explicit Wait for Element To Be Visible.
	public static void waitForElementToBeVisible(WebDriver driver, By locator, int timeout)
	{
		new WebDriverWait(driver, timeout).
		until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	

	//To Check Element is Displayed or No.
	public static void isElementDisplayed(WebElement element) 
	{
		boolean elementDisplayed = element.isDisplayed();
		if(elementDisplayed) 
		{
			System.out.println("Element is Displayed");
		} 
		else 
		{
			System.out.println("Element is not Displayed");
		}
	}

	
	
	//To Check Element is Enabled or No.
	public static void isElementEnabled(WebElement element) 
	{
		boolean elementEnabled = element.isEnabled();
		if(elementEnabled) 
		{
			System.out.println("Element is Enabled");
		} 
		else 
		{
			System.out.println("Element is not Enabled");
		}
		
	}
	
	
	//Extent Report - 1.
		public static String getSystemDate() 
		{
			DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyy_HHmmss");
			Date date = new Date();
			return dateFormat.format(date);
		}

		//Extent Report - 2.
		public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException 
		{
			String dateName = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);

			String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName + ".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
			return destination;
		}

}

