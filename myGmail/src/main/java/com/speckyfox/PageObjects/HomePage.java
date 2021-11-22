package com.speckyfox.PageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.speckyfox.BaseClass.TestBase;
import com.speckyfox.Constants.Constants;
import com.speckyfox.TestUtilities.TestUtility;

public class HomePage extends TestBase {
	
	
	//Compose Button to compose a message
	@FindBy (xpath = "//div[text() =\"Compose\"]")
	private
	WebElement composeButton;
	

	//Recipient Box while composing mail
		@FindBy (xpath = "//textarea[@name='to']")
		private
		WebElement recipientBox;
	
	//Subject Input Box while composing mail
		@FindBy (xpath = "//input[@name=\"subjectbox\"]")
		private
		WebElement subjectInput;
	
	//Body Text while composing mail
		@FindBy (xpath = "//div[@aria-label=\"Message Body\"]")
		private
		WebElement bodyText;
		
	//Compose Send Button while composing mail
		
		@FindBy (xpath = "//div[text() =\"Send\"]")
		private
		WebElement sendButton;
		
	
		
	//Sent Message Text
		@FindBy (xpath = "//*[contains(text(),'Message sent')]")
		private
		WebElement sentMessage;
		
	//All Inbox Emails List
		
		@FindBy (xpath = "//div[@class=\"Cp\"]/div/table/tbody/tr")
		private
		List <WebElement> allEmails;
		
	//Subject of Email after opening a mail
		
		@FindBy (xpath = "//div[@class=\"ha\"]/h2")
		private
		WebElement subjectHeading;
		
		
		
	//All Emails on First page
		
		@FindBy (xpath = "//div[@class=\"J-J5-Ji amH J-JN-I\"]/span/span[2]")
		private
		WebElement numberOfEmails;
		
		
	//Check Star Icon for Opened Mail	
		
		@FindBy (xpath = "//tbody/tr[@class=\"acZ\"]//div/div")
		private
		WebElement starIconCheck;
		
	//Move to Button in a EMail
		
		@FindBy (xpath = "//div[@class=\"iH bzn\"]//div[@class=\"T-I J-J5-Ji T-I-Js-IF mA ns T-I-ax7 L3\"]")
		private
		WebElement moveToBtn;
		
	//More Button in SideNavbar
		@FindBy (xpath = "//span[@class=\"CJ\" and  text()=\"More\"]")
		private
		WebElement moreBtn;
		

		
		
		WebDriver driver;
		WebDriverWait wait;
		String totalEmails;
		public int random;
		String composeSubject = Constants.COMPOSE_SUBJECT;
				
	
		
		
		public HomePage(WebDriver rdriver) 
		{
			
			driver = rdriver;
			PageFactory.initElements(rdriver, this);
			wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
			
		}
		

		public boolean composeTest() throws InterruptedException {
			
			totalEmails = numberOfEmails.getAttribute("innerHTML");
			wait.until(ExpectedConditions.visibilityOfAllElements(allEmails));
			TestUtility.clickOn(driver, composeButton, Constants.EXPLICIT_WAIT);
			log.info("Clicking on Compose Mail");
			Thread.sleep(1000);
			TestUtility.sendKeys(driver, recipientBox,Constants.EXPLICIT_WAIT ,property.getProperty("Username"));
			log.info("Entering Recipent");
			subjectInput.sendKeys(composeSubject);
			log.info("Entering Subject");
			bodyText.sendKeys(Constants.COMPOSE_BODY);
			log.info("Entering Body");
			sendButton.click();
			log.info("Click on Send Button");
			wait.until(ExpectedConditions.visibilityOf(sentMessage));
			log.info("Sent Message Text Found");
			return sentMessage.getText().contains("Message sent");
			
		}
			
		
			
		public boolean verifyMail() {
			    boolean result = false;         
			wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(numberOfEmails,totalEmails)));
			   for(WebElement email : allEmails){                                                                                                                           
			       if(email.isDisplayed() && email.getText().contains(composeSubject)){                                                                                                                                   
			           email.click();
			           log.info("Click on Mail Sent by us");
			          wait.until(ExpectedConditions.visibilityOf(subjectHeading));
			          result = subjectHeading.getText().equals(composeSubject);
			          break;
			          
			       }
			   }
			            
			   return result;
			}
		
		
		public boolean selectVerifyStar() throws InterruptedException {
			LoginPage lp = new LoginPage(driver);
			lp.login();
			int count = 0;
			String checkStarred = "";
			boolean result = false;
			wait.until(ExpectedConditions.visibilityOfAllElements(allEmails));
			   for(WebElement email : allEmails){
				   count++;
			       if(email.isDisplayed() && email.getText().contains(composeSubject)){   
			    	   driver.findElement(By.xpath("//table[@class=\"F cf zt\"]/tbody/tr["+count+"]/td[3]")).click();
			    	     	   
			    	   email.click();
			           log.info("Click on Mail Sent by us");
			           
			           wait.until(ExpectedConditions.visibilityOf(starIconCheck));
			           Thread.sleep(6000);
			         //  wait.until(ExpectedConditions.attributeToBe(starIconCheck, "aria-label", "Starred"));
			           
			           checkStarred = starIconCheck.getAttribute("aria-label");
			           result = checkStarred.equals("Starred");
			          break;
			       }
			   }
			
			   	return result;	
		}
		
		
		public boolean verifyMovedMessage(String folder) throws InterruptedException {
			boolean result;
			totalEmails = numberOfEmails.getAttribute("innerHTML");
			TestUtility.clickOn(driver, moveToBtn, 10);
			TestUtility.waitForElementToBeVisible(driver, By.xpath("//div[text()=\"Move to:\"]"), 10);
			driver.findElement(By.xpath("//div[@class=\"J-N-Jz\" and text()=\""+folder+"\"]")).click();
			TestUtility.waitForElementToBeVisible(driver, By.xpath("//*[contains(text(),\"Conversation moved to \'"+folder+"\'.\")]"), 10);
			wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(numberOfEmails,totalEmails)));
			JavascriptExecutor jse =(JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView();", moreBtn);
			TestUtility.clickOn(driver, moreBtn, 10);
			jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//a[text()=\""+folder+"\"]")));
			TestUtility.clickOn(driver, driver.findElement(By.xpath("//a[text()=\""+folder+"\"]")), 10);
			Thread.sleep(4000);
			result = verifyMail();
						
			return result;
			
			
		}
		
}
		
	
	
	
	
	


