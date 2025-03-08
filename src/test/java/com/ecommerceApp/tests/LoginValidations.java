package com.ecommerceApp.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.ecommerceApp.pageObjects.LoginPage;
import com.ecommerceApp.utils.JSONTestDataReader;
import com.ecommerceApp.utils.TestDataJSONPOJO;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import io.appium.java_client.android.AndroidDriver;

public class LoginValidations extends BaseTest
{
	@Test (priority = 1, dataProvider = "getLoginData")
	public void validateLogin(String name, String gender, String country)
	{
		validateLogin(driver, name, gender, country);
	}

	public void validateLogin(AndroidDriver driver, String name, String gender, String country)
	{
		LoginPage loginPage = new LoginPage(driver);
		Assert.assertEquals(loginPage.getTitle(),"General Store", "Invalid page title");
		loginPage.selectCountry(country);
		loginPage.enterName(name);
		loginPage.chooseGender(gender);
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

	@DataProvider
	public Object[][] getLoginData() throws StreamReadException, DatabindException, IOException
	{
		JSONTestDataReader dataReader=new JSONTestDataReader();
		TestDataJSONPOJO testData = dataReader.readData(System.getProperty("user.dir")+"//src//test//resources//TestData.json");
		return new Object[][] {{testData.getName(), testData.getGender(), testData.getCountry()}};
	}
}