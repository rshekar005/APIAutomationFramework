package com.testrunner;



import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;

@RunWith(Cucumber.class)
@CucumberOptions
(
		features= "E:/Git/APIAutomationRahulShetty/src/main/java/com/feature",//Path of a feature
		glue={"com/stepdefinition"},// Path of Step Definitions
		//format= {"pretty","html:test-output", "json:json_output/cucumber.json", "junit:junit_xml/cucumber.xml"}, //to generate different types of reporting(output)
		monochrome=true,//It will generate a console output in readable format.
	   // strict=true,//It will fail execution if any pending steps are not there.(Example: If in step definition any test is missed then test execution fails)
	    dryRun=false,
	   tags={"@SanityTest"},//It will run a scenario which is declared with @AddPlaceAPI in feature file
		plugin = "json:target/cucumber-reports/CucumberTestReport.json")
public class TestRunner {
}
