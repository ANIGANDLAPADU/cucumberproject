package com.cucumber.test;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.cucumber.pageObjects.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepsdefinition extends BaseClass {
      public static LoginPage lp;
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
