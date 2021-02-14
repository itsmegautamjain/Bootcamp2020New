package assesment;

import org.testng.annotations.Test;

import mySalesforcePages.Assesment02Page;
import utilities.SalesForceSpecificMethods;

public class Assesment02 extends SalesForceSpecificMethods {

	@Test
	public void assesment02() {		
		System.out.println("Executing Assesment02 Test Case");
		Assesment02Page assesment02 = new Assesment02Page(driver);
						assesment02.clickOnServiceLink()
									.clickOnReportsTab()
									.clickOnNewReportButton()
									.clickOnLeads()
									.selectRange("All Time");	
	}
	
}
