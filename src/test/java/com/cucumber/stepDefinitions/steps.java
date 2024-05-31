package com.cucumber.stepDefinitions;

import java.time.Duration;
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

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class steps {
	WebDriver driver;
	LoginPage lp;

	Logger logger; // for logging
	ResourceBundle rb; // for reading properties file
	String br; // to store browser name
	String appurl; // to storeurl of the application

	@Before(order = 3)
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
	public void tearDown() {

		driver.close();
	}

	@AfterStep
	public void takeScreenShoot(Scenario scenario) {
		System.out.println("Scenario status" + scenario.getStatus());
		if (scenario.isFailed()) {

			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());

		}
	}

	@Given("User Launch browser")
	public void user_launch_browser() {
		if (br.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (br.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (br.equals("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Given("opens URL {string}")
	public void opens_url(String appurl) {
		driver.get(appurl);
		driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String pwd) {
		lp = new LoginPage(driver);

		lp.setEmail(email);
		logger.info("Provided Email");
		lp.setPassword(pwd);
		logger.info("Provided Password ");
	}

	@Then("Click on Login button")
	public void click_on_login_button() {

		String expectedMessage = "Welcome to Admin";
		lp.clickLogin();
		logger.info("Clicked on Login button");
		String actualMessage = lp.getLoginMessage();
		if (actualMessage.equals(expectedMessage)) {
			lp.getLoginMessage();
			System.out.println("Login successful");
		} else {
			System.out.println("Login failed");
		}
	}
}
