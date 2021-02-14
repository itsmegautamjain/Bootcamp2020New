package opportunityModule;

import java.time.Duration;
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
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteOpportunity {

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
		
		//6. Click on  the Dropdown icon and Select Delete
		try {
		driver.findElement(By.xpath("(//input[@type='checkbox']/following-sibling::span)[3]")).click();
		}catch(StaleElementReferenceException e){
			executor.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("(//input[@type='checkbox']/following-sibling::span)[3]")));
			driver.findElement(By.xpath("(//input[@type='checkbox']/following-sibling::span)[3]")).click();
		}
		driver.findElement(By.xpath("//a[contains(@class,'slds-button slds-button--icon-x-small')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Delete']"))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[text()='Delete Opportunity']"))));
		driver.findElement(By.xpath("//button[@title='Delete']")).click();
		
		//7. Verify Whether Opportunity is Deleted using Opportunity Name
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='No items to display.']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Opportunity-search-input']"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Opportunity-search-input']"))).sendKeys(opporName);
		driver.findElement(By.xpath("//input[@name='Opportunity-search-input']")).sendKeys(Keys.ENTER);
		if(driver.findElements(By.xpath("(//input[@type='checkbox']/following-sibling::span)[3]")).size()>0){
			System.out.println("Opportunity could not be deleted successfully, Please check");
		}
		else {
			System.out.println("Opportunity deleted successfully");
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
