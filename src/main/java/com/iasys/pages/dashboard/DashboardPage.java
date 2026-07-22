package com.iasys.pages.dashboard;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.iasys.base.BasePage;
import com.iasys.pages.login.LoginPage;

public class DashboardPage extends BasePage {

	private static final Logger logger = LogManager.getLogger(DashboardPage.class);

	public DashboardPage() {
		super();
	}

	private final By administratorMenu = By.xpath("//a[@id='administrator']");

	private final By dashboardTab = By.xpath("//*[@data-testid='id_admin_toolbar_admindashboard']");

	private final By serverStatusHeader = By
			.xpath("//*[@data-testid='id_admin_dashboard_serverStatus_serverStatusHeader']");

	private final By cpuLabel = By.xpath("//*[normalize-space()='CPU']");

	private final By memoryLabel = By.xpath("//*[normalize-space()='Memory']");

	private final By hardDiskLabel = By.xpath("//*[@data-testid='id_admin_dashboard_hardDisk']");

	private final By cpuProgressBar = By
			.xpath("//*[@data-testid='id_admin_dashboard_cpu']/parent::*/following-sibling::*");

	private final By memoryProgressBar = By.xpath("//*[@data-testid='id_admin_dashboard_memory']/following-sibling::*");

	private final By hardDiskProgressBar = By
			.xpath("//*[@data-testid='id_admin_dashboard_hardDisk']/parent::*/following-sibling::*");

	private final By cpuPercentage = By.xpath("//*[@data-testid='id_admin_dashboard_cpu']/following-sibling::*");

	private final By memoryPercentage = By.xpath("//*[@data-testid='id_admin_dashboard_memory']/following-sibling::*");

	private final By hardDiskPercentage = By
			.xpath("//*[@data-testid='id_admin_dashboard_hardDisk']/following-sibling::*");

	// =====================================================================
	// Log Info Section
	// =====================================================================

	private final By logInfoHeader = By.xpath("//*[@data-testid='id_admin_dashboard_logInfo_lbl']");

	private final By logLevelValue = By.xpath("//*[@data-testid='id_admin_dashboard_logLevel_lbl']");

	private final By maxSizeValue = By.xpath("//*[@data-testid='id_admin_dashboard_maxSizeGb_lbl']");

	private final By logStorageDurationValue = By
			.xpath("//*[@data-testid='id_admin_dashboard_logStorageDuration_lbl']");

	// =====================================================================
	// Live Session Section
	// =====================================================================

	private final By liveSessionHeader = By.xpath("//*[normalize-space()='Live User Session']");

	private final By sessionCountText = By.xpath("//div[contains(text(),'of')]");

	private final By searchUserInput = By.xpath("//*[@placeholder='Search user']");

	private final By liveSessionTable = By.xpath("//div[contains(@class,'e-gridcontent')]//table");

	private final By firstSessionRow = By.xpath("(//i[@class='ph ph-sign-out'])[1]");

	private final By usernameColumnHeader = By.xpath("//span[normalize-space()='User Name']");

	private final By firstNameColumnHeader = By.xpath("//span[normalize-space()='First name']");

	// Note: Original locator points to 'User name', not 'Last name'
	private final By lastNameColumnHeader = By.xpath("//span[normalize-space()='Last name']");

	private final By startTimeColumnHeader = By.xpath("//span[normalize-space()='Start time']");

	private final By durationColumnHeader = By.xpath("//span[normalize-space()='Duration']");

	// Refresh button for live session
	private final By liveSessionRefreshButton = By.xpath("//*[@id='liveSessionRefresh']");

	// Export button
	private final By exportButton = By
			.xpath("//*[contains(@class,'export') or @title='Export' or contains(@data-testid,'export')]");

	// Progress bar for session count
	private final By sessionProgressBar = By.xpath("//*[@id='totoalLiveUsersSVG']");

	// =====================================================================
	// Users Login Trend Section
	// =====================================================================

	private final By loginTrendHeader = By.xpath("//*[contains(text(),'Users login trend')]");

	private final By filter12HButton = By.xpath("//*[normalize-space()='12 H']");

	private final By filter24HButton = By.xpath("//*[normalize-space()='24 H']");

	private final By twelveHButtonActive = By
			.xpath("//button[@data-testid='id_admin_dashboard_12_hr_lbl' and contains(@class,'activeHourBtn')]");

	private final By twentyFourHButtonActive = By
			.xpath("//button[@data-testid='id_admin_dashboard_24_hr_lbl' and contains(@class,'activeHourBtn')]");

	private final By loginTrendChart = By.xpath("//*[normalize-space()='Number of users']");

	private final By loginTrendRefreshButton = By
			.xpath("//*[@data-testid='id_admin_dashboard_user_login_refresh_btn']");

	public void navigateToDashboard() {

		click(administratorMenu);
		click(dashboardTab);
		logger.info("Navigated to Dashboard.");
		click(administratorMenu);
	}

	// =====================================================================
	// Server Status Methods
	// =====================================================================

	/**
	 * Returns true if Server Status section header is visible.
	 */
	public boolean isServerStatusDisplayed() {
		logger.info("Verifying Server Status section.");
		return isDisplayed(serverStatusHeader);
	}

	/**
	 * Returns true if all three server metrics (CPU, Memory, Hard Disk) are
	 * visible.
	 */
	public boolean isAllServerMetricsDisplayed() {
		return isDisplayed(cpuLabel) && isDisplayed(memoryLabel) && isDisplayed(hardDiskLabel);
	}

