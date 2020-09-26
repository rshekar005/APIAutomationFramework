package com.resources;
//Enum in java is used for contants methods or variables
public enum APINames 
{
	//APIName like AddPlaceAPI should be same in feature file if not then it will throw an error.
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	public String resource;
	
	//resource  will come from feature file(Ex: AddPlaceAPI) and append it to String resource
	private APINames(String resource) 
	{
		this.resource=resource;
	}
	
	//Above resource will return in below method
	public String getResourceName()
	{
		return resource;
	}

}
