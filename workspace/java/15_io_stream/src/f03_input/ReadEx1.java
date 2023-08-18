package f03_input;

import java.io.*;

public class ReadEx1 {

	public static void main(String[] args) throws IOException {
		InputStream is = new FileInputStream("C:\\Temp\\file2.txt");
		int readBytes = 0;
		byte[] bytes = new byte[100];
		String data = "";
		
		while(true) {
			readBytes = is.read(bytes);		
			System.out.println("읽어드린 바이트 크기 : " + readBytes);
			if(readBytes == -1)  break;	// readBytes가 다 읽었으면 해당 if문 실행(while문 종료)
			data += new String(bytes);
		}
		System.out.println(data);
		is.close();
	}

}
