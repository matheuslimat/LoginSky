package br.com.matheuslima.skytest.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import org.openqa.selenium.chrome.ChromeDriver;

import br.com.matheuslima.skytest.jobs.JobProcessamentoTestSky;

public class BuilderTestSky {
	
	public static ChromeDriver driver;
	
	public static void run() throws InterruptedException {
		abrirArquivo();
	}
	
	public static void abrirArquivo() throws InterruptedException {
		File dir = new File("C:\\");
		File arq = new File(dir, "testar.txt");
		
		FileReader fileReader;
		BufferedReader bufferedReader;
		
		try {
			fileReader = new FileReader(arq);
			bufferedReader = new BufferedReader(fileReader);
			
			List<String> result = new ArrayList<String>();
			
			while(bufferedReader.readLine() != null && !bufferedReader.readLine().isEmpty() && bufferedReader.readLine().contains(":") ){
				String [] linha = null;
				try {
					linha = bufferedReader.readLine().split(":");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
				
				if(linha.length == 2 && linha != null) {
					result.add(linha[0] + ":" + linha[1]);
				}
			}
			
			List<List<String>> partitions = Lists.partition(result, 300);
			
			
			if(!partitions.isEmpty()) {
				for (int i = 0; i < partitions.size(); i++) {
					JobProcessamentoTestSky	jobProcessamento = new JobProcessamentoTestSky(partitions.get(i));
					jobProcessamento.run();
				}
			}else {
				throw new IllegalArgumentException();
			}
			fileReader.close();
			bufferedReader.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
