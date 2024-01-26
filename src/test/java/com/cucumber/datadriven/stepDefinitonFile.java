package com.cucumber.datadriven;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
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
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class stepDefinitonFile {
	public LoginPage lp;
	public WebDriver driver;
	Logger logger; // for logging
	ResourceBundle rb; // for reading properties file
	String br; // to store browser name
	String appurl;
	List<HashMap<String, String>> datamap;

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
		driver.quit();
	}

	@Given("Open the browser and Enter URL as {string}")
	public void open_the_browser_and_enter_url_as(String appurl) {
		if (br.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (br.equals("edge")) {
			driver = new EdgeDriver();
		} else if (br.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.get(appurl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

	}

	@When("I check for the {string} in step")
	public void i_check_for_the_in_step(String rows) {
		datamap = DataReader.data(System.getProperty("user.dir") + "\\testdata\\LoginData.xlsx", "Sheet1");
		try {
			int index = Integer.parseInt(rows) - 1;
			String email = datamap.get(index).get("username");
			String pwd = datamap.get(index).get("password");
			lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();
		} catch (Exception e) {

		}

	}
}
