package campaignModule;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteAttachmentFromCampaign {

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
		
		//5. Click the Bootcamp by your name link
		String campaignName ="Bootcamp by Gautam Jain";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(@title,'"+campaignName+"')])[1]"))).click();
		
		//6. Click on View All link in the Attachments section
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Attachments']/parent::span")).click();
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='View All']"))).click();
		
		//7. Click the dropdown icon for the recently attached document
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@title='Attachments']")));
		Thread.sleep(2000);
		action.moveToElement(driver.findElement(By.xpath("(//span[text()='Show Actions']/preceding-sibling::lightning-primitive-icon)[15]"))).click().build().perform();
		
		//8. Select Delete and Confirm the delete
		action.moveToElement(driver.findElement(By.xpath("//a[@title='Delete']"))).click().build().perform();		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Delete']"))).click();
		
		//9. Verify the file deleted from the Attachments
		Thread.sleep(2000);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(@title,'"+campaignName+"')])[1]"))).click();
		driver.findElement(By.xpath("(//a[contains(@title,'"+campaignName+"')])[1]"));
		Thread.sleep(3000);
		if(driver.findElement(By.xpath("(//a[@title='Selenium bootcamp from TestLeaf'])[1]")).isDisplayed()){
			System.out.println("File could not be deleted");
		}
		else {
			System.out.println("File deleted successfully");
		}
		
		//Log-Out
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
