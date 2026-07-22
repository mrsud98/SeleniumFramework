package com.iasys.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import com.iasys.driver.DriverManager;

public class ScreenshotUtil {

	public static String captureScreenshot(String testName) throws IOException {

		File source = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/test-output/screenshots/" + testName + ".png";

		File destFile = new File(destination);

		destFile.getParentFile().mkdirs();

		/*
		 * Files.copy(source.toPath(), destFile.toPath(),
		 * StandardCopyOption.REPLACE_EXISTING);
		 */

		FileHandler.copy(source, destFile);

		return destination;
	}
}