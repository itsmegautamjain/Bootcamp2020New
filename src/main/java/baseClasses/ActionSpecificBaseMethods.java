package baseClasses;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import design.ActionSpecificMethods;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionSpecificBaseMethods implements ActionSpecificMethods {
	
	public static WebDriver driver;
	public static ChromeOptions options;
	public static FirefoxOptions optionsFirefox;
//	public static EdgeOptions optionsEdge;
	public static WebDriverWait wait; 
	public static Actions action;
	public static JavascriptExecutor executor;
	
	
	public void startApp(String browser, String url) {
		// To launch any specified browser and open the given url
		try {
		if(browser.equalsIgnoreCase("Chrome")) {
			options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			}
			else if(browser.equalsIgnoreCase("Firefox")) {
				optionsFirefox = new FirefoxOptions();
				optionsFirefox.setProfile(new FirefoxProfile());
				optionsFirefox.addPreference("dom.webnotifications.enabled", false);
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver(optionsFirefox);
			}
			else if(browser.equalsIgnoreCase("Edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();			
			}
			
			driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
			action = new Actions(driver);
			wait = new WebDriverWait(driver,40);
			executor = (JavascriptExecutor)driver;
			
			driver.get(url);
			driver.manage().window().maximize();
		}catch(Exception e) {
			System.out.println("Browser could not be launched, Pls check for errors");
		}
	}
	
	public void logoutMyApp() {
		// This method will logout the application
		try {
			clickElement(locateElement("Xpath", "//ul[@class='slds-global-actions']/li[8]//button"));
			clickElement(locateElement("Xpath", "//a[text()='Log Out']"));		
		}finally{
			System.out.println("Verifying whether application is logged out");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='username']")));
		}		
  }
	
	public void closeBrowser() {
		// This method will close the opened browser
		driver.close();
		quitBrowser();
	}

	public void quitBrowser() {
		// This method will close all the browser sessions
		driver.quit();
	}	
	
	public WebElement locateElement(String locatorType, String locatorValue) {
		
		 WebElement element = null;
		
		//This method will locate the given element
		try {
		if(locatorType.equalsIgnoreCase("Xpath")) {
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
			element = driver.findElement(By.xpath(locatorValue));
		}else if(locatorType.equalsIgnoreCase("id")) {
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
			element = driver.findElement(By.xpath(locatorValue));
		}else if(locatorType.equalsIgnoreCase("name")) {
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
			element = driver.findElement(By.xpath(locatorValue));
		}else if(locatorType.equalsIgnoreCase("linkText")) {
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
			element =  driver.findElement(By.xpath(locatorValue));
		}else if(locatorType.equalsIgnoreCase("partialLinkText")) {
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
			element = driver.findElement(By.xpath(locatorValue));
		}else if(locatorType.equalsIgnoreCase("className")) {
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
			element = driver.findElement(By.xpath(locatorValue));
		}else if(locatorType.equalsIgnoreCase("cssSelector")) {
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
			element = driver.findElement(By.xpath(locatorValue));
		}else if(locatorType.equalsIgnoreCase("tagName")) {
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
			element = driver.findElement(By.xpath(locatorValue));
		}
		   }catch(Exception e) {
			   e.printStackTrace();
			System.out.println("Element could not be located, Pls check for errors");
		}
		return element;
	}	
	
	public List<WebElement> locateElements(String locatorType, String locatorValue) {
		
		 List<WebElement> elements = null;
//		 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		//This method will locate the given elements
		try {
		if(locatorType.equalsIgnoreCase("Xpath")) {
			elements = driver.findElements(By.xpath(locatorValue));
		}else if(locatorType.equalsIgnoreCase("id")) {
			elements = driver.findElements(By.xpath(locatorValue));
		}else if(locatorType.equalsIgnoreCase("name")) {
			elements = driver.findElements(By.xpath(locatorValue));
		}else if(locatorType.equalsIgnoreCase("linkText")) {
			elements =  driver.findElements(By.xpath(locatorValue));
		}else if(locatorType.equalsIgnoreCase("partialLinkText")) {
			elements = driver.findElements(By.xpath(locatorValue));
		}else if(locatorType.equalsIgnoreCase("className")) {
			elements = driver.findElements(By.xpath(locatorValue));
		}else if(locatorType.equalsIgnoreCase("cssSelector")) {
			elements = driver.findElements(By.xpath(locatorValue));
		}else if(locatorType.equalsIgnoreCase("tagName")) {
			elements = driver.findElements(By.xpath(locatorValue));
		}
		   }catch(Exception e) {
			   e.printStackTrace();
			System.out.println("Element could not be located, Pls check for errors");
		}
//		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		return elements;
	}
	

	public void clickElement(WebElement element) {
		// This method click the given webElement
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		}catch(NoSuchElementException e) {
			System.out.println("Element not clicked,Trying again using JS Executor");
			wait.until(ExpectedConditions.elementToBeClickable(element));
			executor.executeScript("arguments[0].click();",element);
		}catch(JavascriptException e) {
			System.out.println("Element not clicked,Trying again using JS executor");
			wait.until(ExpectedConditions.elementToBeClickable(element));
			executor.executeScript("arguments[0].click();",element);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Element "+element+" could not be clicked");
		}
	}
	
	
	public String getTextFromElement(WebElement element) {
		// This method will fetch the text from the given webElement		
		String text=null;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			text = element.getText();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Text could not be fetched from the given element, Pls check for errors");
		}
		return text;
	}
	
	public String getAttributeFromElement(WebElement element, String attribute) {
		// This method will fetch the Attribute Value from the given webElement		
		String text=null;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			text = element.getAttribute(attribute);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Text could not be fetched from the given element, Pls check for errors");
		}
		return text;
	}

	public void enterValues(WebElement element, String value) {
		//This method will enter the given values
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.clear();
			element.sendKeys(value);
		}catch(Exception e) {
			e.printStackTrace();
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.clear();
			element.sendKeys(value);
		}
	}
	
	public void enterKeys(WebElement element, Keys key) {
		//This method will enter the given Key values
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.sendKeys(key);
		}catch(Exception e) {
			e.printStackTrace();
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.sendKeys(key);
		}
	}
	
	public void enterKeys(WebElement element, Keys key1,String key2, Keys key3) {
		//This method will enter the given Key values
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.sendKeys(key1 + key2 + key3);
		}catch(Exception e) {
			e.printStackTrace();
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.sendKeys(key1 + key2 + key3);
		}
	}
	
	public void selectByValueFromDropdown(WebElement element, String value) {
		//This method will select given value from given dropdown
		try {
		Select select = new Select(element);
		select.selectByValue(value);
		}catch(Exception e) {
			e.printStackTrace();
			Select select = new Select(element);
			select.deselectByValue(value);
		}
	}
	
	public void waitForElementToBeClickable(WebElement element) {
		//This method will wait for the given element until it becomes clickable
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}catch(ElementClickInterceptedException e) {
			e.printStackTrace();
			System.out.println("Element is not clickable ,Pls check for the errors");
		}
	}
	
	public void waitForElementToBeVisible(WebElement element) {
		//This method will wait for the given element until it becomes visible
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Element not visible, Pls check for errors");
		}
	}
	
	public void waitForElementStaleness(WebElement element) {
		//This method will wait for the given element until it becomes visible
		try {
			wait.until(ExpectedConditions.stalenessOf(element));
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Staleness could not be verified for the given element, Pls check for errors");
		}
	}
		
	public void waitForTextInElement(WebElement element, String text) {
		//This method will wait for the presence of text in the given element 
		try {
			wait.until(ExpectedConditions.textToBePresentInElement(element, text));
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Element is not present with the given text, Pls check for errors");
		}
	}
	
	public boolean compareText(WebElement element, String text) {
		//This method will compare given text within the given element
		boolean flag = false;
		try {
			if(getTextFromElement(element).toString().equals(text)) {
				flag = true;
			}
			else {
				flag = false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Text could not be compared");
		}
		return flag;
	}
	
	public boolean verifyVisiblity(WebElement element) {
		//This method will verify the visibility of the element
		boolean flag = false;
		try {
			waitForElementToBeVisible(element);
			if(element.isDisplayed()) {
				flag = true;
			}
			else {
				flag = false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Visibility could not be verified");
		}
		return flag;
	}
}
