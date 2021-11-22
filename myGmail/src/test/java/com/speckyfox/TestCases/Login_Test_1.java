package com.speckyfox.TestCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.speckyfox.BaseClass.TestBase;
import com.speckyfox.PageObjects.HomePage;
import com.speckyfox.PageObjects.LoginPage;
import com.speckyfox.TestListner.TestListner;

@Listeners(TestListner.class)
public class Login_Test_1 extends TestBase {

	@Test (priority=1)
	public void LoginGmail() throws InterruptedException
	{
		
		LoginPage lp = new LoginPage(driver);
		boolean result = lp.gmailHomePage();
		Assert.assertTrue(result);
	}
	
	
	@Test (priority=2,dependsOnMethods = { "LoginGmail" })
	public void composeTest() throws InterruptedException
	{
		
		HomePage hp = new HomePage(driver);
		boolean result = hp.composeTest();
		Assert.assertTrue(result);
	}
	
	
	@Test (priority=3,dependsOnMethods = { "composeTest" })
	public void verifyMail() throws InterruptedException
	{
		
		HomePage hp = new HomePage(driver);
		boolean result = hp.verifyMail();
		Assert.assertTrue(result);
	}
	 
 }
	

