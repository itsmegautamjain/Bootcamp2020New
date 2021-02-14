package campaignModule;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewOpportunityForCampaign {
	
	static String opporText;

	public static void main(String[] args) {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		//1. Login to https://login.salesforce.com
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("cypress@testleaf.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Bootcamp@123");
		driver.findElement(By.xpath("//input[@id='Login']")).click();

		//2. Click on the toggle menu button from the left corner		
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		
		//3. Click View All and click Sales from App Launcher
		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		
		//4. Click on the Campaigns tab
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//span[text()='Sales'])[3]"))));
		WebElement elm = driver.findElement(By.xpath("//a[@title='Campaigns']"));		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", elm);
		
		//5. Click the Bootcamp by your name link
		String campaignName ="Bootcamp by Gautam Jain";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(@title,'"+campaignName+"')])[1]"))).click();
		
		//Click on the New button
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@title='New'])[2]")));
		driver.findElement(By.xpath("(//a[@title='New'])[2]")).click();
		
		//6. Enter Opportunity name as 'Salesforce Automation by Your Name, Get the text and Store it 
		String opporName = "Salesforce Automation by Gautam Jain";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='New Opportunity']")));		
		driver.findElement(By.xpath("//span[text()='Opportunity Name']/parent::label/following-sibling::input")).sendKeys(opporName);		
		
		//8. Select 'Stage' as Need Analysis
		String stageVal = "Needs Analysis";
		driver.findElement(By.xpath("//span[text()='Stage'][1]/parent::span/following-sibling::div")).click();		
		driver.findElement(By.xpath("//a[text()='"+stageVal+"']")).click();
		
		//7. Choose close date as Tomorrow
		SimpleDateFormat frmt = new SimpleDateFormat("MM/DD/YYYY");
		Date dt = new Date();
		String todayDate = frmt.format(dt);
		Date dtTmrw = new Date(dt.getTime()+(1000*60*60*24));
		String tmrwDate = frmt.format(dtTmrw);
		String dateTobeSelected = tmrwDate;
		driver.findElement(By.xpath("//a[@class='datePicker-openIcon display']")).click();
		driver.findElement(By.xpath("//button[text()='Today']")).click();
		driver.findElement(By.xpath("//a[@class='datePicker-openIcon display']")).click();
		WebElement yearSelectBox = driver.findElement(By.xpath("//select[@class='slds-select picklist__label']"));
		Select yearSelect = new Select(yearSelectBox);
		yearSelect.selectByValue(dateTobeSelected.substring(6,10));
		int monthTobeSelected = Integer.parseInt(dateTobeSelected.substring(0,2)) - Integer.parseInt(todayDate.substring(0,2));
		if(monthTobeSelected>0) {
		for(int i=1;i<=monthTobeSelected;i++) {
			driver.findElement(By.xpath("//a[@title='Go to next month']")).click();
		}
		}
		else {
			for(int i=1;i<=0-monthTobeSelected;i++) {
				driver.findElement(By.xpath("//a[@title='Go to previous month']")).click();
			}			
		}		
		String dayToSelect = dateTobeSelected.substring(3,5);
		if(dayToSelect.substring(0,1).contains("0")) {
			dayToSelect = dayToSelect.substring(1,2);
		}
		driver.findElement(By.xpath("//span[text()='"+dayToSelect+"']")).click();
		
		//9. click Save and VerifyOppurtunity Name
		driver.findElement(By.xpath("//button[@title='Save']//span[1]")).click();		
				 
		//10. Verify new Opportunity in Campaign
		executor.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("(//a[contains(text(),'"+opporName+"')])[1]")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[contains(text(),'"+opporName+"')])[1]")));
		if(driver.findElement(By.xpath("//a[contains(text(),'"+opporName+"')]")).isEnabled()) {
		opporText = driver.findElement(By.xpath("//a[contains(text(),'"+opporName+"')]")).getAttribute("text");
		}
		System.out.println(opporText);
		if(opporText.equals(opporName)){
			System.out.println("Opportunity has been created successfully in campaign tab");
		}
		else { 
			System.out.println("Opportunity has not been created successfully in campaign tab");
		}
		
		//11. Click on Opportunities Tab
		WebElement elmOppor = driver.findElement(By.xpath("//a[@href='/lightning/o/Opportunity/home']//span[1]"));		
		executor.executeScript("arguments[0].click();", elmOppor);
		
		//12. Verify the newly created Opportunity (Opportunity from your name)
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Opportunity-search-input']"))).sendKeys(opporName);
		driver.findElement(By.xpath("//input[@name='Opportunity-search-input']")).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox']/following-sibling::span)[3]")));
		if(driver.findElement(By.xpath("(//input[@type='checkbox']/following-sibling::span)[3]")).isDisplayed()) {
			System.out.println("Opportunity is available in Opportunity tab");
		}else {
			System.out.println("Opportunity is not available in Opportunity tab, Please check for errors");
		}
				
		//Log-Out
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='slds-global-actions']/li[8]//button")));
		driver.findElement(By.xpath("//ul[@class='slds-global-actions']/li[8]//button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Log Out']")));
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Log Out']"))).click().build().perform();
		
		//Closing drivers
		driver.close();
		driver.quit();

	}

}
