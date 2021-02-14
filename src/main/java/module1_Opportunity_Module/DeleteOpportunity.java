package module1_Opportunity_Module;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import mySalesforcePages.HomePage;
import utilities.SalesForceSpecificMethods;

public class DeleteOpportunity extends SalesForceSpecificMethods {
	
	@BeforeClass(groups= {"No"})
	public void beforeClass() throws IOException {
		System.out.println("Before Class DeleteOpportunity");
		fileName = "Bootcamp Test Data_DeleteOpportunity";
		sheetName = "deleteOpportunity";
  }

	@Test(dataProvider="dataForSalesforceModules",groups= {"No"})
	public void deleteOpportunity(String opporName) {
		
				HomePage deleteOpportunity = new HomePage(driver); 
				
							deleteOpportunity.clickOnSalesButton()
											 .clickOnOpportunityTab()
											 .searchOpportunity(opporName)
											 .clickOnOpportunityCheckbox()
											 .clickOnDropdownIcon()
											 .clickOnDeleteFromDropdown()
											 .clickOnDeleteButton()											 
											 .verifyOpportunityDeleted(opporName);
	}
	
	@AfterClass(groups= {"No"})
	public void afterClass() throws IOException {
		System.out.println("After Class DeleteOpportunity");
  }
	
}
