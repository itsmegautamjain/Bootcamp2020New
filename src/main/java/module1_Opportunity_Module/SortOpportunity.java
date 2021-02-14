package module1_Opportunity_Module;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import mySalesforcePages.HomePage;
import utilities.SalesForceSpecificMethods;

public class SortOpportunity extends SalesForceSpecificMethods {

	
	@BeforeClass(groups= {"No"})
	public void beforeClass() throws IOException {
		System.out.println("Before Class SortOpportunity");
		fileName = "Bootcamp Test Data_SortOpportunity";
		sheetName = "SortOpportunity";
  }
	
	@Test(dataProvider="dataForSalesforceModules",groups= {"No"})
	public void sortOpportunity(String iteration) {
		System.out.println("Executing Sort Opportunity "+iteration);
		HomePage sortOpportunity = new HomePage();
		
					sortOpportunity.clickOnSalesButton()
									.clickOnOpportunityTab()
									.selectTableView()
									.sortOpportunityInAscendingOrder();
	}
	
	@AfterClass(groups= {"No"})
	public void afterClass() throws IOException {
		System.out.println("After Class SortOpportunity");
  }
	
}
