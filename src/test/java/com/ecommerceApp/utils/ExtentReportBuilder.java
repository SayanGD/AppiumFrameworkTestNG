package com.ecommerceApp.utils;

import java.io.IOException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportBuilder
{
	public static ExtentReports getExtentReportObject()
	{
		ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\index.html");
		reporter.config().setDocumentTitle("Automation execution result");
		reporter.config().setReportName("Test execution result");

		PropertyReader prop = null;
		try
		{
			prop = new PropertyReader();
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		String testerName=prop.getProperty("Tester");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", testerName);
		return extent;
	}
}