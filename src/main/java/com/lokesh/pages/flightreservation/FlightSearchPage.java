package com.lokesh.pages.flightreservation;

import com.lokesh.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {

    @FindBy(id = "passengers")
    private WebElement passengersDropdown;

    @FindBy(id = "search-flights")
    private WebElement searchFlightsButton;

    @FindBy(id = "depart-from")
    private WebElement departFromDropdown;

    @FindBy(id = "arrive-in")
    private WebElement arriveInDropdown;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(passengersDropdown));
        return passengersDropdown.isDisplayed();
    }

    public void selectPassengers(String numberOfPassengers) {
        Select select = new Select(passengersDropdown);
        select.selectByValue(numberOfPassengers);
    }

    public void selectDepartFrom(String location) {
        Select select = new Select(departFromDropdown);
        select.selectByVisibleText(location);
    }

    public void selectArriveIn(String location) {
        Select select = new Select(arriveInDropdown);
        select.selectByVisibleText(location);
    }

    public void searchFlights() {
        wait.until(ExpectedConditions.elementToBeClickable(searchFlightsButton));
        searchFlightsButton.click();
    }
}
