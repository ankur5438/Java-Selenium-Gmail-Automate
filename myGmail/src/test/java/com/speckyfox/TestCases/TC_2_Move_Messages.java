package com.speckyfox.TestCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.speckyfox.BaseClass.TestBase;
import com.speckyfox.PageObjects.HomePage;
import com.speckyfox.TestListner.TestListner;

@Listeners(TestListner.class)
public class TC_2_Move_Messages extends TestBase {

	@Test (priority=0, dependsOnMethods = { "com.speckyfox.TestCases.Login_Test_1.LoginGmail" })
	public void selectVerifyStar() throws InterruptedException
	{
		
		HomePage hp = new HomePage(driver);
		boolean result = hp.selectVerifyStar();
		Assert.assertTrue(result);
	}
	
	
	@Parameters({ "folder" })
	@Test (priority=1, dependsOnMethods = { "selectVerifyStar" })
	public void verifyMovedMessage(String folder) throws InterruptedException
	{
		
		HomePage hp = new HomePage(driver);
		boolean result = hp.verifyMovedMessage(folder);
		Assert.assertTrue(result);
	}
}
