package Janken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {
	public static void main(String[] args) throws IOException {
		try {
			File file = new File("src\\Janken\\HandsFile.txt");
			FileWriter pw = new FileWriter(file);
			
			pw.write("ああああああ");
			pw.write("test1dddd");
			
			//pw.flush();
			
			pw.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}


//名前、追記機能