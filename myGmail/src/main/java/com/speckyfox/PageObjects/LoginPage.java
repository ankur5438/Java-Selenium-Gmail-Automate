package com.speckyfox.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.speckyfox.BaseClass.TestBase;
import com.speckyfox.Constants.Constants;
import com.speckyfox.TestUtilities.TestUtility;

public class LoginPage extends TestBase{
	
	//Email Input Box
	
			@FindBy (id = "identifierId")
			private
			WebElement emailInput;
			
		//Email Next Button
			
			@FindBy (xpath = "//div[@id=\"identifierNext\"]/div/button")
			private
			WebElement emailNextButton;
			
			
		//Password Input Box

					@FindBy (xpath = "//input[@name=\"password\"]")
					private
					WebElement passwordInput;
			
				//Next button for password
				
					@FindBy (xpath = "//div[@id=\"passwordNext\"]/div/button")
					private
					WebElement passwordNextButton;

		
		
			
		//composeButton
		
			@FindBy (xpath = "//div[@id=\":32\"]/div/div")
			private
			WebElement composeButton;
			
		
			
			WebDriver driver;
			WebDriverWait wait;
			
			
			public LoginPage(WebDriver rdriver) 
			{
				
				driver = rdriver;
				PageFactory.initElements(rdriver, this);
				wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
				

			}	
			
			
			public void login() {
				
				TestUtility.sendKeys(driver, emailInput,Constants.EXPLICIT_WAIT, property.getProperty("Username"));
				log.info("Entering Email Address");
				TestUtility.clickOn(driver, emailNextButton,Constants.EXPLICIT_WAIT);
				log.info("Clicking on Next button");
				TestUtility.sendKeys(driver, passwordInput,Constants.EXPLICIT_WAIT, property.getProperty("Password"));
				log.info("Entering Password for Email");
				TestUtility.clickOn(driver, passwordNextButton,Constants.EXPLICIT_WAIT);
				log.info("Clicking on Next Button");
				wait.until(ExpectedConditions.titleContains(Constants.HOME_PAGE_TITLE));
				
			}
			
		
		public boolean gmailHomePage() throws InterruptedException 
		{
			
			login();
			String title= driver.getTitle();
			boolean isloginTitle = title.contains(Constants.HOME_PAGE_TITLE);
			return isloginTitle;

		}
}
