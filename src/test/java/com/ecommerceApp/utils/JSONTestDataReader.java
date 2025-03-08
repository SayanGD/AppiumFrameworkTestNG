package com.ecommerceApp.utils;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONTestDataReader
{
	public TestDataPOJO readData(String JSONPath) throws StreamReadException, DatabindException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(JSONPath);
		TestDataPOJO data = mapper.readValue(file, TestDataPOJO.class);
		return data;
	}
}