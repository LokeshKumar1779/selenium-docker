package com.lokesh.tests.vendorportal.model;

public record VendorPortalTestData(
            String username,
            String password,
            String monthlyEarning,
            String yearlyEarning,
            String profitMargin,
            String availableInventory,
            String searchKeyword,
            int searchResultCount) {
}
