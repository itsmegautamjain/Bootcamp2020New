package opportunityModule;

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

public class CreateOpportunityWithoutMandatoryFields {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
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
		
		//5. Click on the New button
		driver.findElement(By.xpath("//div[text()='New']")).click();
		
		//6. Choose close date as Tomorrow's Date
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='New Opportunity']")));
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
		
		//7. click Save and VerifyOppurtunity Name
		driver.findElement(By.xpath("//button[@title='Save']//span[1]")).click();
		
		//8. Verify the Alert message (Complete this field) displayed for Name and Stage
		Thread.sleep(2000);
		if (driver.findElement(By.xpath("//li[text()='These required fields must be completed: Opportunity Name, Stage']")).isDisplayed()) {
			System.out.println("Error Msg is displayed correctly");
		}
		else {
			System.out.println("Error Msg is not displayed correctly");
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@title='Cancel']")).click();
				
		//Logout
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@data-aura-class='uiTooltip'])[7]")));
		action.moveToElement(driver.findElement(By.xpath("(//div[@data-aura-class='uiTooltip'])[7]"))).click().build().perform();	
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Log Out']"))).click().build().perform();
		Thread.sleep(2000);
		
		//Closing drivers
		driver.close();
		driver.quit();
		
		
		

	}

}
