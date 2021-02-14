package opportunityModule;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditOpportunity {

	public static void main(String[] args) throws InterruptedException {
		
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
		
		//4. Click on the Opportunity tab
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//span[text()='Sales'])[3]"))));
		WebElement elm =	driver.findElement(By.xpath("//a[@href='/lightning/o/Opportunity/home']//span[1]"));		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", elm);
		
		//5. Search the Opportunity 'Salesforce Automation by Your Name'
		String opporName = "Salesforce Automation by Gautam Jain";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Opportunity-search-input']"))).sendKeys(opporName);
		driver.findElement(By.xpath("//input[@name='Opportunity-search-input']")).sendKeys(Keys.ENTER);
		
		//6. Click on the Dropdown icon and Select Edit
		try {
		driver.findElement(By.xpath("(//input[@type='checkbox']/following-sibling::span)[3]")).click();
		}catch(StaleElementReferenceException e){
			executor.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("(//input[@type='checkbox']/following-sibling::span)[3]")));
			driver.findElement(By.xpath("(//input[@type='checkbox']/following-sibling::span)[3]")).click();
		}
		driver.findElement(By.xpath("//a[contains(@class,'slds-button slds-button--icon-x-small')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Edit']"))).click();
		
		//7. Choose close date as Tomorrow date
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Edit')]")));
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
		
		//8. Select 'Stage' as Perception Analysis
		String stageVal = "Perception Analysis";
		driver.findElement(By.xpath("//span[text()='Stage'][1]/parent::span/following-sibling::div")).click();		
		driver.findElement(By.xpath("//a[text()='"+stageVal+"']")).click();		
		
		//9. Select Deliver Status as In Progress
		String deliveryVal = "In progress";
		driver.findElement(By.xpath("//span[text()='Delivery/Installation Status'][1]/parent::span/following-sibling::div")).click();
		action.moveToElement(driver.findElement(By.xpath("//a[@title='"+deliveryVal+"']"))).click().build().perform();
		
		//10. Enter Description as SalesForce
		driver.findElement(By.xpath("//textarea[@class=' textarea']")).clear();
		driver.findElement(By.xpath("//textarea[@class=' textarea']")).sendKeys("Salesforce");
		
		//11. Click on Save and Verify Stage as Perception Analysis
		driver.findElement(By.xpath("//button[@title='Save']//span[1]")).click();
		String stage = driver.findElement(By.xpath("//table/tbody/tr/td[5]")).getText();
		if(stage.contains("Perception Analysis")) {
			System.out.println("Stage is displayed correctly and displayed as:- "+stage);
		}
		else {
			System.out.println("Stage is not displayed correctly");
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
