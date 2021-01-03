package opportunityModule;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
		
		//5. Search the Opportunity 'Salesforce Automation by Your Name'
		String opporName = "Salesforce Automation by Gautam Jain";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Opportunity-search-input']"))).sendKeys(opporName);
		driver.findElement(By.xpath("//input[@name='Opportunity-search-input']")).sendKeys(Keys.ENTER);
		
		//6. Click on  the Dropdown icon and Select Delete
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='checkbox']/following-sibling::span)[3]"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(@class,'slds-button slds-button--icon-x-small')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Delete']"))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[text()='Delete Opportunity']"))));
		driver.findElement(By.xpath("//button[@title='Delete']")).click();
		
		//7. Verify Whether Opportunity is Deleted using Opportunity Name
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Opportunity-search-input']"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Opportunity-search-input']"))).sendKeys(opporName);
		driver.findElement(By.xpath("//input[@name='Opportunity-search-input']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		if(driver.findElements(By.xpath("(//input[@type='checkbox']/following-sibling::span)[3]")).size()>0){
			System.out.println("Opportunity could not be deleted successfully, Please check");
		}
		else {
			System.out.println("Opportunity deleted successfully");
		}
		
		//Log-Out
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@data-aura-class='uiTooltip'])[7]")));
		action.moveToElement(driver.findElement(By.xpath("(//div[@data-aura-class='uiTooltip'])[7]"))).click().build().perform();	
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Log Out']"))).click().build().perform();
		Thread.sleep(2000);
		
		//Closing drivers
		driver.close();
		driver.quit();	
		
	}

}
