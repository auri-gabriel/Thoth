package br.edu.unipampa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class Test_Issues_180 {
	private WebDriver driver;
	@BeforeEach
	public void setUp() {
		// Set the path to the Chrome driver executable
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		// Create a new Chrome driver instance
		driver = new ChromeDriver();

		// Navigate to the Thoth homepage
		driver.get("http://200.132.136.13/Thoth/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// maximize browser screen
		driver.manage().window().maximize();

		// login every time before test
		driver.findElement(By.linkText("Sign in")).click();
		driver.findElement(By.id("InputEmail1")).click();
		driver.findElement(By.id("InputEmail1")).sendKeys("mateusbalda89@gmail.com");
		driver.findElement(By.id("InputPassword")).sendKeys("12345");
		driver.findElement(By.id("InputPassword")).sendKeys(Keys.ENTER);
	}

    @Test
    public void testLogin() {
		driver.findElement(By.cssSelector("h4")).click();
		assertEquals(driver.findElement(By.cssSelector("h4")).getText(), "My Projects");
    }

	@Test
	public void testAccess_QualityAssessment() {
		driver.findElement(By.cssSelector(".even .btn-outline-success")).click();
		driver.findElement(By.cssSelector(".text-center > h4")).click();
		assertThat(driver.findElement(By.cssSelector(".text-center > h4")).getText(), is("Systematic mapping study on domain-specific language development tools"));
		driver.findElement(By.linkText("Conducting")).click();
		driver.findElement(By.cssSelector(".card-body > h4")).click();
		assertThat(driver.findElement(By.cssSelector(".card-body > h4")).getText(), is("Conducting"));
		driver.findElement(By.cssSelector(".nav-item:nth-child(3) > .nav-link")).click();
		driver.findElement(By.cssSelector(".card-body")).click();
		assertThat(driver.findElement(By.cssSelector("strong")).getText(), is("Quality Assessment"));
	}

	@Test
	public void test_searchFilter() throws InterruptedException {
		driver.findElement(By.linkText("Thoth")).click();
		driver.findElement(By.cssSelector(".even .btn-outline-success")).click();
		driver.findElement(By.linkText("Conducting")).click();
		driver.findElement(By.cssSelector(".nav-item:nth-child(3) > .nav-link")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 450);");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#table_papers_quality .even:nth-child(2) > td:nth-child(2)")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".close > span")).click();
		driver.findElement(By.cssSelector(".select-item:nth-child(1)")).click();
		js.executeScript("window.scrollBy(0, 750);");
		Thread.sleep(3000);
		assertThat(driver.findElement(By.cssSelector(".select-item:nth-child(1)")).getText(), is("1 row selected"));
	}

	@Test
	public void test_searchFilter_falsePositive() throws InterruptedException {
		driver.findElement(By.linkText("Thoth")).click();
		driver.findElement(By.cssSelector(".even .btn-outline-success")).click();
		driver.findElement(By.linkText("Conducting")).click();
		driver.findElement(By.cssSelector(".nav-item:nth-child(3) > .nav-link")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 450);");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#table_papers_quality .even:nth-child(2) > td:nth-child(2)")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".close > span")).click();
		driver.findElement(By.cssSelector(".select-item:nth-child(1)")).click();
		js.executeScript("window.scrollBy(0, 750);");
		Thread.sleep(3000);
		assertThat(driver.findElement(By.cssSelector(".select-item:nth-child(1)")).getText(), is(not("")));
	}

	@AfterEach
	public void tearDown() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.quit();
	}

}
