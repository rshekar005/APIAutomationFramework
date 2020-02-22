package com.stepdefinition;

import java.io.IOException;

import io.cucumber.java.Before;

// Here this class is used when 1st scenario is independent of second scenario. We can Run Second scenario if first scenario which is in feature file is commented.
public class Hooks 
{
	//@@DeletePlaceAPI is acts as tagged hook which is maintained in feature file
	// Utilizing methods of addPlaceAPI methods to get place id in this method to delete
	@Before("@DeletePlaceAPI")
	public void deleteAPI() throws IOException
	{
		//We are running add place api without from feature file
		PlaceAPI p = new PlaceAPI();
		if(p.place_id==null)
		{
			p.add_place_payload_with("Nani", "Hyderabad", "Marati");
			p.user_calls_with_post_http_request("AddPlaceAPI", "POST");
			p.verify_the_place_id_created_that_maps_to_name_using("Nani", "GetPlaceAPI");
		}
		
		
	}

}
