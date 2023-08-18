package f03_input;

import java.io.*;
import java.util.Arrays;

public class ReadEx2 {

	public static void main(String[] args) throws IOException {
		InputStream is = new FileInputStream("C:\\Temp\\file2.txt");
		int available = is.available();
		System.out.println("읽어 들일 수 있는 파일의 크기 : " + available);
		
		byte[] bytes = new byte[3];
//		int readByte = is.read(bytes, 1, bytes.length); // bytes배열의 1번째 index ~ bytes.length(3개)까지 불러오겠다.
		// -> bytes배열은 배열크기를 3개로 지정했기때문에 해당 배열 크기를 벗어나서 IndexOutOfBoundsException이 발상하는거임
		
		int readByte = is.read(bytes, 1, 2);
		System.out.println(Arrays.toString(bytes)); // -> 1번째 index부터 읽기때문에 0번째 index값은 0이 뜨는거임
													// -> byte 타입의 초기값은 0이니깐 ㅇㅇ
		readByte = is.read(bytes, 0, bytes.length);
		System.out.println(Arrays.toString(bytes));
		System.out.println(readByte);
		available = is.available();		// 기존에 9개였지만, 위에서 총 5개를 이미 읽었기때문에
		System.out.println("읽어 들일 수 있는 파일의 크기 : " + available); // 여기서는 남은 4개를 읽을 수 있다고 하는거임 
		
		readByte = is.read(bytes, 0, bytes.length);
		System.out.println(Arrays.toString(bytes));
		System.out.println(readByte);
		
	}

}
