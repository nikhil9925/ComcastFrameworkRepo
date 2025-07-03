package com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {

	// Create Object
	public DataBaseUtility dLib=new DataBaseUtility();
	public FileUtility fLib=new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib=new JavaUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	public WebDriver driver=null;
	public ExtentReports report;
	public ExtentSparkReporter spark;
	public static WebDriver sdriver=null;  // to take the screenshot for capture the session id
	
	@BeforeSuite(groups={"smokeTest","regressionTest"})
	public void configBS() throws SQLException {
		System.out.println("====Connect to DB, Report Config====");
		dLib.closeConnection();
	}

	//@Parameters("BROWSER") for parallel cross browser
	@BeforeClass(groups={"smokeTest","regressionTest"})
	public void configBC(/*String browser*/) throws IOException {
		System.out.println("====Launch the Browser====");
		//String BROWSER = browser;
		String BROWSER=fLib.getDataFromPropertiesFile("browser");
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();
		}
		else {
			driver=new ChromeDriver();	
		}
		sdriver=driver;  // to take the screenshot for capture the session id
		UtilityClassObject.setDriver(driver);
		driver.get(fLib.getDataFromPropertiesFile("url"));
	}

	@BeforeMethod(groups={"smokeTest","regressionTest"})
	public void configBM() throws IOException {
		System.out.println("====Login to Application====");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
	}

	@AfterMethod(groups={"smokeTest","regressionTest"})
	public void configMM() {
		System.out.println("====Logout to Application====");
		HomePage hp=new HomePage(driver);
		hp.logout();
	}

	@AfterClass(groups={"smokeTest","regressionTest"})
	public void configAC() {
		System.out.println("====Close The Browser====");
		driver.quit();
	}

	@AfterSuite(groups={"smokeTest","regressionTest"})
	public void configAS() throws SQLException {
		System.out.println("====Close DB, Report Backup====");
		//dLib.closeConnection();
	}

}
