package com.comcast.crm.contacttest;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;

public class SampleReportTest extends BaseClass {

	@Test
	public void createContactTest() {

		ExtentTest test = report.createTest("createContactTest");
		test.log(Status.INFO, "Login to app");
		test.log(Status.INFO, "Navigate to contact page");
		test.log(Status.INFO, "Create contact");

		if ("HDFC".equals("HDFC")) {
			test.log(Status.PASS, "Contact is created");
		} else {
			test.log(Status.FAIL, "Contact is NOT created");
		}
	}

	@Test
	public void createContactTestWithOrg() {

		ExtentTest test = report.createTest("createContactTestWithOrg");
		test.log(Status.INFO, "Login to app");
		test.log(Status.INFO, "Navigate to contact page");
		test.log(Status.INFO, "Create contact");

		if ("HDFC".equals("HDFC")) {
			test.log(Status.PASS, "Contact is created");
		} else {
			test.log(Status.FAIL, "Contact is NOT created");
		}
	}

	@Test
	public void createContactTestWithPhone() {

		ExtentTest test = report.createTest("createContactTestWithPhone");
		test.log(Status.INFO, "Login to app");
		test.log(Status.INFO, "Navigate to contact page");
		test.log(Status.INFO, "Create contact");

		if ("HDFC".equals("HDFC")) {
			test.log(Status.PASS, "Contact is created");
		} else {
			test.log(Status.FAIL, "Contact is NOT created");
		}
	}

}
