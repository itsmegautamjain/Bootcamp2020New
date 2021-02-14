package assesment;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assesment01 {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	ChromeOptions options;
	JavascriptExecutor executor;
	int i; 
	
	@BeforeMethod
	public void loginToApp() throws InterruptedException {
		options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);

		action = new Actions(driver);
		wait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		//1. Login to https://login.salesforce.com
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("cypress@testleaf.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Bootcamp@123");
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		Thread.sleep(5000);
		
		//2. Click on the toggle menu button from the left corner	
		try {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='slds-icon-waffle']")));
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		}catch(Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='slds-icon-waffle']")));
			driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		}
		
		//3. Click View All
		//4. Click Service Console from App Launcher
		try {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='slds-button']")));
		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
		driver.findElement(By.xpath("//p[text()='Service Console']")).click();
		}catch(Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='slds-button']")));
			driver.findElement(By.xpath("//button[@class='slds-button']")).click();
			driver.findElement(By.xpath("//p[text()='Service Console']")).click();
		}
  }
	
	@Test
	public void test() throws InterruptedException {
		String goalVal;
		
		//5. Select Home from the DropDown
		Thread.sleep(15000);
		driver.findElement(By.xpath("//button[@title='Show Navigation Menu']/lightning-primitive-icon")).click();
		Thread.sleep(8000);
		driver.findElement(By.xpath("//a[@title='Home']")).click();
		Thread.sleep(5000);
		
		//6. Add CLOSED + OPEN values and result should set as the GOAL (If the result is less than 10000 then set the goal as 10000)
		String closedValue = driver.findElement(By.xpath("(//li[@class='metric'])[1]/span[2]")).getText();
		String openValue = driver.findElement(By.xpath("(//li[@class='metric'])[2]/span[2]")).getText();
		System.out.println(closedValue);
		System.out.println(openValue);
		closedValue = closedValue.replaceAll("[^a-zA-Z0-9]","");
		openValue = openValue.replaceAll("[^a-zA-Z0-9]","");
		int cVal = Integer.parseInt(closedValue);
		int opVal = Integer.parseInt(openValue);
		if((cVal+opVal)<10000) {
			goalVal = "10000";
		}
		else {
			goalVal= Integer.toString(cVal+opVal);
		}
		driver.findElement(By.xpath("//button[@title='Edit Goal']/lightning-primitive-icon")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-describedby='currencyCode']"))).clear();
		driver.findElement(By.xpath("//input[@aria-describedby='currencyCode']")).sendKeys(goalVal);
		driver.findElement(By.xpath("//span[text()='Save']/parent::button")).click();
		Thread.sleep(2000);
		
		//7. Select Dashboards from DropDown
		driver.findElement(By.xpath("//button[@title='Show Navigation Menu']/lightning-primitive-icon")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='Dashboards']")).click();
		Thread.sleep(5000);
		
		if(driver.findElements(By.xpath("//button[contains(@title,'Close')]")).size()>0) {
			for(int j=1;j<=(driver.findElements(By.xpath("//button[contains(@title,'Close')]")).size())+1;j++) {
				driver.findElement(By.xpath("(//button[contains(@title,'Close')])["+j+"]")).click();
			}
		}	
					
		//8. Click on New Dashboard
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='New Dashboard']"))).click();
		
		//9. Enter the Dashboard name as "YourName_Workout"
		Thread.sleep(5000);
		WebElement iframe1 = driver.findElement(By.xpath("(//iframe[@title='dashboard'])"));
		driver.switchTo().frame(iframe1);
		Thread.sleep(5000);		

		//10. Enter Description as Testing and Click on Create
		driver.findElement(By.xpath("//input[@id='dashboardNameInput']")).sendKeys("Gautam_Workout");
		driver.findElement(By.xpath("//input[@id='dashboardDescriptionInput']")).sendKeys("Testing");
		driver.findElement(By.xpath("//button[@id='submitBtn']")).click();
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		//11. Click on Done
		WebElement iframe2 = driver.findElement(By.xpath("(//iframe[@title='dashboard'])[1]"));
		driver.switchTo().frame(iframe2);
		Thread.sleep(3000);
				
		driver.findElement(By.xpath("//button[text()='Done']")).click();
				
		//12. Verify the Dashboard is Created
		if(driver.findElement(By.xpath("//span[contains(@title,'Gautam_Workout')]")).isDisplayed()) {
			System.out.println("Dashboard is created successfully");
		}
		else {
			System.out.println("Dashboard could not be created");
		}

		//13. Click on Subscribe
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Subscribe']")).click();
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		
		//14. Select Frequency as "Daily"
		driver.findElement(By.xpath("//span[text()='Daily']")).click();
		
		//15. Time as 10:00 AM
		WebElement time = driver.findElement(By.xpath("//select[@id='time']"));
		Select timeSelect = new Select(time);
		timeSelect.selectByValue("10");
		
		//16. Click on Save
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		
		//17. Verify "You started Dashboard Subscription" message displayed or not
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='You started a dashboard subscription.']")));
		String msg = driver.findElement(By.xpath("//span[text()='You started a dashboard subscription.']")).getText();
		System.out.println(msg);
		if(msg.contains("You started a dashboard subscription")) {
			System.out.println("Message is displayed correctly, Subscription started");
		}else {
			System.out.println("Message not displayed correctly, Pls check for errors, subscription could not be started");
		}
		
		//18. Close the "YourName_Workout" tab
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[contains(@title,'Close')]")).click();
		Thread.sleep(3000);
		
		//19. Click on Private Dashboards
		driver.findElement(By.xpath("(//a[@title='Private Dashboards'])[1]")).click();
		Thread.sleep(5000);
		
		//20. Verify the newly created Dashboard available
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@placeholder,'Search private dashboards')]"))).sendKeys("Gautam_Workout");
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search private dashboards')]")).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table/tbody/tr[1]/th")));
		String myDashboard = driver.findElement(By.xpath("//table/tbody/tr[1]/th")).getText();
		if(myDashboard.contains("Gautam_Workout")) {
			System.out.println("My workout dashboard is avaiable");
		}else {
			System.out.println("My workout dashboard is not displayed, Pls check for errors");
		}
		
		//21. Click on dropdown for the item
		action.moveToElement(driver.findElement(By.xpath("((//table/tbody/tr[1]/td[6])//span/div)//lightning-button-menu/button"))).click().build().perform();
		Thread.sleep(2000);
		
		//22. Select Delete
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Delete']/parent::a"))).click().build().perform();
		
		//23. Confirm the Delete
		driver.findElement(By.xpath("//button[@title='Delete']")).click();
		
		//24. Verify the item is not available under Private Dashboard folder
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@placeholder,'Search private dashboards')]"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@placeholder,'Search private dashboards')]"))).sendKeys("Gautam_Workout");
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search private dashboards')]")).sendKeys(Keys.ENTER);
		if(driver.findElements(By.xpath("//table/tbody/tr[1]/th")).size()>0) {
			System.out.println("Dashboard could not be deleted, Please check for errors ");
		}else {
			System.out.println("Dashboard deleted successfully");
		}
		
		//Logout
		try {
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
