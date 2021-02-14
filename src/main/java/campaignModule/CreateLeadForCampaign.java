package campaignModule;

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

public class CreateLeadForCampaign {

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

		//6. Click Add Leads
		executor.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//a[@title='Add Leads']/div")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Add Leads']/div")));
		driver.findElement(By.xpath("//a[@title='Add Leads']/div")).click();
		
		//7. Click on New Lead
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Add Leads to Campaign']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='New Lead']")));
		driver.findElement(By.xpath("//span[@title='New Lead']")).click();
		
		//8. Pick Salutation as 'Mr.'
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='New Lead']")));
		driver.findElement(By.xpath("(//a[contains(@class,'select')])[1]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Mr.']"))).click();
		
		//9. Enter the first name as <your First Name>
		driver.findElement(By.xpath("(//span[text()='First Name']/parent::label)/following-sibling::input")).sendKeys("Gautam");
		
		//10. Enter the last name as <your last name>
		driver.findElement(By.xpath("(//span[text()='Last Name']/parent::label)/following-sibling::input")).sendKeys("Jain");
		
		//11. Enter company as 'TestLeaf'
		driver.findElement(By.xpath("(//span[text()='Company']/parent::label)/following-sibling::input")).sendKeys("Test Leaf");
		
		//12. Click Save
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		
		//13. Click Next
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Add Leads to Campaign']")));
		try {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']/parent::button"))).click();
		}catch(Exception e){
		 driver.findElement(By.xpath("//span[text()='Next']/parent::button")).click();	
		}
		
		//14. Click Submit on the Add to Campaign pop up
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Add to Campaign']")));
		try {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Submit']/parent::button"))).click();
		}catch(Exception e){
		 driver.findElement(By.xpath("//span[text()='Submit']/parent::button")).click();	
		}
		
		//15. verify the created Lead under Campaign
		executor.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("(//span[text()='View All'])[3]/parent::a")));
		if(driver.findElement(By.xpath("(//span[text()='View All'])[3]/parent::a")).isDisplayed()) {
			System.out.println("Lead is added successfully");
		}else {
			System.out.println("Lead could not be added, Please check for errors");
		}
		
		//16. Navigate to the Leads tab
		executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[@title='Leads']")));
		
		//17. Search for Lead with your Name
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Lead-search-input']"))).sendKeys("Gautam Jain");
		driver.findElement(By.xpath("//input[@name='Lead-search-input']")).sendKeys(Keys.ENTER);
		if(driver.findElements(By.xpath("(//table/tbody/tr)[1]/th")).size()>0) {
			System.out.println("Lead is available under Lead tab");
		}else {
			System.out.println("Lead is not available under Lead tab, Pls check for errors");
		}
		
		//Logout and close
		try {
			try {
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			if(driver.findElements(By.xpath("//button[@title='Close this window']/lightning-primitive-icon")).size()>0) {
				driver.findElement(By.xpath("//button[@title='Close this window']/lightning-primitive-icon")).click();
			}
			}catch(Exception e) {
//				e.printStackTrace();				
			}
			driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='slds-global-actions']/li[8]//button")));
		driver.findElement(By.xpath("//ul[@class='slds-global-actions']/li[8]//button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Log Out']")));
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Log Out']"))).click().build().perform();
		}catch(Exception e) {
			e.printStackTrace();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='slds-global-actions']/li[8]//button")));
			driver.findElement(By.xpath("//ul[@class='slds-global-actions']/li[8]//button")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Log Out']")));
			action.moveToElement(driver.findElement(By.xpath("//a[text()='Log Out']"))).click().build().perform();
		}
		driver.close();
		driver.quit();
	}
		
		
	}


