package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
/**
 * @author Nikhil
 * 
 * Contains Login Page elements and business lib Like login()
 * 
 */
public class LoginPage extends WebDriverUtility{  //Rule-1 Create separate java class
	
	                      //Rule-2 Object Creation
	WebDriver driver;
	
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
  
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	//Rule-3 Object Initialization in test script
	 
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Rule-4 Object Encapsulation
	
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//Rule-5 Business method
	/**
	 * Login to application based on username password
	 *
	 * @param username
	 * @param password
	 */
	
	public void loginToApp(String username, String password) {
		waitForPageToLoad(driver);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
}
