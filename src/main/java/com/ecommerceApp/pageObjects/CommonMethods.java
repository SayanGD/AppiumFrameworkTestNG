package com.ecommerceApp.pageObjects;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class CommonMethods
{
	AndroidDriver driver;
	WebDriverWait wait;

	public CommonMethods(AndroidDriver driver)
	{
		this.driver=driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public String takeScreenshot(String testCaseName) throws IOException
	{
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, destination);
		return destination.getAbsolutePath();
	}

	public void pressHome()
	{
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
	}

	public void waitForElementToBeVisible(WebElement element)
	{
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
	}

	public void waitForContext(String desiredContext)
	{
	   wait.until((WebDriver d) -> ((AndroidDriver) d).getContextHandles().contains(desiredContext));
	}

	public WebElement scrollToText(String text)
	{
		return driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\""+text+"\"))"));
	}

	public void longPress(WebElement element, int time)
	{
		Map <String, Object> params = ImmutableMap.of(
				"elementId", ((RemoteWebElement) element).getId(),
				"duration", time);
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", params);
	}

	public void swipe(WebElement element, String direction, int percentage)
	{
		Map <String, Object> params = ImmutableMap.of(
				"elementID", ((RemoteWebElement) element).getId(),
				"direction", direction,
				"percent", percentage);
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", params);
	}

	public void dragAndDrop(WebElement element, int targetX, int targetY)
	{
		Map <String, Object> params = ImmutableMap.of(
				"elementId", ((RemoteWebElement) element).getId(),
				"endX", targetX,
				"endY", targetY);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", params);
	}
}