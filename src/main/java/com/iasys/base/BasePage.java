package com.iasys.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.iasys.constants.FrameworkConstants;
import com.iasys.driver.DriverManager;
import com.iasys.utilities.ConfigReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.TimeoutException;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;

	private static final Logger logger = LogManager.getLogger(BasePage.class);

	protected BasePage() {

		this.driver = DriverManager.getDriver();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT));

	}

	protected void click(By locator) {
		int maxRetries = 3;
		for (int attempt = 1; attempt <= maxRetries; attempt++) {
			try {
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator))).click();

				logger.info("Thread: {} | Clicking {}", Thread.currentThread().getName(), locator);
				return;

			}

			catch (StaleElementReferenceException | ElementNotInteractableException | TimeoutException e) {

				logger.warn("Failed to click into [{}]. Retry {}/{}", locator, attempt, maxRetries);

				if (attempt == maxRetries) {
					logger.error("Failed to click into [{}] after {} attempts.", locator, maxRetries);
					throw e;
				}

			}
		}
	}

	protected void type(By locator, String text) {

		int maxRetries = 3;
		for (int attempt = 1; attempt <= maxRetries; attempt++) {

			try {

				WebElement element = wait
						.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
				element.sendKeys(text);
				return;
			} catch (StaleElementReferenceException | ElementNotInteractableException | TimeoutException e) {

				logger.warn("Failed to type into [{}]. Retry {}/{}", locator, attempt, maxRetries);

				if (attempt == maxRetries) {
					logger.error("Failed to type into [{}] after {} attempts.", locator, maxRetries);
					throw e;

				}
			}
		}
	}

	protected boolean isDisplayed(By locator) {
	    int maxRetries = 3;

	    for (int attempt = 1; attempt <= maxRetries; attempt++) {
	        try {
	            wait.until(ExpectedConditions.refreshed(
	                    ExpectedConditions.visibilityOfElementLocated(locator)));
	            return true;

	        } catch (Exception e) {
	            if (attempt == maxRetries) {
	                logger.error("Element [{}] was not displayed after {} attempts.", locator, maxRetries, e);
	                return false;
	            }
	        }
	    }

	    return false;
	}
			

	protected void scrollToElement(By locator) {
		WebElement element = wait
				.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
		Actions action = new Actions(DriverManager.getDriver());
		action.moveToElement(element);
	}

}
