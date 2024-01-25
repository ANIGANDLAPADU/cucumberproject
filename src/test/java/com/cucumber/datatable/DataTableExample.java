package com.cucumber.datatable;

import java.time.Duration;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.cucumber.pageObjects.LoginPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DataTableExample {
	 WebDriver driver;
	 LoginPage lp;

	 Logger logger; // for logging
     ResourceBundle rb; // for reading properties file
	 String br; // to store browser name
	 String appurl; // to storeurl of the application

	@Before(order = 1)
	public void setup() // Junit hook - executes once before starting
	{
		// for logging
		logger = LogManager.getLogger(this.getClass());
		// Reading config.properties (for browser)
		rb = ResourceBundle.getBundle("dynamic");
		br = rb.getString("browser");
		appurl = rb.getString("url");

	}

	@After(order = 2)
	public void tearDown(Scenario scenario) {
		System.out.println("Scenario status" + scenario.getStatus());
		if (scenario.isFailed()) {

			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());

		}
		driver.close();
	}

	@Given("User Open The browser")
	public void user_open_the_browser() {
		if (br.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (br.equals("edge")) {
			driver = new EdgeDriver();
		} else if (br.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Given("User Enter URL as {string}")
	public void user_enter_url_as(String appurl) {
		driver.get(appurl);
	}

	@When("User Enter Details")
	public void user_enter_details(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap();
		lp = new LoginPage(driver);
		lp.setEmail(map.get("Email"));
		lp.setPassword(map.get("Password"));
	}

	@Then("Click on Enter Button")
	public void click_on_enter_button() {
		lp = new LoginPage(driver);
		lp.clickLogin();

	}

}
