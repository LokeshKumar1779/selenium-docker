package com.lokesh.tests.flightreservation;

import com.lokesh.pages.flightreservation.*;
import com.lokesh.tests.AbstractTest;
import com.lokesh.tests.flightreservation.model.FlightReservationTestData;
import com.lokesh.tests.util.ConfigReader;
import com.lokesh.tests.util.Constants;
import com.lokesh.tests.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class FlightReservationTest extends AbstractTest {

    public static final Logger log = LoggerFactory.getLogger(FlightReservationTest.class);
//    String url = "https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html";
    private FlightReservationTestData data;

    @BeforeTest
    @Parameters({"testDataPath"})
    public void setUp(String testDataPath) {
          data = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void testUserRegistration() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        log.info("Navigating to the registration page");
//        registrationPage.navigateTo(url);
        registrationPage.navigateTo(ConfigReader.getProp(Constants.FLIGHT_RESERVATION_URL));
        log.debug("Checking if we are on the registration page");
        Assert.assertTrue(registrationPage.isAt());
        log.info("Entering user details");
        registrationPage.enterUserDetails(data.firstname(), data.lastname());
        registrationPage.enterUserCredentials(data.email(), data.password());
        registrationPage.enterAddressDetails(data.street(), data.city(), data.zip());
        log.info("Submitting the registration form");
        registrationPage.register();
    }

    @Test(dependsOnMethods = "testUserRegistration")
    public void testRegisterConfirmation() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        log.info("Verifying user firstname on registration confirmation page");
        Assert.assertEquals(registrationConfirmationPage.getFirstName(),data.firstname());
        log.info("Registration successful, navigating to flight searchOrderHistoryBy page");
        registrationConfirmationPage.goToFlightsSearch();
    }

    @Test(dependsOnMethods = "testRegisterConfirmation")
    public void testFlightSearch() {
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        log.info("Selecting number of passengers");
        flightSearchPage.selectPassengers(data.noOfPassengers());
        log.info("Selecting departure location");
        flightSearchPage.selectDepartFrom(data.departure());
        log.info("Selecting arrival location");
        flightSearchPage.selectArriveIn(data.arrival()); // Adjusted to match the expected dropdown values
        log.info("Clicking on searchOrderHistoryBy flights button");
        flightSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "testFlightSearch")
    public void testFlightSelection() {
        FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.selectFlights();
        flightSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "testFlightSelection")
    public void testFlightReservationConfirmation() {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        log.info("Flight reservation confirmed");
        String price = flightConfirmationPage.getPrice();
        Assert.assertEquals(price, data.expectedPrice(), "Price does not match expected value");
    }

}
