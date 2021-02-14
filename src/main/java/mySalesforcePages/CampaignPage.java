package mySalesforcePages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import baseClasses.ActionSpecificBaseMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CampaignPage extends ActionSpecificBaseMethods {

	@Given("User launch following url {string} in {string} browser")
	public void openUrl(String url, String browser) {		
		startApp(browser,url);
	}	

	
	@And("Click on the Campaign tab")
	public CampaignPage clickOnCampaignTab() {
		// This method will click on Campaign tab 
		try {
			wait.until(ExpectedConditions.visibilityOf(locateElement("Xpath","//li[contains(@class,'metric')][3]/span[1]")));
			clickElement(locateElement("Xpath", "//a[@title='Campaigns']"));
		}catch(Exception e) {
			e.printStackTrace();
			wait.until(ExpectedConditions.visibilityOf(locateElement("Xpath","//li[contains(@class,'metric')][3]/span[1]")));
			clickElement(locateElement("Xpath", "//a[@title='Campaigns']"));
		}
		return this;
	}

	@And("Enter Campaign name as {string}")
	public CampaignPage enterCampaignName(String campaignName) {
		//This method will enter Campaign name
		try {
			waitForElementToBeVisible(locateElement("Xpath", "//h2[text()='New Campaign']"));		
			enterValues(locateElement("Xpath","//span[text()='Campaign Name']/parent::label/following-sibling::input"),campaignName);
		}catch(Exception e) {
			e.printStackTrace();
			waitForElementToBeVisible(locateElement("Xpath", "//h2[text()='New Campaign']"));		
			enterValues(locateElement("Xpath","//span[text()='Campaign Name']/parent::label/following-sibling::input"),campaignName);
		}
		return this;
	}
	
	@Then("Verify Newly created campaign is correct and displayed as {string}")
	public CampaignPage verifyNewCampaign(String campaignName) {
		//This method will verify the newly created campaign
		waitForElementToBeClickable(locateElement("Xpath", "//div[@title='New Contact']"));
		if(verifyVisiblity(locateElement("Xpath", "//div[contains(@title,'"+campaignName+"')]"))) {
			System.out.println("Campaign has been created successfully");
		}else {
			System.out.println("Campaign could not be created, Please check for errors");
		}
		return this;		
	}
	
	@And("Logout the Application")
	public CampaignPage logoutTheApplication() {
		//This method will logout the Application
		logoutMyApp();
		closeBrowser();
		return this;
	}
	
	@And("Click on the {string} link")
	public CampaignPage clickOnCampaign(String campaignName) {
		//This method will click on the campaign name link
		clickElement(locateElement("Xpath", "(//a[contains(@title,'"+campaignName+"')])[1]"));
		return this;
	}
	
	@And("Click on the Detail tab")
	public CampaignPage clickOnDetailTab() {
		//This method will click on the campaign name link
		clickElement(locateElement("Xpath", "//a[@title='Details']"));
		return this;
	}
	
	@And("Select start Date as {string} and {string}")
	public CampaignPage selectStartDate(String dateTobeSelected, String valueDate) {
		//This method will select the given start date
		SimpleDateFormat frmt = new SimpleDateFormat("MM/dd/yyyy");
		Date dt = new Date();
		String todayDate = frmt.format(dt);
		try {
		if(Integer.parseInt(valueDate)==0) {
			dateTobeSelected = todayDate;
		}
		else if(Integer.parseInt(valueDate)!=0) {
		Date dtVal = new Date(dt.getTime()+Integer.parseInt(valueDate)*(1000*60*60*24));
		String ValDate = frmt.format(dtVal);
		dateTobeSelected = ValDate;
		}
		}catch(NumberFormatException e) {
			System.out.println("Date selected as per given in data sheet");
		}
		clickElement(locateElement("Xpath","//span[text()='Start Date']/parent::label/following-sibling::div//a"));
		clickElement(locateElement("Xpath","//button[text()='Today']"));
		clickElement(locateElement("Xpath","//span[text()='Start Date']/parent::label/following-sibling::div//a"));
		
		selectByValueFromDropdown(locateElement("Xpath","//select[@class='slds-select picklist__label']"), dateTobeSelected.substring(6,10));
		
		int monthTobeSelected = Integer.parseInt(dateTobeSelected.substring(0,2)) - Integer.parseInt(todayDate.substring(0,2));
		if(monthTobeSelected>0) {
		for(int i=1;i<=monthTobeSelected;i++) {
			clickElement(locateElement("Xpath","//a[@title='Go to next month']"));
		}
		}
		else {
			for(int i=1;i<=0-monthTobeSelected;i++) {
				clickElement(locateElement("Xpath","//a[@title='Go to previous month']"));
			}			
		}		
		String dayToSelect = dateTobeSelected.substring(3,5);
		if(dayToSelect.substring(0,1).contains("0")) {
			dayToSelect = dayToSelect.substring(1,2);
		}
		if(locateElements("Xpath","//span[text()='"+dayToSelect+"']").size()>1) {
			if(Integer.parseInt(dayToSelect)<15) {
				clickElement(locateElement("Xpath","(//span[text()='"+dayToSelect+"'])[1]"));
			}else {
				clickElement(locateElement("Xpath","(//span[text()='"+dayToSelect+"'])[2]"));
			}
		}else {
		clickElement(locateElement("Xpath","//span[text()='"+dayToSelect+"']"));
		}
		return this;
	}
	
	
	@And("Select end Date as {string} and {string}")
	public CampaignPage selectEndDate(String dateTobeSelected, String valueDate) {
		//This method will select the given end date
		SimpleDateFormat frmt = new SimpleDateFormat("MM/dd/yyyy");
		Date dt = new Date();
		String todayDate = frmt.format(dt);
		try {
		if(Integer.parseInt(valueDate)==0) {
			dateTobeSelected = todayDate;
		}
		else if(Integer.parseInt(valueDate)!=0) {
		Date dtVal = new Date(dt.getTime()+Integer.parseInt(valueDate)*(1000*60*60*24));
		String ValDate = frmt.format(dtVal);
		dateTobeSelected = ValDate;
		}
		}catch(NumberFormatException e) {
			System.out.println("Date selected as per given in data sheet");
		}
		clickElement(locateElement("Xpath","//span[text()='End Date']/parent::label/following-sibling::div//a"));
		clickElement(locateElement("Xpath","//button[text()='Today']"));
		clickElement(locateElement("Xpath","//span[text()='End Date']/parent::label/following-sibling::div//a"));
		
		selectByValueFromDropdown(locateElement("Xpath","//select[@class='slds-select picklist__label']"), dateTobeSelected.substring(6,10));
		
		int monthTobeSelected = Integer.parseInt(dateTobeSelected.substring(0,2)) - Integer.parseInt(todayDate.substring(0,2));
		if(monthTobeSelected>0) {
		for(int i=1;i<=monthTobeSelected;i++) {
			clickElement(locateElement("Xpath","//a[@title='Go to next month']"));
		}
		}
		else {
			for(int i=1;i<=0-monthTobeSelected;i++) {
				clickElement(locateElement("Xpath","//a[@title='Go to previous month']"));
			}			
		}		
		String dayToSelect = dateTobeSelected.substring(3,5);
		if(dayToSelect.substring(0,1).contains("0")) {
			dayToSelect = dayToSelect.substring(1,2);
		}
		if(locateElements("Xpath","//span[text()='"+dayToSelect+"']").size()>1) {
			if(Integer.parseInt(dayToSelect)<15) {
				clickElement(locateElement("Xpath","(//span[text()='"+dayToSelect+"'])[1]"));
			}else {
				clickElement(locateElement("Xpath","(//span[text()='"+dayToSelect+"'])[2]"));
			}
		}else {
		clickElement(locateElement("Xpath","//span[text()='"+dayToSelect+"']"));
		}
		return this;
	}
	
	@And("Update the Expected Revenue as {string}")
	public CampaignPage updateExpectedRevenue(String expectedRevenue) {
		//This method will select the Expected Revenue
		enterKeys(locateElement("Xpath", "//span[text()='Expected Revenue in Campaign']/parent::label/following-sibling::input"), Keys.CONTROL,"a",Keys.DELETE);
		enterValues(locateElement("Xpath", "//span[text()='Expected Revenue in Campaign']/parent::label/following-sibling::input"), expectedRevenue);
		return this;
	}
	
	@And("Update the Budget Cost in Campaign as {string}")
	public CampaignPage updateBudgetCost(String budgetCost) {
		//This method will select the Expected Revenue
		enterKeys(locateElement("Xpath", "//span[text()='Budgeted Cost in Campaign']/parent::label/following-sibling::input"), Keys.CONTROL,"a",Keys.DELETE);
		enterValues(locateElement("Xpath", "//span[text()='Budgeted Cost in Campaign']/parent::label/following-sibling::input"), budgetCost);
		return this;
	}
	
	@Then("verify the updated Values displayed as {string} and {string} and {string}")
	public CampaignPage verifyUpdatedValuesInCampaign(String expectedRevenueGiven, String budgetedCostGiven, String dateTobeSelected) {
		//This will verify the updated values in Campaign
		SimpleDateFormat frmt = new SimpleDateFormat("MM/dd/yyyy");
		Date dt = new Date();
//		String todayDate = frmt.format(dt);
		Date dt4 = new Date(dt.getTime()+(Integer.parseInt(dateTobeSelected))*(1000*60*60*24));
		String tmrw4Date = frmt.format(dt4);
		dateTobeSelected = tmrw4Date;		
		executor.executeScript("arguments[0].scrollIntoView();",locateElement("Xpath", "//a[@title='Details']"));
		try {
			clickElement(locateElement("Xpath", "//button[@title='Edit End Date']") );
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Edit End Date']"))).click();
		}catch(StaleElementReferenceException e) {
			clickElement(locateElement("Xpath", "//button[@title='Edit End Date']") );
//			driver.findElement(By.xpath("//button[@title='Edit End Date']")).click();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[text()='End Date']/parent::label)/following-sibling::div/input")));
		String endDateText = getAttributeFromElement(locateElement("Xpath", "(//span[text()='End Date']/parent::label)/following-sibling::div/input"), "value");
		String expectedRevenue = getAttributeFromElement(locateElement("Xpath", "//span[text()='Expected Revenue in Campaign']/parent::label/following-sibling::input"), "value");
		String budgetedCost = getAttributeFromElement(locateElement("Xpath", "//span[text()='Budgeted Cost in Campaign']/parent::label/following-sibling::input"), "value");
//		String endDateText = driver.findElement(By.xpath("(//span[text()='End Date']/parent::label)/following-sibling::div/input")).getAttribute("value");
//		String expectedRevenue = driver.findElement(By.xpath("//span[text()='Expected Revenue in Campaign']/parent::label/following-sibling::input")).getAttribute("value");
//		String budgetedCost = driver.findElement(By.xpath("//span[text()='Budgeted Cost in Campaign']/parent::label/following-sibling::input")).getAttribute("value");
		System.out.println(endDateText);
		System.out.println(expectedRevenue);
		System.out.println(budgetedCost);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Cancel']")));
//		driver.findElement(By.xpath("//button[@title='Cancel']")).click();
		clickElement(locateElement("Xpath", "//button[@title='Cancel']"));
		if(expectedRevenue.replaceAll("[^0-9a-zA-Z]","").equals(expectedRevenueGiven) & budgetedCost.replaceAll("[^0-9a-zA-Z]","").equals(budgetedCostGiven) & endDateText.equals(dateTobeSelected)) {
			System.out.println("Values are updated as expected");
		}
		else {
			System.out.println("Values could not be updated, Please check for errors");
		}
		return this;
	}
	
	@And("Click on the Upload Button")
	public CampaignPage clickOnUploadButton() {
		//This method will click on the upload button
		clickElement(locateElement("Xpath", "//a[@title='Upload Files']"));
		return this;
	}
	
	@When("User Select a file from local and upload it")
	public CampaignPage uploadFile() throws InterruptedException {
		//This method will select a file from local and upload the same
		Thread.sleep(3000);
		String path = "E:\\Test Leaf\\WorkSpace\\BootCamp\\Selenium bootcamp from TestLeaf.pdf";
		StringSelection filePath = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
		Thread.sleep(5000);
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		}catch(AWTException e){
			e.printStackTrace();
		}	
		Thread.sleep(5000);
		waitForElementToBeVisible(locateElement("Xpath", "//h2[text()='Upload Files']"));
		clickElement(locateElement("Xpath", "//span[text()='Done']/parent::button"));
		return this;
	}
	
	@Then("Verify whether the file name is displayed as a link")
	public CampaignPage verifyFileNameAsLink() {
		//This method will verify whether the file is displayed as a link
		if(verifyVisiblity(locateElement("Xpath", "(//a[@title='Selenium bootcamp from TestLeaf'])[1]"))){
			System.out.println("Upload is successful");
		}
		else {
			System.out.println("Upload is unsuccessful");
		}
		return this;
	}
	
	@Then("Verify new Opportunity in Campaign tab displayed as {string}")
	public CampaignPage verifyNewOpportunityInCampaignTab(String opporName) {
		//This method will verify opportunity in campaign tab
		String opporText = null;
		executor.executeScript("arguments[0].scrollIntoView();",locateElement("Xpath", "(//a[contains(text(),'"+opporName+"')])[1]"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[contains(text(),'"+opporName+"')])[1]")));
		if(driver.findElement(By.xpath("//a[contains(text(),'"+opporName+"')]")).isEnabled()) {
			opporText = getAttributeFromElement(locateElement("Xpath", "//a[contains(text(),'"+opporName+"')]"), "text");
		}
		System.out.println(opporText);
		if(opporText.equals(opporName)){
			System.out.println("Opportunity has been created successfully in campaign tab");
		}
		else { 
			System.out.println("Opportunity has not been created successfully in campaign tab");
		}
		return this;
	}
	
	@And("Verify the newly created Opportunity in Opportunity tab displayed as {string}")
	public CampaignPage verifyNewOpportunityInOpportunityTab(String opporName) {
		//This method will verify opportunity in Opportunity tab
		enterValues(locateElement("Xpath", "//input[@name='Opportunity-search-input']"), opporName);
		enterKeys(locateElement("Xpath", "//input[@name='Opportunity-search-input']"), Keys.ENTER);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox']/following-sibling::span)[3]")));
		if(verifyVisiblity(locateElement("Xpath", "(//input[@type='checkbox']/following-sibling::span)[3]"))) {
			System.out.println("Opportunity is available in Opportunity tab");
		}else {
			System.out.println("Opportunity is not available in Opportunity tab, Please check for errors");
		}
		return this;
	}
}
