package com.ecommerceApp.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.ecommerceApp.pageObjects.CommonMethods;
import com.ecommerceApp.tests.BaseTest;
import io.appium.java_client.android.AndroidDriver;


public class TestNGListeners implements ITestListener
{
	ExtentReports extent = ExtentReportBuilder.getExtentReportObject();
	ExtentTest test;
	AndroidDriver driver;

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
		test.fail("Test failed: " + result.getThrowable());
	    try
	    {
	        BaseTest testInstance = (BaseTest) result.getInstance();
	        driver = testInstance.driver;
	        if (driver!=null)
	        {
	            CommonMethods commonMethods = new CommonMethods(driver);
	            String screenshotPath = commonMethods.takeScreenshot(result.getMethod().getMethodName());
	            test.addScreenCaptureFromPath(screenshotPath);
	        }
	    }
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
	}

	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
}