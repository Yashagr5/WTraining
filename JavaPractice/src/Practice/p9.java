package Practice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class p9 {

	public void readFile() throws IOException {
		// TODO Auto-generated method stub
		FileReader file = null;
		BufferedReader buff = null;
		
		try {
			file = new FileReader("F:/Projects/Wipro_batch-1/JavaPractice/src/Practice/p8.txt");
			buff = new BufferedReader(file);
			
			for(int counter=0; counter<3; counter++) {
				System.out.println("FileInput: "+buff.readLine());
			}
		}
		catch(FileNotFoundException e) {
			System.err.println("caught: "+e.getMessage());
		}
		finally {
			System.out.println("Go on");
		}
	}

}
