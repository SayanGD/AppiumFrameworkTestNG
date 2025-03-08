package com.ecommerceApp.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.ecommerceApp.pageObjects.CartPage;
import com.ecommerceApp.pageObjects.ProductsPage;
import com.ecommerceApp.pageObjects.WebViewPage;

public class OrderProductValidation extends BaseTest
{
	@Test
	public void validateEndToEnd() throws InterruptedException
	{
		LoginValidations loginValidation = new LoginValidations();
		loginValidation.validateLogin(driver,"Titas","Female","Belgium");

		ProductsPage productsPage = new ProductsPage(driver);
		Assert.assertEquals(productsPage.getTitle(), "Products", "Invalid page title");
		productsPage.clickCartButton();
		Assert.assertEquals(productsPage.getToastMessage(), "Please add some product at first", "Invalid error message");
		productsPage.selectProduct("Jordan 6 Rings");
		productsPage.selectProduct("Air Jordan 9 Retro");
		double sumOfPrices = productsPage.getTotalPrice();
		productsPage.clickCartButton();

		CartPage cartPage = new CartPage(driver);
		Assert.assertEquals(cartPage.getTitle(), "Cart", "Invalid page title");
		double totalPrice = cartPage.getTotalPrice();
		Assert.assertEquals(sumOfPrices, totalPrice, "Incorrect price displayed");
		cartPage.selectConsentCheckbox();
		Assert.assertEquals(cartPage.getTermsConditionsPopupTitle(), "Terms Of Conditions", "Incorrect popup title");
		cartPage.clickCartButton();

		Thread.sleep(3000);
		WebViewPage webviewPage = new WebViewPage(driver);
		webviewPage.enterSearchCriteria("Sayan");
		webviewPage.pressHome();
	}
}