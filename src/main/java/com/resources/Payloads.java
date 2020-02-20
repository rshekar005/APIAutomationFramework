package com.resources;

import java.util.ArrayList;

import com.pojo.LocationPojo;
import com.pojo.MapPojo;

public class Payloads 
{
	public MapPojo addPlace(String name,String address,String language)
	{
		MapPojo m= new MapPojo();
		m.setAccuracy(50);
		//m.setAddress("29, side layout, cohen 09");
		//m.setName("Rajashekar");
		m.setAddress(address);
		m.setName(name);
		m.setPhone_number("871220333");
		m.setWebsite("https://Rajashekar");
		//m.setLanguage("English");
		m.setLanguage(language);
		
		//Adding list of objects
		ArrayList<String> mylist= new ArrayList<String>();
		mylist.add("Kirana Shop");
		mylist.add("Mutton Shop");
		m.setTypes(mylist);
		
		//It is a different pojo class so that we need to create a new method for this class and assign values to that objects and map location object to map object
		
		LocationPojo location= new LocationPojo();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		
		m.setLocation(location);
		return m;
	}
	
	//Delete place method. Here place_id passing as parameter which takes input from add place api
	public String deleteplaceAPI(String place)
	{
		return "{\r\n \"place_id\": \""+place+"\"\r\n}";
	}

}
