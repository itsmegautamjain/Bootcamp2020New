package campaignModule;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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

public class AddAttachmentToCampaign {

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
		
		//6. Click on the Upload button 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Upload Files']"))).click();
		Thread.sleep(2000);
		
		//7. Select a file from local and upload  a pdf file
		String path = "E:\\Test Leaf\\WorkSpace\\BootCamp\\Selenium bootcamp from TestLeaf.pdf";
		StringSelection filePath = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
		Thread.sleep(5000);
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		}catch(AWTException e){
			e.printStackTrace();
		}	
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Upload Files']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Done']/parent::button"))).click();
		
		//8. Verify the file name displayed as a link
		if(driver.findElement(By.xpath("(//a[@title='Selenium bootcamp from TestLeaf'])[1]")).isDisplayed()){
			System.out.println("Upload is successful");
		}
		else {
			System.out.println("Upload is unsuccessful");
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
