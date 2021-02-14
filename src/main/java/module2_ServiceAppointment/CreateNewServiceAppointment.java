package module2_ServiceAppointment;

import org.testng.annotations.Test;

import mySalesforcePages.ServiceAppointmentPage;
import utilities.SalesForceSpecificMethods;

public class CreateNewServiceAppointment extends SalesForceSpecificMethods {

	@Test
	public void createNewServiceAppointment() {
		System.out.println("Executing CreateNewServiceAppointment");
		ServiceAppointmentPage createNewServiceAppointment = new ServiceAppointmentPage(driver);
								createNewServiceAppointment.clickOnServiceAppointment()
							   							    .clickOnNewButton()
							   							    .enterDescription("Creating Service Appointment")
							   							    .clickOnSearchAccounts()
							   							    .clickOnNewAccounts()
							   							    .enterAccountName("Gautam Jain")
							   							    .clickOnSaveButton();
	}				
}
