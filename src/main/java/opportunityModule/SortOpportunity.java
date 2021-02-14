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
		
		//5. Select the Table view
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@title,'Display as')]"))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li[@title='Table']/a"))));
		action.moveToElement(driver.findElement(By.xpath("//li[@title='Table']/a"))).click().build().perform();
		
		//6. Sort the Opportunities by Close Date in ascending order
		List<String> closeDate = new ArrayList<String>(); 		
		Thread.sleep(2000);
		String count = driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']")).getText().replaceAll("\\D","");
		int recordsCount = Integer.parseInt(count);
		
		for(int i=1;i<=recordsCount;i++) {
			WebElement rowOppor = driver.findElement(By.xpath("(//span[@data-aura-class='uiOutputDate'])["+i+"]"));
			executor.executeScript("arguments[0].scrollIntoView();", rowOppor);
			closeDate.add(rowOppor.getText());
			if(i==recordsCount) {
				count = driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']")).getText().replaceAll("\\D","");
				recordsCount = Integer.parseInt(count);
			}
		}
		System.out.println("Total records count = "+recordsCount);
		System.out.print(" ");
		System.out.println(closeDate);
		System.out.print("After");
		List<Date> closeDates = new ArrayList<Date>();
		SimpleDateFormat frmt = new SimpleDateFormat("MM/dd/yyyy");
		for (String str:closeDate) {
			Date dtt = frmt.parse(str);
			closeDates.add(dtt);
		}
		System.out.println(closeDates);
		System.out.print("Ascending order:- ");
		List<Date> ascUi = new ArrayList<Date>();
		List<Date> dscUi = new ArrayList<Date>();
		ascUi.addAll(closeDates);
		Collections.sort(ascUi);
		System.out.println(ascUi);
		System.out.print("Descending Order ");
		dscUi.addAll(closeDates);
		Collections.sort(dscUi,Collections.reverseOrder());
		System.out.println(dscUi);
		
		
		executor.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[@title='Close Date']/parent::a")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Close Date']/parent::a"))).click();
		
		List<String> closeDateAfterSort = new ArrayList<String>(); 		
		Thread.sleep(2000);
		count = driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']")).getText().replaceAll("\\D","");
		recordsCount = Integer.parseInt(count);
		
		for(int i=1;i<=recordsCount;i++) {
			WebElement rowOppor = driver.findElement(By.xpath("(//span[@data-aura-class='uiOutputDate'])["+i+"]"));
			executor.executeScript("arguments[0].scrollIntoView();", rowOppor);
			closeDateAfterSort.add(rowOppor.getText());
			if(i==recordsCount) {
				count = driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']")).getText().replaceAll("\\D","");
				recordsCount = Integer.parseInt(count);
			}
		}
		System.out.print("After Sorting");
		List<Date> closeDateAfterSortDesc = new ArrayList<Date>();
		for(String str:closeDateAfterSort) {
			Date dtt = frmt.parse(str);
			closeDateAfterSortDesc.add(dtt);
		}
		
		System.out.println(closeDateAfterSortDesc);
		
		executor.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[@title='Close Date']/parent::a")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Close Date']/parent::a"))).click();
		
		List<String> closeDateAfterSort2 = new ArrayList<String>(); 		
		Thread.sleep(2000);
		count = driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']")).getText().replaceAll("\\D","");
		recordsCount = Integer.parseInt(count);
		
		for(int i=1;i<=recordsCount;i++) {
			WebElement rowOppor = driver.findElement(By.xpath("(//span[@data-aura-class='uiOutputDate'])["+i+"]"));
			executor.executeScript("arguments[0].scrollIntoView();", rowOppor);
			closeDateAfterSort2.add(rowOppor.getText());
			if(i==recordsCount) {
				count = driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']")).getText().replaceAll("\\D","");
				recordsCount = Integer.parseInt(count);
			}
		}
		System.out.print("After Sorting");
		List<Date> closeDateAfterSortAsc = new ArrayList<Date>();
		for(String str:closeDateAfterSort2) {
			Date dtt = frmt.parse(str);
			closeDateAfterSortAsc.add(dtt);
		}
		
		System.out.println(closeDateAfterSortAsc);
		
		
		if(ascUi.equals(closeDateAfterSortAsc)) {
			System.out.println("Sorted in Ascending order");
		}
		else {
			System.out.println("Sorted in Descending order");
		}
	}

}
