package com.lokesh.tests.vendorportal;

import com.lokesh.pages.vendorportal.DashboardPage;
import com.lokesh.pages.vendorportal.LoginPage;
import com.lokesh.tests.AbstractTest;
import com.lokesh.tests.util.ConfigReader;
import com.lokesh.tests.util.Constants;
import com.lokesh.tests.util.JsonUtil;
import com.lokesh.tests.vendorportal.model.VendorPortalTestData;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class  VendorPortalTest extends AbstractTest {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(VendorPortalTest.class);

//    String baseUrl = "https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html";
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setUp(String testDataPath) {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        log.info("Setting up Vendor Portal tests with test data from: {}", testDataPath);
        testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
        log.info("Test data loaded: {}", testData);
    }

    @Test
    public void loginTest() {

//        loginPage.navigateTo(baseUrl);
        log.info("Navigating to Vendor Portal URL: {}", ConfigReader.getProp(Constants.VENDOR_PORTAL_URL));
        loginPage.navigateTo(ConfigReader.getProp(Constants.VENDOR_PORTAL_URL));
        log.info("Verifying if login page is displayed");
        Assert.assertTrue(loginPage.isAt());
        log.info("Logging in with username: {} and password: {}", testData.username(), testData.password());
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest() {
        log.info("Verifying if dashboard page is displayed");
        Assert.assertTrue(dashboardPage.isAt());
        log.info("Verifying dashboard elements");
        Assert.assertEquals(dashboardPage.getMonthlyEarningElement(),testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarningElement(),testData.yearlyEarning());
        Assert.assertEquals(dashboardPage.getProfitMarginElement(),testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventoryElement(),testData.availableInventory());
        log.info("Searching order history with keyword: {}", testData.searchKeyword());
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        log.info("Verifying search results count");
        Assert.assertEquals(dashboardPage.getSearchResultsCount(),testData.searchResultCount());

    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest() {
        log.info("Logging out from Vendor Portal");
        loginPage.logout();
        log.info("Verifying if user is logged out");
        Assert.assertTrue(loginPage.isLoggedOut());
        log.info("User successfully logged out from Vendor Portal");
    }

}
