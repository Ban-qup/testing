package org.example.stepdefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

public class LoginSteps {
    WebDriver driver;

    @Given("I open the login page")
    public void iOpenTheLoginPage() {
        WebDriverManager.chromedriver().setup();  // Automatically downloads and sets up ChromeDriver
        driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @When("I enter {string} as username")
    public void iEnterUsername(String username) {
        WebElement userField = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        userField.sendKeys(username);
    }

    @And("I enter {string} as password")
    public void iEnterPassword(String password) {
        WebElement passField = driver.findElement(By.id("password"));
        passField.sendKeys(password);
    }

    @And("I click on the login button")
    public void iClickOnLoginButton() {
        WebElement loginButton = driver.findElement(By.id("submit"));
        loginButton.click();
    }

    @Then("I should be redirected to the dashboard")
    public void iShouldBeRedirectedToDashboard() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("logged-in-successfully"));
        assertTrue(driver.getCurrentUrl().contains("logged-in-successfully"), "Login failed!");
        driver.quit();
    }
}
