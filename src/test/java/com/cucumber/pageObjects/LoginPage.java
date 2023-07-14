package com.cucumber.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@name='txtuId']")
	WebElement txtEmailAddress;

	@FindBy(xpath = "//input[@name='txtPword']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@name='login']")
	WebElement btnLogin;
	@FindBy(xpath = "//td[@class='headings style12']//font//font")
	WebElement display;

	public void setEmail(String email) {
		txtEmailAddress.sendKeys(email);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void clickLogin() {
		btnLogin.click();
	}

	public String getLoginMessage() {
		String str = display.getText();
		return str;
		
	}

}
