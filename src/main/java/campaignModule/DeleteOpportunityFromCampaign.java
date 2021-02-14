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

public class DeleteOpportunityFromCampaign {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
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
		
		//6. Click on the dropdown icon in the Opportunities
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Campaign Members']/parent::a")));
		executor.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//span[@title='Campaign Members']/parent::a")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Campaign Members']/parent::a")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[1]/div/a")));
		action.moveToElement(driver.findElement(By.xpath("//ul/li[1]/div/a"))).click().build().perform();
		
		//7. Select Delete and Confirm the delete
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Delete']")));
		action.moveToElement(driver.findElement(By.xpath("//a[@title='Delete']"))).click().build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Delete']"))).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//ul/li[1]/div/a")));
		
		//8. Verify the Deleted Opportunity under the selected campaign
		if(driver.findElements(By.xpath("//ul/li[1]/div/a")).size()>0) {
			System.out.println("Opportunity could not be deleted successfully");
		}else {
			System.out.println("Opportunity is deleted successfully");
		}
		
		//9. Click on Opportunities Tab
		WebElement elmOppor =	driver.findElement(By.xpath("//a[@href='/lightning/o/Opportunity/home']//span[1]"));		
		executor.executeScript("arguments[0].click();", elmOppor);		
		
		//10. Verify the deleted Opportunity
		String opporName = "Salesforce Automation by Gautam Jain";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Opportunity-search-input']"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Opportunity-search-input']"))).sendKeys(opporName);
		driver.findElement(By.xpath("//input[@name='Opportunity-search-input']")).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='No items to display.']")));
		if(driver.findElements(By.xpath("(//input[@type='checkbox']/following-sibling::span)[3]")).size()>0){
			System.out.println("Opportunity could not be deleted successfully from Opportunity tab, Please check");
		}
		else {
			System.out.println("Opportunity deleted successfully from Opportunity tab");
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
