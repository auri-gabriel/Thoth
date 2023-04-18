package br.edu.unipampa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest {

	private static WebDriver driver = new ChromeDriver();
	private static Dimension newDimension = new Dimension(1936, 1056);

	@BeforeAll
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver.manage().window().setSize(newDimension);
		driver.get("http://200.132.136.13/Thoth/");
	}

	@Test
	public void LoginCorreto() throws InterruptedException {

			driver.findElement(By.linkText("Sign in")).click();
			driver.findElement(By.id("InputEmail1")).sendKeys("joaovilla.aluno@unipampa.edu.br");
			driver.findElement(By.id("InputPassword")).sendKeys("braz2205388");
			driver.findElement(By.cssSelector(".btn-success")).click();

			String expectedPage = "http://200.132.136.13/Thoth/dashboard";
			String actualPage = driver.getCurrentUrl();
			assertEquals(expectedPage, actualPage);

			Thread.sleep(1000);
			driver.quit();
	}

	@Test
	public void LoginIncorreto() throws InterruptedException {

			driver.findElement(By.linkText("Sign in")).click();
			driver.findElement(By.id("InputEmail1")).sendKeys("joaovilla.aluno@unipampa.edu.br");
			driver.findElement(By.id("InputPassword")).sendKeys("a");
			driver.findElement(By.cssSelector(".btn-success")).click();

			String expectedPage = "http://200.132.136.13/Thoth/dashboard";
			String actualPage = driver.getCurrentUrl();
			assertEquals(expectedPage, actualPage);

			Thread.sleep(1500);
			driver.quit();
	}

	@Test
	public void SignUpEmailCaractereInvalido() throws InterruptedException {

		driver.findElement(By.linkText("Sign up")).click();
		driver.findElement(By.id("name")).sendKeys("joao");
		driver.findElement(By.id("InputEmail1")).sendKeys("!!!!@.####");
		driver.findElement(By.id("InputPassword\"")).sendKeys("123");
		driver.findElement(By.cssSelector(".btn-success")).click();

		String expectedPage = "http://200.132.136.13/Thoth/dashboard";
		String actualPage = driver.getCurrentUrl();
		assertEquals(expectedPage, actualPage);

		Thread.sleep(1500);
		driver.quit();
	}

	@Test
	public void SignUpNomeInvalido() throws InterruptedException {
		driver.findElement(By.linkText("Sign up")).click();
		driver.findElement(By.id("name")).sendKeys("");
		driver.findElement(By.id("InputEmail1")).sendKeys("marcio@gmail.com");
		driver.findElement(By.id("InputPassword\"")).sendKeys("123");
		driver.findElement(By.cssSelector(".btn-success")).click();

		String expectedPage = "http://200.132.136.13/Thoth/dashboard";
		String actualPage = driver.getCurrentUrl();
		assertEquals(expectedPage, actualPage);

		Thread.sleep(1500);
		driver.quit();
	}

	@Test
	public void SignUpSenhaCurta() throws InterruptedException {

		driver.findElement(By.linkText("Sign up")).click();
		driver.findElement(By.id("name")).sendKeys("marcio");
		driver.findElement(By.id("InputEmail1")).sendKeys("marcio@gmail.com");
		driver.findElement(By.id("InputPassword\"")).sendKeys("1");
		driver.findElement(By.cssSelector(".btn-success")).click();

		String expectedPage = "http://200.132.136.13/Thoth/dashboard";
		String actualPage = driver.getCurrentUrl();
		assertEquals(expectedPage, actualPage);

		Thread.sleep(1500);
		driver.quit();
	}

	@Test
	public void SignUpEmailCaracteresInvalidos2() throws InterruptedException {

		driver.findElement(By.linkText("Sign up")).click();
		driver.findElement(By.id("name")).sendKeys("marcio");
		driver.findElement(By.id("InputPassword\"")).sendKeys("1");
		driver.findElement(By.id("InputEmail1")).sendKeys("!!!!@.$$$");
		Thread.sleep(2000);
		driver.findElement(By.id("InputEmail1")).clear();
		driver.findElement(By.id("InputEmail1")).sendKeys("!!!!@.555");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".btn-success")).click();

		String expectedPage = "http://200.132.136.13/Thoth/dashboard";
		String actualPage = driver.getCurrentUrl();
		assertEquals(expectedPage, actualPage);

		Thread.sleep(3000);
		driver.quit();
	}

	public void auxLoginCompleto() {
		driver.findElement(By.linkText("Sign in")).click();
		driver.findElement(By.id("InputEmail1")).sendKeys("joaovilla.aluno@unipampa.edu.br");
		driver.findElement(By.id("InputPassword")).sendKeys("braz2205388");
		driver.findElement(By.cssSelector(".btn-success")).click();
		driver.findElement(By.cssSelector(".form-group:nth-child(1)")).click(); //click on "Open" button
	}

	@Test
	public void ProjectPlanning() throws InterruptedException {

		auxLoginCompleto();

		/*String expectedPage = "http://200.132.136.13/Thoth/dashboard";
		String actualPage = driver.getCurrentUrl();
		assertEquals(expectedPage, actualPage);*/

		Thread.sleep(3000);
		driver.quit();
	}



}
