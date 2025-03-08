package com.ecommerceApp.utils;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONTestDataReader
{
	public TestDataJSONPOJO readData(String JSONPath) throws StreamReadException, DatabindException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(JSONPath);
		TestDataJSONPOJO data = mapper.readValue(file, TestDataJSONPOJO.class);
		return data;
	}
}