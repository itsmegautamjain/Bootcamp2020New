package orderModule;

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

public class CreateNewOrder {
	
	static int i=0;

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		
		//1. Login to https://login.salesforce.com
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("cypress@testleaf.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Bootcamp@123");
		driver.findElement(By.xpath("//input[@id='Login']")).click();

		//2. Click on the toggle menu button from the left corner		
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		
		//3. Click View All and click Service Console from App Launcher
		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
		driver.findElement(By.xpath("//p[text()='Service Console']")).click();
		
		//4. Click on the drop-down and select Orders
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@title='Show Navigation Menu']//lightning-primitive-icon")));
		driver.findElement(By.xpath("//button[@title='Show Navigation Menu']//lightning-primitive-icon")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@title='Orders'])[2]")));
		executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//a[@title='Orders'])[2]")));
				
		//5. Click on New
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='New']")));
		driver.findElement(By.xpath("//a[@title='New']")).click();
		
		//6. Select Account Name
		String accName = "Bootcamp";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='New Order']")));
		driver.findElement(By.xpath("//input[@title='Search Accounts']")).sendKeys(accName);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text()='\""+accName+"\" in Accounts']")))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Account Results']")));
		driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]/a")).click();
		
		//7. Select Contract Number
		String contractNumber = "00000123";
		driver.findElement(By.xpath("//input[@title='Search Contracts']")).sendKeys(contractNumber);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text()='\""+contractNumber+"\" in Contracts']")))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Contract Results']")));
		driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]/a")).click();
		
		//8. Select Status as activated(Selected as Draft as was not able to select Activated due to functionality limitation)
		driver.findElement(By.xpath("//a[text()='Draft']")).click();
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Draft']")));
		
		//9.Select Order Start Date as next month 10th date
		String dateTobeSelected = "02/10/2021";
		SimpleDateFormat frmt = new SimpleDateFormat("MM/DD/YYYY");
		Date dt = new Date();
		String todayDate = frmt.format(dt);
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
		
		//10. Click Save
		driver.findElement(By.xpath("//button[@title='Save']//span[1]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Order']")));
		if(driver.findElement(By.xpath("//div[text()='Order']")).isEnabled()) {
			System.out.println("Order Has been created successfully");
		}
		else {
			System.out.println("Order could not be created, Please check for errors");
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@title='Details'])[2]")));
				
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
