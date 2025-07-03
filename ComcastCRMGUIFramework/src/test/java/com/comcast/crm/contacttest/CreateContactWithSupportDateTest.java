package com.comcast.crm.contacttest;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithSupportDateTest {
	public static void main(String[] args) throws IOException {

		// Create Object
		JavaUtility jlib = new JavaUtility();
		FileUtility flib= new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		WebDriverUtility wlib=new WebDriverUtility();

		// Read common data from property file
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String UN = flib.getDataFromPropertiesFile("username");
		String PW = flib.getDataFromPropertiesFile("password");

		// Read Test script data from Excel File
		String lastName = elib.getDataFromExcel("contact", 4, 2)+ jlib.getRandomNumber();
		
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

		// Step 2: navigate to Contacts module
		driver.findElement(By.linkText("Contacts")).click();
        driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
        String startDate = jlib.getSytemDateYYYYDDMM();
        String endDate = jlib.getRequiredDateYYYYDDMM(30);

		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);

		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();

		// Verify start date and end date
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if (actStartDate.equals(startDate)) {
			System.out.println(startDate + " is verified successfully==PASS");
		} else
			System.out.println(startDate + " is NOT verified successfully==FAIL");
		// Step 5: Log out
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();

	}
}
