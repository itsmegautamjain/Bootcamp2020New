package mySalesforcePages;

import org.openqa.selenium.WebDriver;
import baseClasses.ActionSpecificBaseMethods;

public class ServiceAppointmentPage extends ActionSpecificBaseMethods {

//	public ServiceAppointmentPage(WebDriver driver) {
//		this.driver = driver;
//	}
	
	public ServiceAppointmentPage clickOnServiceAppointment() {
		//This method will click on the Service Appointment link
		clickElement(locateElement("Xpath", "//a[@data-label='Service Appointments']"));
		return this;
	}
	
	public ServiceAppointmentPage clickOnSearchAccounts() {
		//This method will click on Search Accounts text box
		clickElement(locateElement("Xpath", "//input[@title='Search Accounts']"));
		return this;
	}
		
	public ServiceAppointmentPage clickOnNewAccounts() {
		//This method will click on New Accounts link
		waitForElementToBeVisible(locateElement("Xpath", "//span[@title='New Account']"));
		clickElement(locateElement("Xpath", "//span[@title='New Account']"));
		waitForElementToBeVisible(locateElement("Xpath", "//h2[text()='New Account']"));
		return this;
	}
	
	public ServiceAppointmentPage clickOnNewButton() {
		// This method will click on New Button 
			waitForElementToBeClickable(locateElement("Xpath", "//div[text()='New']"));
			clickElement(locateElement("Xpath", "//div[text()='New']"));
			waitForElementToBeVisible(locateElement("Xpath", "//h2[text()='New Service Appointment']"));
		return this;
	}

	public ServiceAppointmentPage enterDescription(String description) {
		//This method will enter description on the ServiceAppointment window
		enterValues(locateElement("Xpath", "//textarea[@class=' textarea']"), description);
		return this;
	}
		
	public ServiceAppointmentPage enterAccountName(String accountName) {
		//This method will enter Account name 
		enterValues(locateElement("Xpath", "(//span[text()='Account Name']/parent::label)/following-sibling::input"), accountName);
		return this;
	}
			
	public ServiceAppointmentPage clickOnSaveButton() {
		//This method will click on save button
		clickElement(locateElement("Xpath", "(//button[@title='Save']//span)[2]"));			
		return this;
	}
			
}
