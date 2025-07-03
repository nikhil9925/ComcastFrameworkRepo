package com.comcast.crm.listenerutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImpClass implements ITestListener, ISuiteListener{
	
	public ExtentReports report;
	public static ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		// Spark report config
				ExtentSparkReporter spark = new ExtentSparkReporter("./ExtentReport/report_"+time+".html");
				spark.config().setDocumentTitle("CRM Test Suite Result");
				spark.config().setReportName("CRM Report");
				spark.config().setTheme(Theme.DARK);

				// Add environment info and create test
				report = new ExtentReports();
				report.attachReporter(spark);
				report.setSystemInfo("OS", "Windows-11");
				report.setSystemInfo("BROWSER", "CHROME-100");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("==========="+result.getMethod().getMethodName()+"=====START======");
		 test = report.createTest(result.getMethod().getMethodName());
		 UtilityClassObject.setTest(test);
		 test.log(Status.INFO, result.getMethod().getMethodName()+"===> STARTED <===");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("==========="+result.getMethod().getMethodName()+"=====END======");
		 test.log(Status.PASS, result.getMethod().getMethodName()+"===> COMPLETED <===");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		TakesScreenshot t = (TakesScreenshot) BaseClass.sdriver;
		String filePath = t.getScreenshotAs(OutputType.BASE64);
		
				String time = new Date().toString().replace(" ", "_").replace(":", "_");
				test.addScreenCaptureFromBase64String(filePath, testName+"_"+time);
				
				 test.log(Status.FAIL, result.getMethod().getMethodName()+"===> FAILED <===");


	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		report.flush();
	}
   
	
}
