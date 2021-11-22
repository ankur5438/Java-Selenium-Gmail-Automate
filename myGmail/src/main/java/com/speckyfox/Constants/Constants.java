package com.speckyfox.Constants;

public class Constants 
{
	public static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/Drivers/chromedriver.exe";
	public static final String INTERNET_EXPLORER_DRIVER_PATH = System.getProperty("user.dir") + "/Drivers/IEDriverServer.exe";
	public static final String FIREFOX_DRIVER_PATH = System.getProperty("user.dir") + "/Drivers/geckodriver.exe";
	
	public static final int PAGE_LOAD_TIMEOUT = 30;
	public static final int IMPLICIT_WAIT = 15;
	public static final int EXPLICIT_WAIT = 15;

	public static final int SHORT_WAIT = 3000;
	public static final int MEDIUM_WAIT = 6000;
	public static final int LONG_WAIT = 10000;
	
	public static final String LOGIN_PAGE_TITLE = "Gmail";
	public static final String HOME_PAGE_TITLE = "ankur.huria2@gmail.com - Gmail";
	public static final String COMPOSE_SUBJECT = "HELLO I AM AUTOMATED SUBJECT 766877";
	public static final String COMPOSE_BODY = "HELLO I AM AUTOMATED BODY";
	

}
