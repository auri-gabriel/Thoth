package br.edu.unipampa;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest {

    @Test
    public void testFirefox() {
        // Set the path to the Firefox driver executable
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");

        // Create a new Firefox driver instance
        WebDriver driver = new FirefoxDriver();

        // Navigate to the Google homepage
        driver.get("https://www.google.com");

        // Verify that the page title is correct
        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle);

        // Close the browser window
        driver.quit();
    }
}
