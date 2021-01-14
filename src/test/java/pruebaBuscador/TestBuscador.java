package pruebaBuscador;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

public class TestBuscador {
	WebDriver driver;
	public void takeScreenShot(String string, WebDriver driver) {}     
	public String getDate() {
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss-SS");
	Date date = new Date();
	return dateFormat.format(date);
	}
	
	@BeforeTest
	public void beforeTest()throws InterruptedException, AWTException, IOException {
		System.setProperty("webdriver.ie.driver","C:\\Users\\HOME\\eclipse\\IEDriverServer.exe");
			DesiredCapabilities capabilities1 = DesiredCapabilities.internetExplorer();
			capabilities1.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			driver = new InternetExplorerDriver();
			driver.get("http://localhost:3000/shows");
			System.out.println("Ingreso a URL");	
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			
			//captura
			File CapPantalladeBusqueda = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(CapPantalladeBusqueda, new File("./Screenshots/"+ getDate() + " "+" Pantalla de inicio.jpg" ));
			System.out.println("Pantalla de inicio capturada");
	  }
	
	@Test //Busqueda de Batman
	  public void BusquedaBatman() throws InterruptedException, AWTException, IOException {
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div/div/div/form/input")).sendKeys("batman");
		System.out.println("Ingreso de la cadena 'batman' a cuadro de texto");
		File IngresoBatman = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(IngresoBatman, new File("./Screenshots/"+ getDate() + " "+" Ingreso de cadena batman y botón buscar.jpg" ));
		System.out.println("cadena capturada");
		driver.findElement(By.xpath("/html/body/div/div/div/form/button")).click();
		System.out.println("Click al botón [Search]");
				
		WebDriverWait wait = new WebDriverWait(driver,5);
		WebElement segundolinkurl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div/div[2]/a")));
		System.out.println("Resultados mostrados");
		File CapsClickenURL = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(CapsClickenURL, new File("./Screenshots/"+ getDate() + " "+" Click a la segunda url.jpg" ));
		System.out.println("Click a segunda URL");
		segundolinkurl.click();
		Thread.sleep(1000);
		
		File Capsegundolinkurlresultadomain = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Capsegundolinkurlresultadomain, new File("./Screenshots/"+ getDate() + " "+" Resultado de segunda url.jpg" ));
		System.out.println("Resultado de click a la segunda URL");
		
		driver.navigate().back();
		System.out.println("Navegación atás");
		
		WebElement BatmanUnlimited = driver.findElement(By.xpath("/html/body/div/div[10]/div/div/div[1]/span"));
		JavascriptExecutor jsdoc1 = (JavascriptExecutor) driver;
		jsdoc1.executeScript("arguments[0].scrollIntoView(true);", BatmanUnlimited);
		System.out.println("Desplazamiento a 'Batman Unlimited'");
		
		File BatmanUnlimitedCap = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(BatmanUnlimitedCap, new File("./Screenshots/"+ getDate() + " "+" Batman Unlimited.jpg" ));
		System.out.println("captura a 'Batman Unlimited'");
		
		((JavascriptExecutor) driver).executeScript("document.querySelector('body > div > div:nth-child(12) > div > div').style.backgroundColor = '#4a148c';");
		((JavascriptExecutor) driver).executeScript("document.querySelector('body > div > div:nth-child(12) > div > div').style.background = '#4a148c';");
		System.out.println("codigo de color");		
		Thread.sleep(1000);
		
		((JavascriptExecutor) driver).executeScript("document.querySelector('body > div > div:nth-child(12) > div > div').style.backgroundColor = 'red';");
		((JavascriptExecutor) driver).executeScript("document.querySelector('body > div > div:nth-child(12) > div > div').style.background = 'red';");
		System.out.println("nombre de color");	
		Thread.sleep(1000);
		
		/*((JavascriptExecutor) driver).executeScript("document.querySelector('body > div > div:nth-child(12) > div > div').body.style.backgroundColor = 'green';");
		((JavascriptExecutor) driver).executeScript("document.querySelector('body > div > div:nth-child(12) > div > div').body.style.background = 'green';");
		System.out.println("body y nombre de color");
		Thread.sleep(1000);*/
		
		WebElement BackButton = driver.findElement(By.xpath("/html/body/div/a"));
		JavascriptExecutor jsBackButton = (JavascriptExecutor) driver;
		jsBackButton.executeScript("arguments[0].scrollIntoView(true);", BackButton);
		System.out.println("Desplazamiento a botón 'Back'");
		Thread.sleep(500);
		File BackButtonCap = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(BackButtonCap, new File("./Screenshots/"+ getDate() + " "+" Click a botón Back.jpg" ));
		System.out.println("Captura botón [Back]");
		
		BackButton.click();
		System.out.println("Click a botón [Back]");
		Thread.sleep(500);
		File BackButtonResultCap = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(BackButtonResultCap, new File("./Screenshots/"+ getDate() + " "+" Resultado de botón Back.jpg" ));
	}
	

}
