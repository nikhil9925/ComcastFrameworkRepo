package com.comcast.crm.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContatctWithOrgTest {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		FileUtility flib = new FileUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();
		ExcelUtility elib = new ExcelUtility();
		
		// Read common data from property file
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String UN = flib.getDataFromPropertiesFile("username");
		String PW = flib.getDataFromPropertiesFile("password");

		// Read Test script data from Excel File
		String orgName = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String lastName = elib.getDataFromExcel("contact", 7, 3) + jlib.getRandomNumber();

		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else
			driver = new ChromeDriver();

		// Step 1: login to app
		wlib.waitForPageToLoad(driver);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(UN);
		driver.findElement(By.name("user_password")).sendKeys(PW);
		driver.findElement(By.id("submitButton")).click();

		// Step 2: navigate to organization module
		driver.findElement(By.linkText("Organizations")).click();

		// Step 3: Click on create organization button
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// Step 4: Enter all the details & create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();

		// Verify Header msg Expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " is verified==PASS");
		} else
			System.out.println(orgName + " is not verified==FAIL");

		// Step 5: Navigate to contact module

		driver.findElement(By.linkText("Contacts")).click();

		// Step 6: Click on create Contacts button
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// Step 7: Enter all the details & create new organization
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By
				.xpath("//input[@name='account_name']/following-sibling::img[@src='themes/softed/images/select.gif']"))
				.click();
		// switch to child window
		wlib.switchToTabOnUrl(driver, "module=Accounts");

		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// switch to parent window
		wlib.switchToTabOnUrl(driver, "Contacts&action");

		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();

		// Verify Header phone number info Expected Result
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actLastName.equals(lastName)) {
			System.out.println(lastName + " is verified successfully==PASS");
		} else
			System.out.println(lastName + " is NOT verified successfully==FAIL");

	}
}
