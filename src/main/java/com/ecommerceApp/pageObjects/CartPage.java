package com.ecommerceApp.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends CommonMethods
{
	@FindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElement titleToolBar;

	@FindBy(className = "android.widget.CheckBox")
	private WebElement checkbox;

	@FindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalPrice;

	@FindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement linkTermsConditions;

	@FindBy(id = "com.androidsample.generalstore:id/alertTitle")
	private WebElement titlePopup;

	@FindBy(id = "android:id/button1")
	private WebElement btnPopupClose;

	@FindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement btnProceed;
	
	AndroidDriver driver;

	public CartPage(AndroidDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public String getTitle()
	{
		return titleToolBar.getText();
	}

	public double getTotalPrice()
	{
		return Double.parseDouble(totalPrice.getText().replace("$", ""));
	}

	public void selectConsentCheckbox()
	{
		checkbox.click();
	}

	public String getTermsConditionsPopupTitle()
	{
		longPress(linkTermsConditions, 2000);
		String popupTitle = titlePopup.getText();
		btnPopupClose.click();
		return popupTitle;
	}

	public void clickCartButton()
	{
		btnProceed.click();
	}
}