package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrganizationWithPhoneNumberTest {
	public static void main(String[] args) throws IOException {
		// Read common data from property file
		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
		Properties p = new Properties();
		p.load(fis);
		String BROWSER = p.getProperty("browser");
		String URL = p.getProperty("url");
		String UN = p.getProperty("username");
		String PW = p.getProperty("password");

		// Generate Random number
		Random r = new Random();
		int ranInt = r.nextInt(1000);

		// Read Test script data from Excel File
		FileInputStream fis1 = new FileInputStream("./testData/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet s = wb.getSheet("org");
		Row row = s.getRow(7);
		String orgName = row.getCell(2).toString() + ranInt;
		String phoneNumber = row.getCell(3).toString();
		wb.close();

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
		driver.findElement(By.id("phone")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();

		// Verify Header phone number info Expected Result
		String actPhoneNumb = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actPhoneNumb.equals(phoneNumber)) {
			System.out.println(phoneNumber + " is verified successfully==PASS");
		} else
			System.out.println(phoneNumber + " is NOT verified successfully==FAIL");
		// Step 5: Log out
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();

	}
}
