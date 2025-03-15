package com.ecommerceApp.tests;

import java.io.IOException;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.ecommerceApp.pageObjects.CartPage;
import com.ecommerceApp.pageObjects.ProductsPage;
import com.ecommerceApp.pageObjects.WebViewPage;
import com.ecommerceApp.utils.JSONTestDataReader;
import com.ecommerceApp.utils.TestDataPOJO;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public class OrderProductValidation extends BaseTest
{
	@Test (dataProvider = "getData")
	public void validateEndToEnd(String name, String gender, String country, List<String> products) throws InterruptedException
	{
		LoginValidations loginValidation = new LoginValidations();
		loginValidation.validateLogin(driver, name, gender, country);

		ProductsPage productsPage = new ProductsPage(driver);
		Assert.assertEquals(productsPage.getTitle(), "Products", "Invalid page title");
		productsPage.clickCartButton();
		Assert.assertEquals(productsPage.getToastMessage(), "Please add some product at first", "Invalid error message");
		for (String product:products)
			productsPage.selectProduct(product);
		double sumOfPrices = productsPage.getTotalPrice();
		productsPage.clickCartButton();

		CartPage cartPage = new CartPage(driver);
		Assert.assertEquals(cartPage.getTitle(), "Cart", "Invalid page title");
		double totalPrice = cartPage.getTotalPrice();
		Assert.assertEquals(sumOfPrices, totalPrice, "Incorrect price displayed");
		cartPage.selectConsentCheckbox();
		Assert.assertEquals(cartPage.getTermsConditionsPopupTitle(), "Terms Of Conditions", "Incorrect popup title");
		cartPage.clickCartButton();

		//Thread.sleep(3000);
		WebViewPage webviewPage = new WebViewPage(driver);
		webviewPage.enterSearchCriteria("Sayan");
		webviewPage.pressHome();
	}

	@DataProvider
	public Object[][] getData() throws StreamReadException, DatabindException, IOException
	{
		JSONTestDataReader dataReader = new JSONTestDataReader();
		TestDataPOJO testData = dataReader.readData(System.getProperty("user.dir")+"//src//test//resources//TestData.json");
		return new Object[][] {{testData.getName(), testData.getGender(), testData.getCountry(), testData.getProducts()}};
	}
}