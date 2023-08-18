package f06_stream_reader_writer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class StreamReaderEx {

	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("C:\\Temp\\fos.txt");
			Reader reader = new InputStreamReader(fis);
			
			char[] cbuf = new char[100];
			int readData = reader.read(cbuf);
			System.out.println("읽어들인 문자열 개수 : " +readData);
			System.out.println(new String(cbuf));
			reader.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // try catch 종료
		
	} // main 종료

}
