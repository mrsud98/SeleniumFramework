package com.iasys.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentManager {

	private static final ExtentReports EXTENT = new ExtentReports();

	static {

		String reportPath = System.getProperty("user.dir") + "/report/ExtentReport.html";

		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

		spark.config().setDocumentTitle("IASYS Automation Report");
		spark.config().setReportName("Automation Execution Report");

		EXTENT.attachReporter(spark);

		EXTENT.setSystemInfo("Framework", "Selenium Java");
		EXTENT.setSystemInfo("Execution", "TestNG");
		EXTENT.setSystemInfo("Author", "Sudarshan");
	}

	private ExtentManager() {
	}

	public static ExtentReports getInstance() {
		return EXTENT;
	}

}