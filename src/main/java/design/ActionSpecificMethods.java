package design;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public interface ActionSpecificMethods {

	/**
	 * This method will launch the specifed browser and
	 * maximise the browser and set the wait for 40 seconds
	 * and load the url 
	 * @param browser - This browser will be launched
	 * @param url - This url will be loaded into the opened browser 
	 * @author - Gautam Jain
	 * @throws - MalformedURLException
	 */
	public void startApp(String browser, String url);
		
	/**
	 * This method will close the opened browser 
	 * @param - No parameters required
	 * @author - Gautam Jain
	 */
	public void closeBrowser();
	
	
	/**
	 * This method will close all the browser sessions 
	 * @param - No parameters required
	 * @author - Gautam Jain
	 */
	public void quitBrowser();
		
	/**
	 * This method will locate the given webElement
	 * @param locatorType - It can be Xpath, Id,name, etc..
	 * @param locatorValue - Value of the locator
	 * @author - Gautam Jain
	 * @throws - NoSuchElementException
	 */
	public WebElement locateElement(String locatorType, String locatorValue);
	
	
	/**
	 * This method will click the given webElement
	 * @param element - Element to be clicked
	 * @author - Gautam Jain
	 * @throws - NoSuchElementException
	 */
	public void clickElement(WebElement element);
	
		
	/**
	 * This method will return the text from the 
	 * specified element
	 * @param element - Element to be clicked
	 * @author - Gautam Jain
	 * @throws - NoSuchElementException
	 */
	public String getTextFromElement(WebElement element);
	
	/**
	 * This method will return the attribute from the 
	 * specified element
	 * @param element - Element to be clicked
	 * @param attribute - Name of the Attribute
	 * @author - Gautam Jain
	 * @throws - NoSuchElementException
	 */
	public String getAttributeFromElement(WebElement element,String attribute);
	
	/**
	 * This method will enter the given values in given element
	 * @param element - Element where values will be entered
	 * @param value - Value to be entered
	 * @author - Gautam Jain
	 * @throws - NoSuchElementException
	 */
	public void enterValues(WebElement element, String value);
	
	/**
	 * This method will press the virtual keys in given editbox
	 * @param element - Element where Keys will be pressed
	 * @param key - Key Values to be pressed
	 * @author - Gautam Jain
	 * @throws - NoSuchElementException
	 */
	public void enterKeys(WebElement element, Keys key);
	
	/**
	 * This method will press the virtual keys in given editbox
	 * @param element - Element where Keys will be pressed
	 * @param key1 - Key Values to be pressed
	 * @param key2 - Key Values to be pressed
	 * @param key3 - Key Values to be pressed 
	 * @author - Gautam Jain
	 * @throws - NoSuchElementException
	 */
	public void enterKeys(WebElement element, Keys key1,String key2, Keys key3);
	
	/**
	 * This method will select the given value from given dropdown
	 * @param element - dropdown from where value will be selected
	 * @param value - Value to be selected
	 * @author - Gautam Jain
	 * @throws - NoSuchElementException
	 */
	public void selectByValueFromDropdown(WebElement element, String value);
	
	/**
	 * This method will wait for the given element until it becomes clickable
	 * @param element - element to be waited for
	 * @author - Gautam Jain
	 * @throws - Timeout Exception,ElementClickIntercepted Exception 
	 */
	public void waitForElementToBeClickable(WebElement element);
	
	/**
	 * This method will wait for the given element until it becomes visible
	 * @param element - element to be checked for visibility
	 * @author - Gautam Jain
	 * @throws - Timeout Exception,ElementClickIntercepted Exception 
	 */
	
	public void waitForElementToBeVisible(WebElement element);
	
	/**
	 * This method will fetch text from the given element
	 * and compares it with the given text
	 * @param element - element from which text will be fetched
	 * @param text - text to be compared
	 * @author - Gautam Jain
	 * @return - will return True if compare is successful else false
	 * @throws - NoSuchElementException 
	 */
	public boolean compareText(WebElement element, String text);
	
	/**
	 * This method will verify the visibility of the given element
	 * @param element - element from which text will be fetched
	 * @author - Gautam Jain
	 * @return - will return True if element is displayed else false
	 * @throws - NoSuchElementException 
	 */
	public boolean verifyVisiblity(WebElement element);	
	
	/**
	 * This method will wait for the staleness of the given element
	 * @param element - element whose staless will be checked
	 * @author - Gautam Jain
	 * @throws - Timeout Exception,StaleElement Exception 
	 */
	public void waitForElementStaleness(WebElement element);
	
	/**
	 * This method will wait for the text to be present in the given element
	 * @param element - element in which presence of the text will be checked
	 * @param text - text to be verified
	 * @author - Gautam Jain
	 * @throws - Timeout Exception 
	 */
	public void waitForTextInElement(WebElement element, String text);
}
