package com.lokesh.pages.flightreservation;

import com.lokesh.pages.AbstractPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelectionPage extends AbstractPage {


    @FindBy(name = "departure-flight")
    private List<WebElement> departureFlightSelectionOptions;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalFlightSelectionOptions;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightButton;

    public FlightSelectionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(confirmFlightButton));
        return confirmFlightButton.isDisplayed();
    }

    public void selectFlights(){
        int random = ThreadLocalRandom.current().nextInt(0, departureFlightSelectionOptions.size());
        // Select a random departure flight
        departureFlightSelectionOptions.get(random).click();
        arrivalFlightSelectionOptions.get(random).click();
    }

    public void confirmFlights() {
//        wait.until(ExpectedConditions.elementToBeClickable(confirmFlightButton));
//        confirmFlightButton.click();
//        475, 692
//        WebElement element = driver.findElement(By.id("submit"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", confirmFlightButton);
    }
}
