package com.iasys.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.iasys.driver.BrowserFactory;
import com.iasys.driver.DriverManager;
import com.iasys.utilities.ConfigReader;

public class BaseTest {
	ConfigReader config = ConfigReader.getInstance();

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void setUp(@Optional("CHROME") String browser) {

		WebDriver driver = BrowserFactory.valueOf(browser.toUpperCase()).createDriver();

		DriverManager.setDriver(driver);

		DriverManager.getDriver().manage().deleteAllCookies();

		// DriverManager.getDriver().get(ConfigReader.getProperty("url"));

		DriverManager.getDriver().get(config.get("url"));

		DriverManager.getDriver().navigate().refresh();
	}

	@AfterMethod(alwaysRun = true)

	public void tearDown() {

		DriverManager.quit();

	}

}
