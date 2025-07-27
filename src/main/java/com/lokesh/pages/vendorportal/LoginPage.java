package com.lokesh.pages.vendorportal;

import com.lokesh.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class LoginPage extends AbstractPage {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoginPage.class);

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(css = "#userDropdown img")
    private WebElement userProfileImage;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(css = "#logoutModal a")
    private WebElement confirmLogoutButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        log.info("Checking if Login Page is displayed");
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        log.info("Login Page is displayed");
        return loginButton.isDisplayed();
    }

    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public void logout() {
        userProfileImage.click();
        wait.until(ExpectedConditions.visibilityOf(logoutLink));
        logoutLink.click();
        wait.until(ExpectedConditions.visibilityOf(confirmLogoutButton));
        confirmLogoutButton.click();
    }

    public boolean isLoggedOut() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginButton));
            log.info("Successfully logged out.");
            return true;
        } catch (Exception e) {
            log.error("Logout failed: {}", e.getMessage());
            return false;
        }
    }
}
