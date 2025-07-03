package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	@FindBy(linkText = "Organizations")
	private WebElement orgLink;

	@FindBy(linkText = "Contacts")
	private WebElement contactLink;

	@FindBy(linkText = "Campaigns")
	private WebElement campaignsLink;

	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getCampaignsLink() {
		return campaignsLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public void navigateToCampaignsPage() {
		Actions a = new Actions(driver);
		a.moveToElement(moreLink).perform();
		campaignsLink.click();
	}
   
	public void logout() {
		Actions a=new Actions(driver);
		a.moveToElement(adminImg).perform();
		signOutLink.click();
		
		
	}
}
