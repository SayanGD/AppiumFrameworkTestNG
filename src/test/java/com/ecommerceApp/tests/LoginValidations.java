package com.ecommerceApp.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.ecommerceApp.pageObjects.LoginPage;
import io.appium.java_client.android.AndroidDriver;

public class LoginValidations extends BaseTest
{
	@Test (priority = 1)
	public void validateLogin()
	{
		validateLogin(driver);
	}

	public void validateLogin(AndroidDriver driver)
	{
		LoginPage loginPage = new LoginPage(driver);
		Assert.assertEquals(loginPage.getTitle(),"General Store", "Invalid page title");
		loginPage.selectCountry("Belgium");
		loginPage.enterName("Titas");
		loginPage.chooseGender("Female");
		loginPage.clickShopButton();
	}

	@Test (priority = 2)
	public void validateLoginErrorWithMissingName()
	{
		LoginPage loginPage = new LoginPage(driver);
		Assert.assertEquals(loginPage.getTitle(),"General Store");
		loginPage.selectCountry("Belgium");
		loginPage.chooseGender("Male");
		loginPage.clickShopButton();
		Assert.assertEquals(loginPage.getToastMessage(), "Please enter your name", "Invalid error message");
	}
}