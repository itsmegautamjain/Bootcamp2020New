package campaignModule;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditCampaign {

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
		
		//6. Click on the Details tab
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Details']"))).click();
		
		//7. Change the End Date as Today+4
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Edit End Date']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Save']//span[1]")));
		SimpleDateFormat frmt = new SimpleDateFormat("MM/DD/YYYY");
		Date dt = new Date();
		String todayDate = frmt.format(dt);
		Date dt4 = new Date(dt.getTime()+4*(1000*60*60*24));
		String tmrw4Date = frmt.format(dt4);
		String dateTobeSelected = tmrw4Date;
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='End Date']/parent::label/following-sibling::div//a")));
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='End Date']/parent::label/following-sibling::div//a")));
		executor.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//button[text()='Today']")));
		driver.findElement(By.xpath("//button[text()='Today']")).click();
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='End Date']/parent::label/following-sibling::div//a")));
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
		SimpleDateFormat frmtNew = new SimpleDateFormat("M/D/YYYY");
		String dateToCompare = frmtNew.format(new Date(dt.getTime()+4*(1000*60*60*24)));
		dateTobeSelected = dateToCompare;
		
		//8. Update the Expected Revenue as 1000000
		String expectedRevenueGiven = "1000000";
		driver.findElement(By.xpath("//span[text()='Expected Revenue in Campaign']/parent::label/following-sibling::input")).sendKeys(Keys.CONTROL,"a",Keys.DELETE);		
		driver.findElement(By.xpath("//span[text()='Expected Revenue in Campaign']/parent::label/following-sibling::input")).sendKeys("1000000");
		
		//9. Update the Budget Cost in Campaign as 100000
		String budgetedCostGiven = "100000";
		driver.findElement(By.xpath("//span[text()='Budgeted Cost in Campaign']/parent::label/following-sibling::input")).sendKeys(Keys.CONTROL,"a",Keys.DELETE);
		driver.findElement(By.xpath("//span[text()='Budgeted Cost in Campaign']/parent::label/following-sibling::input")).sendKeys("100000");
		
		//10. Click on Save and verify the updated values
		driver.findElement(By.xpath("//button[@title='Save']//span[1]")).click();
		executor.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//a[@title='Details']")));
		try {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Edit End Date']"))).click();
		}catch(StaleElementReferenceException e) {
			driver.findElement(By.xpath("//button[@title='Edit End Date']")).click();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[text()='End Date']/parent::label)/following-sibling::div/input")));
		String endDateText = driver.findElement(By.xpath("(//span[text()='End Date']/parent::label)/following-sibling::div/input")).getAttribute("value");
		String expectedRevenue = driver.findElement(By.xpath("//span[text()='Expected Revenue in Campaign']/parent::label/following-sibling::input")).getAttribute("value");
		String budgetedCost = driver.findElement(By.xpath("//span[text()='Budgeted Cost in Campaign']/parent::label/following-sibling::input")).getAttribute("value");
		System.out.println(endDateText);
		System.out.println(expectedRevenue);
		System.out.println(budgetedCost);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Cancel']")));
		driver.findElement(By.xpath("//button[@title='Cancel']")).click();
		
		if(expectedRevenue.replaceAll("[^0-9a-zA-Z]","").equals(expectedRevenueGiven) & budgetedCost.replaceAll("[^0-9a-zA-Z]","").equals(budgetedCostGiven) & endDateText.equals(dateTobeSelected)) {
			System.out.println("Values are updated as expected");
		}
		else {
			System.out.println("Values could not be updated, Please check for errors");
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
