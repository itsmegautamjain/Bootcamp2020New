package module1_Opportunity_Module;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import mySalesforcePages.HomePage;
import utilities.SalesForceSpecificMethods;

public class CreateOpportunityWithoutMandatoryFields extends SalesForceSpecificMethods {
	
	@BeforeClass(groups= {"No"})
	public void beforeClass() throws IOException {
		System.out.println("Before Class CreateOpportunityWithoutMandatory");
		fileName = "Bootcamp Test Data_CreateOppoWithoutMandatoryField";
		sheetName = "CreateOppoWithoutMandatoryField";
  }
	
	@Test(dataProvider="dataForSalesforceModules",groups= {"No"})
	public void createOpportunityWithoutMandatoryFields(String dateTobeSelected) {
		System.out.println("Executing CreateOpportunityWithoutMandatory");
		HomePage createOpporWithoutMandatoryFields = new HomePage(driver);
		
		createOpporWithoutMandatoryFields.clickOnSalesButton()
											.clickOnOpportunityTab()
											.clickOnNewButton()
									    	.selectCloseDate(dateTobeSelected, "1")
									    	.clickOnSaveButton()
											.verifyAlertMessageOnNewOpportunityPage()
											.clickOnCancelButton()
											.waitForNewButtonToBeClickable();

	}
	
	@AfterClass(groups= {"No"})
	public void afterClass() throws IOException {
		System.out.println("After Class CreateOpportunityWithoutMandatory");
  }
	
}
