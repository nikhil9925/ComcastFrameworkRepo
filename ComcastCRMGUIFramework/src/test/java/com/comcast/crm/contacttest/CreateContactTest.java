package com.comcast.crm.contacttest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.HomePage;
/**
 * @author Nikhil
 * 
 */
@Listeners(com.comcast.crm.listenerutility.ListenerImpClass.class)
public class CreateContactTest extends BaseClass {
	@Test(groups={"smokeTest"})
	public void createContactTest() throws IOException {

		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		// Read Test script data from Excel File
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// Step 2: navigate to Contacts module
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to contact module");
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// Step 3: Click on create Contacts button
		UtilityClassObject.getTest().log(Status.INFO, "Click on create contact button");
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// Step 4: Enter all the details & create new organization
		UtilityClassObject.getTest().log(Status.INFO, "Enter all details and create new organisation");
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();

		// Verify Header phone number info Expected Result
		UtilityClassObject.getTest().log(Status.PASS, "Verify header phone number");
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actLastName.equals(lastName)) {
			System.out.println(lastName + " is verified successfully==PASS");
		} else
			System.out.println(lastName + " is NOT verified successfully==FAIL");
	}

	@Test(groups="regressionTest")
	public void createContactWithSupportDateTest() throws IOException {

		// Read Test script data from Excel File
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		// Step 2: navigate to Contacts module
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		String startDate = jLib.getSytemDateYYYYDDMM();
		String endDate = jLib.getRequiredDateYYYYDDMM(30);

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
	}

	@Test(groups="regressionTest")
	public void createContactWithOrgTest() throws IOException {

		// Read Test script data from Excel File
		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
		String lastName = eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNumber();

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
		wLib.switchToTabOnUrl(driver, "module=Accounts");

		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// switch to parent window
		wLib.switchToTabOnUrl(driver, "Contacts&action");

		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();

		// Verify Header phone number info Expected Result
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actLastName.equals(lastName)) {
			System.out.println(lastName + " is verified successfully==PASS");
		} else
			System.out.println(lastName + " is NOT verified successfully==FAIL");

	}

}
