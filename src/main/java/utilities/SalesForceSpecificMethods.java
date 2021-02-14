package utilities;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import baseClasses.ActionSpecificBaseMethods;
import mySalesforcePages.LoginPage;

public class SalesForceSpecificMethods extends ActionSpecificBaseMethods {
	
	public static String fileName;
	public static String sheetName;
	public static String userName = "cypress@testleaf.com";
	public static String password = "Bootcamp@123";
	
	@DataProvider
	public static String[][] dataForSalesforceModules() throws IOException{
		DataLibrary obj = new DataLibrary();
	    return	obj.readExcelData(fileName, sheetName);		
	}
	
	@BeforeTest(alwaysRun=true)
	public void beforeTest() {
		System.out.println("Before Test");
	}

	@Parameters("Browser")
	@BeforeMethod(alwaysRun=true)
	public void setUp(String browser) {
		
		startApp(browser,"https://login.salesforce.com");
		LoginPage login = new LoginPage();
		login.enterUsername(userName).enterPassword(password).clickOnLoginButton()
															 .clickOnToggleMenuButton()
															 .clickOnViewAllButton();
	}
	
	@AfterMethod(alwaysRun=true)
	public void logout() {	
	
		logoutMyApp();
		closeBrowser();
//		quitBrowser();
	}
	
	@AfterTest(alwaysRun=true)
	public void afterTest() {
		System.out.println("Before Test");
	}
	
}
