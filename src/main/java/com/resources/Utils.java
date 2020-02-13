package com.resources;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils 
{
    public static RequestSpecification req;
	public  RequestSpecification requestSpecification() throws IOException
	{
		if(req==null)
		{
		   PrintStream log= new PrintStream(new FileOutputStream("log.txt"));
		//RestAssured.baseURI="http://2160.10.245.166";
		   req= new RequestSpecBuilder().setBaseUri(getPropertyValue("baseURL")).
                addQueryParam("key", "qaclick123").
                addFilter(RequestLoggingFilter.logRequestTo(log)).//It is used to log request
                addFilter(ResponseLoggingFilter.logResponseTo(log)).//It is used to log response
                setContentType(ContentType.JSON).build();
		   return req;
		}
		return req;
	}
    
	public String getPropertyValue(String key) throws IOException
	{
		Properties prop= new Properties();
		FileInputStream f= new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/resources/config.properties");
		prop.load(f);
		String value=prop.getProperty(key);
		return value;
		
		
	}
}
