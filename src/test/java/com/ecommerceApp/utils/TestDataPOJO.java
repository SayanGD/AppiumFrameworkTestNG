package com.ecommerceApp.utils;

import java.util.List;

public class TestDataPOJO
{
	private String name;
	private String gender;
	private String country;
	private List<String> products;

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getGender()
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public String getCountry()
	{
		return country;
	}
	public void setCountry(String country)
	{
		this.country = country;
	}
	public List<String> getProducts()
	{
		return products;
	}
	public void setProducts(List<String> products)
	{
		this.products = products;
	}
}