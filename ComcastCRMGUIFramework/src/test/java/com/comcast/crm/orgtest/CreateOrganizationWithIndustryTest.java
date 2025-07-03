package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;


public class CreateOrganizationWithIndustryTest {
	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) throws IOException {

		// Create Object
		JavaUtility jlib = new JavaUtility();
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		
		// Read common data from property file
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String UN = flib.getDataFromPropertiesFile("username");
		String PW = flib.getDataFromPropertiesFile("password");
		
		// Generate Random number
	     jlib.getRandomNumber();

		// Read Test script data from Excel File
		String orgName =elib.getDataFromExcel("org", 4, 2)+  jlib.getRandomNumber();
		String industry =elib.getDataFromExcel("org", 4, 3);
		String type = elib.getDataFromExcel("org", 4, 4);
	
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
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
		WebElement wbsele = driver.findElement(By.name("industry"));
		Select sel = new Select(wbsele);
		sel.selectByVisibleText(industry);
		Select sel1 = new Select(driver.findElement(By.name("accounttype")));
		sel1.selectByVisibleText(type);
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();

		// Verify the drop down industry and type info
		String actIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actIndustry.equals(industry)) {
			System.out.println(industry + " information is verified==PASS");
		} else
			System.out.println(industry + " information is NOT verified==FAIL");
		WebElement actType = driver.findElement(By.id("dtlview_Type"));
		if (actType.equals(type)) {
			System.out.println(type + " information is verified==PASS");
		} else
			System.out.println(type + " information is NOT verified==FAIL");

		// Step 5: Log out
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();

	}
}
