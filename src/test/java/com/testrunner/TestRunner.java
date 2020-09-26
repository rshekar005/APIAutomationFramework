package com.testrunner;





import org.testng.annotations.AfterMethod;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


/*@RunWith(Cucumber.class)*/
@CucumberOptions
(
		features= "E:/Git/APIAutomationRahulShetty/src/main/java/com/feature",//Path of a feature
		glue={"com/stepdefinition"},// Path of Step Definitions
		/*format= {"pretty", 
                "html:target/cucumber-reports/cucumber-pretty",
               }, *///to generate different types of reporting(output)
		monochrome=true,//It will generate a console output in readable format.
	  // strict=true,//It will fail execution if any pending steps are not there.(Example: If in step definition any test is missed then test execution fails)
	   dryRun=false,
	   tags={"@AddPlaceAPI"},//It will run a scenario which is declared with @AddPlaceAPI in feature file
		plugin ={"html:target/cucumber-html-report",
				"json:target/cucumber-reports/cucumber.json",""
				+ "junit:target/cucumber-reports/cucumber.xml",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)
public class TestRunner extends AbstractTestNGCucumberTests {
	

	

}
