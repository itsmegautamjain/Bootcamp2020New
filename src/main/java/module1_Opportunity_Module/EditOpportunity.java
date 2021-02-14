package module1_Opportunity_Module;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import mySalesforcePages.HomePage;
import utilities.SalesForceSpecificMethods;

public class EditOpportunity extends SalesForceSpecificMethods {

	@BeforeClass(groups= {"No"})
	public void beforeClass() throws IOException {
		System.out.println("Before Class EditOpportunity");
		fileName = "Bootcamp Test Data_EditOpportunity";
		sheetName = "EditOpportunity";
  }
	
	@Test(dataProvider="dataForSalesforceModules",groups= {"No"})
	public void editOpportunity(String opporName,String dateTobeSelected, String stageVal2, String deliveryVal,String description) {
			System.out.println("Executing Edit Opportunity");
				HomePage editOpportunity = new HomePage(driver);
				
						editOpportunity.clickOnSalesButton()
										.clickOnOpportunityTab()
										.searchOpportunity(opporName)
										.clickOnOpportunityCheckbox()
										.clickOnDropdownIcon()
										.clickOnEditFromDropdown()
										.selectCloseDate(dateTobeSelected, "1")
										.selectValueFromStageDropdown(stageVal2)
										.selectDeliveryStatus(deliveryVal)
										.enterDescription(description)
										.clickOnSaveButton()
										.waitForDeliveryStatusText(stageVal2)
										.verifyStageIsDisplayedCorrectly(stageVal2);
	}
	
	@AfterClass(groups= {"No"})
	public void afterClass() throws IOException {
		System.out.println("After Class EditOpportunity");
  }
	
}
