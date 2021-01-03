package opportunityModule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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

public class SortOpportunity {

	public static void main(String[] args) throws InterruptedException, ParseException {

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
		
		//5. Select the Table view
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@title,'Display as')]"))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li[@title='Table']/a"))));
		action.moveToElement(driver.findElement(By.xpath("//li[@title='Table']/a"))).click().build().perform();
		
		//6. Sort the Opportunities by Close Date in ascending order
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Sort']/parent::a)[5]"))).click();
		List<String> closeDate = new ArrayList<String>(); 
//		closeDate = 
//				List<WebElement> closeDate = driver.findElements(By.xpath("//table/tbody/tr/td[6]/span/span[1]/span[1]"));
//				Thread.sleep(5000);
//				List<WebElement> closeDate = driver.findElements(By.xpath("//span[@data-aura-class='uiOutputDate']"));
		
		
		Thread.sleep(2000);
//		System.out.println(driver.findElements(By.xpath("//table/tbody/tr/td[6]/span/span[1]/span[1]")).get(0).getText());
//		Thread.sleep(2000);
//		for(WebElement el:closeDate) {
//			System.out.println(el.getText());
//		}
//		Iterator<WebElement> itr = closeDate.iterator();
//		while(itr.hasNext()) {
//		    System.out.println(itr.next());
//		}
		
//		for(int i=0;i<closeDate.size();i++) {
//			String temp = driver.findElements(By.xpath("//table/tbody/tr/td[6]/span/span[1]/span[1]")).get(i).getText();
//			System.out.println(temp);
//		}
		String count = driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']")).getText().replaceAll("\\D","");
//		System.out.println(count);
		int recordsCount = Integer.parseInt(count);
//		System.out.println(recordsCount);
		
		for(int i=1;i<=recordsCount;i++) {
			WebElement rowOppor = driver.findElement(By.xpath("(//span[@data-aura-class='uiOutputDate'])["+i+"]"));
			executor.executeScript("arguments[0].scrollIntoView();", rowOppor);
			closeDate.add(rowOppor.getText());
			if(i==recordsCount) {
				count = driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']")).getText().replaceAll("\\D","");
				recordsCount = Integer.parseInt(count);
			}
		}
		System.out.println(closeDate);
		System.out.print("After");
		List<String> closeDates = new ArrayList<String>();
		SimpleDateFormat frmt = new SimpleDateFormat("MM/DD/YYYY");
		SimpleDateFormat frmtNew = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
		for (String str:closeDate) {
			Date dt = frmt.parse(str);
			String dtt = frmtNew.format(dt);
			closeDates.add(dtt);
		}
		System.out.println(closeDates);

		
	}

}
