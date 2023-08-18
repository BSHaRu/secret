package f04_writer_reader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class WriterEx {

	public static void main(String[] args) {
		
		try {
			Writer writer = new FileWriter("C:\\Temp\\data.txt",true);
			String str = "홍길동";
			char[] chars = str.toCharArray();
			System.out.println(Arrays.toString(chars));
			
//			writer.write(chars); // 이렇게도 되고 아래 방식도 가능함
			writer.write("이춘향");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
