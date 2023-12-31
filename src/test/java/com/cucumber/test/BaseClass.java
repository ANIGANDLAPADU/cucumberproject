package com.cucumber.test;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class BaseClass {
public static	WebDriver driver;
public static	Logger logger; // for logging
public static	ResourceBundle rb; // for reading properties file
public static	String br; // to store browser name
public static	String appurl; // to storeurl of the application

	@Before(order=1)
	public void setup() // Junit hook - executes once before starting
	{
		// for logging
		logger = LogManager.getLogger(this.getClass());
		// Reading config.properties (for browser)
		rb = ResourceBundle.getBundle("dynamic");
		br = rb.getString("browser");
		appurl = rb.getString("url");

	}

	@After(order=2)
	public void tearDown(Scenario scenario) {
		System.out.println("Scenario status" + scenario.getStatus());
		if (scenario.isFailed()) {

			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());

		}
		driver.quit();
	}
}
