package com.ecommerceApp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader
{
	Properties properties;
	public PropertyReader() throws IOException
	{
		FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Configuration.properties"));
		properties=new Properties();
		properties.load(fis);
	}

	public String getProperty(String key)
	{
		return properties.getProperty(key);
	}
}