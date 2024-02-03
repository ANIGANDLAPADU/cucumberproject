package com.cucumber.hooks;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.cucumber.pageObjects.LoginPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	public LoginPage lp;
	Logger logger; // for logging
	public ResourceBundle rb; // for reading properties file
	String br; // to store browser name
	String appurl;
	public WebDriver driver;

	@Before(order = 1)
	public WebDriver setup() // Junit hook - executes once before starting
	{
		// for logging
		logger = LogManager.getLogger(this.getClass());
		// Reading config.properties (for browser)
		rb = ResourceBundle.getBundle("dynamic");
		br = rb.getString("browser");
		appurl = rb.getString("url");
		if (br.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (br.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		return driver;
	}

	@After(order = 2)
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
