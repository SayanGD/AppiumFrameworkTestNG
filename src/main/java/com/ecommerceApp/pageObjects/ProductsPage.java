package com.ecommerceApp.pageObjects;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsPage extends CommonMethods
{
	@FindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElement titleToolBar;

	@FindBy(xpath = "//android.widget.Toast")
	private WebElement toastMessage;

	@FindBy(id = "com.androidsample.generalstore:id/productName")
	private List<WebElement> products;

	@FindBy(id = "com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> btnAddToCart;

	@FindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> prices;

	@FindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement btnCart;
	
	AndroidDriver driver;
	private double obtainedSum;

	public ProductsPage(AndroidDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public String getTitle()
	{
		waitForElementToBeVisible(titleToolBar);
		return titleToolBar.getText();
	}

	public void selectProduct(String productName)
	{
		scrollToText(productName);
		int numberOfVisibleProducts = products.size();
		for(int i=0; i<numberOfVisibleProducts; i++)
		{
			if(products.get(i).getText().equalsIgnoreCase(productName))
			{
				if(numberOfVisibleProducts==btnAddToCart.size())
				{
					obtainedSum=obtainedSum+Double.parseDouble(prices.get(i).getText().replace("$", ""));
					btnAddToCart.get(i).click();
				}
				else
				{
					obtainedSum=obtainedSum+Double.parseDouble(prices.get(i+1).getText().replace("$", ""));
					btnAddToCart.get(i+1).click();
				}
			}
		}
	}

	public double getTotalPrice()
	{
		return obtainedSum;
	}

	public void clickCartButton()
	{
		btnCart.click();
	}

	public String getToastMessage()
	{
		return toastMessage.getDomAttribute("name");
	}
}