package orderModule;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditOrder {
	
	static int i=0;

	public static void main(String[] args) throws InterruptedException {
			
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		SoftAssert as = new SoftAssert();
				
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
		Thread.sleep(3000);
		executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//span[text()='Orders']")));
		Thread.sleep(5000);
		
		//5.Click drop down near Recently Viewed and Select All Orders
		while(i==0) {
			
		if(driver.findElements(By.xpath("//span[text()='Recently Viewed']")).size()>0){
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Select List View']")));
			driver.findElement(By.xpath("//a[@title='Select List View']")).click();
			driver.findElement(By.xpath("//span[text()='All Orders']")).click();
			i=1;
		}
		else {
			driver.findElement(By.xpath("//button[@title='Show Navigation Menu']//lightning-primitive-icon")).click();
			Thread.sleep(1000);
//			executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//span[text()='Orders']")));
//			Thread.sleep(5000);
			action.moveToElement(driver.findElement(By.xpath("(//ul[@aria-label='Navigation Menu']/li[8]/div/a/span[2])//span"))).click().build().perform();
			}
		}
		i=0;

		//6. Select the first result, click the dropdown of the result and click on Edit
		Thread.sleep(3000);
		action.moveToElement(driver.findElement(By.xpath("(//input[@type='checkbox']/following-sibling::span)[3]"))).click().build().perform();
		driver.findElement(By.xpath("//div[@id='split-left']/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[8]/span[1]/div[1]/a[1]/lightning-icon[1]/lightning-primitive-icon[1]")).click();
		driver.findElement(By.xpath("//a[@title='Edit']")).click();
		
		//7. Click the close button of the Account Name and Select Another Name
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Edit')]")));
		driver.findElement(By.xpath("(//span[contains(@class,'deleteIcon')])[2]")).click();
		String accName = "Bootcamp";
		driver.findElement(By.xpath("//input[@title='Search Accounts']")).sendKeys(accName);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text()='\""+accName+"\" in Accounts']")))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Account Results']")));
		driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]/a")).click();
		
		//8. Click the close button of the Contract Number and select the same Account Name's contract number
		driver.findElement(By.xpath("(//span[contains(@class,'deleteIcon')])[1]")).click();
		String contractNumber = "00000123";
		driver.findElement(By.xpath("//input[@title='Search Contracts']")).sendKeys(contractNumber);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text()='\""+contractNumber+"\" in Contracts']")))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Contract Results']")));
		driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]/a")).click();
		
		//Select Date
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
		
		//9. Click Save
		driver.findElement(By.xpath("//button[@title='Save']//span[1]")).click();
		
		//Log-Out
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@data-aura-class='uiTooltip'])[7]")));
		action.moveToElement(driver.findElement(By.xpath("(//div[@data-aura-class='uiTooltip'])[7]"))).click().build().perform();	
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Log Out']"))).click().build().perform();
		Thread.sleep(2000);		
		
		//Closing drivers
		driver.close();
		driver.quit();
		
	}

}