	/**
	 * Returns true if progress bars for all metrics are visible.
	 */
	public boolean isServerProgressBarsDisplayed() {
		return isDisplayed(cpuProgressBar) && isDisplayed(memoryProgressBar) && isDisplayed(hardDiskProgressBar);
	}

	/**
	 * Returns CPU usage percentage text (e.g. "4%").
	 */
	public String getCpuPercentage() {

		return driver.findElement(cpuPercentage).getText().trim();

	}

	/**
	 * Returns Memory usage percentage text (e.g. "72%").
	 */
	public String getMemoryPercentage() {
		return driver.findElement(memoryPercentage).getText().trim();

	}

	/**
	 * Returns Hard Disk usage percentage text (e.g. "18%").
	 */
	public String getHardDiskPercentage() {

		return driver.findElement(hardDiskPercentage).getText().trim();
	}

	// =====================================================================
	// Log Info Methods
	// =====================================================================

	/**
	 * Returns true if Log Info section header is visible.
	 */
	public boolean isLogInfoDisplayed() {
		logger.info("Verifying Log Info section.");
		return isDisplayed(logInfoHeader);
	}

	/**
	 * Returns true if all log info fields are visible.
	 */
	public boolean isAllLogInfoFieldsDisplayed() {
		return isDisplayed(logLevelValue) && isDisplayed(maxSizeValue) && isDisplayed(logStorageDurationValue);
	}

	/**
	 * Returns the Log Level value text (e.g. "ERROR").
	 */
	public String getLogLevel() {
		return driver.findElement(logLevelValue).getText().trim();

	}

	/**
	 * Returns the Max Size value text (e.g. "15").
	 */
	public String getMaxSize() {
		return driver.findElement(maxSizeValue).getText().trim();

	}

	// =====================================================================
	// Live Session Methods
	// =====================================================================

	/**
	 * Returns true if Live Session section header is visible.
	 */
	public boolean isLiveSessionDisplayed() {
		logger.info("Verifying Live Session section.");
		return isDisplayed(liveSessionHeader);
	}

	/**
	 * Returns true if live session table has at least one row.
	 */
	public boolean isLiveSessionTablePopulated() {
		return isDisplayed(firstSessionRow);
	}

	/**
	 * Returns the session count text (e.g. "5 of 100").
	 */
	public String getSessionCountText() {
		return driver.findElement(sessionCountText).getText().trim();

	}

	/**
	 * Returns true if session progress bar is visible.
	 */
	public boolean isSessionProgressBarDisplayed() {
		return isDisplayed(sessionProgressBar);
	}

	/**
	 * Returns true if all table column headers are visible.
	 */
	public boolean isLiveSessionTableHeadersDisplayed() {
		return isDisplayed(usernameColumnHeader) && isDisplayed(firstNameColumnHeader)
				&& isDisplayed(lastNameColumnHeader) && isDisplayed(startTimeColumnHeader)
				&& isDisplayed(durationColumnHeader);
	}

	/**
	 * Searches for a user in the live session search box.
	 */
	public void searchUser(String username) {
		type(searchUserInput, username);
		logger.info("Searched for user: {}", username);
	}

	/**
	 * Clears the search box.
	 */
	/*
	 * public void clearSearch() { WebElement element1
	 * =driver.findElement(searchUserInput); element1.clear();
	 * logger.info("Cleared search box."); }
	 */

	/**
	 * Clicks the refresh button in the Live Session section.
	 */
	public void refreshLiveSession() {
		click(liveSessionRefreshButton);
		// waitForSpinnerToDisappear();
		logger.info("Refreshed live session data.");
	}

	/**
	 * Clicks Username column header to sort.
	 */
	public void sortByUsername() {
		click(usernameColumnHeader);
		// waitForSpinnerToDisappear();
		logger.info("Sorted live session by username.");
	}

	/**
	 * Clicks the export button.
	 */
	public void clickExport() {
		click(exportButton);
		logger.info("Clicked export button.");
	}

	// =====================================================================
	// Login Trend Methods
	// =====================================================================

	/**
	 * Returns true if Users Login Trend section is visible.
	 */
	public boolean isLoginTrendDisplayed() {
		logger.info("Verifying Users Login Trend section.");
		return isDisplayed(loginTrendHeader);
	}

	/**
	 * Returns true if the login trend chart is visible.
	 */
	public boolean isLoginTrendChartDisplayed() {
		return isDisplayed(loginTrendChart);
	}

	/**
	 * Returns true if 12H filter button is visible.
	 */
	public boolean is12HFilterDisplayed() {
		return isDisplayed(twelveHButtonActive);
	}

	public boolean is24HFilterDisplayed() {
		return isDisplayed(twentyFourHButtonActive);
	}

	/**
	 * Clicks the 12H filter button.
	 */
	public void apply12HFilter() {
		click(filter12HButton);
		// waitForSpinnerToDisappear();
		logger.info("Applied 12H filter on login trend.");
	}

	/**
	 * Clicks the 24H filter button.
	 */
	public void apply24HFilter() {
		click(filter24HButton);
		// waitForSpinnerToDisappear();
		logger.info("Applied 24H filter on login trend.");
	}

	/**
	 * Clicks the refresh button in the Login Trend section.
	 */
	public void refreshLoginTrend() {
		click(loginTrendRefreshButton);
		// waitForSpinnerToDisappear();
		logger.info("Refreshed login trend chart.");
	}

}
