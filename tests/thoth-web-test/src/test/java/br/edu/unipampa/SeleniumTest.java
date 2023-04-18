package br.edu.unipampa;

import org.junit.jupiter.api.AfterAll;
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

	}

	public void auxLoginCompleto() {
		driver.findElement(By.linkText("Sign in")).click();
		driver.findElement(By.id("InputEmail1")).sendKeys("joaovilla.aluno@unipampa.edu.br");
		driver.findElement(By.id("InputPassword")).sendKeys("braz2205388");
		driver.findElement(By.cssSelector(".btn-success")).click();
		driver.findElement(By.linkText("Open")).click();
	}

	@Test
	public void QualityAssessementSelecaoVazia() throws InterruptedException {

		String xpath = ".nav-item:nth-child(7) > .nav-link";
		auxLoginCompleto();
		driver.findElement(By.linkText("Planning")).click();
		driver.findElement(By.xpath("//a[contains(@href, '#tab_quality')]")).click();
		driver.findElement(By.id("min_to_QQ01")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("min_to_QQ01")).sendKeys("Yes");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".swal2-confirm")).click();

		/*String expectedPage = "http://200.132.136.13/Thoth/dashboard";
		String actualPage = driver.getCurrentUrl();
		assertEquals(expectedPage, actualPage);*/

	}

	@Test
	public void FecharMensagemDeSenhaInvalida() throws InterruptedException {

		driver.findElement(By.linkText("Sign in")).click();
		driver.findElement(By.id("InputEmail1")).sendKeys("joaovilla.aluno@unipampa.edu.br");
		driver.findElement(By.id("InputPassword")).sendKeys("aaaaa");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".btn-success")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("InputPassword")).clear();
		driver.findElement(By.id("InputEmail1")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("InputEmail1")).sendKeys("joaovilla.aluno@unipampa.edu.br");
		driver.findElement(By.id("InputPassword")).sendKeys("braz2205388");
		driver.findElement(By.cssSelector(".btn-success")).click();
		Thread.sleep(1800);
		driver.findElement(By.linkText("Open")).click();
		driver.findElement(By.linkText("Planning")).click();
		Thread.sleep(2800);
		driver.findElement(By.cssSelector(".alert span")).click();
		Thread.sleep(2800);
		driver.findElement(By.linkText("Conducting")).click();
		Thread.sleep(2700);
		driver.findElement(By.cssSelector(".alert span")).click();
		driver.findElement(By.linkText("Planning")).click();
		Thread.sleep(2700);
		driver.findElement(By.cssSelector(".alert span")).click();

	}

	@Test
	public void ConductingFilterTest() throws InterruptedException {

		auxLoginCompleto();
		driver.findElement(By.linkText("Conducting")).click();
		driver.findElement(By.cssSelector(".nav-item:nth-child(3) > .nav-link")).click();
		driver.findElement(By.xpath("//table[@id='table_papers_quality']/tbody/tr/td[2]")).click();
		driver.findElement(By.cssSelector(".close:nth-child(4) > span")).click();
		driver.findElement(By.cssSelector("form-control-sm")).click();
		driver.findElement(By.cssSelector("form-control-sm")).sendKeys("aaaa");
	}

	@AfterAll
	public static void fim() throws InterruptedException {
		Thread.sleep(2500);
		driver.quit();
	}

}
