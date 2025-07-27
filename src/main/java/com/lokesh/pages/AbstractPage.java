package com.lokesh.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {

    // This class can be extended by other page classes to provide common functionality
    // such as navigation, waiting for elements, etc.

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Default wait time of 10 seconds
        PageFactory.initElements(driver,this);
    }

    /**
     * This method should be implemented by each page class to verify if the page is loaded.
     * It typically checks for the presence of a specific element that is unique to that page.
     *
     * @return true if the page is loaded, false otherwise
     */

    public abstract boolean isAt();

    /**
     * Navigate to a specific URL.
     *
     * @param url the URL to navigate to
     */
    public void navigateTo(String url) {
        driver.get(url);
    }
}
