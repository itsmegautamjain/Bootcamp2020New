package opportunityModuleTestNG;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseClasses.ActionSpecificBaseMethods;
import mySalesforcePages.HomePage;
import mySalesforcePages.LoginPage;
import utilities.DataLibrary;


public class OpportunityModule extends ActionSpecificBaseMethods {
	
	String opporName,stageVal,dateTobeSelected,deliveryVal,description,stageVal2;
	public static String fileName = "E:\\Test Leaf\\WorkSpace\\BootCamp\\Bootcamp Test Data.xlsx";
	public static String sheetName = "Opportunity";
	public static String userName = "cypress@testleaf.com";
	public static String password = "Bootcamp@123";
	
	@DataProvider
	public static String[][] dataForOpportunityModule() throws IOException{
		DataLibrary obj = new DataLibrary();
	    return	obj.readExcelData(fileName, sheetName);		
	}
	
	@Factory(dataProvider="dataForOpportunityModule")
	public OpportunityModule(String opporName,String stageVal,String deliveryVal,String description,String stageVal2,String dateTobeSelected) {
		this.opporName = opporName;
		this.stageVal = stageVal;
		this.dateTobeSelected = dateTobeSelected;
		this.deliveryVal = deliveryVal;
		this.description = description;
		this.stageVal2 = stageVal2;
	}
	
	@BeforeTest
	public void setDriver() throws IOException {
		System.out.println("Before Test");
  }
	
	@Parameters("Browser")
	@BeforeMethod
	public void setUp(String browser) {
		
		startApp(browser,"https://login.salesforce.com");
		LoginPage login = new LoginPage();
		login.enterUsername(userName).enterPassword(password).clickOnLoginButton()
															 .clickOnToggleMenuButton()
															 .clickOnViewAllButton()
															 .clickOnSalesButton()
															 .clickOnOpportunityTab();	
	}
	
	@Test(priority=0,enabled=false)
	public void createOpportunity() {
								
				HomePage createOpportunity = new HomePage();
				createOpportunity.clickOnNewButton().enterOpportunityName(opporName)
													.selectValueFromStageDropdown(stageVal)
													.selectCloseDate(dateTobeSelected,"No")
													.clickOnSaveButton()
													.compareOpportunityText(opporName);

	}
	
	@Test(priority=1)
	public void createOpportunityWithoutMandatoryFields() {
		
		HomePage createOpporWithoutMandatoryFields = new HomePage();
		
		createOpporWithoutMandatoryFields.clickOnNewButton()
									    	.selectCloseDate(dateTobeSelected, "1")
									    	.clickOnSaveButton()
											.verifyAlertMessageOnNewOpportunityPage()
											.clickOnCancelButton()
											.waitForNewButtonToBeClickable();

	}
	
	@Test(priority=2,dependsOnMethods="createOpportunity",enabled=false)
	public void editOpportunity() {
		
				HomePage editOpportunity = new HomePage();
				
						editOpportunity.searchOpportunity(opporName)
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
	
	
	@Test(priority=3,enabled=false)
	public void sortOpportunity() {
		
		HomePage sortOpportunity = new HomePage();
		
					sortOpportunity.selectTableView()
									.sortOpportunityInAscendingOrder();
	}
	
	
	@Test(priority=4,dependsOnMethods="createOpportunity",enabled=false)
	public void deleteOpportunity() {
		
				HomePage deleteOpportunity = new HomePage(); 
				
							deleteOpportunity.searchOpportunity(opporName)
											 .clickOnOpportunityCheckbox()
											 .clickOnDropdownIcon()
											 .clickOnDeleteFromDropdown()
											 .clickOnDeleteButton()											 
											 .verifyOpportunityDeleted(opporName);
	}
	
	@AfterMethod(alwaysRun=true)
	public void logout() {
			logoutMyApp();
			closeBrowser();
			quitBrowser();
	}
	
	@AfterTest(alwaysRun=true)
	public void closeDrivers() {
		System.out.println("After Test");
	}
}
