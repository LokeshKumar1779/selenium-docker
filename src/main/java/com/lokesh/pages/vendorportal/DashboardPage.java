package com.lokesh.pages.vendorportal;

import com.lokesh.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarningElement;

    @FindBy(id = "annual-earning")
    private WebElement annualEarningElement;

    @FindBy(id = "profit-margin")
    private WebElement profitMarginElement;

    @FindBy(id = "available-inventory")
    private WebElement availableInventoryElement;

    @FindBy(css = "#dataTable_filter input")
    private WebElement searchInput;

    @FindBy(id = "dataTable_info")
    private WebElement dataTableInfoElement;



    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(monthlyEarningElement));
        return monthlyEarningElement.isDisplayed();
    }

    public String getMonthlyEarningElement() {
        return monthlyEarningElement.getText();
    }

    public String getAnnualEarningElement() {
        return annualEarningElement.getText();
    }

    public String getProfitMarginElement() {
        return profitMarginElement.getText();
    }

    public String getAvailableInventoryElement() {
        return availableInventoryElement.getText();
    }

    public void searchOrderHistoryBy(String query) {
        searchInput.sendKeys(query);
    }

    public int getSearchResultsCount() {
        int count = Integer.parseInt(dataTableInfoElement.getText().split(" ")[5]);
        log.info("Search results count: {}", count);
        return count;
    }





}
