package com.comcast.crm.orgtest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTest extends BaseClass {
	@Test(groups="smokeTest")
	public  void createOrganizationTest() throws EncryptedDocumentException, IOException {


		// Read Test script data from Excel File
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
	
		// Step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
        //hp.navigateToCampaignsPage(); 
        
		// Step 3: Click on create organization button
		OrganizationsPage op=new OrganizationsPage(driver);
        op.getCreateNewOrgBtn().click();
        
		// Step 4: Enter all the details & create new organization
		CreatingNewOrganizationPage cno=new CreatingNewOrganizationPage(driver);
		cno.createOrg(orgName);

		// Verify Header msg Expected result
	     OrganizationInformationPage oip=new OrganizationInformationPage(driver);
	     String actualOrgName = oip.getHeaderMsg().getText();
	     if(actualOrgName.contains(orgName)) {
	    	 System.out.println(orgName + " name is verified==PASS");
	     }
	     else 
	    	 System.out.println(orgName + " name is NOT verified==PASS");
	}
	     @Test(groups="regressionTest")
	     public void createOrganizationWithIndustryTest() throws EncryptedDocumentException, IOException {


	 		// Read Test script data from Excel File
	 		String orgName =eLib.getDataFromExcel("org", 4, 2)+  jLib.getRandomNumber();
	 		String industry =eLib.getDataFromExcel("org", 4, 3);
	 		String type = eLib.getDataFromExcel("org", 4, 4);
	 
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

	}
}
