package f06_stream_reader_writer;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class SystemReaderEx {

	public static void main(String[] args) throws Exception {
		InputStream is = System.in;
		Reader reader = new InputStreamReader(is);
		
		int readData = 0;
		char[] cbuf = new char[100];
		System.out.println("입력 대기중.."); 
						// 사용자가 console에서 입력을 해줄때까지 reader에서는 대기중임
		while((readData = reader.read(cbuf)) != -1) {
			String data = new String(cbuf, 0, readData); // 100개를 생성했기때문에 남는 공간까지 다 대기할꺼임
													// -> 그래서 0번째 index ~ readData(실제크기)까지 출력해라고 정하는거
			if(data.startsWith("exit")) break; // startsWith(문자열) : 해당문자가 나오면 true 
			System.out.println(data);		// -> exit가 나오면 while문 탈출
		} // while 문 종료		
		
		reader.close();
		is.close();
		System.out.println("종료"); 
		
	} // main 종료

}
