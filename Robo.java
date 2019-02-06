import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Robo {
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {

		Logger logs = Logs.Logger();
		logs.info("[RetrieveDados] Inicializando logger...");
		ArrayList<String> lista = Execucoes.readFile();
		BrowserRadioButton.setBrowser();
		Thread.sleep(4000);
		logs.info("[RetrieveDados] Inicializando browser " + BrowserRadioButton.getBrowser());		
//		String browser = JOptionPane.showInputDialog("Escolha entre Firefox, Chrome ou Edge: ");

		for (String numero : lista) {

			try {

				/**
				 * 
				 * Instanciate the webdriver with the browser selected.
				 * 
				 */
				WebDriver driver = Driver.webDriver(BrowserRadioButton.getBrowser());

				
				/**
				 * setUrlBase: Set the main URL used for this robot. navigateToPesquisaInstacia:
				 * Metod that navigate to the button 'Pesquisa Instancia'. searchNrc: Search an
				 * 'Instancia' by the current NRC code. setFocus: Set the focus on the new tab
				 * IBM WebSphere MQ Workflow. efetuaLogin: Apply the login and password required
				 * for this page.
				 */
				Driver.setUrlBase();
				Driver.navigateToPesquisaInstacia();
				Driver.searchNrc(numero);
				logs.info("Executando NRC "+ numero);
				Execucoes.setFocus();
				Thread.sleep(1000);
				Execucoes.efetuaLogin();

				/**
				 * 
				 * Begin the current cenario procedure. Click in element 'RetrieveDados' and
				 * Wait page load
				 * 
				 */
				WebElement map1 = driver.findElement(By.cssSelector("map"));
				WebElement area1 = map1.findElement(By.xpath("//html/body/map/area[35]"));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", area1);
//			Thread.sleep(2000);										
//			
//			
//			/**
//			 * 
//			 * Click in 'Subprocesso' icon
//			 * 
//			 */			
//			driver.findElement(By.xpath("/html/body/table/tbody/tr/td[3]/a/img")).click();		
//			
//			
//			/**
//			 * 
//			 * Click in 'PSSBAExecutoin' element and wait page load
//			 * 
//			 */
//			WebElement map2 = driver.findElement(By.cssSelector("map"));										
//			WebElement area2 = map2.findElement(By.xpath("/html/body/map/area[8]"));		 															
//			executor.executeScript("arguments[0].click();", area2);											
//			Thread.sleep(2000);		
//			
//			
//			/**
//			 * 
//			 * Click in 'Subprocesso' element and wait page load
//			 * 
//			 */									
//			driver.findElement(By.xpath("/html/body/table/tbody/tr/td[3]/a/img")).click();		 																										
//			
//			
//			/**
//			 * 
//			 * Click in 'RetrieveDadosPSSBA' element and wait page load
//			 * 
//			 */
//			WebElement map3 = driver.findElement(By.cssSelector("map"));										
//			WebElement area3 = map3.findElement(By.xpath("/html/body/map/area[8]"));		 																		
//			executor.executeScript("arguments[0].click();", area3);											
//			Thread.sleep(2000);
//			
//			/**
//			 * 
//			 * Click in 'For�ar a conclus�o da Atividade' icon
//			 * 
//			 */
//			driver.findElement(By.xpath("/html/body/table/tbody/tr/td[4]/a[3]/img")).click();					 
//
//			
//			/**
//			 * 
//			 * Fill in the field PSSBA_RESPONSE.PSSBA_RETURN_CODE with the value '0'
//			 * 
//			 */
//			driver.findElement(By.xpath("/html/body/form/table/tbody/tr[40]/td[3]/input")).sendKeys("0");
//			
//			
//			/**
//			 * 
//			 * Click in 'For�ar a conclus�o da Atividade' button
//			 * 
//			 */
//			driver.findElement(By.xpath("/html/body/form/input[3]")).click();		
//			
//			/**
//			 * Quit and close the webdriver
//			 */
				driver.quit();
				logs.info("[RetrieveDados] Sucesso na execu��o do NRC " +numero);
			} catch (Exception e) {
				logs.info("[RetrieveDados] Erro ao executar NRC "+ numero);
				Driver.getWebDriver().quit();
				continue;
			}

		}
	}
}
