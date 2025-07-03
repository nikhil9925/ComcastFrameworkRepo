package practice.test;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	public ExtentReports report;

	@BeforeSuite
	public void configBS() {
		// Spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// Add environment info and create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}

	@AfterSuite
	public void configAS() {
		report.flush();
	}

	@Test
	public void createContactTest() {

		WebDriver driver = new ChromeDriver();
		driver.get("http://49.249.28.218:8888/");

		TakesScreenshot t = (TakesScreenshot) driver;
		String filePath = t.getScreenshotAs(OutputType.BASE64);

		ExtentTest test = report.createTest("createContactTest");

		test.log(Status.INFO, "Login to app");
		test.log(Status.INFO, "Navigate to contact page");
		test.log(Status.INFO, "Create contact");

		if ("HDFC".equals("HFC")) {
			test.log(Status.PASS, "Contact is created");
		} else {
			test.addScreenCaptureFromBase64String(filePath, "ErrorFile");
		}

		driver.close();
	}
}
