package com.cucumber.testRunner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(

		
		  features = { "D:\\projects\\cucumberproject\\Features\\UsingExcel.feature"
		   },
		 
		/*
		 * features = {
		 * "D:\\projects\\cucumberproject\\Features\\UsingDataTable.feature" },
		 */
		 
		/*
		 * features = { "D:\\javaprojects\\cucumberproject\\Features\\Login.feature" },
		 */
		// features = { "D:\\javaprojects\\cucumberproject\\Features\\login.feature" },

		// features = "@target/rerun.txt", // it is used to run only failuer testcases
		glue = "com.cucumber.datadriven", plugin = { "pretty", "html:reports/myreport.html",
				"json:reports/myreport.json", "rerun:target/rerun.txt",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		}, // Mandatory to capture failures
		dryRun = false, monochrome = true,
		publish=true,tags = "@sanity"// Scenarios tagged with @sanity,
)
public class TestRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
