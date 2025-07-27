package com.lokesh.tests;

import com.lokesh.tests.listener.Listener;
import com.lokesh.tests.util.ConfigReader;
import com.lokesh.tests.util.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners({Listener.class})
public abstract class AbstractTest {

    public WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    @BeforeSuite
    public void setUpConfig() {
        log.info("Initializing configuration");
        ConfigReader.initialize();
        log.info("Configuration initialized");
    }

    @BeforeTest
//    @Parameters({"browser"})
    public void setDriver(ITestContext context) throws MalformedURLException {
//        log.debug(String.valueOf(Boolean.parseBoolean(System.getProperty("selenium.grid.enabled"))));
/*        if (Boolean.parseBoolean(System.getProperty("selenium.grid.enabled"))) {
//            driver = getRemoteDriver(browser);
            driver = getRemoteDriver();
        } else {
            driver = getLocalDriver();
        }*/

        log.info("Grid enabled: {}", ConfigReader.getProp(Constants.GRID_ENABLED));
        driver = Boolean.parseBoolean(ConfigReader.getProp(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
        log.info("WebDriver initialized: {}", driver.getClass().getSimpleName());
        context.setAttribute(Constants.DRIVER, driver);
        log.info("Setting up WebDriver for tests");
        driver.manage().window().maximize();
        log.info("Browser initialized and window maximized");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        log.info("Implicit wait set to 10 seconds");
    }


    private WebDriver getLocalDriver() {
       /* ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("autofill.profile_enabled", false);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);*/
        log.info("Initializing local Chrome driver");
        return new ChromeDriver(getChromeOptions());
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
//        prefs.put("autofill.profile_enabled", false);
//        URL remoteUrl = new URL("http://localhost:4444/wd/hub");
        String urlFormat = ConfigReader.getProp(Constants.GRID_URL_FORMAT);
        String hubHost = ConfigReader.getProp(Constants.GRID_HUB_HOST);
        URL remoteUrl = new URL(String.format(urlFormat, hubHost));
        log.info("Connecting to Selenium Grid at: {}", remoteUrl);
        if (Constants.CHROME.equalsIgnoreCase(ConfigReader.getProp(Constants.BROWSER))) {
            log.info("Initializing remote Chrome driver");
            driver = new RemoteWebDriver(remoteUrl, getChromeOptions());
        } else {
            log.info("Initializing remote Firefox driver");
            driver = new RemoteWebDriver(remoteUrl, new FirefoxOptions());
        }

        return driver;
    }

    private ChromeOptions getChromeOptions() {
        log.info("Setting up Chrome options");
        ChromeOptions options = new ChromeOptions();
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put(Constants.AUTOFILL_PROFILE_ENABLED, ConfigReader.getProp(Constants.AUTOFILL_PROFILE_ENABLED));
//        prefs.put(Constants.CREDENTIALS_ENABLE_SERVICE, ConfigReader.getProp(Constants.CREDENTIALS_ENABLE_SERVICE));
//        prefs.put(Constants.PROFILE_PASSWORD_MANAGER_ENABLED, ConfigReader.getProp(Constants.PROFILE_PASSWORD_MANAGER_ENABLED));
//        prefs.put(Constants.PROFILE_PASSWORD_MANAGER_LEAK_DETECTION, ConfigReader.getProp(Constants.PROFILE_PASSWORD_MANAGER_LEAK_DETECTION));
//        options.setExperimentalOption("prefs", prefs);
//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-infobars");
        log.info("Adding Chrome options");
        options.addArguments("guest");
        log.info("Chrome options set: {}", options.asMap());
        return options;
    }

    @AfterTest
    public void quitDriver() {
        if (driver != null) {
            log.info("Quiting the browser");
            driver.quit();
        }
    }

/*    @AfterMethod
    public void sleepAfterEachMethod(){
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
    }*/

    /**
     * String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s' />";
     */
}
