package com.jira;
import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JIRATest {
	
	public static void main(String args[])
	{
		RestAssured.baseURI="http://localhost:8090";
		
		
		//Login
		//Here sessionFilter is used to get session cookie and same will be passed to other api's
		SessionFilter sessionFilter= new SessionFilter();
		String loginResponse=given().header("Content-Type", "application/json")
		       .body("{\r\n    \"username\": \"rshekar005\","
				    + "\r\n    \"password\": \"Shekar@1234\"\r\n}")
		       .log().all()
		       .filter(sessionFilter)
		       .when()
		       .post("/rest/auth/1/session")
		       .then().extract().response().asString();
		
		JsonPath json = new JsonPath(loginResponse);
		System.out.println(json.prettify());
		
		
		//Add comment
		//Here it is path parameter which is associated with URI
		// http://localhost:8090/rest/api/2/issue/10002/comment .. Here 10002 is a path parameter
		String comment= "New Comment";
	String createIssueresponse=	given().pathParam("id", "10003")
		.header("Content-Type", "application/json")
		.body("{\r\n    \"body\": \""+comment+"\","
				+ "\r\n    \"visibility\": "
				+ "{\r\n        \"type\": \"role\",\r\n        \"value\": \"Administrators\"\r\n    }\r\n}")
		.filter(sessionFilter)
		.when().log().all()
		.post("/rest/api/2/issue/{id}/comment")
		.then().assertThat().statusCode(201).extract().response().asString();
	
	JsonPath jsonPath1=new JsonPath(createIssueresponse);
		String commentid=	jsonPath1.getString("id");
		System.out.println("----------------------------------Comment id is--------------------------------- "+commentid);
	 
	 
	 //Add Attachment
	 	
	/*Response attachmentrespone= given().header("X-Atlassian-Token","no-check")
	         .filter(sessionFilter)
	         .pathParam("id", "10003")
	         .header("Content-Type","multipart/form-data")
	         .log().all()
	         //We cannot send file directly
	         .multiPart("file", new File("Attachment.txt"))
	         .when()
	         .post("/rest/api/2/issue/{id}/attachments")
	         .then().log().all().extract().response();
	//System.out.println(attachmentrespone.jsonPath().prettify());
	
	System.out.println(attachmentrespone.getStatusCode());*/
	
	
	//Get Issue
	String issueDetails= given().filter(sessionFilter).
	 pathParam("id", "10003").
	 //http://localhost:8090/rest/api/2/issue/10003?fields=comment
	 //This query parameter will retrieve only comment section
	 queryParam("fields", "comment").
	 log().all().
	 when().get("/rest/api/2/issue/{id}").
	 then().log().all().
	 extract().response().asString();
	 
	 JsonPath jsonPath= new JsonPath(issueDetails);
	int commentsCount= jsonPath.get("fields.comment.comments.size()");
	for(int i=0;i<commentsCount;i++)
	{
		String commentIssue= jsonPath.get("fields.comment.comments["+i+"].id").toString();
		if(commentIssue.equalsIgnoreCase(commentid))
		{
			String message=jsonPath.get("fields.comment.comments["+i+"].body").toString();
			System.out.println("Comment is "+message);
			Assert.assertEquals(message, comment);
		}
	}
	 
	         
		
		
	}
	

}
