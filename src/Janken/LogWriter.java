package Janken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {
	public static void main(String[] args) throws IOException {
		try {
			File file = new File("src\\Janken\\HandsFile.txt");
			FileWriter pw = new FileWriter(file);
			
			pw.write("������������");
			pw.write("test1dddd");
			
			//pw.flush();
			
			pw.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}


//���O�A�ǋL�@�\