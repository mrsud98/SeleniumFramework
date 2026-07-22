package com.iasys.utilities;

import org.openqa.selenium.*;
import com.iasys.driver.DriverManager;

public class JavaScriptUtil {

	private static JavascriptExecutor js() {
		return (JavascriptExecutor) DriverManager.getDriver();
	}

	public static void click(By locator) {
		WebElement element = DriverManager.getDriver().findElement(locator);
		js().executeScript("arguments[0].click();", element);
	}

	public static void scrollIntoView(WebElement element) {
		js().executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollToBottom() {
		js().executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
	}

	public static void scrollToTop() {
		js().executeScript("window.scrollTo(0,0)");
	}

	public static void setValue(WebElement element, String value) {
		js().executeScript("arguments[0].value=arguments[1]", element, value);
	}

	public static String getTitle() {
		return (String) js().executeScript("return document.title;");
	}

	public static void highlight(WebElement element) {
		js().executeScript("arguments[0].style.border='3px solid red'", element);
	}
}