package br.edu.unipampa;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest {

    @Test
    public void testFirefox() {
        // Set the path to the Firefox driver executable
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        // Create a new Firefox driver instance
        WebDriver driver = new ChromeDriver();

        // Navigate to the Google homepage
        driver.get("https://www.google.com");

        // Verify that the page title is correct
        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle);

        // Close the browser window
        driver.quit();
    }

	@Test
	public void LoginCorreto() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Dimension newDimension = new Dimension(1936, 1056);

		try {
			driver.get("http://200.132.136.13/Thoth/");
			driver.manage().window().setSize(newDimension);
			driver.findElement(By.linkText("Sign in")).click();
			driver.findElement(By.id("InputEmail1")).sendKeys("joaovilla.aluno@unipampa.edu.br");
			driver.findElement(By.id("InputPassword")).sendKeys("braz2205388");
			driver.findElement(By.cssSelector(".btn-success")).click();

		} finally {
			driver.wait(1500);
			driver.quit();}
	}

	@Test
	public void LoginIncorreto() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Dimension newDimension = new Dimension(1936, 1056);

			driver.get("http://200.132.136.13/Thoth/");
			driver.manage().window().setSize(newDimension);
			driver.findElement(By.linkText("Sign in")).click();
			driver.findElement(By.id("InputEmail1")).sendKeys("joaovilla.aluno@unipampa.edu.br");
			driver.findElement(By.id("InputPassword")).sendKeys("a");
			driver.findElement(By.cssSelector(".btn-success")).click();

			//driver.wait(1000);
			driver.quit();
	}

	@Test
	public void SignUpEmailCaractereInvalido() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Dimension newDimension = new Dimension(1936, 1056);

		driver.manage().window().setSize(newDimension);
		driver.get("http://200.132.136.13/Thoth/");

		driver.findElement(By.linkText("Sign up")).click();
		driver.findElement(By.id("name")).sendKeys("joao");
		driver.findElement(By.id("InputEmail1")).sendKeys("!!!!@####");
		driver.findElement(By.id("InputPassword\"")).sendKeys("123");
		driver.findElement(By.cssSelector(".btn-success")).click();

		driver.wait(1000);
		driver.quit();
	}

	@Test
	public void SignUpNomeInválido() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Dimension newDimension = new Dimension(1936, 1056);

		driver.manage().window().setSize(newDimension);
		driver.get("http://200.132.136.13/Thoth/");


		driver.findElement(By.linkText("Sign up")).click();
		driver.findElement(By.id("name")).sendKeys("");
		driver.findElement(By.id("InputEmail1")).sendKeys("marcio@gmail.com");
		driver.findElement(By.id("InputPassword\"")).sendKeys("123");
		driver.findElement(By.cssSelector(".btn-success")).click();

		driver.wait(1000);
		driver.quit();
	}

	@Test
	public void SignUpSenhaCurta() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		Dimension newDimension = new Dimension(1936, 1056);
		WebDriver driver = new ChromeDriver();

		driver.get("http://200.132.136.13/Thoth/");
		driver.manage().window().setSize(newDimension);

		driver.findElement(By.linkText("Sign up")).click();
		driver.findElement(By.id("name")).sendKeys("marcio");
		driver.findElement(By.id("InputEmail1")).sendKeys("marcio@gmail.com");
		driver.findElement(By.id("InputPassword\"")).sendKeys("1");
		driver.findElement(By.cssSelector(".btn-success")).click();

		driver.wait(1000);
		driver.quit();
	}

	@Test
	public void SignUpEmailCaracteresInvalidos2() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Dimension newDimension = new Dimension(1936, 1056);

		driver.manage().window().setSize(newDimension);
		driver.get("http://200.132.136.13/Thoth/");

		driver.findElement(By.linkText("Sign up")).click();
		driver.findElement(By.id("name")).sendKeys("marcio");
		driver.findElement(By.id("InputPassword\"")).sendKeys("1");
		driver.findElement(By.id("InputEmail1")).sendKeys("!!!!@.$$$");
		Thread.sleep(2000);
		driver.findElement(By.id("InputEmail1")).clear();
		driver.findElement(By.id("InputEmail1")).sendKeys("!!!!@.555");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".btn-success")).click();

		Thread.sleep(3000);
		driver.quit();
	}







}
