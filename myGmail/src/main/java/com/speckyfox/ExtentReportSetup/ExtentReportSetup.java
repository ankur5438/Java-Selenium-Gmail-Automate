package com.speckyfox.ExtentReportSetup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.speckyfox.BaseClass.TestBase;
import com.speckyfox.TestUtilities.*;
public class ExtentReportSetup extends TestBase
{	
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public static ExtentSparkReporter sparkReport;
	
	public static ExtentReports extentReportSetup()
	{
		sparkReport = new ExtentSparkReporter(System.getProperty("user.dir") + "/ExtentResults/GmailExtentReport" + TestUtility.getSystemDate() + ".html");
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReport);
		
		sparkReport.config().setTheme(Theme.STANDARD);
		sparkReport.config().setReportName("Test Automation Report");
		sparkReport.config().setDocumentTitle("Test Automation Report");
		
		return extent;
	}
}
