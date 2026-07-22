package com.iasys.dashboardTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.iasys.base.BaseTest;
import com.iasys.loginTest.LoginTest;
import com.iasys.pages.dashboard.DashboardPage;
import com.iasys.pages.login.LoginPage;
import com.iasys.utilities.ConfigReader;

public class DashboardTest extends BaseTest {

	ConfigReader config = ConfigReader.getInstance();
	private static final Logger logger = LogManager.getLogger(LoginTest.class);

	@BeforeMethod()

	public void TC_01_verifyValidLogin() {

		// String username= ConfigReader.getProperty("userName");
		// String password= ConfigReader.getProperty("password");

		String username = config.get("userName");
		String password = config.get("password");

		LoginPage loginPage = new LoginPage();

		loginPage.login(username, password);

		Assert.assertTrue(loginPage.isLogoutButtonVisible());

	}

	@Test(priority = 1, groups = { "smoke", "sanity",
			"regression" }, description = "DASH_TC_01 Verify server status section is displayed with CPU, Memory and Hard Disk")
	public void DASH_TC_01_verifyServerStatusDisplayed() {
		logger.info("Navigated to Dashboard for test.");

		DashboardPage dashboardPage = new DashboardPage();
		// dashboardPage.navigateToDashboard();
		dashboardPage.navigateToDashboard();

		logger.info("Starting DASH_TC_01_verifyServerStatusDisplayed");

		Assert.assertTrue(dashboardPage.isServerStatusDisplayed(), "Server Status section header should be visible");

		Assert.assertTrue(dashboardPage.isAllServerMetricsDisplayed(),
				"CPU, Memory and Hard Disk labels should all be visible");

		Assert.assertTrue(dashboardPage.isServerProgressBarsDisplayed(),
				"Progress bars for all server metrics should be visible");

		logger.info("CPU: {} | Memory: {} | Hard Disk: {}", dashboardPage.getCpuPercentage(),
				dashboardPage.getMemoryPercentage(), dashboardPage.getHardDiskPercentage());

		logger.info("DASH_TC_01_verifyServerStatusDisplayed PASSED");
	}

	// =====================================================================
	// Log Info
	// =====================================================================

	@Test(priority = 2, groups = { "smoke", "sanity",
			"regression" }, description = "DASH_TC_02 Verify log info section is displayed with correct fields")
	public void DASH_TC_02_verifyLogInfoDisplayed() {
		logger.info("Starting DASH_TC_02_verifyLogInfoDisplayed");

		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.navigateToDashboard();

		Assert.assertTrue(dashboardPage.isLogInfoDisplayed(), "Log Info section header should be visible");

		Assert.assertTrue(dashboardPage.isAllLogInfoFieldsDisplayed(),
				"Log level, Max size and Log storage duration should all be visible");

		logger.info("DASH_TC_02_verifyLogInfoDisplayed PASSED");
	}

	// =====================================================================
	// Live Session
	// =====================================================================

	@Test(priority = 3, groups = { "smoke", "sanity",
			"regression" }, description = "DASH_TC_03 Verify live session table is displayed with active sessions")
	public void DASH_TC_03_verifyLiveSessionDisplayed() {
		logger.info("Starting DASH_TC_03_verifyLiveSessionDisplayed");

		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.navigateToDashboard();

		Assert.assertTrue(dashboardPage.isLiveSessionDisplayed(), "Live Session section header should be visible");

		Assert.assertTrue(dashboardPage.isLiveSessionTableHeadersDisplayed(),
				"Live session table headers should be visible "
						+ "(Username, First name, Last name, Start time, Duration)");

		Assert.assertTrue(dashboardPage.isLiveSessionTablePopulated(),
				"Live session table should have at least one active session");

		// logger.info("Session count: {}", dashboardPage.getSessionCountText());

		logger.info("DASH_TC_03_verifyLiveSessionDisplayed PASSED");
	}

	// =====================================================================
	// Session Progress Bar
	// =====================================================================

	@Test(priority = 4, groups = { "sanity",
			"regression" }, description = "DASH_TC_04 Verify session progress bar and count are displayed")
	public void DASH_TC_04_verifySessionProgressBar() {
		logger.info("Starting DASH_TC_04_verifySessionProgressBar");

		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.navigateToDashboard();

		Assert.assertTrue(dashboardPage.isSessionProgressBarDisplayed(), "Session progress bar should be visible");

		logger.info("DASH_TC_04_verifySessionProgressBar PASSED");
	}

	// =====================================================================
	// Live Session Refresh
	// =====================================================================

