package com.iasys.pages.dvpR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iasys.base.BasePage;
import com.iasys.driver.DriverManager;
import com.iasys.utilities.JavaScriptUtil;

import io.reactivex.rxjava3.functions.Action;

public class CreateDvpPage extends BasePage {

	private static final Logger logger = LogManager.getLogger(CreateDvpPage.class);

	private By testDVPSiderbarButton = By.cssSelector("a[id='testdvp']");
	private By createTestDvpButton = By.cssSelector(".test-dvp-create-btn.gap-1");
	private By fileUploadArea = By.id("fileUpload");
	private By fileUploadButton = By.xpath("//button[text()='Browse...']");

	public CreateDvpPage() {
		super();
	}

	public void uploadFile() {
		click(testDVPSiderbarButton);
		click(createTestDvpButton);

		scrollToElement(fileUploadButton);
		// type(fileUploadArea, "D:\\Current Working\\FileUpload\\Test.xlsx");

		WebElement fileUF = DriverManager.getDriver().findElement(fileUploadArea);
		fileUF.sendKeys("D:\\Current Working\\FileUpload\\Test.xlsx");
	}
}
