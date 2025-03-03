package com.ecommerceApp.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestNGListeners implements ITestListener
{
	ExtentReports extent = ExtentReportBuilder.getExtentReportObject();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result)
	{
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
	    test.pass("Test passed");
	}

	@Override
	public void onTestSkipped(ITestResult result)
	{
		test.skip("Test skipped");
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
	    test.fail("Test failed: "+result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
}