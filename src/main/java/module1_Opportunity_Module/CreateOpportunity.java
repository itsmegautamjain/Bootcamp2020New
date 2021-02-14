package module1_Opportunity_Module;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import mySalesforcePages.HomePage;
import utilities.SalesForceSpecificMethods;

public class CreateOpportunity extends SalesForceSpecificMethods {
	

	@BeforeClass(groups= {"No"})
	public void beforeClass() throws IOException {
		System.out.println("Before Class CreateOpportunity");
		fileName = "Bootcamp Test Data_CreateOpportunity";
		sheetName = "CreateOpportunity";
  }
	
	@Test(dataProvider="dataForSalesforceModules",groups= {"No"})
	public void createOpportunity(String opporName,String stageVal, String dateTobeSelected) {
				System.out.println("Executing Create Opportunity");				
				HomePage createOpportunity = new HomePage(driver);
				createOpportunity.clickOnSalesButton().clickOnOpportunityTab()	
													  .clickOnNewButton()
													  .enterOpportunityName(opporName)
													  .selectValueFromStageDropdown(stageVal)
													  .selectCloseDate(dateTobeSelected,"No")
													  .clickOnSaveButton()
													  .compareOpportunityText(opporName);

	}
	
	@AfterClass(groups= {"No"})
	public void afterClass() throws IOException {
		System.out.println("After Class CreateOpportunity");
  }
}
