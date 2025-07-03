package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest {
	public static void main(String[] args) throws IOException {

		// create object
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();

		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String UN = flib.getDataFromPropertiesFile("username");
		String PW = flib.getDataFromPropertiesFile("password");

		// Generate Random number
		jLib.getRandomNumber();

		// Read Test script data from Excel File
		String orgName = elib.getDataFromExcel("org", 10, 2) + jLib.getRandomNumber();

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

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(UN, PW);

		// Step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		// hp.navigateToCampaignsPage();

		// Step 3: Click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// Step 4: Enter all the details & create new organization
		CreatingNewOrganizationPage cno = new CreatingNewOrganizationPage(driver);
		cno.createOrg(orgName);

		// Verify Header msg Expected result
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actualOrgName = oip.getHeaderMsg().getText();
		if (actualOrgName.contains(orgName)) {
			System.out.println(orgName + " name is verified==PASS");
		} else
			System.out.println(orgName + " name is NOT verified==PASS");

		// Go back to organization page
		hp.getOrgLink().click();
		// Search for organization
		op.getSearchEdt().sendKeys(orgName);
		wlib.select(op.getSearchDD(), "Organization Name");
		op.getSearchBtn().click();

		// In dynamic web table select & delete organization
		//driver.findElement(
		//By.xpath("//a[text()='" + orgName +"']/ancestor::tr[@class='lvtColData']/descendant::a[text()='del']")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']/../../td[8]/a[text()='del']")).click();
		// Step 5: Log out

		//hp.logout();
	}
}
