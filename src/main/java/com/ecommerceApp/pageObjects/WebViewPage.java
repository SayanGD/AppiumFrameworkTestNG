package com.ecommerceApp.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WebViewPage extends CommonMethods
{
	@FindBy(name = "q")
	private WebElement fieldSearch;
	
	AndroidDriver driver;

	public WebViewPage(AndroidDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		driver.context("WEBVIEW_com.androidsample.generalstore");
	}

	public void enterSearchCriteria(String phrase)
	{
		fieldSearch.sendKeys(phrase);
		fieldSearch.sendKeys(Keys.ENTER);
	}
}