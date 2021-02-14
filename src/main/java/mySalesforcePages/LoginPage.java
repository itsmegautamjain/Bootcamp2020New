package mySalesforcePages;

import org.openqa.selenium.WebDriver;

import baseClasses.ActionSpecificBaseMethods;
import io.cucumber.java.en.And;

public class LoginPage extends ActionSpecificBaseMethods {
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@And("Enter Username as {string}")
	public LoginPage enterUsername(String userName) {
		enterValues(locateElement("Xpath","//input[@id='username']"), userName);
		return this;
	}
	
	@And("Enter Password as {string}")
	public LoginPage enterPassword(String password) {
		enterValues(locateElement("Xpath","//input[@id='password']"), password);
		return this;
	}
	
	@And("Click on Login button")
	public HomePage clickOnLoginButton() {
		clickElement(locateElement("Xpath","//input[@id='Login']"));
		return new HomePage(driver);
	}
	
}
