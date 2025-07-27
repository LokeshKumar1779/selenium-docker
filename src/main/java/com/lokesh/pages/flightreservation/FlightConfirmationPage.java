package com.lokesh.pages.flightreservation;

import com.lokesh.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    private WebElement flightConfirmation;

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement totalPrice;

    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }



    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(flightConfirmation));
        return flightConfirmation.isDisplayed();

    }

    public String getPrice() {
        String price = totalPrice.getText();
        log.info("Total price: {}", price);
        log.info("Flight confirmation number: {}", flightConfirmation.getText());
        return price;
    }
}
