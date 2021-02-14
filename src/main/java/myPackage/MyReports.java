package myPackage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class MyReports {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//File Level
		ExtentSparkReporter spark = new ExtentSparkReporter("./sparkReports/results.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spark);
		
		//Test Case level
		ExtentTest test = extent.createTest("TC01_CreateCampaign", "Create a new Campaign");
		test.assignAuthor("Gautam Jain");
		test.assignCategory("Smoke");
		test.assignDevice("HP Laptop");
		
		//Step Level
		test.pass("Logged in to Salesforce successfully");
		test.pass("Campaign has been created successfully");
		test.pass("Logged out successfully");
		test.fail("Database issue");
		test.warning("Kindly check DB connections");
		test.info("This Test case is from Smoke Pack");
		test.skip("Test Case skipped");
		
		//Write the logic
		extent.flush();
	}

}