	@Test(priority = 5, groups = {
			"regression" }, description = "DASH_TC_05 Verify live session refresh button updates the data")
	public void DASH_TC_05_verifyLiveSessionRefresh() {
		logger.info("Starting DASH_TC_05_verifyLiveSessionRefresh");

		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.navigateToDashboard();

		dashboardPage.refreshLiveSession();

		// Verify table is still displayed after refresh
		Assert.assertTrue(dashboardPage.isLiveSessionDisplayed(),
				"Live session section should still be visible after refresh");

		Assert.assertTrue(dashboardPage.isLiveSessionTableHeadersDisplayed(),
				"Live session table headers should still be visible after refresh");

		logger.info("DASH_TC_05_verifyLiveSessionRefresh PASSED");
	}

	// =====================================================================
	// Search User
	// =====================================================================

	@Test(priority = 6, groups = { "sanity",
			"regression" }, description = "DASH_TC_06 Verify search user filters live session results")
	public void DASH_TC_06_verifySearchUser() {
		logger.info("Starting DASH_TC_06_verifySearchUser");

		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.navigateToDashboard();

		// Use the logged in username from config for search
		String searchTerm = config.get("userName");

		dashboardPage.searchUser(searchTerm);

		// Verify table still visible with filtered results
		Assert.assertTrue(dashboardPage.isLiveSessionDisplayed(),
				"Live session section should be visible after search");

		Assert.assertTrue(dashboardPage.isLiveSessionTablePopulated(),
				"Live session table should show filtered results for: " + searchTerm);

		logger.info("Search for '{}' completed successfully.", searchTerm);
		logger.info("DASH_TC_06_verifySearchUser PASSED");
	}

	// =====================================================================
	// Sort
	// =====================================================================

	@Test(priority = 7, groups = {
			"regression" }, description = "DASH_TC_07 Verify sort functionality on live session table")
	public void DASH_TC_07_verifySortFunctionality() {
		logger.info("Starting DASH_TC_07_verifySortFunctionality");

		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.navigateToDashboard();

		// Verify table is loaded before sorting
		Assert.assertTrue(dashboardPage.isLiveSessionTablePopulated(),
				"Live session table should be populated before sorting");

		// Apply sort by username
		dashboardPage.sortByUsername();

		// Verify table still visible and populated after sort
		Assert.assertTrue(dashboardPage.isLiveSessionTableHeadersDisplayed(),
				"Table headers should still be visible after sorting");

		Assert.assertTrue(dashboardPage.isLiveSessionTablePopulated(),
				"Live session table should still show data after sorting");

		logger.info("DASH_TC_07_verifySortFunctionality PASSED");
	}

	// =====================================================================
	// Login Trend — 12H Filter (default)
	// =====================================================================

	@Test(priority = 8, groups = { "smoke", "sanity",
			"regression" }, description = "DASH_TC_08 Verify login trend chart is displayed with 12H filter by default")
	public void DASH_TC_08_verifyLoginTrend12HDefault() {
		logger.info("Starting DASH_TC_08_verifyLoginTrend12HDefault");

		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.navigateToDashboard();

		Assert.assertTrue(dashboardPage.isLoginTrendDisplayed(), "Users Login Trend section should be visible");

		Assert.assertTrue(dashboardPage.isLoginTrendChartDisplayed(), "Login trend chart should be visible");

		// Assert.assertTrue(DashboardPage.is12HFilterDisplayed(),
		// "12H filter button should be visible as default");

		logger.info("DASH_TC_08_verifyLoginTrend12HDefault PASSED");
	}

	// =====================================================================
	// Login Trend — 24H Filter
	// =====================================================================

	@Test(priority = 9, groups = {
			"regression" }, description = "DASH_TC_09 Verify login trend chart updates when 24H filter is applied")
	public void DASH_TC_09_verifyLoginTrend24HFilter() {
		logger.info("Starting DASH_TC_09_verifyLoginTrend24HFilter");

		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.navigateToDashboard();

		// Apply 24H filter
		dashboardPage.apply24HFilter();

		// Verify chart still visible after filter change
		Assert.assertTrue(dashboardPage.isLoginTrendChartDisplayed(),
				"Login trend chart should be visible after applying 24H filter");

		// Assert.assertTrue(DashboardPage.is24HFilterDisplayed(),
		// "Login trend section should still be visible after 24H filter");

		logger.info("DASH_TC_09_verifyLoginTrend24HFilter PASSED");
	}

	// =====================================================================
	// Login Trend — Refresh
	// =====================================================================

	@Test(priority = 10, groups = {
			"regression" }, description = "DASH_TC_10 Verify login trend refresh button updates the chart")
	public void DASH_TC_10_verifyLoginTrendRefresh() {
		logger.info("Starting DASH_TC_10_verifyLoginTrendRefresh");

		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.navigateToDashboard();

		dashboardPage.refreshLoginTrend();

		// Verify chart still visible after refresh
		Assert.assertTrue(dashboardPage.isLoginTrendDisplayed(),
				"Login trend section should still be visible after refresh");

		Assert.assertTrue(dashboardPage.isLoginTrendChartDisplayed(),
				"Login trend chart should still be visible after refresh");

		logger.info("DASH_TC_10_verifyLoginTrendRefresh PASSED");
	}

}
