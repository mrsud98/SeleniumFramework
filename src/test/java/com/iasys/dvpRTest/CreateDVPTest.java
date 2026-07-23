package com.iasys.dvpRTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.iasys.base.BaseTest;
import com.iasys.pages.dvpR.CreateDvpPage;
import com.iasys.pages.login.LoginPage;
import com.iasys.utilities.ConfigReader;

public class CreateDVPTest extends BaseTest {
//change in gitHub
	ConfigReader config = ConfigReader.getInstance();
	private static final Logger logger = LogManager.getLogger(CreateDVPTest.class);

	@BeforeMethod()
	public void TC_01_verifyValidLogin() {
		String username = config.get("userName");
		String password = config.get("password");
		LoginPage loginPage = new LoginPage();
		loginPage.login(username, password);
		Assert.assertTrue(loginPage.isLogoutButtonVisible());
		logger.info("Login success");
	}

	@Test
	public void TC_001_CraetDvpnR() throws InterruptedException {
		CreateDvpPage createDvpPage = new CreateDvpPage();
		logger.info("Test start");
		createDvpPage.uploadFile();
		logger.info("Test success");
		Thread.sleep(8000);

	}
}
