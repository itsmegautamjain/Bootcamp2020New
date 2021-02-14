package steps_Campaign;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import baseClasses.ActionSpecificBaseMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import mySalesforcePages.HomePage;

public class CreateCampaign extends ActionSpecificBaseMethods {		
	
	@Given("User launch following url {string} in {string} browser")
	public void openUrl(String url, String browser) {		
		startApp(browser,url);
	}	

	
	@And("Click on the Campaign tab")
	public CreateCampaign clickOnCampaignTab() {
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
	public CreateCampaign enterCampaignName(String campaignName) {
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
	public CreateCampaign verifyNewCampaign(String campaignName) {
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
	public CreateCampaign logoutTheApplication() {
		//This method will logout the Application
		logoutMyApp();
		closeBrowser();
		return this;
	}
	
	@And("Click on the {string} link")
	public CreateCampaign clickOnCampaign(String campaignName) {
		//This method will click on the campaign name link
		clickElement(locateElement("Xpath", "(//a[contains(@title,'"+campaignName+"')])[1]"));
		return this;
	}
	
	@And("Click on the Detail tab")
	public CreateCampaign clickOnDetailTab() {
		//This method will click on the campaign name link
		clickElement(locateElement("Xpath", "//a[@title='Details']"));
		return this;
	}
	
	@And("Select start Date as {string} and {string}")
	public CreateCampaign selectStartDate(String dateTobeSelected, String valueDate) {
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
	public CreateCampaign selectEndDate(String dateTobeSelected, String valueDate) {
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
	public CreateCampaign updateExpectedRevenue(String expectedRevenue) {
		//This method will select the Expected Revenue
		enterKeys(locateElement("Xpath", "//span[text()='Expected Revenue in Campaign']/parent::label/following-sibling::input"), Keys.CONTROL,"a",Keys.DELETE);
		enterValues(locateElement("Xpath", "//span[text()='Expected Revenue in Campaign']/parent::label/following-sibling::input"), expectedRevenue);
		return this;
	}
	
	@And("Update the Budget Cost in Campaign as {string}")
	public CreateCampaign updateBudgetCost(String budgetCost) {
		//This method will select the Expected Revenue
		enterKeys(locateElement("Xpath", "//span[text()='Budgeted Cost in Campaign']/parent::label/following-sibling::input"), Keys.CONTROL,"a",Keys.DELETE);
		enterValues(locateElement("Xpath", "//span[text()='Budgeted Cost in Campaign']/parent::label/following-sibling::input"), budgetCost);
		return this;
	}
	
	@Then("verify the updated Values displayed as {string} and {string} and {string}")
	public CreateCampaign verifyUpdatedValuesInCampaign(String expectedRevenueGiven, String budgetedCostGiven, String dateTobeSelected) {
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
	public CreateCampaign clickOnUploadButton() {
		//This method will click on the upload button
		clickElement(locateElement("Xpath", "//a[@title='Upload Files']"));
		return this;
	}
	
	@When("User Select a file from local and upload it")
	public CreateCampaign uploadFile() throws InterruptedException {
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
	public CreateCampaign verifyFileNameAsLink() {
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
	public CreateCampaign verifyNewOpportunityInCampaignTab(String opporName) {
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
	public CreateCampaign verifyNewOpportunityInOpportunityTab(String opporName) {
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
	
//	WebDriver driver;
//
//	@Given("User launch following url {string}")
//	public void openUrl(String url) {
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--disable-notifications");
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
//		driver.get(url);
//		driver.manage().window().maximize();
//	}
//
//	@And("Enters Username as {string}")
//	public void enterUsername(String userName) {
//		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(userName);
//	}
//
//	@And("Enters Password as {string}")
//	public void enterPassword(String password) {
//		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
//	}
//	
//	@And("Clicks on Login button")
//	public void clicksOnLoginButton() {
//		driver.findElement(By.xpath("//input[@id='Login']")).click();
//	}
//	
//	@And("Clicks on the toggle menu button from the left corner")
//	public void clicksOnToggleMenu() {
//		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
//	}
//	
//	@And("Clicks on View All")
//	public void clickOnViewAll() {
//		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
//	}
//	
//	@And("Clicks on Sales from App launcher")
//	public void clickOnSales() {
//		driver.findElement(By.xpath("//p[text()='Sales']")).click();
//	}
//	
//	@And("Clicks on the Campaign tab")
//	public void clickOnCampaignTab() throws InterruptedException {
//		Thread.sleep(3000);
//		WebElement elm = driver.findElement(By.xpath("//a[@title='Campaigns']"));		
//		JavascriptExecutor executor = (JavascriptExecutor)driver;
//		executor.executeScript("arguments[0].click();", elm);
//	}
//	
//	@And("Clicks on the New Button")
//	public void clickOnNewButton() throws InterruptedException {
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//a[@title='New']")).click();
//	}
//	
//	@And("Enter {string}")
//	public void enterCampaignName(String campaignName) throws InterruptedException {
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//span[text()='Campaign Name']/parent::label/following-sibling::input")).sendKeys(campaignName);
//		driver.close();
//		driver.quit();
//	}	
	
}
