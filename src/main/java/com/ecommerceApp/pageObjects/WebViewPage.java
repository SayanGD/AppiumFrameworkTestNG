package com.ecommerceApp.pageObjects;

import java.time.Duration;
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
	    this.driver = driver;
	    PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
	    String desiredContext = "WEBVIEW_com.androidsample.generalstore";
	    waitForContext(desiredContext);
	    driver.context(desiredContext);
	}

	public void enterSearchCriteria(String phrase)
	{
		fieldSearch.sendKeys(phrase);
		fieldSearch.sendKeys(Keys.ENTER);
	}
}