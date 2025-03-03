package com.ecommerceApp.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import com.ecommerceApp.utils.PropertyReader;

public class BaseTest
{
	AppiumDriverLocalService service;
	AndroidDriver driver;
	PropertyReader prop;

	@BeforeClass
	public void startAppiumService() throws IOException
	{
		prop = new PropertyReader();
		String IP = prop.getProperty("IPAddress");
		int port = Integer.parseInt(prop.getProperty("PortNumber"));

		System.out.println();
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(System.getProperty("user.home")+"\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(IP).usingPort(port).withArgument(() -> "--allow-insecure", "chromedriver_autodownload").build();
		service.start();
	}

	@BeforeMethod
	public void initializeAndroidDriver()
	{
		String deviceName=prop.getProperty("DeviceName");
		UiAutomator2Options options = new UiAutomator2Options();
		options.setApp(System.getProperty("user.dir")+"\\src\\main\\resources\\General-Store.apk");
		options.setDeviceName(deviceName);
		options.setCapability("chromedriver_autodownload", true);

		driver = new AndroidDriver(service.getUrl(), options);
		driver.rotate(ScreenOrientation.PORTRAIT);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.hideKeyboard();
	}

	@AfterMethod
	public void closeAndroidDriver()
	{
		driver.quit();
	}

	@AfterClass
	public void closeAppiumService()
	{
		service.stop();
	}
}