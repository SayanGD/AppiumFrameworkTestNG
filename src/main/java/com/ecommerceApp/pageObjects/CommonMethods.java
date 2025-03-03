package com.ecommerceApp.pageObjects;

import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class CommonMethods
{
	AndroidDriver driver;
	
	public CommonMethods(AndroidDriver driver)
	{
		this.driver=driver;
	}

	public void pressHome()
	{
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
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
}