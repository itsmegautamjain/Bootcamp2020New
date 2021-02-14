package mySalesforcePages;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseClasses.ActionSpecificBaseMethods;

public class Assesment02Page extends ActionSpecificBaseMethods {

//	public Assesment02Page(WebDriver driver) {
//		this.driver = driver;
//	}
	
	public Assesment02Page clickOnServiceLink() {
		//This method will click on Service link
		clickElement(locateElement("Xpath","//p[text()='Service']"));
		return this;
	}
	
	public Assesment02Page clickOnReportsTab() {
		// This method will click on Reports tab  
			waitForElementToBeVisible(locateElement("Xpath", "//li[contains(@class,'metric')][3]/span[1]"));
			clickElement(locateElement("Xpath", "//a[@title='Reports']"));
		return this;
	}
	
	public Assesment02Page clickOnNewReportButton() {
		// This method will click on New Report(Salesforce Classic) Button 
			clickElement(locateElement("Xpath", "//a[@title='New Report (Salesforce Classic)']"));
		return this;
	}
	
	public Assesment02Page clickOnLeads() throws MalformedURLException, IOException {
		// This method will click on Leads link 
			WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@class,'reportsReportBuilder')]"));
			driver.switchTo().frame(iframe);
			clickElement(locateElement("Xpath", "//span[text()='Leads']"));
			String src = getAttributeFromElement(locateElement("Xpath", "//img[@id='thePage:dummyForm:report_img']"), "src");
			System.out.println(src);
			BufferedImage bufferedImage = ImageIO.read(new URL(src));
			File outputfile = new File("saved.png");
			ImageIO.write(bufferedImage, "png", outputfile);

			clickElement(locateElement("Xpath", "//input[@value='Create']"));
			driver.switchTo().defaultContent();
		return this;
	}
	
	public Assesment02Page selectRange(String rangeValue) {
		// This method will click on Leads link 
			WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@class,'reportsReportBuilder')]"));
			driver.switchTo().frame(iframe);
			clickElement(locateElement("Xpath", "//input[@name='duration']"));
			clickElement(locateElement("Xpath", "//div[text()='"+rangeValue+"']"));
			clickElement(locateElement("Xpath", "//input[@name='endDate']/following-sibling::img[1]"));
			clickElement(locateElement("Xpath", "//span[text()='18']"));
			if(verifyVisiblity(locateElement("Xpath", "//button[text()='Tabular Format']"))) {
				System.out.println("Preview is displayed in Tabular Format");
			}else {
				System.out.println("Preview is not displayed in Tabular Format");
			}
			int size = locateElements("Xpath", "//div[@id='gridViewScrollpreviewPanelGrid']/div/div").size();
			System.out.println("Total Records= "+size);
//			size = locateElements("Xpath", "(//div[@id='gridViewScrollpreviewPanelGrid']/div/div)[3]/table/tbody/tr/td").size();
//			System.out.println(size);			
			List<String> ls = new ArrayList<String>();
			for(int i=1;i<size-1;i++) {
				ls.add(getTextFromElement(locateElement("Xpath", "(//div[@id='gridViewScrollpreviewPanelGrid']/div/div)["+i+"]/table/tbody/tr/td[5]")));
			}
			for(String str:ls) {
				System.out.println(str);
			}
			clickElement(locateElement("Xpath", "//button[text()='Save']"));
			driver.switchTo().defaultContent();
		return this;
	}
}
