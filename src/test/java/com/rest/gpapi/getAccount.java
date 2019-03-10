package com.rest.gpapi;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.qa.util.TestBase;
import com.qa.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
public class getAccount extends TestBase {
	
	@BeforeMethod
	public void setUp(){
		TestBase.init();
	}
	
	@DataProvider
	public Object[][] getData(){
		Object testData[][] = TestUtil.getDataFromSheet(TestUtil.getAccountSheetName);
		return testData;
	}
	
	@Test(dataProvider="getData")
	public void getAccountTest(String apiversion,	String request, String gpid, String gpgameid,	
			String opid, String currency,	String sessionID, String device){
		
		//CONVERT OPID AND GAME PROOVIDER ID TO INTEGER
		double d = Double.parseDouble(opid);
		int operatorID = (int) d;
		
		d=Double.parseDouble(gpid);
		int gameproviderID=(int) d;
	
		//1. make a request/execute the request:
		Response response=RestAssured.given().
				param("apiversion", apiversion).
				param("request",request).
				param("gpid",gameproviderID).
				param("gpgameid",gpgameid).
				param("opid",operatorID).
				param("currency",currency).
				param("sessionid",sessionID).
				param("device",device).
		when().log().all().get().then().
		contentType(ContentType.XML)
		.extract().response();
		
		
		
		//3. get the response body:
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: "+ responseBody);
		//validate session Id or validate the key or value
		Assert.assertEquals(responseBody.contains(sessionID), true);
				
		//5. get the status code and validate it:
		int statusCode = response.getStatusCode();
		System.out.println("the status code is: "+ statusCode);
				
	}
	
	//@Test
    /*public void validategetAccount_tc1() {
		//Verify getaccount Response with valid session ID
		Response response=RestAssured.given().
				param("apiversion", "1.5").
				param("request","getaccount").
				param("gpid","100").
				param("gpgameid","madmadmonkeyhq").
				param("opid","5").
				param("currency","sek").
				param("sessionid","2ff34945-e6b0-4b30-9f3e-2fcf75e2d994").
				param("device","desktop").
		when().get().then().
		contentType(ContentType.XML)
		.extract().response();
		
		System.out.println(response.asString ());	
		
	}*/

}
