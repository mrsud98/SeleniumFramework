package com.iasys.loginTest;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.iasys.base.BaseTest;
import com.iasys.dataprovider.LoginDataProvider;
import com.iasys.pages.login.LoginPage;
import com.iasys.utilities.ConfigReader;

public class LoginTest extends BaseTest {

	private static final Logger logger = LogManager.getLogger(LoginTest.class);
	ConfigReader config = ConfigReader.getInstance();

	@Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
	public void dataLogin(String username, String password) {
		logger.info("Starting TC_01_verifyLoginPageLoads");
		LoginPage loginPage = new LoginPage();
		loginPage.login(username, password);

	}

	@Test()
	public void TC_01_verifyLoginPageLoads() {
		logger.info("Starting TC_01_verifyLoginPageLoads");
		LoginPage loginPage = new LoginPage();

		Assert.assertFalse(loginPage.isLogoDisplayed(), "Login page logo should be visible on page load");

		logger.info("TC_01 PASSED");
	}

	@Test()
	public void TC_01_verifyValidLogin() {

		String username = config.get("userName");
		String password = config.get("password");

		LoginPage loginPage = new LoginPage();

		loginPage.login(username, password);

		Assert.assertTrue(loginPage.isLogoutButtonVisible());

	}

	@Test
	public void TC_03_invalidUsername() {

		String usernameInvalid = config.get("invalidUserName");
		String passwordN = config.get("password");
		logger.info("Starting TC_03_invalidUsername");
		LoginPage loginPage = new LoginPage();

		loginPage.login(usernameInvalid, passwordN);

		Assert.assertTrue(loginPage.isToastForInvalidUsernameDisplayed(), "test passed");

		logger.info("TC_03 PASSED ");
	}

	@Test
	public void TC_04_invalidPassword() {
		logger.info("Starting TC_04_invalidPassword — {}");

		String userName = config.get("userName");
		String passwordN = config.get("invalidUserName");
		LoginPage loginPage = new LoginPage();

		loginPage.login(userName, passwordN);

		Assert.assertTrue(loginPage.isToastForInvalidPasswordDisplayed(), "Invalid password test case passe");

		logger.info("TC_04 PASSED — {}");
	}

	@Test
	public void TC_06_usernameMandatory() {
		logger.info("Starting TC_06_usernameMandatory");
		LoginPage loginPage = new LoginPage();

		loginPage.enterPassword(config.get("password"));
		loginPage.clickSignIn();

		Assert.assertTrue(loginPage.isToastForEmptyUsernameDisplayed(), "Empty username toast  show. : ");

		logger.info("TC_06 PASSED");
	}
}
