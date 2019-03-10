package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;

public class TestBase {
	public static Properties prop;
	
	public static void init(){
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("C:\\Users\\admin\\Desktop\\selenium\\seleniumRestassured\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
				RestAssured.baseURI = prop.getProperty("gpapiurl");
	}
	
	
	
	
	
	
	

}
