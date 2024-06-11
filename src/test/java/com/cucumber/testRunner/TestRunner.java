package com.cucumber.testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "D:\\projects\\cucumberproject\\Features" },

		// features = "@target/rerun.txt", // it is used to run only failuer testcases
		glue = { "com.cucumber.stepDefinitions" }, plugin = { "pretty", "html:reports/myreport.html",
				"json:reports/myreport.json", "rerun:target/rerun.txt",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, // Mandatory to capture
																							// failures
		dryRun = false, monochrome = true, publish = true, tags = "@sanity"// Scenarios tagged with @sanity,
)
public class TestRunner {
	/*
	 * @Override
	 * 
	 * @DataProvider(parallel = true) public Object[][] scenarios() { return
	 * super.scenarios(); }
	 */
}
