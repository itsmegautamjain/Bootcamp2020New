package mySalesforcePages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import baseClasses.ActionSpecificBaseMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class HomePage extends ActionSpecificBaseMethods{
	
//	public HomePage(WebDriver driver) {
//		this.driver = driver;
//	}

	@And("Click on the toggle menu button from the left corner")
	public HomePage clickOnToggleMenuButton() {
		//This method will Click on the toggle menu button from the left corner
		try {
			waitForElementToBeClickable(locateElement("Xpath", "//button[@title='Learn More']"));
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Learn More']")));	
		clickElement(locateElement("Xpath","//div[@class='slds-icon-waffle']"));
		}catch(Exception e) {
			e.printStackTrace();
			waitForElementToBeClickable(locateElement("Xpath", "//button[@title='Learn More']"));
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Learn More']")));
			clickElement(locateElement("Xpath","//div[@class='slds-icon-waffle']"));
		}
		return this;
	}
	
	@And("Click on View All")
	public HomePage clickOnViewAllButton() {
		// This method will click on view all button on toggle menu 
		try {
			clickElement(locateElement("Xpath","//button[@class='slds-button']"));
		}catch(Exception e) {
			e.printStackTrace();
			clickElement(locateElement("Xpath","//button[@class='slds-button']"));
		}
		return this;
	}
	
	@And("Click on Sales from App launcher")
	public HomePage clickOnSalesButton() {
		// This method will click on Sales button on App launcher 
			clickElement(locateElement("Xpath","//p[text()='Sales']"));
		return this;
	}
	
	@And("Click on Opportunities Tab")
	public HomePage clickOnOpportunityTab() {
		// This method will click on Opportunity tab  
		try {
			waitForElementToBeVisible(locateElement("Xpath", "//li[contains(@class,'metric')][3]/span[1]"));
//			wait.until(ExpectedConditions.visibilityOf(locateElement("Xpath","//li[contains(@class,'metric')][3]/span[1]")));
			clickElement(locateElement("Xpath", "//a[@href='/lightning/o/Opportunity/home']//span[1]"));
		}catch(Exception e) {
			e.printStackTrace();
			waitForElementToBeVisible(locateElement("Xpath", "//li[contains(@class,'metric')][3]/span[1]"));
//			wait.until(ExpectedConditions.visibilityOf(locateElement("Xpath","//li[contains(@class,'metric')][3]/span[1]")));
			clickElement(locateElement("Xpath", "//a[@href='/lightning/o/Opportunity/home']//span[1]"));
		}
		return this;
	}
	
	@And("Click on the New Button")
	public HomePage clickOnNewButton() {
		// This method will click on New Button 
			waitForElementToBeClickable(locateElement("Xpath", "//div[text()='New']"));
//			wait.until(ExpectedConditions.elementToBeClickable(locateElement("Xpath","//div[text()='New']")));
			clickElement(locateElement("Xpath", "//div[text()='New']"));
		return this;
	}
	
	@And("Enter Opportunity name as {string}")
	public HomePage enterOpportunityName(String opporName) {
		//This method will enter Opportunity name
		try {
			waitForElementToBeVisible(locateElement("Xpath", "//h2[text()='New Opportunity']"));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='New Opportunity']")));		
			enterValues(locateElement("Xpath","((//span[text()='Opportunity Name'])[2]/parent::label)/following-sibling::input"),opporName);
		}catch(Exception e) {
			e.printStackTrace();
			waitForElementToBeVisible(locateElement("Xpath", "//h2[text()='New Opportunity']"));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='New Opportunity']")));		
			enterValues(locateElement("Xpath","((//span[text()='Opportunity Name'])[2]/parent::label)/following-sibling::input"),opporName);
		}
		return this;
	}
	
	@And("Select Stage as {string}")
	public HomePage selectValueFromStageDropdown(String stageVal) {
		//This method will select given value from the stage dropdown 
		try {
			waitForElementToBeClickable(locateElement("Xpath", "//span[text()='Stage'][1]/parent::span/following-sibling::div"));
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Stage'][1]/parent::span/following-sibling::div")));
			clickElement(locateElement("Xpath", "//span[text()='Stage'][1]/parent::span/following-sibling::div"));
			waitForElementToBeVisible(locateElement("Xpath", "//a[text()='"+stageVal+"']"));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+stageVal+"']")));
			clickElement(locateElement("Xpath", "//a[text()='"+stageVal+"']"));
		}catch(Exception e) {
			e.printStackTrace();
			waitForElementToBeClickable(locateElement("Xpath", "//span[text()='Stage'][1]/parent::span/following-sibling::div"));
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Stage'][1]/parent::span/following-sibling::div")));
			clickElement(locateElement("Xpath", "//span[text()='Stage'][1]/parent::span/following-sibling::div"));
			waitForElementToBeVisible(locateElement("Xpath", "//a[text()='"+stageVal+"']"));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+stageVal+"']")));
			clickElement(locateElement("Xpath", "//a[text()='"+stageVal+"']"));
		}
		return this;
	}
	
	@And("Select close date as {string} and {string}")
	public HomePage selectCloseDate(String dateTobeSelected, String valueDate) {
		//This method will select the given date
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
		clickElement(locateElement("Xpath","//a[@class='datePicker-openIcon display']"));
		clickElement(locateElement("Xpath","//button[text()='Today']"));
		clickElement(locateElement("Xpath","//a[@class='datePicker-openIcon display']"));
		
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
	
	@When("User click on Save button")
	public HomePage clickOnSaveButton() {
		//This method will click on save button
		try {
			clickElement(locateElement("Xpath", "//button[@title='Save']//span[1]"));			
		}catch(Exception e) {
			e.printStackTrace();
			clickElement(locateElement("Xpath", "//button[@title='Save']//span[1]"));
		}
		return this;
	}
	
	public HomePage clickOnCancelButton() {
		//This method will click on cancel button
		try {
			clickElement(locateElement("Xpath", "//button[@title='Cancel']"));			
		}catch(Exception e) {
			e.printStackTrace();
			clickElement(locateElement("Xpath", "//button[@title='Cancel']"));
		}
		return this;
	}
	
	public HomePage waitForNewButtonToBeClickable() {
		//This method will wait until element becomes clickable
		waitForElementToBeClickable(locateElement("Xpath", "//div[text()='New']"));
		return this;
	}
	
	public HomePage compareOpportunityText(String opporName) {
		//This method will fetch text from opportunity section and compare it with opportunity name
		if(compareText(locateElement("Xpath","//slot[@name='primaryField']//lightning-formatted-text[1]"), opporName)){
			System.out.println("Opportunity has been created successfully");
			System.out.println(getTextFromElement(locateElement("Xpath", "//slot[@name='primaryField']//lightning-formatted-text[1]")));
		}
		else { 
			System.out.println("Opportunity has not been created successfully");
		}
		return this;
	}
	
	public HomePage verifyAlertMessageOnNewOpportunityPage() {
		//This method will fetch text from opportunity section and compare it with opportunity name
		if(verifyVisiblity(locateElement("Xpath", "//li[text()='These required fields must be completed: Opportunity Name, Stage']"))){
			System.out.println("Error Msg is displayed correctly");
		}
		else { 
			System.out.println("Error Msg is not displayed correctly");
		}
		return this;		
	}
	
	public HomePage searchOpportunity(String opporName) {
		//This method will search for the opportunity on the Opportunity home page
		waitForElementToBeClickable(locateElement("Xpath", "(//input[@type='checkbox']/following-sibling::span)[3]"));
		enterValues(locateElement("Xpath", "//input[@name='Opportunity-search-input']"), opporName);
		enterKeys(locateElement("Xpath", "//input[@name='Opportunity-search-input']"), Keys.ENTER);
		return this;
	}
	
	public HomePage clickOnOpportunityCheckbox() {
		//This method will click on the checkbox of the first opportunity searched
		try {
			waitForElementStaleness(locateElement("Xpath","(//input[@type='checkbox']/following-sibling::span)[3]"));
			clickElement(locateElement("Xpath", "(//input[@type='checkbox']/following-sibling::span)[3]"));
		}catch(Exception e){
			e.printStackTrace();
			clickElement(locateElement("Xpath", "(//input[@type='checkbox']/following-sibling::span)[3]"));
		}
		return this;
	}
	
	public HomePage clickOnDropdownIcon() {
		//This method will click on the dropdown icon of the selected opportunity
			clickElement(locateElement("Xpath", "//a[contains(@class,'slds-button slds-button--icon-x-small')]"));
		return this;
	}
	
	public HomePage clickOnEditFromDropdown() {
		//This method will click on the edit from the dropdown icon on the selected opportunity
			clickElement(locateElement("Xpath", "//a[@title='Edit']"));
		return this;
	}
	
	public HomePage selectDeliveryStatus(String delivery) {
		//This method will select the delivery status 
		clickElement(locateElement("Xpath", "//span[text()='Delivery/Installation Status'][1]/parent::span/following-sibling::div"));
		clickElement(locateElement("Xpath", "//a[@title='"+delivery+"']"));
		return this;
	}
	
	public HomePage enterDescription(String description) {
		//This method will enter description on the edit pop-up window
		enterValues(locateElement("Xpath", "//textarea[@class=' textarea']"), description);
		return this;
	}
	
	public HomePage waitForDeliveryStatusText(String text) {
		//This method will wait until delivery status is not shown as given in edited opportunity 
		try {
			waitForTextInElement(locateElement("Xpath", "//table/tbody/tr[1]/td[5]"), text);
		}catch(Exception e) {
			waitForTextInElement(locateElement("Xpath", "//table/tbody/tr[1]/td[5]"), text);
		}
		return this;
	}
	
	public HomePage verifyStageIsDisplayedCorrectly(String stage) {
		//This method will verify whether updated stage value is displayed correctly
		if(compareText(locateElement("Xpath", "//table/tbody/tr[1]/td[5]"), stage)) {
			System.out.println("Stage is displayed correctly and displayed as:- "+stage);
		}else {
			System.out.println("Stage is not displayed correctly");
		}
		return this;
	}
	
	public HomePage clickOnDeleteFromDropdown() {
		//This method will click on the delete from the dropdown icon on the selected opportunity
			clickElement(locateElement("Xpath", "//a[@title='Delete']"));
		return this;
	}
	
	public HomePage clickOnDeleteButton() {
		//This method will click on the delete button
			clickElement(locateElement("Xpath", "//button[@title='Delete']"));
		return this;
	}
	
	public HomePage verifyOpportunityDeleted(String opporName) {
		//This method will verify whether opportunity has been deleted
		waitForElementToBeVisible(locateElement("Xpath", "//p[text()='No items to display.']"));

		if(locateElements("Xpath", "(//input[@type='checkbox']/following-sibling::span)[3]").size()>0){
			System.out.println("Opportunity could not be deleted successfully, Please check for errors");
		}
			else if(verifyVisiblity(locateElement("Xpath", "//p[text()='No items to display.']"))){
			System.out.println("Opportunity deleted successfully");
		}
		return this;
	}	
	
	public HomePage selectTableView() {
		//This method will select the Table view for the opportunities displayed
		waitForElementToBeVisible(locateElement("Xpath", "//span[@class='countSortedByFilteredBy']"));
		clickElement(locateElement("Xpath", "//button[contains(@title,'Display as')]"));
		waitForElementToBeVisible(locateElement("Xpath", "//li[@title='Table']/a"));
		clickElement(locateElement("Xpath","//li[@title='Table']/a"));
		return this;
	}
	
	public HomePage sortOpportunityInAscendingOrder() {
		//This method will sort the opportunities in Ascending order
		List<String> closeDate = new ArrayList<String>(); 	
		String count = getTextFromElement(locateElement("Xpath", "//span[@class='countSortedByFilteredBy']")).replaceAll("\\D","");
		int recordsCount = Integer.parseInt(count);
		
		for(int i=1;i<=recordsCount;i++) {
			WebElement rowOppor = locateElement("Xpath", "(//span[@data-aura-class='uiOutputDate'])["+i+"]");
			executor.executeScript("arguments[0].scrollIntoView();", rowOppor);
			closeDate.add(getTextFromElement(rowOppor));
			if(i==recordsCount) {
				count = getTextFromElement(locateElement("Xpath", "//span[@class='countSortedByFilteredBy']")).replaceAll("\\D","");
				recordsCount = Integer.parseInt(count);
			}
		}
		System.out.println("Total records count = "+recordsCount);
		System.out.print(" ");
		System.out.println(closeDate);
		System.out.print("After");
		List<Date> closeDates = new ArrayList<Date>();
		SimpleDateFormat frmt = new SimpleDateFormat("MM/dd/yyyy");
		for (String str:closeDate) {
			try {
				Date dtt = frmt.parse(str);
				closeDates.add(dtt);
			} catch (ParseException e) {
				e.printStackTrace();
			}					
		}
		System.out.println(closeDates);
		System.out.print("Ascending order:- ");
		List<Date> ascUi = new ArrayList<Date>();
		List<Date> dscUi = new ArrayList<Date>();
		ascUi.addAll(closeDates);
		Collections.sort(ascUi);
		System.out.println(ascUi);
		System.out.print("Descending Order ");
		dscUi.addAll(closeDates);
		Collections.sort(dscUi,Collections.reverseOrder());
		System.out.println(dscUi);		
		
		executor.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[@title='Close Date']/parent::a")));
		clickElement(locateElement("Xpath","//span[@title='Close Date']/parent::a"));
		wait.until(ExpectedConditions.textToBePresentInElement(locateElement("Xpath","//span[@class='countSortedByFilteredBy']"),"Sorted by Close Date"));
		if(getTextFromElement(locateElement("Xpath","//span[@class='countSortedByFilteredBy']")).contains("Sorted by Close Date")) {
			System.out.println("Clicked on sorted1");
		}	
		try {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Close Date']/parent::a")));
		waitForElementToBeVisible(locateElement("Xpath", "//span[@title='Close Date']/parent::a"));
		}catch(StaleElementReferenceException e) {
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Close Date']/parent::a")));
			waitForElementToBeVisible(locateElement("Xpath", "//span[@title='Close Date']/parent::a"));
		}
		List<String> closeDateAfterSort = new ArrayList<String>(); 		
	
		count = getTextFromElement(locateElement("Xpath", "//span[@class='countSortedByFilteredBy']")).replaceAll("\\D","");
		recordsCount = Integer.parseInt(count);
		
		for(int i=1;i<=recordsCount;i++) {
			WebElement rowOppor = locateElement("Xpath", "(//span[@data-aura-class='uiOutputDate'])["+i+"]");
			executor.executeScript("arguments[0].scrollIntoView();", rowOppor);
			closeDateAfterSort.add(getTextFromElement(rowOppor));
			if(i==recordsCount) {
				count = getTextFromElement(locateElement("Xpath", "//span[@class='countSortedByFilteredBy']")).replaceAll("\\D","");
				recordsCount = Integer.parseInt(count);
			}
		}
		System.out.print("After Sorting");
		List<Date> closeDateAfterSortDesc = new ArrayList<Date>();
		for(String str:closeDateAfterSort) {
			try {
				Date dtt = frmt.parse(str);
				closeDateAfterSortDesc.add(dtt);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(closeDateAfterSortDesc);
		
		executor.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[@title='Close Date']/parent::a")));
		clickElement(locateElement("Xpath","//span[@title='Close Date']/parent::a"));
		clickElement(locateElement("Xpath","//div[text()='New']"));
		clickElement(locateElement("Xpath","//button[@title='Cancel']"));

		List<String> closeDateAfterSort2 = new ArrayList<String>(); 		
		count = getTextFromElement(locateElement("Xpath", "//span[@class='countSortedByFilteredBy']")).replaceAll("\\D","");
		recordsCount = Integer.parseInt(count);
		
		for(int i=1;i<=recordsCount;i++) {
			WebElement rowOppor = locateElement("Xpath", "(//span[@data-aura-class='uiOutputDate'])["+i+"]");
			executor.executeScript("arguments[0].scrollIntoView();", rowOppor);
			closeDateAfterSort2.add(getTextFromElement(rowOppor));
			if(i==recordsCount) {
				count = getTextFromElement(locateElement("Xpath", "//span[@class='countSortedByFilteredBy']")).replaceAll("\\D","");
				recordsCount = Integer.parseInt(count);
			}
		}
		System.out.print("After Sorting");
		List<Date> closeDateAfterSortAsc = new ArrayList<Date>();
		for(String str:closeDateAfterSort2) {
			try {
				Date dtt = frmt.parse(str);
				closeDateAfterSortAsc.add(dtt);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(closeDateAfterSortAsc);	
		
		if(ascUi.equals(closeDateAfterSortAsc)) {
			System.out.println("Sorted in Ascending order");
		}
		else if(dscUi.equals(closeDateAfterSortDesc)) {
			System.out.println("Sorted in Descending order");
		}else {
			System.out.println("Data could not be checked , Pls check for errors");
		}
		return this;
	}
	
}
