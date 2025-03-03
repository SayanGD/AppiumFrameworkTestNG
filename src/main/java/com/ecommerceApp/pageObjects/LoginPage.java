package com.ecommerceApp.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends CommonMethods
{
	@FindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElement titleToolBar;

	@FindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement dropdownCountry;

	@FindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement fieldName;

	@FindBy(id = "com.androidsample.generalstore:id/radioMale")
	private WebElement btnMale;

	@FindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement btnFemale;

	@FindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement btnShop;

	@FindBy(xpath = "//android.widget.Toast")
	private WebElement toastMessage;

	AndroidDriver driver;

	public LoginPage(AndroidDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public String getTitle()
	{
		return titleToolBar.getText();
	}

	public void enterName(String name)
	{
		fieldName.sendKeys(name);
	}

	public void chooseGender(String gender)
	{
		switch (gender.toLowerCase())
		{
			case "male": btnMale.click();
						break;
			case "female": btnFemale.click();
						break;
		}
	}

	public void selectCountry(String country)
	{
		dropdownCountry.click();
		scrollToText(country).click();
	}

	public void clickShopButton()
	{
		btnShop.click();
	}

	public String getToastMessage()
	{
		return toastMessage.getDomAttribute("name");
	}
}