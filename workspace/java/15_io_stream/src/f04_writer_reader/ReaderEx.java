package f04_writer_reader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public class ReaderEx {

	public static void main(String[] args) {
		try {
			Reader reader = new FileReader("c:\\Temp\\data.txt");
			int readData;
			/* 이렇게도 가능하지만 배열로도 가능함
			while(true) {
				readData = reader.read();
				if(readData == -1) break;
				System.out.print((char)readData);
			} // while문 종료
			*/
			char[] cBuf = new char[100];
			while((readData = reader.read(cBuf)) != -1) {
				System.out.println(readData);			// 실제 읽어들인 문자 개수
				System.out.println(Arrays.toString(cBuf)); // Reader & Writer는 available가 존재하지 않음
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
