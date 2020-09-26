package com.stepdefinition;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.resources.APINames;
import com.resources.Payloads;
import com.resources.Utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class PlaceAPI extends Utils
{
	RequestSpecification res;
	public static Response response;
	Payloads payload= new Payloads();
    public static String place_id;
	//Static Data through pojo
	/*@Given("Add place payload")
	public void add_place_payload() throws IOException {
		 res=given().spec(requestSpecification()).body(addplacepayload.addPlace());
		 System.out.println("********************************");
		}*/
	
  //Dynamic Data through feature file
  	@Given("Add place payload with {string} {string} {string}")
  	public void add_place_payload_with(String name, String address, String language) throws IOException {
  		res=given().spec(requestSpecification()).body(payload.addPlace(name, address, language));
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
  	@When("user calls {string} with {string} http request")
  	//Here resource will come from feature file
  	public void user_calls_with_post_http_request(String resource, String method) throws FileNotFoundException {
  		//valueOf method will take resource value and assign it to an oject
  		APINames apiname=APINames.valueOf(resource);
  		//From Enum object we are returning api name
  		System.out.println("Endpoint is "+apiname.getResourceName());
  	//ResponseSpecification resp= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
  	if(method.equalsIgnoreCase("POST"))
  		 response =res.when().post(apiname.getResourceName());
  		else if(method.equalsIgnoreCase("GET"))
  			 response =res.when().get(apiname.getResourceName());
  	System.out.println(response.asString());
  	}

  	@Then("the api call is sucess with the status code with {int}")
  	public void the_api_call_is_sucess_with_the_status_code_with(Integer int1) {
  	    Assert.assertEquals(200, response.getStatusCode());
  	}

  	@Then("{string} in response is {string}")
  	public void in_response_is(String keyValue, String expectedValue) {
  		//Here we are printing expected value from Response matching with the feature file
  		Assert.assertEquals(expectedValue, getResponseValue(response, keyValue));
  	}
  	
  	@Then("verify the place_id created that maps to {string} using {string}")
  	public void verify_the_place_id_created_that_maps_to_name_using(String string,String resource) throws IOException {
  		 place_id=getResponseValue(response, "place_id");
  		 System.out.println("Place Id is "+place_id);
  		res=given().spec(requestSpecification()).queryParam("place_id", place_id);	
  		user_calls_with_post_http_request(resource, "GET");
  		String name=getResponseValue(response, "name");
  		System.out.println("Name is " +name+ "place is" +place_id);
  		//Assert.assertEquals(string, name);    
  	}
  
  	@Given("DeletePlace Payload")
  	public void deleteplace_Payload() throws IOException {
  		res=given().spec(requestSpecification()).body(payload.deleteplaceAPI(place_id));
  	}
	
}
