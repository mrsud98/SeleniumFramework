package com.iasys.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iasys.base.BasePage;

public class LoginPage extends BasePage {

	private static final Logger logger = LogManager.getLogger(LoginPage.class);

	private final By userNameInput = By.xpath("//input[@placeholder='Username']");
	private final By passwordInput = By.xpath("//input[@placeholder='Password']");
	private final By signInButton = By.xpath("//button[contains(normalize-space(.), 'Sign In')]");
	private final By logoutButton = By.xpath("//img[@alt='logout']");

	private final By languageComboBox = By.xpath("//span[@role='combobox']");
	private final By brixLogo = By.xpath("//div[@class='text-center']//img[@alt='Background']");

	private final By toastInvalidUsername = By
			.xpath("//div[text()='Authentication failed. Please verify your credentials and try again.']");
	private final By toastInvalidPassword = By.xpath("//div[contains(text(),'Invalid username or password')]");
	private final By toastEmptyUsername = By.xpath("//div[text()='Please enter valid user name']");
	private final By toastAccountLocked = By
			.xpath("//div[contains(text(),'Account locked out. Too many failed attempts')]");

	// License agreement — only shown on first login; handled here if needed
	private final By licenseAgreementTitle = By.xpath("//*[text()='License Agreement']");
	private final By licenseCheckbox = By.xpath(
			"//*[text()='END USER LICENSE AGREEMENT']/parent::*/parent::*/following-sibling::div[@class='d-flex ps-1']/child::*/child::*");

	public LoginPage() {

		super();

	}

	public boolean isLogoutButtonVisible() {

		return isDisplayed(logoutButton);
	}

	public boolean isLogoDisplayed() {
		return isDisplayed(brixLogo);
	}

	public boolean isLogoutButtonDisplayed() {
		return isDisplayed(logoutButton);
	}

	public boolean isToastForInvalidUsernameDisplayed() {
		return isDisplayed(toastInvalidUsername);
	}

	public boolean isToastForEmptyUsernameDisplayed() {
		return isDisplayed(toastEmptyUsername);
	}

	public boolean isToastForInvalidPasswordDisplayed() {
		return isDisplayed(toastInvalidPassword);
	}

	public boolean isToastForAccountLockedDisplayed() {
		return isDisplayed(toastAccountLocked);
	}

	public void login(String username, String password) {

		type(userNameInput, username);
		type(passwordInput, password);
		click(signInButton);

	}

	public void enterUsername(String username) {
		type(userNameInput, username);
	}

	public void enterPassword(String password) {
		type(passwordInput, password);
	}

	public void clickSignIn() {
		click(signInButton);
	}

}
