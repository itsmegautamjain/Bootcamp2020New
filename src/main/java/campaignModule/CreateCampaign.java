package campaignModule;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaign {

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
		
		//4. Click on the Campaigns tab
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//span[text()='Sales'])[3]"))));
		WebElement elm = driver.findElement(By.xpath("//a[@title='Campaigns']"));		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", elm);
		
		//5. Click on the New button
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='New']"))).click();
		
		//6. Enter Campaign name as 'Bootcamp', Get the text and Store it 
		String campaignName ="Bootcamp by Gautam Jain";
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[text()='New Campaign']"))));
		driver.findElement(By.xpath("//span[text()='Campaign Name']/parent::label/following-sibling::input")).sendKeys(campaignName);
		
		//7. Choose Start date as Tomorrow
		SimpleDateFormat frmt = new SimpleDateFormat("MM/DD/YYYY");
		Date dt = new Date();
		String todayDate = frmt.format(dt);
		Date dtTmrw = new Date(dt.getTime()+(1000*60*60*24));
		String tmrwDate = frmt.format(dtTmrw);
		String dateTobeSelected = tmrwDate;
		driver.findElement(By.xpath("//span[text()='Start Date']/parent::label/following-sibling::div//a")).click();
		driver.findElement(By.xpath("//button[text()='Today']")).click();
		driver.findElement(By.xpath("//span[text()='Start Date']/parent::label/following-sibling::div//a")).click();
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
		
		//8. End date as Tomorrow+1
		dateTobeSelected = frmt.format(dt.getTime()+2*(1000*60*60*24));
		driver.findElement(By.xpath("//span[text()='End Date']/parent::label/following-sibling::div//a")).click();
		driver.findElement(By.xpath("//button[text()='Today']")).click();
		driver.findElement(By.xpath("//span[text()='End Date']/parent::label/following-sibling::div//a")).click();
		WebElement yearSelectBoxEndDate = driver.findElement(By.xpath("//select[@class='slds-select picklist__label']"));
		Select yearSelectEndDate = new Select(yearSelectBoxEndDate);
		yearSelectEndDate.selectByValue(dateTobeSelected.substring(6,10));
		monthTobeSelected = Integer.parseInt(dateTobeSelected.substring(0,2)) - Integer.parseInt(todayDate.substring(0,2));
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
		dayToSelect = dateTobeSelected.substring(3,5);
		if(dayToSelect.substring(0,1).contains("0")) {
			dayToSelect = dayToSelect.substring(1,2);
		}
		driver.findElement(By.xpath("//span[text()='"+dayToSelect+"']")).click();
		
		//9. click Save and Verify the newly created Campaign
		driver.findElement(By.xpath("//button[@title='Save']//span[1]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='New Contact']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@title,'"+campaignName+"')]")));
		if(driver.findElement(By.xpath("//div[contains(@title,'"+campaignName+"')]")).isDisplayed()){
			System.out.println("Campaign has been created successfully");
		}
		else {
			System.out.println("Campaign could not be created, Please check for errors");
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
