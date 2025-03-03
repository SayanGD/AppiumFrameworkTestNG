package com.ecommerceApp.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportBuilder
{
	public static ExtentReports getExtentReportObject()
	{
		ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\index.html");
		reporter.config().setDocumentTitle("Automation execution result");
		reporter.config().setReportName("Test execution result");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Sayan Ghosh Dastidar");
		return extent;
	}
}