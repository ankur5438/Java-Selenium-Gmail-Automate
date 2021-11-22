package com.speckyfox.BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.speckyfox.Constants.Constants;


public class TestBase
{
	public static WebDriver driver; 
	public static Properties property;
	public static ChromeOptions chromeOptions;
	public static Logger log;	
	
		
	public TestBase()
	{
		
		try 
		{
			property = new Properties();
			FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/speckyfox/Configuration/Configuration.properties");
			property.load(inputStream);
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
		
		
public static void initialization()
		{
			String broswerName = property.getProperty("Browser");
			if(broswerName.equals("Chrome"))
			{
				chromeOptions = new ChromeOptions();
				System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
				driver = new ChromeDriver(chromeOptions);
			}
			else if(broswerName.equals("IE"))
			{
				System.setProperty("webdriver.ie.driver", Constants.INTERNET_EXPLORER_DRIVER_PATH);
				driver = new InternetExplorerDriver();
			}
			else if(broswerName.equals("Firefox"))
			{
				System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER_PATH);
				driver = new FirefoxDriver();
			}
			else
			{
				System.out.println("Path of Driver Executable is not Set for any Browser");
			}
			
			
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
			
			
		}

	

		
	@BeforeClass
	public void setup () 
	
	{

		initialization();
		driver.get(property.getProperty("Url"));
		log = (Logger) LogManager.getLogger("TestBase.class");	
	}
	
	
	
	@AfterClass
	public void tearDown() 
	{
		
		driver.quit();
	}
	
}
