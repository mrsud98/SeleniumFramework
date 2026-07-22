package com.iasys.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.iasys.reports.ExtentManager;
import com.iasys.reports.ExtentTestManager;
import com.iasys.utilities.ScreenshotUtil;

public class TestListener implements ITestListener {

	@Override
	public void onStart(ITestContext context) {

		System.out.println("========== Test Execution Started ==========");
	}

	@Override
	public void onTestStart(ITestResult result) {

		ExtentTestManager.setTest(ExtentManager.getInstance().createTest(result.getMethod().getMethodName()));

		ExtentTestManager.getTest().log(Status.INFO, "Test Started");

		ExtentTestManager.getTest().info("Thread : " + Thread.currentThread().getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		ExtentTestManager.getTest().pass("Test Passed");

		ExtentTestManager.unload();
	}

	@Override
	public void onTestFailure(ITestResult result) {

		ExtentTestManager.getTest().fail(result.getThrowable());

		try {

			String screenshotPath = ScreenshotUtil.captureScreenshot(result.getMethod().getMethodName());

			ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);

		} catch (Exception e) {

			ExtentTestManager.getTest().warning("Unable to capture screenshot : " + e.getMessage());
		}

		ExtentTestManager.unload();
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		ExtentTestManager.getTest().skip(result.getThrowable());

		try {

			String screenshotPath = ScreenshotUtil.captureScreenshot(result.getMethod().getMethodName());

			ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);

		} catch (Exception e) {

			ExtentTestManager.getTest().warning("Unable to capture screenshot : " + e.getMessage());
		}

		ExtentTestManager.unload();
	}

	@Override
	public void onFinish(ITestContext context) {

		ExtentManager.getInstance().flush();

		System.out.println("========== Test Execution Finished ==========");
	}

}