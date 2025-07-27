package com.lokesh.pages.flightreservation;

import com.lokesh.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(id = "go-to-flights-search")
    private WebElement flightsSearchButton;

    @FindBy(css = "#registration-confirmation-section p b")
    private WebElement firstNameElement;

    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(flightsSearchButton));
        return flightsSearchButton.isDisplayed();
    }

    public void goToFlightsSearch() {
        flightsSearchButton.click();
    }

    public String getFirstName() {
        return firstNameElement.getText();
    }
}
