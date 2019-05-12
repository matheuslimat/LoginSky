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
	
	public void run() {
		abrirArquivo();
		
	}
	
	public void abrirArquivo() {
		File dir = new File("C:\\");
		File arq = new File(dir, "testar.txt");
		
		try {
			FileReader fileReader = new FileReader(arq);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			List<String> result = new ArrayList<String>();
			
			while(bufferedReader.readLine() != null || !bufferedReader.readLine().isEmpty()){
				String [] linha = null;
				try {
					linha = bufferedReader.readLine().split(":");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
				
				if(linha.length == 2 && linha != null) {
					result.add(bufferedReader.readLine());
				}
			}
			
			List<List<String>> partitions = Lists.partition(result, result.size() / 1000);
			
			
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
