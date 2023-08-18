package f03_input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadEx {

	public static void main(String[] args) {
		InputStream is = null;
		
		try {
			File file = new File("C:\\Temp\\file2.txt");
			is = new FileInputStream(file);
			int readByte = 0;
			
			while(true) {
				// byte 단위로 읽어와서 읽은 값을 int로 반환
				readByte = is.read(); 		// IOException 예외처리
				System.out.println(readByte);
				// EOF(End Of File) == -1	// 파일의 끝에 도달하면 EOF(-1)를 반환하게됨  
				if(readByte == -1) break; 	// -> 파일을 다 읽었으면 해당 while문 종료해라는 의미
				
				System.out.println((char)readByte);
			} // while문 종료
			
		} catch (IOException e) {	// 이게 입출력 상위 예외라서 나머지 입출력 예외처리를 안해도됨
			e.printStackTrace();	// -> FileNotFoundException이걸 안해도 없어도 오류가 안나는거임
		} finally {		// 뭘 하던간에 마지막에 close로 닫아줘야하니깐 finally통해서 close()처리
			try {
				is.close();	// 사용중일 수도 있으니 이것 또한 예외처리 해줘야함
			} catch (IOException e) {
				e.printStackTrace();
			}
		}// try catch문 종료
		
		System.out.println("read 완료");
		
	}//main 종료
}
