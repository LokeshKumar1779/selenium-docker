package com.lokesh.tests.flightreservation.model;

public record FlightReservationTestData(
            String firstname,
            String lastname ,
            String email,
            String password,
            String street,
            String city,
            String zip,
            String departure,
            String arrival,
            String noOfPassengers,
            String expectedPrice) {
}
