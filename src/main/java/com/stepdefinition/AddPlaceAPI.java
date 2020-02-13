package com.stepdefinition;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.pojo.LocationPojo;
import com.pojo.MapPojo;
import com.resources.APINames;
import com.resources.Payloads;
import com.resources.Utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

public class AddPlaceAPI extends Utils
{
	RequestSpecification res;
	Response response;
	Payloads addplacepayload= new Payloads();
	
	//Static Data through pojo
	/*@Given("Add place payload")
	public void add_place_payload() throws IOException {
		 res=given().spec(requestSpecification()).body(addplacepayload.addPlace());
		 System.out.println("********************************");
		}*/
	
	//Dynamic Data through feature file
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException {
		res=given().spec(requestSpecification()).body(addplacepayload.addPlace(name, address, language));
		 System.out.println("********************************");
	}

	//Static method which we are declared method directly
	/*@When("user calls {string} with post http request")
	public void user_calls_with_post_http_request(String string) throws FileNotFoundException {
	ResponseSpecification resp= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	   response=res.when().post("/maps/api/place/add/json").then().spec(resp).extract().response();
	   System.out.println(response.asString());
	}*/
	
	//Dynamic method which used enums to get required api name
	@When("user calls {string} with post http request")
	//Here resource will come from feature file
	public void user_calls_with_post_http_request(String resource) throws FileNotFoundException {
		//valueOf method will take resource value and assign it to an oject
		APINames apiname=APINames.valueOf(resource);
		//From Enum object we are returning api name
		System.out.println(apiname.getResourceName());
	ResponseSpecification resp= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	   response=res.when().post(apiname.getResourceName()).then().spec(resp).extract().response();
	   System.out.println(response.asString());
	}

	@Then("the api call is sucess with the status code with {int}")
	public void the_api_call_is_sucess_with_the_status_code_with(Integer int1) {
	    Assert.assertEquals(200, response.getStatusCode());
	}

	@Then("{string} in response is {string}")
	public void in_response_is(String keyValue, String expectedValue) {
	    String resp=response.asString();
	    JsonPath json= new JsonPath(resp);
	    Assert.assertEquals(expectedValue,json.get(keyValue).toString());
	}
}
