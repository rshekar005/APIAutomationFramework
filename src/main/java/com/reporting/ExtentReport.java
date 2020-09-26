/*package com.reporting;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.relevantcodes.extentreports.ExtentReports;


public class ExtentReport {
	
	public static ExtentReports report=null;
	public static String extentreportpath="";
	

	//To avoid external initialization
	private ExtentReport() {
		extentreportpath=System.getProperty("user.dir")+"/ExtentReports/index.html";
		ExtentHtmlReporter extentHtmlReporter= new ExtentHtmlReporter(extentreportpath);
		report=new ExtentReports();
		report.attachReporter(extentHtmlReporter);
		report.
		report.loadConfig(new File(System.getProperty("user.dir")+"/src/main/java/com/resources/extentreport.xml"));
		
	}

	public static void initialize()
	{
		new ExtentReport();
	}
	

}
*/