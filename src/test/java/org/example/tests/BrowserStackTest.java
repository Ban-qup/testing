package org.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import static org.testng.Assert.assertTrue;

public class BrowserStackTest {

    public static final String USERNAME = "bannotqup_WjkmQM";  // Replace with actual username
    public static final String ACCESS_KEY = "6e2NA9xxzypiq78UnGR9";  // Replace with actual access key
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @Test
    public void loginTest() throws MalformedURLException {
        // BrowserStack Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "10");
        browserstackOptions.put("browserName", "Chrome");
        browserstackOptions.put("browserVersion", "latest");
        browserstackOptions.put("sessionName", "BrowserStack Login Test");

        caps.setCapability("bstack:options", browserstackOptions);

        // Start BrowserStack session
        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        driver.get("https://practicetestautomation.com/practice-test-login/");


        // Use WebDriverWait to wait for elements
        driver.get("https://practicetestautomation.com/practice-test-login/");

        // Use WebDriverWait for elements to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate elements using their correct identifiers from the website
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit"))); // The login button

        // Enter test credentials
        userField.sendKeys("student"); // Example username
        passField.sendKeys("Password123"); // Example password
        loginButton.click();

        // Wait for page navigation after login
        wait.until(ExpectedConditions.urlContains("logged-in-successfully"));

        // Validate login success by checking the URL
        assertTrue(driver.getCurrentUrl().contains("logged-in-successfully"), "Login failed!");

        // Close browser
        driver.quit();
    }
}
